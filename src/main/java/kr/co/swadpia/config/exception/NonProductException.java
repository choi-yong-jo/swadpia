package kr.co.swadpia.config.exception;

public class NonProductException extends RuntimeException {
    public NonProductException() {
        super();
    }

    public NonProductException(String message) {
        super(message);
    }

    public NonProductException(Long productId) {
        super("[" + productId + "] 상품 정보가 없습니다.");
    }

    public NonProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonProductException(Throwable cause) {
        super(cause);
    }

    protected NonProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
