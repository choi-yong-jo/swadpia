package kr.co.swadpia.sample.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","username","age"})
public class MemberSample {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="team_id")
    private TeamSample team;


    public MemberSample(String username) {
        this(username, 0);
    }

    public MemberSample(String username, int age) {
        this(username, age, null);
    }

    public MemberSample(String username, int age, TeamSample team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    private void changeTeam(TeamSample team) {
        this.team = team;
        team.getMembers().add(this);
    }

}
