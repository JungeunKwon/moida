package com.ssafy.moida.web.dto.dm;


import com.ssafy.moida.domain.account.Account;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatroomUserDto {
	private Long id;
	private String roomuuid;
	private Account account;
	
	@Builder
	public ChatroomUserDto(Long id, String roomuuid, Account account) {
		this.id = id;
		this.roomuuid = roomuuid;
		this.account = account;
	}
}
