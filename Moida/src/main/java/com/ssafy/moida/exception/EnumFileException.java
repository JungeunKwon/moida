package com.ssafy.moida.exception;

import lombok.Getter;

@Getter
public enum EnumFileException implements EnumExceptionBase {
	FILE_NOT_FOUND("021", "해당 파일을 찾을 수 없습니다."),
	DELETEFILE_NOT_FOUNT("022", "삭제하고자하는 파일을 찾을 수 없습니다.");
	private String code;
	private String desc;
	
	EnumFileException(String code, String desc){
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
