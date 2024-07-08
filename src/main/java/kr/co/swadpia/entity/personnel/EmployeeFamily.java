package kr.co.swadpia.entity.personnel;

import jakarta.persistence.*;
import kr.co.swadpia.common.constant.GenderType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
@Comment("가족사항")
public class EmployeeFamily {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_family_id")
	@Comment("가족사항 ID")
	private Long id;

	@Comment("가족관계")
	private String relationCode;

	@Comment("가족성명")
	private String name;

	@Enumerated(EnumType.STRING)
	@Comment("성별")
	private GenderType gender;

	@Comment("생년월일")
	private String birthday;

	@Comment("동거유무")
	private String cohabitationYn;

	@ManyToOne
	@JoinColumn(name = "employee_information_id")
	private EmployeeInformation employeeInformation;
}
