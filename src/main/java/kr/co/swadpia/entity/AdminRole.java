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
public class AdminRole extends AuditingAt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminRoleId;
    private Long adminId;
    private Long roleId;


    @Builder
    public AdminRole(Long adminId, Long roleId) {
        this.adminId = adminId;
        this.roleId = roleId;
    }
}
