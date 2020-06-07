package com.ssafy.moida.web.dto.dm;


import com.ssafy.moida.domain.dm.Chatroom;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatroomResponseDto {
	private Long id;
    private String roomuuid;
	private String hostNickname;
	private String hostProfileImg; 
    private String userNickname;
    private String userProfileImg;
    
    @Builder
    public ChatroomResponseDto(Chatroom entity){
    	this.id = entity.getId();
    	this.roomuuid = entity.getRoomuuid();
    	this.hostNickname = entity.getChat_host().getNickname();
    	this.hostProfileImg = entity.getChat_host().getProfileImg();
    	this.userNickname = entity.getChat_user().getNickname();
    	this.userProfileImg = entity.getChat_user().getProfileImg();
    }
}
