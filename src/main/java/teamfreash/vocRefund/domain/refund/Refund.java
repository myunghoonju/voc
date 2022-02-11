package teamfreash.vocRefund.domain.refund;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamfreash.vocRefund.domain.penalty.Penalty;
import teamfreash.vocRefund.domain.time.BaseTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@NoArgsConstructor
@Entity
public class Refund extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refund_id")
    private Long id;
    @Column(columnDefinition = "varchar(100) not null comment 'identifier of employee'")
    private String partner_emp_id;
    @Column(columnDefinition = "varchar(100) not null comment 'accident description'")
    private String description;
    @Column(columnDefinition = "bigint not null comment 'amount charged for penalty'")
    private long charge_amt;
    @OneToOne
    @JoinColumn(name = "penalty_id")
    private Penalty penalty;

    @Builder
    public Refund(String partner_emp_id, String description, long charge_amt, Penalty penalty) {
        this.partner_emp_id = partner_emp_id;
        this.description = description;
        this.charge_amt = charge_amt;
        this.penalty = penalty;
    }
}
