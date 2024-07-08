package kr.co.swadpia.entity.customer;

import jakarta.persistence.*;
import kr.co.swadpia.entity.AuditingAt;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Setter
@Getter
public class CustomerAttachment extends AuditingAt {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("첨부파일 일련번호")
    private Long attachmentId;

    @ManyToOne
    @JoinColumn(name = "customer_account_id")
    private CustomerAccount customerAccount;

    @Comment("회원번호")
    private Long customerNo;

    @Comment("첨부파일명")
    private String fileName;

    @Comment("첨부파일명")
    private String fileOriginalName;

    @Comment("첨부파일경로")
    private String filePath;

    @Comment("첨부파일크기")
    private String fileSize;

}