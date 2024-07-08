package kr.co.swadpia.common.constant;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS("Success", "성공"),
    FAIL("Fail", "실패"),
    NOT_FOUND("Not Found", "데이터를 찾을 수 없습니다."),
    NOT_VALIDATED("Not Validated", "데이터 전달 값이 유효하지 않습니다."),
    INSERT("Insert", "등록 완료되었습니다."),
    FAIL_INSERT("Not Found", "등록 실패하였습니다."),
    UPDATE("Update", "수정 완료되었습니다."),
    FAIL_UPDATE("Not Found", "수정 실패하였습니다."),
    DELETE("Delete", "삭제되었습니다."),
    INVALIDED_TOKEN("Invalided token", "INVALIDED_TOKEN");

    private final String name;
    private final String value;

    ResultCode(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
