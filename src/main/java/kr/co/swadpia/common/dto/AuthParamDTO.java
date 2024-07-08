package kr.co.swadpia.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AuthParamDTO {
    @Schema(example = "aaaa@aaaa.com")
    private String email;
    @Schema(example = "aaa")
    private String password;
}
