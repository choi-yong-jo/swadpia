package kr.co.swadpia.team.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;

@Entity
@Data
@Table(schema = "admin")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamSeq;

    @Column(length = 10, nullable = false)
    @Comment("부서ID")
    private String teamId;

    @Column(length = 20, nullable = false)
    @Comment("부서명")
    private String teamNm;

    @Column(columnDefinition = "char", length = 1, nullable = false)
    @Comment("사용여부")
    private String useYn = "Y";

    @Comment("부서 설명")
    private String description;

}
