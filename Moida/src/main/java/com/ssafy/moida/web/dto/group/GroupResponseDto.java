package com.ssafy.moida.web.dto.group;

import java.time.LocalDateTime;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.group.GroupTB;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupResponseDto {
	private Long id;
	private String subject;
	private int limitUser;
	private LocalDateTime deleteTime;
	private boolean isPrivate;
	private String imgUrl;
	private String description;
	private Account host;
	
	public GroupResponseDto(GroupTB entity) {
		this.id = entity.getId();
		this.subject = entity.getSubject();
		this.limitUser = entity.getLimitUser();
		this.deleteTime = entity.getDeleteTime();
		this.isPrivate = entity.isPrivate();
		this.imgUrl = entity.getImgUrl();
		this.description = entity.getDescription();
		this.host = entity.getHost();
	}
}
