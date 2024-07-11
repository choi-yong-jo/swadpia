package kr.co.swadpia.member.entity;

import jakarta.persistence.*;
import kr.co.swadpia.common.entity.AuditingAt;
import lombok.*;

@Entity
@Data
@Table(schema = "admin")
public class MemberRole extends AuditingAt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberRoleSeq;
    private Long memberSeq;
    private Long roleSeq;

}
