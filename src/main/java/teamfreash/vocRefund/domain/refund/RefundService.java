package teamfreash.vocRefund.domain.refund;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import teamfreash.vocRefund.domain.penalty.PenaltyService;
import teamfreash.vocRefund.web.refund.dto.RefundRequestDto;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RefundService {

    private final RefundRepository refundRepository;
    private final PenaltyService penaltyService;

    @Transactional(readOnly = true)
    public List<Refund> getList() {
        return refundRepository.findAll();
    }

    @Transactional
    public long save(RefundRequestDto refund) {
        Refund refundInfo = Refund.builder()
                .partner_emp_id(refund.getPartner_emp_id())
                .description(refund.getDescription())
                .charge_amt(refund.getCharge_amt())
                .voc_id(refund.getVoc_id())
                .penalty_id(refund.getPenalty_id())
                .build();

        Refund saved = refundRepository.save(refundInfo);
        if (ObjectUtils.isEmpty(saved)) {
            return 0L;
        }

        long vocId = saved.getVoc_id();
        complete(vocId);

        return vocId;
    }

    private void complete(long vocId) {
        penaltyService.complete(vocId);
    }
}
