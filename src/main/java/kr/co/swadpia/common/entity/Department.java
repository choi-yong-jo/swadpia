package kr.co.swadpia.common.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Department extends AuditingAtByCU {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "department_id")
	private Long id;

	@Comment("부서명")
	private String departmentName;

	@Comment("파트장 존재 여부")
	private String managerExistsYn = "Y";

	@ManyToOne
	@JoinColumn(name = "parent_id",foreignKey = @ForeignKey(name="fk_department__department_id"))
	private Department parent;

	@OneToMany(mappedBy = "parent")
	private List<Department> subDepartments = new ArrayList<>();

}
