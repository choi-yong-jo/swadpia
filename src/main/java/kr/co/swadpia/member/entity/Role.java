package kr.co.swadpia.member.entity;

import jakarta.persistence.*;
import kr.co.swadpia.entity.AuditingAt;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(schema = "admin")
public class Role extends AuditingAt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleSeq;

    private String roleId;

    @Column(name = "roleNm")
    private String name;

    private String description;

    private String useYn;

    /**
     * Role const
     * @param roleId 값
     * @param name 이름
     * @param description 설명
     */
    public Role(String roleId, String name, String description) {
        this.roleId = roleId;
        this.name = name;
        this.description = description;
    }

    public Role() {

    }
}
