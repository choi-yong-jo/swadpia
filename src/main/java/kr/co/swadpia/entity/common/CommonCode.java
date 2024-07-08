package kr.co.swadpia.entity.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.List;



@Entity
@Getter
@Setter
@NoArgsConstructor
@Comment("공통코드 테이블")
public class CommonCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_id")
    @Comment("공통코드 PK")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    @Comment("그룹코드 FK")
    private CommonGroupCode group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_cd_id")
    @Comment("상위공통코드")
    private CommonCode parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<CommonCode> children;

    @Comment("코드")
    private String code;

    @Comment("코드명")
    private String codeName;

    @Comment("코드설명")
    private String description;

    @Comment("코드 뎁스")
    private Integer depth;

    @Comment("사용여부")
    private Boolean useYn = true;
}
