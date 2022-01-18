package teamfreash.vocRefund.domain.penalty;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import teamfreash.vocRefund.domain.voc.VocRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static teamfreash.vocRefund.constants.Accidents.DAMAGED;
import static teamfreash.vocRefund.constants.Constants.STATUS_N;
import static teamfreash.vocRefund.constants.ProcessStatus.PENDING;

@SpringBootTest
class PenaltyTest {

    @Autowired
    PenaltyRepository penaltyRepository;
    @Autowired
    PenaltyService penaltyService;
    @Autowired
    VocRepository vocRepository;

    @Test
    @DisplayName("penalty 등록 테스트")
    void savePenalty() {
        String accident = DAMAGED.getAccident();
        String status = PENDING.getStatus();
        String partner_emp_id = "emp111";
        String claim_yn = STATUS_N.getStatus();
        String confirmed_yn = STATUS_N.getStatus();
        long charge_amt = 100_000L;
        Penalty savedPenalty = penaltyRepository.save(
                Penalty.builder()
                        .accident(accident)
                        .status(status)
                        .partner_emp_id(partner_emp_id)
                        .claim_yn(claim_yn)
                        .confirm_yn(confirmed_yn)
                        .charge_amt(charge_amt)
                        .voc_id(1)
                        .build());

        List<Penalty> penaltyList = penaltyRepository.findAll();
        Penalty foundPenalty = penaltyList.get(0);

        assertThat(savedPenalty.getAccident()).isEqualTo(foundPenalty.getAccident());
    }

    @Test
    @DisplayName("기사님으로부터 패널티 인정확인요청 시 해당 penalty 정보갱신(n -> y) 테스트")
    void updateConfirm() {
        penaltyService.confirm(1L);
        Penalty penalty = penaltyRepository.findByVocId(1L);

        assertThat("y").isEqualTo(penalty.getConfirm_yn());
    }
}