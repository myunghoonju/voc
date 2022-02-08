package teamfreash.vocRefund.domain.penalty;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import teamfreash.vocRefund.domain.time.BaseTime;
import teamfreash.vocRefund.domain.voc.Voc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@NoArgsConstructor
@DynamicUpdate
@Entity
public class Penalty extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "penalty_id")
    private Long id;
    @Column(columnDefinition = "varchar(100) not null comment 'accident description'")
    private String accident;
    @Column(columnDefinition = "varchar(50) not null comment 'pending, accepted, denied'")
    private String status;
    @Column(columnDefinition = "varchar(100) not null comment 'identifier of employee'")
    private String partner_emp_id;
    @Column(columnDefinition = "varchar(10) not null comment 'whether a courier claimed'")
    private String claim_yn;
    @Column(columnDefinition = "varchar(10) not null comment 'whether a courier accepted'")
    private String confirm_yn;
    @Column(columnDefinition = "bigint not null comment 'amount charged for penalty'")
    private long charge_amt;
    @OneToOne
    @JoinColumn(name = "voc_id")
    private Voc voc;

    @Builder
    public Penalty(String accident, String status, String partner_emp_id, String claim_yn, String confirm_yn, long charge_amt, Voc voc) {
        this.accident = accident;
        this.status = status;
        this.partner_emp_id = partner_emp_id;
        this.claim_yn = claim_yn;
        this.confirm_yn = confirm_yn;
        this.charge_amt = charge_amt;
        this.voc = voc;
    }

    public void updateConfirm(String confirm_yn) {
        this.confirm_yn = confirm_yn;
    }

    public void updateStatus(String status) {
        this.status = status;
    }
}
