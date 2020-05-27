package com.ssafy.moida.web.dto.group;

import com.ssafy.moida.domain.group.AccountGroup;
import com.ssafy.moida.domain.group.GroupTB;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AccountGroupGroupResponseDto {
	private Long groupId;
	
	public AccountGroupGroupResponseDto(AccountGroup entity) {
		this.groupId = entity.getGroupId();
	}

	@Builder
	public AccountGroupGroupResponseDto(Long groupId) {
		this.groupId = groupId;
	}
}
