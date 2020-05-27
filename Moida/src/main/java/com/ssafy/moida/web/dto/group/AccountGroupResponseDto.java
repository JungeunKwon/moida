package com.ssafy.moida.web.dto.group;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.group.AccountGroup;
import com.ssafy.moida.domain.group.GroupTB;
import com.ssafy.moida.web.dto.group.AccountGroupGroupResponseDto.AccountGroupGroupResponseDtoBuilder;

import lombok.Builder;
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
	
	@Builder
	public AccountGroupResponseDto(Long id, Account account) {
		super();
		this.id = id;
		this.account = account;
	}
}
