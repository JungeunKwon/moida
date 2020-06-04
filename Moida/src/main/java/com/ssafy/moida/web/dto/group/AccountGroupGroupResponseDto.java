package com.ssafy.moida.web.dto.group;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.group.AccountGroup;
import com.ssafy.moida.domain.group.GroupTB;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AccountGroupGroupResponseDto {
	private Long groupId;
	private String subject;
	private Boolean isPrivate;
	private String hostNickname;
	
	public AccountGroupGroupResponseDto(AccountGroup entity) {
		this.groupId = entity.getGroupTB().getId();
		this.subject = entity.getGroupTB().getSubject();
		this.isPrivate = entity.getGroupTB().isPrivate();
		this.hostNickname = entity.getGroupTB().getHost().getNickname();
	}

	@Builder
	public AccountGroupGroupResponseDto(Long groupId, String subject, Boolean isPrivate, String hostNickname) {
		super();
		this.groupId = groupId;
		this.subject = subject;
		this.isPrivate = isPrivate;
		this.hostNickname = hostNickname;
	}

	
}
