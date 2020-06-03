package com.ssafy.moida.exception;

import lombok.Getter;

@Getter
public enum EnumGroupException implements EnumExceptionBase {
	
	GROUP_NOT_FOUND("041", "그룹을 찾을 수 없습니다."),
	GROUP_IS_FULL("042", "그룹이 가득 찼습니다."),
	GROUP_NOT_JOIN("043", "생선한 그룹에 가입하지 못했습니다.");
	
	private String code;
	private String desc;
	
	EnumGroupException(String code, String desc){
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
