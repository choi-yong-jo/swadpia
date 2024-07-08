package kr.co.swadpia.config.advice;

import kr.co.swadpia.config.exception.CustomException;
import kr.co.swadpia.common.dto.ErrorResponseDTO;
import kr.co.swadpia.common.constant.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler({CustomException.class})
    protected ResponseEntity<ErrorResponseDTO> handleCustomException(CustomException ex, WebRequest request) {
        log.error(ex.getMessage());
        ErrorResponseDTO result = new ErrorResponseDTO(ex.getErrorCode(), ex.getMessage());
        return new ResponseEntity<>(
                result, new HttpHeaders(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ErrorResponseDTO> handleAnyException(Exception ex, WebRequest request) {
        log.error(ex.getMessage());
        ErrorResponseDTO result = new ErrorResponseDTO(ErrorCode.FAIL, ex.getMessage());
        return new ResponseEntity<>(
                result, new HttpHeaders(), HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        ErrorResponseDTO result = new ErrorResponseDTO(ErrorCode.NOT_VALIDATED, errors,"유효성 검사 실패");
        return new ResponseEntity<>(
                result, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED
        );
    }



}
