package kr.co.swadpia.config.exception;

public class NonCategoryException extends RuntimeException {

    public NonCategoryException() {
        super();
    }

    public NonCategoryException(String message) {
        super(message);
    }

    public NonCategoryException(Long categroyId) {
        super("[" + categroyId + "] 카테고리 정보가 없습니다.");
    }

    public NonCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonCategoryException(Throwable cause) {
        super(cause);
    }

    protected NonCategoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
