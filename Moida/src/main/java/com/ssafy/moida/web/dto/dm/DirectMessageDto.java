package com.ssafy.moida.web.dto.dm;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.dm.Chatroom;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class DirectMessageDto {
	 public enum MessageType {
	        JOIN, TALK, QUIT, LOG, SHARE
	    }
	    private MessageType type;
	    private String roomuuid;
	    private Account writer;
	    private String content;
	    private String loguser;
	    
	    @Builder
		public DirectMessageDto(MessageType type, String roomuuid, Account writer, String content, String loguser) {
			this.type = type;
			this.roomuuid = roomuuid;
			this.writer = writer;
			this.content = content;
			this.loguser = loguser;
		}
}
