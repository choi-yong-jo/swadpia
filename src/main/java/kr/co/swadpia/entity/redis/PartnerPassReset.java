package kr.co.swadpia.entity.redis;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash(value = "partner_password_reset", timeToLive = 60 * 10)
public class PartnerPassReset {

    @Id
    private String resetPasswordKey;
    private String email;
    private Long partnerId;
}
