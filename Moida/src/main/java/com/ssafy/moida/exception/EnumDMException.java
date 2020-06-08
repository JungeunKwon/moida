package com.ssafy.moida.exception;

import lombok.Getter;

@Getter
public enum EnumDMException implements EnumExceptionBase{
	CHATROOM_NOT_FOUND("091", "채팅방을 찾을수 없습니다.");
	
	private String code;
	private String desc;
	
	EnumDMException(String code, String desc){
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
