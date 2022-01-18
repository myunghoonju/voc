package teamfreash.vocRefund.domain.refund;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamfreash.vocRefund.domain.time.BaseTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Refund extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "varchar(100) not null comment 'identifier of employee'")
    private String partner_emp_id;
    @Column(columnDefinition = "varchar(100) not null comment 'accident description'")
    private String description;
    @Column(columnDefinition = "bigint not null comment 'amount charged for penalty'")
    private long charge_amt;
    @Column(columnDefinition = "bigint not null comment 'voc identifier'")
    private long voc_id;
    @Column(columnDefinition = "bigint not null comment 'penalty identifier'")
    private long penalty_id;

    @Builder
    public Refund(String partner_emp_id, String description,
                  long charge_amt, long voc_id,
                  long penalty_id) {
        this.partner_emp_id = partner_emp_id;
        this.description = description;
        this.charge_amt = charge_amt;
        this.voc_id = voc_id;
        this.penalty_id = penalty_id;
    }
}
