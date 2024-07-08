package kr.co.swadpia.entity.redis;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash(value = "partner_verification", timeToLive = 60 * 10)
public class PartnerVerification {

    @Id
    private String corpNum;
    private Boolean email;
    private String emailCode;
    private Boolean mobile;
    private String mobileCode;
    private Boolean bankbook;
    private Boolean paymentManagerMobile;
    private String paymentManagerMobileCode;
    private Boolean uploadBusinessLicense;
    private Boolean uploadBankbookCopy;
}
