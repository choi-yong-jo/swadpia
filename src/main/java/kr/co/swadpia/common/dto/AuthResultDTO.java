package kr.co.swadpia.common.dto;

import kr.co.swadpia.member.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class AuthResultDTO {
    private String email;
    private String token;
    private String name;
    private String profileImage;
    private List<Role> roleList;
}
