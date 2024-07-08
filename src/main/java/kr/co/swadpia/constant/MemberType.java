package kr.co.swadpia.constant;

import lombok.Getter;

@Getter
public enum MemberType {
    MEMBER("회원", "MEMBER"),
    PARTNER("파트너", "PARTNER");

    private final String name;
    private final String value;

    MemberType(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
