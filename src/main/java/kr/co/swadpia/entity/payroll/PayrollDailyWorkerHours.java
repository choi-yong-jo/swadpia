package kr.co.swadpia.entity.payroll;

import jakarta.persistence.*;
import kr.co.swadpia.entity.AuditingAtByCU;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Setter
@Getter
public class PayrollDailyWorkerHours extends AuditingAtByCU {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payroll_daily_worker_hours_id")
	@Comment("일용직 일별근무시간 일련번호")
	private Long id;

	@Comment("지급일자")
	private String paymentDt;

	@Comment("성명")
	private String name;

	@Comment("근무날짜")
	private String workDate;

	@Comment("근무시간")
	private String hoursWorked;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payroll_daily_worker_id",foreignKey = @ForeignKey(name="fk_payroll_daily_worker__payroll_daily_worker_id"))
	private PayrollDailyWorker payrollDailyWorker;
}