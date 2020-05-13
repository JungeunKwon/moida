package com.ssafy.moida.service.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.ssafy.moida.domain.account.AccountRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
	
	private final AccountRepository accountRepository;
	
	public UserDetails loadUserByUsername(String userPk) {
		return accountRepository.findById(Long.valueOf(userPk)).orElseThrow(()->new IllegalArgumentException("아이디가 존재하지 않습니다"));
	}
}
