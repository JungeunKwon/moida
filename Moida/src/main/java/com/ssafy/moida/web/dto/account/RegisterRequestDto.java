package com.ssafy.moida.web.dto.account;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.moida.domain.account.Account;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequestDto {
	private String email;
	private String password;
	private String username;
	private String nickname;
	private int gender;
	private String phone;
	private String profileImg;
	private MultipartFile uploadFile;
	
	public Account toEntity() {
		return Account.builder()
				.email(email)
				.password(password)
				.username(username)
				.nickname(nickname)
				.gender(gender)
				.phone(phone)
				.profileImg(profileImg)
				.build();
	}

	@Builder
	public RegisterRequestDto(String email, String password, String username, String nickname, int gender, String phone,
			String profileImg,MultipartFile uploadFile) {
		super();
		this.email = email;
		this.password = password;
		this.username = username;
		this.nickname = nickname;
		this.gender = gender;
		this.phone = phone;
		this.profileImg = profileImg;
		this.uploadFile = uploadFile;
	}
}
