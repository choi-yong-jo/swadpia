package kr.co.swadpia.common.entity;

import jakarta.persistence.*;
import kr.co.swadpia.common.constant.EmailType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EmailTemplate extends AuditingAt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emailTemplateId;
    private String title;
    @Enumerated(EnumType.STRING)
    private EmailType type;
    private String body;


}
