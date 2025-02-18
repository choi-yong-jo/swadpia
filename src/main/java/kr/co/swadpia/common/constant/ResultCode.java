package kr.co.swadpia.common.constant;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS("SUCCESS", "성공"),
    FAIL("FAIL", "실패"),
    EMPTY("EMPTY", "해당 데이터가 존재하지 않습니다."),
    INSERT("INSERT", "등록 완료되었습니다."),
    UPDATE("UPDATE", "수정 완료되었습니다."),
    DELETE("DELETE", "삭제되었습니다."),
    NOT_INSERT_MEMBER_ROLE_EXIST("Not Insert Member Role Exist", "회원 권한 대상이 존재합니다."),
    NOT_INSERT_MEMBER_ROLE_CHECK("Not Insert Member Role Check", "추가할 회원 권한 대상이 존재하지 않습니다."),
    NOT_DELETE_MEMBER_ROLE("Not Delete Member Role", "삭제할 회원 권한 대상이 없습니다."),
    NOT_FOUND_INFO("Not Found Infomation", "해당 정보를 찾을 수 없습니다."),
    NOT_VALIDATED_COLUMN_COUNT("Not validation Column Count", "컬럼 수가 서로 일치하지 않습니다."),
    NOT_VALIDATED_REQUEST("Not Validated", "데이터 요청 값이 유효하지 않습니다."),
    NOT_VALIDATED_PASSWORD("Not Validated Password", "패쓰워드가 일치하지 않습니다."),
    NOT_INSERT_SAME_MEMBER_ID("Not Insert Same Member ID", "동일한 아이디로 등록할 수 없습니다."),
    NOT_VALIDATED_EMAIL("Not validation email", "이메일 형식이 올바르지 않습니다."),
    NOT_VALIDATED_MOBILE("Not validation mobile", "휴대폰 전화번호 형식이 올바르지 않습니다."),
    NOT_INSERT_SAME_ROLE("Not Insert Same Role", "동일한 권한 ID와 이름으로 등록할 수 없습니다."),
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
