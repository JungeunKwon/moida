package com.ssafy.moida.exception;

import lombok.Getter;

@Getter
public enum EnumMusicException implements EnumExceptionBase {
	MUSIC_DUPLICATE("013", "중복된 노래 입니다.");
	
	private String code;
	private String desc;
	
	EnumMusicException(String code, String desc){
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
