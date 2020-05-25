package com.ssafy.moida.service.group;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moida.component.DeleteS3;
import com.ssafy.moida.component.UploadS3;
import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.account.AccountRepository;
import com.ssafy.moida.domain.group.AccountGroup;
import com.ssafy.moida.domain.group.AccountGroupRepository;
import com.ssafy.moida.domain.group.GroupTB;
import com.ssafy.moida.domain.group.GroupTBRepository;
import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.exception.EnumAccountException;
import com.ssafy.moida.exception.EnumGroupException;
import com.ssafy.moida.web.dto.group.AccountGroupResponseDto;
import com.ssafy.moida.web.dto.group.GroupResponseDto;
import com.ssafy.moida.web.dto.group.GroupUpdateRequestDto;
import com.ssafy.moida.web.dto.group.SaveAccountGroupRequestDto;
import com.ssafy.moida.web.dto.group.SaveGroupRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {
	
	private final GroupTBRepository groupTBRepository;
	private final AccountGroupRepository accountGroupRepository;
	private final AccountRepository accountRepository;
	private final UploadS3 uploadS3;
	private final DeleteS3 deleteS3;
	
	private final String DEFAULT_URL = "default.png";
	
	@Transactional
	public Long saveGroup(SaveGroupRequestDto requestDto) throws BaseException, IllegalArgumentException, IOException {
		requestDto.setHost(getAccount());
		String imgUrl;
		if(requestDto.getUploadFile()==null) {
			imgUrl = DEFAULT_URL;
		}else {
			imgUrl = uploadS3.uploadFile(requestDto.getUploadFile(), "group/" + requestDto.getHost().getEmail());
		}
		requestDto.setImgUrl(imgUrl);
		return groupTBRepository.save(requestDto.toEntity()).getId();
	}
	
	@Transactional
	public Long saveAccountGroup(SaveAccountGroupRequestDto requestDto) throws NumberFormatException, BaseException {
		GroupTB group = groupTBRepository.findById(requestDto.getGroupId()).orElseThrow(() -> new BaseException(EnumGroupException.GROUP_NOT_FOUND));
		if(group.getLimitUser()<group.getAccount().size()+1){
			throw new BaseException(EnumGroupException.GROUP_NOT_FOUND);
		}
		requestDto.setAccount(getAccount());
		requestDto.setGroupTB(group);
		return accountGroupRepository.save(requestDto.toEntity()).getId();
	}
	
	@Transactional
	public void deleteAccountGroup(Long groupId) throws NumberFormatException, BaseException {
		AccountGroup accountGroup = accountGroupRepository.findByAccountAndGroupTB(getAccount().getId(), groupId).orElseThrow(()->new BaseException(EnumGroupException.GROUP_NOT_FOUND));
		accountGroupRepository.delete(accountGroup);
	}
	
	@Transactional
	public void deleteGroup(Long groupId) throws BaseException {
		GroupTB group = groupTBRepository.findById(groupId).orElseThrow(()->new BaseException(EnumGroupException.GROUP_NOT_FOUND));
		group.updateDeleteDate(LocalDateTime.now());
	}
	
	@Transactional(readOnly = true)
	public List<GroupResponseDto> findAllGroup(){
		return groupTBRepository.findAll().stream()
				.map(GroupResponseDto :: new)
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<GroupResponseDto> findAllGroupExcludeDeleted(){
		return groupTBRepository.findAllGroupExcludeDeleted().stream()
				.map(GroupResponseDto :: new)
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<AccountGroupResponseDto> findByGroupTBId(Long groupId) throws BaseException{
		return accountGroupRepository.findByGroupId(groupId).stream()
				.map(AccountGroupResponseDto :: new)
				.collect(Collectors.toList());
	}
	
	@Transactional
	public void updateGroup(GroupUpdateRequestDto requestDto) {
		
	}

	public Account getAccount() throws NumberFormatException, BaseException {
	      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	      Account tmp = (Account) authentication.getPrincipal();
	      Account account = accountRepository.findByEmail(tmp.getEmail()).orElseThrow(()->new BaseException(EnumAccountException.USER_NOT_FOUND));
	      return account;
	}
}
