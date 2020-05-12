package com.ssafy.moida.service.account;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.account.AccountRepository;
import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.web.dto.account.AccountResponseDto;
import com.ssafy.moida.web.dto.account.AccountUpdateRequestDto;
import com.ssafy.moida.web.dto.account.RegisterRequestDto;
import com.ssafy.moida.web.dto.account.SignInRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;
	
	@Transactional
	public Long register(RegisterRequestDto requestDto) {
		return accountRepository.save(requestDto.toEntity()).getId();
	}

	@Override
	public Account findByEmail(SignInRequestDto requestDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String signIn(SignInRequestDto requestDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountResponseDto findByAccount() throws NumberFormatException, BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAccount(AccountUpdateRequestDto requestDTO) throws IOException, BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAccount() throws NumberFormatException, BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean checkNickname(String nickname) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
