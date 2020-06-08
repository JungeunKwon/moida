package com.ssafy.moida.service.group;

import java.io.IOException;
import java.util.List;



import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.web.dto.group.AccountGroupGroupResponseDto;
import com.ssafy.moida.web.dto.group.AccountGroupResponseDto;
import com.ssafy.moida.web.dto.group.GroupResponseDto;
import com.ssafy.moida.web.dto.group.GroupUpdateRequestDto;
import com.ssafy.moida.web.dto.group.SaveAccountGroupRequestDto;
import com.ssafy.moida.web.dto.group.SaveGroupRequestDto;

public interface GroupService {
	Long saveGroup(SaveGroupRequestDto requestDto) throws NumberFormatException, BaseException, IllegalArgumentException, IOException;
	Long saveAccountGroup(SaveAccountGroupRequestDto requestDto) throws NumberFormatException, BaseException;
	void deleteAccountGroup(Long groupId) throws NumberFormatException, BaseException;
	void deleteGroup(Long groupId) throws BaseException;
	List<GroupResponseDto> findAllGroup();
	List<GroupResponseDto> findAllGroupExcludeDeleted();
	List<AccountGroupResponseDto> findByGroupTBId(Long groupId) throws BaseException;
	void updateGroup(GroupUpdateRequestDto requestDto) throws BaseException, IllegalArgumentException, IOException;
	List<GroupResponseDto> findBySubjectLike(String subject);
	List<GroupResponseDto> findByNicknameLike(String nickname);
	List<GroupResponseDto> findByDescriptionLike(String description);
	List<GroupResponseDto> findGroupbyAccount() throws NumberFormatException, BaseException;
	GroupResponseDto findByGroupId(Long groupId) throws BaseException;
	Boolean kickByGroup(Long groupid, Long kickaccountid) throws NumberFormatException, BaseException;
	List<GroupResponseDto> findJoinGroupByNickname(String nickname) throws NumberFormatException, BaseException;
	
	
}	
