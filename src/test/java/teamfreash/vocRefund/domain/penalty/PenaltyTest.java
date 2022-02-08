package teamfreash.vocRefund.domain.penalty;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import teamfreash.vocRefund.domain.voc.Voc;
import teamfreash.vocRefund.domain.voc.VocService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static teamfreash.vocRefund.constants.Constants.STATUS_N;
import static teamfreash.vocRefund.constants.ProcessStatus.PENDING;

@SpringBootTest
class PenaltyTest {

    @Autowired
    PenaltyRepository penaltyRepository;
    @Autowired
    PenaltyService penaltyService;
    @Autowired
    VocService vocService;

    @Test
    @DisplayName("penalty 등록 테스트")
    void savePenalty() {
        Voc voc = vocService.getVoc(2L);
        String status = PENDING.getStatus();
        String claim_yn = STATUS_N.getStatus();
        String confirmed_yn = STATUS_N.getStatus();
        long charge_amt = 200_000L;

        Penalty savedPenalty = penaltyRepository.save(
                Penalty.builder()
                        .accident(voc.getDescription())
                        .status(status)
                        .partner_emp_id(voc.getPartner_emp_id())
                        .claim_yn(claim_yn)
                        .confirm_yn(confirmed_yn)
                        .charge_amt(charge_amt)
                        .voc(voc)
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