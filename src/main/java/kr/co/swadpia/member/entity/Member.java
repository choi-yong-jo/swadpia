package kr.co.swadpia.member.entity;

import jakarta.persistence.*;
import kr.co.swadpia.entity.AuditingAt;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(schema = "admin")
public class Member extends AuditingAt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberSeq;

    private String memberId;

    private String teamId;

    @Column(name = "member_nm")
    private String name;

    @Column(name = "member_pw")
    private String password;

    private String email;

    private String mobile;

    private String refreshToken;

    private String useYn;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "memberRole", joinColumns = @JoinColumn(name = "memberId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<Role> roles;

}