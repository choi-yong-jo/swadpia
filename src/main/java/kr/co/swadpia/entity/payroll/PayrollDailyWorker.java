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
public class PayrollDailyWorker extends AuditingAtByCU {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payroll_daily_worker_id")
	@Comment("일용직 급여대장 일련번호")
	private Long id;

	@Comment("지급일자")
	private String paymentDt;

	@Comment("성명")
	private String name;

	@Comment("주민등록번호")
	private String residentRegistration;

	@Comment("전화번호")
	private String telNo;

	@Comment("은행명")
	private String bankName;

	@Comment("은행코드")
	private String bankCode;

	@Comment("계좌번호")
	private String bankAccount;

	@Comment("주소")
	private String address;

	@Comment("근무시간")
	private String workHour;

	@Comment("일당")
	private Integer dailyWage;

	@Comment("과세일수")
	private Integer taxableDays;

	@Comment("과세급여")
	private Integer taxableIncome;

	@Comment("총지급액")
	private Integer totalPayment;

	@Comment("소득세")
	private Integer incomeTax;

	@Comment("지방소득세")
	private Integer localIncomeTax;

	@Comment("국민연금")
	private Long nationalPension;

	@Comment("건강보험")
	private Long healthInsurance;

	@Comment("고용보험")
	private Long employmentInsurance;

	@Comment("장기요양보험")
	private Long longTermCareInsurance;

	@Comment("기타공제1")
	private Long deduction1;

	@Comment("기타공제2")
	private Long deduction2;

	@Comment("공제총액")
	private Long totalDeduction;

	@Comment("실수령액")
	private Long netAmount;

	@OneToMany(mappedBy = "payrollDailyWorker", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PayrollDailyWorkerHours> payrollDailyWorkerHours;

}