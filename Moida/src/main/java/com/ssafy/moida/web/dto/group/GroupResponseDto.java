package com.ssafy.moida.web.dto.group;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.group.GroupTB;

import lombok.Builder;

public class GroupResponseDto {
	private Long id;
	private String name;
	private int limitUser;
	private Account host;
	
	public GroupResponseDto(GroupTB entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.limitUser = entity.getLimitUser();
		this.host = entity.getHost();
	}
	
	@Builder
	public GroupResponseDto(Long id, String name, int limitUser, Account host) {
		super();
		this.id = id;
		this.name = name;
		this.limitUser = limitUser;
		this.host = host;
	}
}
