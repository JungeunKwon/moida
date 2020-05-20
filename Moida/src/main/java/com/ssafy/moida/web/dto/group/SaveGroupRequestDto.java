package com.ssafy.moida.web.dto.group;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.group.GroupTB;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaveGroupRequestDto {
	private String name;
	private String password;
	private Account host;
	
	public GroupTB toEntity() {
		return GroupTB.builder()
				.name(name)
				.password(password)
				.host(host)
				.build();
	}

	@Builder
	public SaveGroupRequestDto(String name, String password, Account host) {
		super();
		this.name = name;
		this.password = password;
		this.host = host;
	}
}
