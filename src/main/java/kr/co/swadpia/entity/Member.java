package kr.co.swadpia.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Data
@Table(schema = "admin")
public class Member extends AuditingAt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String email;
    private String password;
    private String name;
    private String mobile;
    private String verificationCode;
    private String refreshToken;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "memberRole", joinColumns = @JoinColumn(name = "memberId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<Role> roles;

}