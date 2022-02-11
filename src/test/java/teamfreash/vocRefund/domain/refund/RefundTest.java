package teamfreash.vocRefund.domain.refund;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import teamfreash.vocRefund.domain.penalty.Penalty;
import teamfreash.vocRefund.domain.penalty.PenaltyService;
import teamfreash.vocRefund.domain.voc.VocRepository;

import static teamfreash.vocRefund.constants.Accidents.DAMAGED;

@SpringBootTest
class RefundTest {

    @Autowired
    RefundRepository refundRepository;
    @Autowired
    PenaltyService penaltyService;
    @Autowired
    VocRepository vocRepository;

    @Test
    @DisplayName("배상등록 테스트")
    void saveRefund() {
        String partner_emp_id = "emp111";
        String description = DAMAGED.getAccident();
        long charge_amt = 100_000L;
        long voc_id = 1L;
        long penalty_id = 1L;

        Penalty penalty = penaltyService.getPenalty(penalty_id);

        refundRepository.save(Refund.builder()
                        .partner_emp_id(partner_emp_id)
                        .description(description)
                        .charge_amt(charge_amt)
                        .penalty(penalty)
                        .build());

        Refund savedRefund = refundRepository.findById(1L).get();

        Assertions.assertThat(voc_id).isEqualTo(savedRefund.getPenalty().getVoc().getId());
    }
}