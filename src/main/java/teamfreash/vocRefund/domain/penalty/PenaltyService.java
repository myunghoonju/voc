package teamfreash.vocRefund.domain.penalty;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import teamfreash.vocRefund.web.penalty.dto.PenaltyRequestDto;

import static teamfreash.vocRefund.constants.Constants.STATUS_N;
import static teamfreash.vocRefund.constants.Constants.STATUS_Y;
import static teamfreash.vocRefund.constants.ProcessStatus.ACCEPTED;
import static teamfreash.vocRefund.constants.ProcessStatus.PENDING;

@RequiredArgsConstructor
@Service
public class PenaltyService {

    private final PenaltyRepository penaltyRepository;

    @Transactional
    public long save(PenaltyRequestDto penalty) {
        Penalty penaltyInfo = Penalty.builder()
                .accident(penalty.getAccident())
                .status(PENDING.getStatus())
                .partner_emp_id(penalty.getPartner_emp_id())
                .claim_yn(penalty.getClaim_yn())
                .confirm_yn(penalty.getConfirmed_yn())
                .charge_amt(penalty.getCharge_amt())
                .voc_id(penalty.getVoc_id())
                .build();

        Penalty saved = penaltyRepository.save(penaltyInfo);
        if (ObjectUtils.isEmpty(saved)) {
            return 0L;
        }

        return saved.getId();
    }

    @Transactional
    public long confirm(long vocId) {
        Penalty penalty = penaltyRepository.findByVocId(vocId);
        if (ObjectUtils.isEmpty(penalty)) {
            throw new IllegalStateException("Cannot find VOC with id = " + vocId);
        }

        penalty.updateConfirm(STATUS_Y.getStatus());

        return penalty.getId();
    }

    @Transactional(readOnly = true)
    public boolean isRefundable(long vocId) {
        Penalty penalty = penaltyRepository.findByVocId(vocId);
        String confirm_yn = penalty.getConfirm_yn();
        String claim_yn = penalty.getClaim_yn();

        return isQualified(confirm_yn, claim_yn);
    }

    @Transactional
    public void complete(long vocId) {
        Penalty penalty = penaltyRepository.findByVocId(vocId);
        penalty.updateStatus(ACCEPTED.getStatus());
    }

    private boolean isQualified(String confirm_yn, String claim_yn) {
        return STATUS_Y.getStatus().equals(confirm_yn) && STATUS_N.getStatus().equals(claim_yn);
    }
}
