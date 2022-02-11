package teamfreash.vocRefund.domain.refund;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import teamfreash.vocRefund.domain.penalty.Penalty;
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
        Penalty penalty = penaltyService.getPenalty(refund.getPenalty_id());

        Refund refundInfo = Refund.builder()
                .partner_emp_id(refund.getPartner_emp_id())
                .description(refund.getDescription())
                .charge_amt(refund.getCharge_amt())
                .penalty(penalty)
                .build();

        Refund saved = refundRepository.save(refundInfo);
        if (ObjectUtils.isEmpty(saved)) {
            return 0L;
        }

        Penalty savedPenalty = saved.getPenalty();
        complete(savedPenalty);

        return saved.getId();
    }

    private void complete(Penalty savedPenalty) {
        penaltyService.complete(savedPenalty);
    }
}
