package kr.co.swadpia.config.exception;

public class NonOrderException extends RuntimeException {
    public NonOrderException() {
        super();
    }

    public NonOrderException(String orderNo) {
        super("[" + orderNo + "] 주문 번호가 없습니다.");
    }
    public NonOrderException(String orderNo,String meesage) {
        super("[" + orderNo + "]" + meesage);
    }

    public NonOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonOrderException(Throwable cause) {
        super(cause);
    }

    protected NonOrderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
