package kr.co.swadpia.entity.personnel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Comment("신상정보")
public class EmployeeInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_information_id")
	@Comment("인사정보 ID")
	private Long id;

	@Comment("주소")
	private String address;

	@Comment("주민등록번호")
	private String residentRegistration;

	@Comment("혈액형")
	private String bloodType;

	@Comment("시력")
	private String vision;

	@Comment("색맹")
	private String colorBlindnessYn;

	@Comment("신체사항기타")
	private String physicalInformationEtc;

	@Comment("군필여부")
	private String militaryServiceStatus;

	@Comment("병역시작기간")
	private Date militaryServiceStartDt;

	@Comment("병역종료기간")
	private Date militaryServiceEndDt;

	@Comment("미필사유")
	private String exemptionReason;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id",foreignKey = @ForeignKey(name="fk_employee__employee_id"))
	private Employee employee;

	@OneToMany(mappedBy = "employeeInformation", fetch = FetchType.LAZY)
	private List<EmployeeCareer> careers;

	@OneToMany(mappedBy = "employeeInformation", fetch = FetchType.LAZY)
	private List<EmployeeCertification> certifications;

	@OneToMany(mappedBy = "employeeInformation", fetch = FetchType.LAZY)
	private List<EmployeeLanguageSkills> languageSkills;

	@OneToMany(mappedBy = "employeeInformation", fetch = FetchType.LAZY)
	private List<EmployeeDisability> disabilities;

	@OneToMany(mappedBy = "employeeInformation", fetch = FetchType.LAZY)
	private List<EmployeeEducation> educations;

	@OneToMany(mappedBy = "employeeInformation", fetch = FetchType.LAZY)
	private List<EmployeeFamily> families;
}
