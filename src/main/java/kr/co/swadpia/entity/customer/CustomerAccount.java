package kr.co.swadpia.entity.customer;

import jakarta.persistence.*;
import kr.co.swadpia.entity.AuditingAt;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Setter
@Getter
public class CustomerAccount extends AuditingAt {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("계좌관리일련번호")
    private Long customerAccountId;

    @OneToOne
    @JoinColumn(name = "customer_no")
    @Comment("회원번호")
    private Customer customer;

    @Comment("회원아이디")
    private String customerId;

    @Comment("회원명")
    private String customerName;

    @Comment("회원명(상호)")
    private String companyName;

    @Comment("은행명")
    private String bankName;

    @Comment("은행코드")
    private String bankCd;

    @Comment("예금주")
    private String accountHolder;

    @Comment("계좌번호")
    private String bankAccount;

    @Comment("CEO명")
    private String ceoName;

    @Comment("서류접수경로")
    private String documentReceivePath;

    @Comment("서류접수시간")
    private String documentReceiveDt;

    @Comment("상태")
    private String state;

    @Comment("거부사유")
    private String reason;

    @Comment("처리자")
    private String processManager;


}