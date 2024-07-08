package kr.co.swadpia.config.exception;

import kr.co.swadpia.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException{
    ErrorCode errorCode;
    String message;
}