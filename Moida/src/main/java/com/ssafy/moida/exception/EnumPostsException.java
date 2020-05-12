package com.ssafy.moida.exception;

import lombok.Getter;

@Getter
public enum EnumPostsException implements EnumExceptionBase{
	POSTS_NOT_FOUND("031", "해당 게시글을 찾을 수 없습니다."),
	COMMENTS_NOT_FOUND("041", "해당 댓글을 찾을 수 없습니다.");
	private String code;
	private String desc;
	
	EnumPostsException(String code, String desc){
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
