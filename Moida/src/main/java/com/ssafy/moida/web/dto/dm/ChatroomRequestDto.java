package com.ssafy.moida.web.dto.dm;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatroomRequestDto {
	private String id;
    private String roomuuid;
    private String user1;
    private String user2;
    
    @Builder
	public ChatroomRequestDto(String id, String roomuuid, String user1, String user2) {
		super();
		this.id = id;
		this.roomuuid = roomuuid;
		this.user1 = user1;
		this.user2 = user2;
	}
    
}
