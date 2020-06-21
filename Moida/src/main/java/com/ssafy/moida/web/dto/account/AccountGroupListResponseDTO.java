package com.ssafy.moida.web.dto.account;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountGroupListResponseDTO {
	Long id;
	String subject;
	
	@Builder
	public AccountGroupListResponseDTO(Long id, String subject) {
		super();
		this.id = id;
		this.subject = subject;
	}
	
	
	
}
