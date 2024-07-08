package kr.co.swadpia.entity.personnel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.Date;

@Entity
@Getter
@Setter
@Comment("장애여부")
public class EmployeeDisability {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_disability_id")
	@Comment("장애여부 ID")
	private Long id;

	@Comment("장애등급")
	private String disabilityGrade;

	@Comment("장애등록번호")
	private String disabilityNo;

	@Comment("등록일자")
	private Date registrationDt;

	@Comment("장애인등록여부")
	private String disabilityRegistrationYn;

	@ManyToOne
	@JoinColumn(name = "employee_information_id")
	private EmployeeInformation employeeInformation;
}
