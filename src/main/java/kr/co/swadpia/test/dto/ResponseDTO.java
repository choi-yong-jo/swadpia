package kr.co.swadpia.test.dto;

import lombok.Data;

@Data
public class ResponseDTO {
    private String resultCode;
    private String msg;
    private Object res;
}
