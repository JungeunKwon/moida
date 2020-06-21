package com.ssafy.moida.web.dto.dm;

import com.ssafy.moida.domain.dm.DirectMessage;

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
	private String writer;
	private String content;
	private String loguser;
	private String type2String;
	private String lastDate;

	@Builder
	public DirectMessageDto(MessageType type, String roomuuid, String writer, String content, String loguser,
			String lastDate) {
		this.type = type;
		this.roomuuid = roomuuid;
		this.writer = writer;
		this.content = content;
		this.loguser = loguser;
		this.lastDate = lastDate;
	}

	public DirectMessage toEntity() {
		return DirectMessage.builder().roomuuid(roomuuid).type(type.toString()).writer(writer).content(content).build();
	}

	public DirectMessageDto(DirectMessage Entity) {
		this.roomuuid = Entity.getRoomuuid();
		this.writer = Entity.getWriter();
		this.content = Entity.getContent();
		this.type2String = Entity.getType();
		this.lastDate = Entity.getCreateDate().toString();
	}
}
