package kr.co.swadpia.common.constant;

import lombok.Getter;

@Getter
public enum EmailType {

    partner_reset_password("Partner Reset Password", "partner_reset_password"),
    partner_verify_email("Partner Verify Email", "partner_verify_email"),
    find_password("Find Password", "find_password"),
    qna_answer("Qna Answer", "qna_answer");

    private final String name;
    private final String value;

    EmailType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
