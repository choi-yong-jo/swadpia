package kr.co.swadpia.entity.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.List;

@Entity
@Getter
@Setter
@Comment("공통그룹코드 테이블")
public class CommonGroupCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("공통그룹코드 PK")
    private Long groupId;

    @Comment("그룹코드")
    private String groupCode;

    @Comment("그룹코드명")
    private String groupName;

    @Comment("그룹코드설명")
    private String description;

    @OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE)
    private List<CommonCode> commonCodes;

    @Comment("사용여부")
    private Boolean useYn = true;
}
