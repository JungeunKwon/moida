package com.ssafy.moida.exception;

public enum EnumHabittrackerException implements EnumExceptionBase{
	USER_NOT_FOUND("011", "유저를 찾을 수 없습니다."),
	USER_NOT_OWN("052", "자신이 작성한 해빗트래커가 아닙니다.");
	
	private String code;
	private String desc;
	
	EnumHabittrackerException(String code, String desc){
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

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return null;
	}
}
