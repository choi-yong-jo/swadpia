package kr.co.swadpia.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.co.swadpia.entity.AuditingAt;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
public class MemberRole extends AuditingAt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberRoleId;
    private Long memberId;
    private Long roleId;


    @Builder
    public MemberRole(Long memberId, Long roleId) {
        this.memberId = memberId;
        this.roleId = roleId;
    }
}
