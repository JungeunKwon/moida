package com.ssafy.moida.service.group;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.account.AccountRepository;
import com.ssafy.moida.domain.group.AccountGroup;
import com.ssafy.moida.domain.group.AccountGroupRepository;
import com.ssafy.moida.domain.group.GroupTB;
import com.ssafy.moida.domain.group.GroupTBRepository;
import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.exception.EnumAccountException;
import com.ssafy.moida.exception.EnumGroupException;
import com.ssafy.moida.web.dto.group.GroupResponseDto;
import com.ssafy.moida.web.dto.group.SaveAccountGroupRequestDto;
import com.ssafy.moida.web.dto.group.SaveGroupRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {
	
	private final GroupTBRepository groupTBRepository;
	private final AccountGroupRepository accountGroupRepository;
	private final AccountRepository accountRepository;
	private final PasswordEncoder passwordEncoder;
	
	public Long saveGroup(SaveGroupRequestDto requestDto) throws NumberFormatException, BaseException {
		requestDto.setHost(getAccount());
		return groupTBRepository.save(requestDto.toEntity()).getId();
	}
	
	public Long saveAccountGroup(SaveAccountGroupRequestDto requestDto) throws NumberFormatException, BaseException {
		if(requestDto.getPassword()==null || !passwordEncoder.matches(requestDto.getPassword(), requestDto.getPassword())) {
		GroupTB group = groupTBRepository.findById(requestDto.getGroupId()).orElseThrow(() -> new BaseException(EnumGroupException.GROUP_NOT_FOUND));
		requestDto.setAccount(getAccount());
		requestDto.setGroupTB(group);
		return accountGroupRepository.save(requestDto.toEntity()).getId();
		}
		else {
			throw new BaseException(EnumAccountException.PASS_NOT_CORRECT);
		}
	}
	
	public void deleteAccountGroup(Long groupId) throws NumberFormatException, BaseException {
		AccountGroup accountGroup = accountGroupRepository.findByAccountAndGroupTB(getAccount().getId(), groupId).orElseThrow(()->new BaseException(EnumGroupException.GROUP_NOT_FOUND));
		accountGroupRepository.delete(accountGroup);
	}
	
	public void deleteGroup(Long groupId) throws BaseException {
		GroupTB group = groupTBRepository.findById(groupId).orElseThrow(()->new BaseException(EnumGroupException.GROUP_NOT_FOUND));
		group.updateDeleteDate();
	}
	
	public List<GroupResponseDto> findAllGroup(){
		return groupTBRepository.findAll().stream()
				.map(GroupResponseDto :: new)
				.collect(Collectors.toList());
	}

	public Account getAccount() throws NumberFormatException, BaseException {
	      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	      Account tmp = (Account) authentication.getPrincipal();
	      Account account = accountRepository.findByEmail(tmp.getEmail()).orElseThrow(()->new BaseException(EnumAccountException.USER_NOT_FOUND));
	      return account;
	}
	
	
}
