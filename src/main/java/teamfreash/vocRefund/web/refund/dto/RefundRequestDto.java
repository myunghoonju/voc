package teamfreash.vocRefund.web.refund.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RefundRequestDto {

    private String partner_emp_id;
    private String description;
    private long penalty_id;
    private long charge_amt;
    private long voc_id;

}
