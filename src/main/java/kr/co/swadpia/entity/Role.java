package kr.co.swadpia.entity;

import jakarta.persistence.*;
import kr.co.swadpia.entity.personnel.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    private String value;
    private String name;
    private String description;

    /*@OneToOne(mappedBy = "role")
    private MenuGroupAuthority menuGroupAuthority;*/

    @ManyToMany(mappedBy = "roles")
    private List<Employee> employees = new ArrayList<>();

    /**
     * Role const
     * @param value 값
     * @param name 이름
     * @param description 설명
     */
    public Role(String value, String name, String description) {
        this.value = value;
        this.name = name;
        this.description = description;
    }
}
