package kr.co.swadpia.member.dto.request;

import lombok.Data;

@Data
public class LoginDTO {
    private String memberId;
    private String password;
}
