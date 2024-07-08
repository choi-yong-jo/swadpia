package kr.co.swadpia.common.constant;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNAUTHORIZED("Unauthorized", "UNAUTHORIZED"),
    EXPIRED_TOKEN("Expired token", "EXPIRED_TOKEN"),
    INVALIDED_TOKEN("Invalided token", "INVALIDED_TOKEN"),
    FAIL("Fail", "FAIL"),
    NOT_VALIDATED("Not Validated", "NOT_VALIDATED"),
    DUPLICATED_PARAM("Duplicated parameters", "DUPLICATED_PARAM"),
    DUPLICATED_MOBILE("Duplicated mobile", "DUPLICATED_MOBILE"),
    DUPLICATED_BUSINESS_NUMBER("Duplicated business number", "DUPLICATED_BUSINESS_NUMBER"),
    DUPLICATED_EMAIL("Duplicated email", "DUPLICATED_EMAIL"),
    INVALIDED_PARAM("Invalided parameters", "INVALIDED_PARAM"),
    DO_NOT_PERMIT("Do not permit", "DO_NOT_PERMIT"),
    NOT_FOUND("Not Found", "NOT_FOUND"),
    NON_TRADABLE("Non Tradable", "NON_TRADABLE"),
    INVALIDED_CODE("Invalided code", "INVALIDED_CODE"),
    INTERNAL_SERVER_ERROR("Internal Server Error", "INTERNAL_SERVER_ERROR");



    private final String name;
    private final String value;

    ErrorCode(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
