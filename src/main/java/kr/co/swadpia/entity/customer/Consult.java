package kr.co.swadpia.entity.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.co.swadpia.entity.AuditingAt;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Setter
@Getter
public class Consult extends AuditingAt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("상담문의번호")
    private Long consultId;

    @Comment("문의유형")
    private String consultType;

    @Comment("문의제목")
    private String consultSubject;

    @Comment("문의내용")
    private String consultContent;

    @Comment("회원아이디")
    private String customerId;

    @Comment("회원명")
    private String customerName;

    @Comment("회원명(상호)")
    private String companyName;

    @Comment("이메일")
    private String customerEmail;

    @Comment("이메일 수신여부")
    private String emailReceiveYn;

    @Comment("상태")
    private String status;

    @Comment("답변")
    private String reply;

    @Comment("답변자")
    private String replyAuthor;

    @Comment("답변일자")
    private String replyDt;

    @Comment("답변여부")
    private String replyYn;

    @Comment("주문번호")
    private String orderNo;

    @Comment("담당자명")
    private String managerName;

    @Comment("담당자연락처")
    private String managerTelNo;

    @Comment("담당자핸드폰")
    private String managerMoblie;

}