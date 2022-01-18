package teamfreash.vocRefund.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Accidents {

    DEFECTIVE("defective_product"),
    MISSING_PW("mission_pw"),
    WRONG_ADDR("wrong_address"),
    DAMAGED("damaged_parcel"),
    LATE_DLVR("late_delivery");

    private String accident;
}
