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
	private String userNickname;
	private String userProfile;
	private LocalDateTime lastDate;
	private String lastSentence;
	
	@Builder
	public ChatroomUserDto(Long id, String roomuuid, String userNickname, String userProfile, LocalDateTime lastDate,
			String lastSentence) {
		this.id = id;
		this.roomuuid = roomuuid;
		this.userNickname = userNickname;
		this.userProfile = userProfile;
		this.lastDate = lastDate;
		this.lastSentence = lastSentence;
	}
}
