package teamfreash.vocRefund.domain.voc;

import teamfreash.vocRefund.web.voc.dto.VocResponseDto;

import java.util.List;

public interface VocRepositoryCustom {

    List<VocResponseDto> getList();
}
