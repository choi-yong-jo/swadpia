package kr.co.swadpia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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
