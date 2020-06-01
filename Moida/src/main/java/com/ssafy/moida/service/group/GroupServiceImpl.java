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
import com.ssafy.moida.service.account.AccountService;
import com.ssafy.moida.web.dto.group.AccountGroupGroupResponseDto;
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
	private final AccountService accountService;
	private final String DEFAULT_URL = "default.png";
	
	@Transactional
	public Long saveGroup(SaveGroupRequestDto requestDto) throws BaseException, IllegalArgumentException, IOException {
		requestDto.setHost(accountService.getAccount());
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
		System.out.println(group.getLimitUser());
		System.out.println(group.getAccount());
		
		if(group.getLimitUser()<group.getAccount().size()+1){
			throw new BaseException(EnumGroupException.GROUP_IS_FULL);
		}
		requestDto.setAccount(accountService.getAccount());
		requestDto.setGroupTB(group);
		return accountGroupRepository.save(requestDto.toEntity()).getId();
	}
	
	@Transactional
	public void deleteAccountGroup(Long groupId) throws NumberFormatException, BaseException {
		AccountGroup accountGroup = accountGroupRepository.findByAccountAndGroupTB(accountService.getAccount().getId(), groupId).orElseThrow(()->new BaseException(EnumGroupException.GROUP_NOT_FOUND));
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
		return groupTBRepository.findByDeleteTimeIsNull().stream()
				.map(GroupResponseDto :: new)
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<AccountGroupResponseDto> findByGroupTBId(Long groupId) throws BaseException{
		GroupTB group =  groupTBRepository.findById(groupId).orElseThrow(()->new BaseException(EnumGroupException.GROUP_NOT_FOUND));
		List<AccountGroup> list = group.getAccount();
		return list.stream()
				.map(AccountGroupResponseDto :: new)
				.collect(Collectors.toList());
	}
	
	@Transactional
	public void updateGroup(GroupUpdateRequestDto requestDto) throws BaseException, IllegalArgumentException, IOException {
		GroupTB group = groupTBRepository.findById(requestDto.getGroupId()).orElseThrow(() -> new BaseException(EnumGroupException.GROUP_NOT_FOUND));
		String imgUrl;
		if(requestDto.getUploadFile()==null) {
			imgUrl = DEFAULT_URL;
		}
		else {
			deleteS3.deleteFile(deleteS3.getFilePath(group.getImgUrl()));
			imgUrl = uploadS3.uploadFile(requestDto.getUploadFile(), "group/" + group.getHost().getEmail());
		}
		group.updateGroup(requestDto.getSubject(), requestDto.getLimitUser(), requestDto.isPrivate(), requestDto.getDescription(), imgUrl);
	}
	
	@Transactional(readOnly = true)
	public List<GroupResponseDto> findBySubjectLike(String subject) {
		return groupTBRepository.findBySubjectContaining(subject).stream()
				.map(GroupResponseDto :: new)
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<GroupResponseDto> findByNicknameLike(String nickname){
		return groupTBRepository.findByNicknameLikeQuery(nickname).stream()
				.map(GroupResponseDto :: new)
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<GroupResponseDto> findByDescriptionLike(String description){
		return groupTBRepository.findByDescriptionContaining(description).stream()
				.map(GroupResponseDto :: new)
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<AccountGroupGroupResponseDto> findGroupbyAccount() throws NumberFormatException, BaseException{
		return accountGroupRepository.findByAccount(accountService.getAccount()).stream()
				.map(AccountGroupGroupResponseDto :: new)
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public GroupResponseDto findByGroupId(Long groupId) throws BaseException {
		GroupTB group = groupTBRepository.findById(groupId).orElseThrow(()->new BaseException(EnumGroupException.GROUP_NOT_FOUND));
		return GroupResponseDto.builder()
				.id(group.getId())
				.subject(group.getSubject())
				.limitUser(group.getLimitUser())
				.deleteTime(group.getDeleteTime())
				.isPrivate(group.isPrivate())
				.imgUrl(group.getImgUrl())
				.description(group.getDescription())
				.hostId(group.getHost().getId())
				.hostNickname(group.getHost().getNickname())
				.hostProfileImg(group.getHost().getProfileImg())
				.curUser(accountGroupRepository.countByGroupId(groupId))
				.build();
				
	}
	
}
