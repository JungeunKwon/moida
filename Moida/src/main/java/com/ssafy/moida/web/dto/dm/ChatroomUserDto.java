package com.ssafy.moida.web.dto.dm;


import java.time.LocalDateTime;

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
	private LocalDateTime lastDate;
	private String lastSentence;
	
	@Builder
	public ChatroomUserDto(Long id, String roomuuid, Account account, LocalDateTime lastDate, String lastSentence) {
		this.id = id;
		this.roomuuid = roomuuid;
		this.account = account;
		this.lastDate = lastDate;
		this.lastSentence = lastSentence;
	}
}
