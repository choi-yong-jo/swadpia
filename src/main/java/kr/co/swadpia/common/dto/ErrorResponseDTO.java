package kr.co.swadpia.common.dto;

import kr.co.swadpia.constant.ErrorCode;
import lombok.Getter;

import java.util.Map;

@Getter
public class ErrorResponseDTO {
    private final ErrorCode code;
    private final String message;
    private Map<String,String> errors;


    public ErrorResponseDTO(ErrorCode code, String message) {
        this.code = code;
        this.message = message;

    }
    public ErrorResponseDTO(ErrorCode code, Map<String,String> errorData ,String message) {
        this.errors = errorData;
        this.code = code;
        this.message = message;

    }

}
