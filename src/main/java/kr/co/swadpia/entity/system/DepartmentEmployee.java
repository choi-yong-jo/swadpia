package kr.co.swadpia.entity.system;

import jakarta.persistence.*;
import kr.co.swadpia.entity.AuditingAtByCU;
import kr.co.swadpia.entity.personnel.Employee;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
public class DepartmentEmployee extends AuditingAtByCU {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "department_employee_id")
	private Long id;

	@Comment("파트장 여부")
	private String mangerYn = "N";

	@Comment("직책코드")
	private String departmentRoleCode; //겸직때문에.. 여기테이블에 빼놓음.

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id",foreignKey = @ForeignKey(name="fk_employee__employee_id"))
	private Employee employee;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id",foreignKey = @ForeignKey(name="fk_department__department_id"))
	private Department department;
}
