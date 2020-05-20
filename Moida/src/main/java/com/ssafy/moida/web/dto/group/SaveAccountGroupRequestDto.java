package com.ssafy.moida.web.dto.group;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.group.AccountGroup;
import com.ssafy.moida.domain.group.GroupTB;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaveAccountGroupRequestDto {
	private Account account;
	private Long groupId;
	private GroupTB groupTB;
	private String password;
	
	public AccountGroup toEntity() {
		return AccountGroup.builder()
				.account(account)
				.groupTB(groupTB)
				.build();
	}
}
