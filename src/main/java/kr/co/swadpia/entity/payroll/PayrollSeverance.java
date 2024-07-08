package kr.co.swadpia.entity.payroll;

import jakarta.persistence.*;
import kr.co.swadpia.entity.AuditingAtByCU;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Setter
@Getter
public class PayrollSeverance extends AuditingAtByCU {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("퇴직금 일련번호")
    @Column(name = "payroll_severance_id")
    private Long id;

    @Comment("지급일자")
    private String paymentDt;

    @Comment("사번")
    private String employeeNo;

    @Comment("당월 퇴직금")
    private Long currentMonthSeverance;

    @Comment("퇴직금 유형")
    private String severanceType;

    @Comment("누계 퇴직금")
    private Long cumulativeSeverance;

    @Comment("총 퇴직금")
    private Long totalSeverance;

    @OneToOne
    @JoinColumn(name = "payroll_id",foreignKey = @ForeignKey(name="fk_payroll__payroll_id"))
    private Payroll payroll;
}