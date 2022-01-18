package teamfreash.vocRefund.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ProcessStatus {

    PENDING("pending"),
    ACCEPTED("accepted"),
    DENIED("denied");

    private String status;
}
