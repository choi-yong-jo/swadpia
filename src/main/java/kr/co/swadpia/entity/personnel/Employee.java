package kr.co.swadpia.entity.personnel;

import jakarta.persistence.*;
import kr.co.swadpia.entity.AuditingAtByCU;
import kr.co.swadpia.member.entity.Role;
import kr.co.swadpia.entity.system.DepartmentEmployee;
import kr.co.swadpia.entity.system.MenuGroupAuthority;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.Date;
import java.util.List;

/**
 * 유저 테이블
 */
@Entity
@Setter
@Getter
public class Employee extends AuditingAtByCU {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    @Comment("사원ID")
    private Long id;

    @Comment("사번")
    private String employeeNo;

    @Comment("생년월일")
    private String birthDay;
    
    @Comment("이메일")
    private String email;

    @Comment("비밀번호")
    private String password;

    @Comment("성명(한글)")
    private String nameKr;

    @Comment("성명(영어)")
    private String nameEn;

    @Comment("성명(한자)")
    private String nameZh;

    @Comment("휴대폰번호")
    private String mobile;

    @Comment("직급코드")
    private String positionCode;

    @Comment("인증코드")
    private String verificationCode;

    @Comment("리플레시토큰")
    private String refreshToken;

    @Comment("사업장 (ex-충무로)")
    private String workplaceCode;

    @Comment("근무형태 (정규직or비정규직)")
    private String employeeCode;

    @Comment("입사일")
    private Date joinDt;

    @Comment("퇴사일")
    private Date resignationDt;

    @Comment("퇴사여부")
    private String resignationYn = "N";

    @Comment("퇴사전 사번")
    private String previousEmployeeNo;

    @ManyToMany
    @JoinTable(
            name = "relation_user_roles",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @ManyToMany(mappedBy = "employees")
    private List<MenuGroupAuthority> menuGroupAuthorities;

    @OneToMany(mappedBy = "employee")
    private List<DepartmentEmployee> departmentEmployees;
}
