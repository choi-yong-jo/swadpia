package kr.co.swadpia.common.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Setter
@Getter
@Table(name = "\"external_api_history\"")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExternalAPIHistory extends AuditingAtByCU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("서비스 영역 [partner/memeber/admin]")
    private String serviceArea;

    @Comment("api명")
    private String apiName;

    @Column(length = 4000)
    @Comment("API 호출 파라메터 정보")
    private String parameters;

    @Column(length = 4000)
    @Comment("API 응답 데이터")
    private String response;

    @Comment("API 호출 소요시간/ MS")
    private Long apiLeadTimeMs;

    @Comment("서비스 메소드 시그니처")
    private String signature;

    public ExternalAPIHistory(String serviceArea,String apiName, String parameters, String response, long apiLeadTimeMs,String signature) {
        this.serviceArea = serviceArea;
        this.apiName = apiName;
        this.parameters = parameters;
        this.response = response;
        this.apiLeadTimeMs = apiLeadTimeMs;
        this.signature = signature;
    }
}