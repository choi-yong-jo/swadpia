package kr.co.swadpia.entity.payroll;

import jakarta.persistence.*;
import kr.co.swadpia.entity.AuditingAtByCU;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.List;

@Entity
@Setter
@Getter
public class Payroll extends AuditingAtByCU {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("급여대장 일련번호")
    @Column(name = "payroll_id")
    private Long id;

    @Comment("지급일자")
    private String paymentDt;

    @Comment("사번")
    private String employeeNo;

    @Comment("소속")
    private String department;

    @Comment("성명")
    private String name;

    @Comment("연 급여")
    private Long annualSalary;

    @Comment("기본급")
    private Long baseSalary;

    @Comment("시급")
    private Long hourlyWage;

    @Comment("시간외수당")
    private Long overtimeAllowance;

    @Comment("연장근로수당")
    private Long extendedWorkAllowance;

    @Comment("휴일수당")
    private Long holidayAllowance;

    @Comment("야근교대수당")
    private Long nightShiftAllowance;

    @Comment("상여금")
    private Long bonus;

    @Comment("결근외")
    private Long absenceOther;

    @Comment("미사용연차")
    private Integer unusedAnnualLeave;

    @Comment("연차수당")
    private Long annualLeaveAllowance;

    @Comment("심야수당")
    private Long nightWorkAllowance;

    @Comment("교통비")
    private Long transportationExpense;

    @Comment("식대")
    private Long mealAllowance;

    @Comment("전월착오수당")
    private Long previousMonthErrorAllowance;

    @Comment("연구비")
    private Long researchFund;

    @Comment("차량보조금")
    private Long vehicleSubsidy;

    @Comment("기타수당")
    private Long otherAllowance;

    @Comment("자격증수당")
    private Long certificateAllowance;

    @Comment("지급총액")
    private Long totalPayment;

    @Comment("소득세")
    private Long incomeTax;

    @Comment("주민세")
    private Long residentTax;

    @Comment("국민연금 요율")
    private Long nationalPensionRate;

    @Comment("국민연금 금액")
    private Long nationalPensionAmount;

    @Comment("건강보험 요율")
    private Long healthInsuranceRate;

    @Comment("건강보험 금액")
    private Long healthInsuranceAmount;

    @Comment("장기요양보험 요율")
    private Long longTermCareInsuranceRate;

    @Comment("장기요양보험 금액")
    private Long longTermCareInsuranceAmount;

    @Comment("고용보험 요율")
    private Long employmentInsuranceRate;

    @Comment("고용보험 금액")
    private Long employmentInsuranceAmount;

    @Comment("연말정산소득세")
    private Long yearEndSettlementIncomeTax;

    @Comment("연말정산주민세")
    private Long yearEndSettlementResidentTax;

    @Comment("건강보험정산")
    private Long healthInsuranceAdjustment;

    @Comment("장기요양정산")
    private Long longTermCareAdjustment;

    @Comment("학자금공제")
    private Long tuitionDeduction;

    @Comment("정산금")
    private Long settlementAmount;

    @Comment("기타공제")
    private Long otherDeduction;

    @Comment("공제총액")
    private Long totalDeduction;

    @Comment("차인지급액(월급여)")
    private Long netSalary;

    @Comment("회사부담금총액")
    private Long totalCompanyCharge;

    @Comment("국민연금 요율")
    private Long companyNationalPensionRate;

    @Comment("국민연금 금액")
    private Long companyNationalPensionAmount;

    @Comment("건강보험 요율")
    private Long companyHealthInsuranceRate;

    @Comment("건강보험 금액")
    private Long companyHealthInsuranceAmount;

    @Comment("장기요양보험 요율")
    private Long companyLongTermCareInsuranceRate;

    @Comment("장기요양보험 금액")
    private Long companyLongTermCareInsuranceAmount;

    @Comment("고용보험 요율")
    private Long companyEmploymentInsuranceRate;

    @Comment("고용보험 금액")
    private Long companyEmploymentInsuranceAmount;

    @Comment("산재보험 요율")
    private Long companyIndustrialAccidentInsuranceRate;

    @Comment("산재보험 금액")
    private Long companyIndustrialAccidentInsuranceAmount;

    @OneToMany(mappedBy = "payrollMember")
    private List<PayrollExemption> reductionTargets;

    @OneToOne(mappedBy = "payroll")
    private PayrollSeverance payrollSeverance;
}