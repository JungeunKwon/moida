package com.ssafy.moida.service.account;

import java.io.IOException;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.web.dto.account.AccountResponseDto;
import com.ssafy.moida.web.dto.account.AccountUpdateRequestDto;
import com.ssafy.moida.web.dto.account.RegisterRequestDto;
import com.ssafy.moida.web.dto.account.SignInRequestDto;

public interface AccountService {
	public Long register(RegisterRequestDto requestDto) throws IllegalArgumentException, IOException;
	public Account findByEmail(SignInRequestDto requestDto) throws BaseException;
	public Account findById(String id) throws NumberFormatException, BaseException;
	public String signIn(SignInRequestDto requestDto) throws BaseException;
	public AccountResponseDto findByAccount() throws NumberFormatException, BaseException;
	public void updateAccount(AccountUpdateRequestDto requestDTO) throws IOException, BaseException;
	public void deleteAccount() throws NumberFormatException, BaseException;
	public Boolean checkEmail(String email);
	public Boolean checkNickname(String nickname);
	
}
