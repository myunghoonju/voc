package teamfreash.vocRefund.web.voc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VocResponseDto {

    private String receiver;
    private String description;
    private String accident;
    private String confirm_yn;
    private String claim_yn;
    private long charge_amt;
    private String status;
}
