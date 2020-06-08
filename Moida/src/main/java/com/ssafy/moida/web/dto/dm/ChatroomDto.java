package com.ssafy.moida.web.dto.dm;

import java.io.Serializable;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.dm.Chatroom;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatroomDto implements Serializable{
	private static final long serialVersionUID = 6494678977089006639L;
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
    
    public ChatroomDto(Chatroom entity) {
    	this.id = entity.getId();
    	this.roomuuid = entity.getRoomuuid();
    	this.host = entity.getChat_host();
    	this.user = entity.getChat_user();
    }
}
