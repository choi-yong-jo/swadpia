package kr.co.swadpia.entity.payroll;

import jakarta.persistence.*;
import kr.co.swadpia.entity.AuditingAtByCU;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.Set;

@Entity
@Setter
@Getter
public class PayrollContract extends AuditingAtByCU {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payroll_contract_id")
	@Comment("도급 급여대장 일련번호")
	private Long id;

	@Comment("지급일자")
	private String paymentDt;

	@Comment("구분")
	private String category;

	@Comment("사번")
	private String employeeNo;

	@Comment("부서명")
	private String departmentName;

	@Comment("성명")
	private String name;

	@Comment("생년월일")
	private String dateOfBirth;

	@Comment("입사일")
	private String hireDate;

	@Comment("퇴사일")
	private String resignationDate;

	@Comment("근무일수")
	private Integer workDay;

	@Comment("시간공제")
	private Integer timeDeduction;

	@Comment("연장근로")
	private Integer overtimeWork;

	@Comment("휴일근로")
	private Integer holidayWork;

	@Comment("심야근로")
	private Integer nightWork;

	@Comment("연차")
	private Integer annualLeave;

	@Comment("기본급")
	private Long baseSalary;

	@Comment("직무수당")
	private Long dutyAllowance;

	@Comment("상여금")
	private Long bonus;

	@Comment("중식대")
	private Long mealAllowance;

	@Comment("시간외수당")
	private Long overtimeAllowance;

	@Comment("휴일근로수당")
	private Long holidayWorkAllowance;

	@Comment("심야수당")
	private Long nightWorkAllowance;

	@Comment("연차수당")
	private Long annualLeaveAllowance;

	@Comment("기타수당")
	private Long otherAllowance;

	@Comment("직접비계")
	private Long directCost;

	@Comment("기타공제")
	private Long otherDeductions;

	@Comment("공제후지급액")
	private Long netPayment;

	@Comment("국민연금 요율")
	private Long nationalPensionRate;

	@Comment("국민연금 금액")
	private Long nationalPensionAmount;

	@Comment("건강보험 요율")
	private Long healthInsuranceRate;

	@Comment("건강보험 금액")
	private Long healthInsuranceAmount;

	@Comment("요양보험 요율")
	private Long longTermCareInsuranceRate;

	@Comment("요양보험 금액")
	private Long longTermCareInsuranceAmount;

	@Comment("고용보험 요율")
	private Long employmentInsuranceRate;

	@Comment("고용보험 금액")
	private Long employmentInsuranceAmount;

	@Comment("산재보험 요율")
	private Long industrialAccidentInsuranceRate;

	@Comment("산재보험 금액")
	private Long industrialAccidentInsuranceAmount;

	@Comment("사업소세 요율")
	private Long localIncomeTaxRate;

	@Comment("사업소세 금액")
	private Long localIncomeTaxAmount;

	@Comment("임채 요율")
	private Long laborLoanRate;

	@Comment("임채 금액")
	private Long laborLoanAmount;

	@Comment("장애인분담금")
	private Long disabledContribution;

	@Comment("석면 요율")
	private Long asbestosRate;

	@Comment("석면 금액")
	private Long asbestosAmount;

	@Comment("간접비계")
	private Long indirectCost;

	@Comment("복리후생비")
	private Long welfareExpense;

	@Comment("행정관리비 요율")
	private Long administrativeManagementCostRate;

	@Comment("행정관리비 금액")
	private Long administrativeManagementCostAmount;

	@Comment("이익준비금 요율")
	private Long profitReserveRate;

	@Comment("이익준비금 금액")
	private Long profitReserveAmount;

	@Comment("기타비용")
	private Long otherExpenses;

	@Comment("관리비계")
	private Long totalManagementCost;

	@Comment("공급가액")
	private Long supplyValue;

	@Comment("부가세")
	private Long vat;

	@Comment("총합계")
	private Long totalSum;

	@OneToMany(mappedBy = "payrollContractMember")
	private Set<PayrollExemption> reductionTargets;

}