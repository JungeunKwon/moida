package com.ssafy.moida.exception;

import lombok.Data;

@Data
public class BaseException extends Exception {
	private String code;
	private String desc;
	private Exception e;
	private EnumExceptionBase enumCode;

	public BaseException(EnumExceptionBase code) {
		this.code = code.getCode();
		this.desc = code.getDesc();
		this.enumCode = code;
	}

	public BaseException(EnumExceptionBase code, Exception e) {
		this.e = e;
		this.code = code.getCode();
		this.desc = code.getDesc();
		this.enumCode = code;
	}
}
