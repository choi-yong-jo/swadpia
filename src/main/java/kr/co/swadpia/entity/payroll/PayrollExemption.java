package kr.co.swadpia.entity.payroll;

import jakarta.persistence.*;
import kr.co.swadpia.entity.AuditingAtByCU;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Setter
@Getter
public class PayrollExemption extends AuditingAtByCU {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("감면대상 일련번호")
    @Column(name = "payroll_exemption_id")
    private Long id;

    @Comment("사번")
    private String employeeNo;

    @Comment("감면대상")
    private String exemption;

    @Comment("주민등록번호")
    private String residentRegistration;

    @Comment("생년월일")
    private String dateOfBirth;

    @Comment("나이")
    private String age;

    @Comment("장애인 여부")
    private String disabledYn;

    @Comment("경력단절여성 여부")
    private String careerInterruptedWomanYn;

    @Comment("입사일")
    private String hireDate;

    @Comment("입대일")
    private String dateOfEnlistment;

    @Comment("전역일")
    private String dateOfDischarge;

    @Comment("감면종료일")
    private String exemptionEndDate;

    @Comment("감면율")
    private String exemptionRate;

    @Comment("감면한도")
    private String exemptionLimit;

    @ManyToOne
    @JoinTable(
        name = "payroll_exemption_payroll",
        joinColumns = @JoinColumn(name = "payroll_exemption_id", foreignKey = @ForeignKey(name="fk_payroll_exemption__payroll_exemption_id")),
        inverseJoinColumns = @JoinColumn(name = "payroll_id", foreignKey = @ForeignKey(name="fk_payroll__payroll_id"))
    )
    private Payroll payrollMember;

    @ManyToOne
    @JoinTable(
        name = "payroll_exemption_payroll_contract",
        joinColumns = @JoinColumn(name = "payroll_exemption_id", foreignKey = @ForeignKey(name="fk_payroll_exemption__payroll_exemption_id")),
        inverseJoinColumns = @JoinColumn(name = "payroll_contract_id", foreignKey = @ForeignKey(name="fk_payroll_contract__payroll_contract_id"))
    )
    private PayrollContract payrollContractMember;
}