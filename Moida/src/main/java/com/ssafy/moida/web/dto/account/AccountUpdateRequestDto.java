package com.ssafy.moida.web.dto.account;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountUpdateRequestDto {
	private String password;
	private String phone;
	private MultipartFile uploadFile;
	private String profileImg;
	private String nickname;
	
	@Builder
	public AccountUpdateRequestDto(String password, String phone, MultipartFile uploadFile, String nickname) {
		this.password = password;
		this.phone = phone;
		this.uploadFile = uploadFile;
		this.nickname = nickname;
	}
}
