package kr.co.swadpia.common.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonExceptionHistory extends AuditingAtByCU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("서비스 영역 [partner/memeber/admin]")
    private String serviceArea;
    
    @Comment("서비스 메소드 시그니처")
    private String signature;

    @Column(length = 4000)
    @Comment("메소드 파라메터 정보")
    private String parameters;

    @Comment("Exception 유형")
    private String exceptionType;

    @Column(length = 4000)
    @Comment("Exception 메세지")
    private String exceptionMessage;

    @Column(length = 2000)
    @Comment("Exception stacktrace")
    private String exceptionStacktrace;

    public CommonExceptionHistory(String serviceArea,String signature, String parameters, String exceptionType, String exceptionMessage,String exceptionStacktrace) {
        this.serviceArea = serviceArea;
        this.signature = signature;
        this.parameters = parameters;
        this.exceptionType = exceptionType;
        this.exceptionMessage = exceptionMessage;
        this.exceptionStacktrace = exceptionStacktrace;
    }
}