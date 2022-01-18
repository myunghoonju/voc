package teamfreash.vocRefund.web.penalty.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyRequestDto {

    private String accident;
    private String partner_emp_id;
    private String claim_yn;
    private String confirmed_yn;
    private long charge_amt;
    private long voc_id;
}
