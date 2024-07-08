package kr.co.swadpia.entity.personnel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
@Comment("학력사항")
public class EmployeeEducation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_education_id")
	@Comment("학력 ID")
	private Long id;

	@Comment("학교명")
	private String schoolName;

	@Comment("학과명")
	private String majorName;

	@Comment("입학년월")
	private String admissionDate;

	@Comment("졸업년월")
	private String graduationDate;

	@Comment("졸업여부")
	private String graduationYn;

	@ManyToOne
	@JoinColumn(name = "employee_information_id")
	private EmployeeInformation employeeInformation;
}
