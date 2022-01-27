package teamfreash.vocRefund.domain.voc;

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
public class Voc extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "varchar(100) not null comment 'identifier of client'")
    private String client_id;
    @Column(columnDefinition = "varchar(100) not null comment 'identifier of partner'")
    private String partner_id;
    @Column(columnDefinition = "varchar(100) not null comment 'accident description'")
    private String description;
    @Column(columnDefinition = "varchar(100) not null comment 'product identifier'")
    private String product_id;
    @Column(columnDefinition = "varchar(100) not null comment 'identifier of employee'")
    private String partner_emp_id;
    @Column(columnDefinition = "varchar(100) not null comment 'assign who is responsible'")
    private String receiver;

    @Builder
    public Voc(String client_id, String partner_id,
               String description, String product_id,
               String partner_emp_id, String receiver) {
        this.client_id = client_id;
        this.partner_id = partner_id;
        this.description = description;
        this.product_id = product_id;
        this.partner_emp_id = partner_emp_id;
        this.receiver = receiver;
    }
}
