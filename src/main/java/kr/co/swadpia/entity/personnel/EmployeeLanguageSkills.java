package kr.co.swadpia.entity.personnel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
@Comment("어학능력")
public class EmployeeLanguageSkills {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_language_id")
	@Comment("어학능력 ID")
	private Long id;

	@Comment("종류")
	private String category;

	@Comment("회화")
	private String conversation;

	@Comment("작문")
	private String writing;

	@Comment("독해")
	private String reading;

	@ManyToOne
	@JoinColumn(name = "employee_information_id")
	private EmployeeInformation employeeInformation;
}

