package teamfreash.vocRefund.web.voc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VocRequestDto {

    private String client_id;
    private String partner_id;
    private String description;
    private String product_id;
    private String partner_emp_id;
    private String receiver;
}
