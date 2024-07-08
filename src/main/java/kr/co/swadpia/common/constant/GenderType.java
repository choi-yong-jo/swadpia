package kr.co.swadpia.common.constant;

import lombok.Getter;

@Getter
public enum GenderType {

	Male("남자", "male"),
	Female("여자", "female");
	private final String name;
	private final String value;

	GenderType(String name, String value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
