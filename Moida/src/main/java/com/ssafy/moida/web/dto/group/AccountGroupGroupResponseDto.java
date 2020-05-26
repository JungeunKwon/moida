package com.ssafy.moida.web.dto.group;

import com.ssafy.moida.domain.group.AccountGroup;
import com.ssafy.moida.domain.group.GroupTB;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AccountGroupGroupResponseDto {
	private GroupTB group;
	
	public AccountGroupGroupResponseDto(AccountGroup entity) {
		this.group = entity.getGroupTB();
	}

	@Builder
	public AccountGroupGroupResponseDto(GroupTB group) {
		this.group = group;
	}
}
