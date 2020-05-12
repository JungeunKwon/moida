package com.ssafy.moida.web.dto.account;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponseDto {
	private Long id;
	private String email;
	private String username;
	private String nickname;
	private int gender;
	private String profileImg;
	private String phone;
	private List<String> roles;
	
	@Builder
	public AccountResponseDto(Long id, String email, String username, String nickname, int gender, String profileImg, String phone, List<String> roles) {
		this.id = id;
		this.email = email;
		this.username = username;
		this.nickname = nickname;
		this.gender = gender;
		this.profileImg = profileImg;
		this.phone = phone;
		this.roles = roles;
	}
}
