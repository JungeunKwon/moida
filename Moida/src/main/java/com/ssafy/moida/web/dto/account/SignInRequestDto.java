package com.ssafy.moida.web.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequestDto {
	@JsonProperty
	String email;
	@JsonProperty
	String password;
}

