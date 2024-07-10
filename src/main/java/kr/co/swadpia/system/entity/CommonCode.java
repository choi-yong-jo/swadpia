package kr.co.swadpia.system.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.List;



@Entity
@Data
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

    @Comment("코드")
    private String code;

    @Comment("코드명")
    private String codeName;

    @Comment("코드설명")
    private String description;

    @Comment("코드 정렬순서")
    private Integer sortNo;

    @Comment("사용여부")
    private Boolean useYn = true;
}
