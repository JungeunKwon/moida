package com.ssafy.moida.service.account;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moida.domain.account.AccountRepository;
import com.ssafy.moida.web.dto.account.RegisterRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;
	
	@Transactional
	public Long Register(RegisterRequestDto requestDto) {
		return accountRepository.save(requestDto.toEntity()).getId();
	}
	
}
