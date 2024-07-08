package kr.co.swadpia.entity.personnel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.Date;

@Entity
@Getter
@Setter
@Comment("경력사항")
public class EmployeeCareer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_career_id")
	@Comment("경력사항 ID")
	private Long id;

	@Comment("회사명")
	private String companyName;

	@Column(name = "career_start_dt")
	@Comment("경력시작일")
	private Date startDt;

	@Column(name = "career_end_dt")
	@Comment("경력종료일")
	private Date endDt;

	@Comment("최종직급")
	private String lastPositionCode;

	@Comment("담당업무")
	private String duty;

	@ManyToOne
	@JoinColumn(name = "employee_information_id")
	private EmployeeInformation employeeInformation;
}
