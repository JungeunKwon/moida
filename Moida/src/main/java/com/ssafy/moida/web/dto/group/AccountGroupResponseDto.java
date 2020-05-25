package com.ssafy.moida.web.dto.group;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.group.AccountGroup;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountGroupResponseDto {
	private Long id;
	private Account account;
	
	public AccountGroupResponseDto(AccountGroup entity) {
		this.id = entity.getId();
		this.account = entity.getAccount();
	}
}
