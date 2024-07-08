package kr.co.swadpia.common.constant;

import lombok.Getter;

@Getter
public enum QnaType {

    question("Question", "question"),
    answer("Answer", "answer");

    private final String name;
    private final String value;

    QnaType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
