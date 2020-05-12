package com.ssafy.moida.exception;

public interface EnumBase extends EnumRoot {
	public EnumBase[] getValues();
	public String getDesc();
	public String getCode();
	public String getName();
}
