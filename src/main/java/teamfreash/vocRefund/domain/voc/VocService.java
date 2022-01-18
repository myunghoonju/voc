package teamfreash.vocRefund.domain.voc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamfreash.vocRefund.web.voc.dto.VocRequestDto;
import teamfreash.vocRefund.web.voc.dto.VocResponseDto;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VocService {

    private final VocRepository vocRepository;

    @Transactional(readOnly = true)
    public List<VocResponseDto> getList() {
        return vocRepository.getList();
    }

    @Transactional
    public Long save(VocRequestDto voc) {
        Voc vocInfo = Voc.builder()
                .client_id(voc.getClient_id())
                .partner_id(voc.getPartner_id())
                .description(voc.getDescription())
                .product_id(voc.getProduct_id())
                .partner_emp_id(voc.getPartner_emp_id())
                .receiver(voc.getReceiver())
                .build();

        Voc saved = vocRepository.save(vocInfo);

        return saved.getId();
    }
}
