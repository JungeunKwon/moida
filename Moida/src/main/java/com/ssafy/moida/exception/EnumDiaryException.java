package com.ssafy.moida.exception;

import lombok.Getter;

@Getter
public enum EnumDiaryException implements EnumExceptionBase {
	DIARY_NOT_FOUND("013", "다이어리를 찾을수 없습니다.");
	
	private String code;
	private String desc;
	
	EnumDiaryException(String code, String desc){
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
