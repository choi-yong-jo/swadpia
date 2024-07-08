package kr.co.swadpia.entity.personnel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.Date;

@Entity
@Getter
@Setter
@Comment("자격면허")
public class EmployeeCertification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_certification_id")
	@Comment("자격면허 ID")
	private Long id;

	@Column(name = "certification_category")
	@Comment("종류")
	private String category;

	@Comment("등급")
	private String categoryGrade;

	@Comment("취득일")
	private Date registrationDt;

	@Comment("발행기관")
	private String issuer;

	@ManyToOne
	@JoinColumn(name = "employee_information_id")
	private EmployeeInformation employeeInformation;
}
