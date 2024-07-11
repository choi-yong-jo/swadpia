package kr.co.swadpia.member.entity;

import jakarta.persistence.*;
import kr.co.swadpia.common.entity.AuditingAt;
import lombok.Data;

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

}
