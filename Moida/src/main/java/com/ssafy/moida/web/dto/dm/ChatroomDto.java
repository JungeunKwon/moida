package com.ssafy.moida.web.dto.dm;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.dm.Chatroom;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatroomDto {
	private Long id;
    private String roomuuid;
	private Account host;
    private Account user;
    
    @Builder
	public ChatroomDto(Long id, String roomuuid, Account host, Account user) {
		super();
		this.id = id;
		this.roomuuid = roomuuid;
		this.host = host;
		this.user = user;
	}
    
    public Chatroom toEntity() {
    	return Chatroom.builder()
    			.roomuuid(roomuuid)
    			.chat_host(host)
    			.chat_user(user)
    			.build();
    }
    
}
