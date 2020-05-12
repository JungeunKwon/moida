package com.ssafy.moida.exception;

import lombok.Getter;


@Getter
public enum EnumAccountException implements EnumExceptionBase {
	USER_NOT_FOUND("012", "유저를 찾을 수 없습니다.");
	
	private String code;
	private String desc;
	
	EnumAccountException(String code, String desc){
		this.code = code;
		this.desc = desc;
	}

	@Override
	public EnumBase[] getValues() {
		return values();
	}

	@Override
	public String getName() {
		return name();
	}
}
