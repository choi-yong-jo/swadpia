package kr.co.swadpia.config.exception;

import kr.co.swadpia.common.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException{
    ErrorCode errorCode;
    String message;
}