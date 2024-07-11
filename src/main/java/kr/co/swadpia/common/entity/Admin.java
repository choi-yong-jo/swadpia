package kr.co.swadpia.common.entity;

import jakarta.persistence.*;
import kr.co.swadpia.member.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Admin extends AuditingAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;
    private String email;
    private String password;
    private String name;
    private String refreshToken;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "adminRole", joinColumns = @JoinColumn(name = "adminId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<Role> roles;
}
