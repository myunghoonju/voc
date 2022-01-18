package teamfreash.vocRefund.domain.voc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import teamfreash.vocRefund.web.voc.dto.VocResponseDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static teamfreash.vocRefund.constants.Accidents.DAMAGED;
import static teamfreash.vocRefund.constants.Company.CLIENT_SARUGA;
import static teamfreash.vocRefund.constants.Company.PARTNER_HANSOL;

@Slf4j
@SpringBootTest
class VocTest {

    @Autowired
    VocRepository vocRepository;

    @Test
    @DisplayName("voc 등록 테스트")
    void saveVoc() {
        String client_id = CLIENT_SARUGA.getId();
        String partner_id = PARTNER_HANSOL.getId();
        String accident = DAMAGED.getAccident();
        String product_id = "product111";
        String partner_emp_id = "emp111";

        Voc savedVoc = vocRepository.save(Voc.builder()
                .client_id(client_id)
                .partner_id(partner_id)
                .description(accident)
                .product_id(product_id)
                .partner_emp_id(partner_emp_id)
                .receiver(partner_id)
                .build());

        List<Voc> vocList = vocRepository.findAll();
        Voc foundVoc = vocList.get(0);

        assertThat(savedVoc.getClient_id()).isEqualTo(foundVoc.getClient_id());
        assertThat(savedVoc.getProduct_id()).isEqualTo(foundVoc.getProduct_id());
    }

    @Test
    @DisplayName("voc, penalty 정보를 함께 조회해서 필요한 정보를 가져오는 테스트")
    void searchList() {
        List<VocResponseDto> searchList = vocRepository.getList();
        for (VocResponseDto voc : searchList) {
            log.info(voc.getReceiver());
            log.info(String.valueOf(voc.getCharge_amt()));
        }
    }
}