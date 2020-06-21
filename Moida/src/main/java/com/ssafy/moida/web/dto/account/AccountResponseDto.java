package com.ssafy.moida.web.dto.account;

import java.util.List;

import com.ssafy.moida.domain.group.AccountGroup;
import com.ssafy.moida.domain.group.GroupTB;
import com.ssafy.moida.domain.habittracker.AccountHabittracker;
import com.ssafy.moida.domain.habittracker.Habittracker;

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
	private List<AccountGroupListResponseDTO> groupList;


	
	@Builder
	public AccountResponseDto(Long id, String email, String username, String nickname, int gender, String profileImg, String phone, List<String> roles
			, List<AccountGroupListResponseDTO> groupList) {
		this.id = id;
		this.email = email;
		this.username = username;
		this.nickname = nickname;
		this.gender = gender;
		this.profileImg = profileImg;
		this.phone = phone;
		this.roles = roles;
		this.groupList = groupList;

	}
}
