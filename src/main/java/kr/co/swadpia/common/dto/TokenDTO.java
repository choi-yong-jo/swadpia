package kr.co.swadpia.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDTO {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpireIn;
    private Long refreshTokenExpireIn;
    private String authority;
    private String info;
}
