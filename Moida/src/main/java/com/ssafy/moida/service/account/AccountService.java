package com.ssafy.moida.service.account;

import com.ssafy.moida.web.dto.account.RegisterRequestDto;

public interface AccountService {
	public Long Register(RegisterRequestDto requestDto);
}
