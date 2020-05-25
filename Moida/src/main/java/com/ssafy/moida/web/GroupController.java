package com.ssafy.moida.web;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.service.group.GroupService;
import com.ssafy.moida.web.dto.group.AccountGroupResponseDto;
import com.ssafy.moida.web.dto.group.GroupResponseDto;
import com.ssafy.moida.web.dto.group.GroupUpdateRequestDto;
import com.ssafy.moida.web.dto.group.SaveAccountGroupRequestDto;
import com.ssafy.moida.web.dto.group.SaveGroupRequestDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = {"3. Group"})
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class GroupController {

	private final GroupService groupService;
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "그룹생성", httpMethod = "POST", notes = "그룹 생성")
	@PostMapping(value = "/group", consumes = "multipart/form-data")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<Long> SaveGroup(@Valid @RequestParam("uploadFile") @Nullable MultipartFile uploadFile,
			@RequestParam("subject") String subject,
			@RequestParam("limitUser") int limitUser,
			@RequestParam("isPrivate") boolean isPrivate,
			@RequestParam("description") String description
			) throws BaseException, IllegalArgumentException, IOException {
		SaveGroupRequestDto requestDto = SaveGroupRequestDto.builder()
										.subject(subject)
										.limitUser(limitUser)
										.isPrivate(isPrivate)
										.uploadFile(uploadFile)
										.description(description)
										.build();
		return new ResponseEntity<Long>(groupService.saveGroup(requestDto), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "그룹참여", httpMethod = "POST", notes = "그룹 참여")
	@PostMapping(value = "/group/join")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<Long> SaveAccountGroup(@RequestBody SaveAccountGroupRequestDto requestDto) throws NumberFormatException, BaseException {
		return new ResponseEntity<Long>(groupService.saveAccountGroup(requestDto), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "그룹나가기", httpMethod = "DELETE", notes = "그룹 탈퇴")
	@DeleteMapping(value = "/group/leave/{groupId}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<HttpStatus> deleteAccountGroup(@PathVariable Long groupId) throws NumberFormatException, BaseException {
		groupService.deleteAccountGroup(groupId);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "방장이 그룹삭제", httpMethod = "POST", notes = "그룹 삭제")
	@PostMapping(value = "/group/{groupId}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<HttpStatus> deleteGroup(@PathVariable Long groupId) throws BaseException{
		groupService.deleteGroup(groupId);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "모든 그룹가져오기-삭제된것 제외", httpMethod = "GET", notes = "모든 그룹 가져오기-삭제된것제외")
	@GetMapping(value = "/group")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<GroupResponseDto>> findAllGroupExcludeDeleted() throws BaseException{
		return new ResponseEntity<List<GroupResponseDto>>(groupService.findAllGroupExcludeDeleted(), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "해당그룸모든멤버 조회", httpMethod = "GET", notes = "그룹아이디에 포함된 모든 멤버조회")
	@GetMapping(value = "/group/{groupId}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<AccountGroupResponseDto>> findByGroupTBId(@PathVariable Long groupId) throws BaseException{
		return new ResponseEntity<List<AccountGroupResponseDto>>(groupService.findByGroupTBId(groupId), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "그룹 수정", httpMethod = "PUT", notes = "그룹 ")
	@PutMapping(value = "/group/{groupId}", consumes = "multipart/form-data")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<HttpStatus> updateGroup(@Valid @RequestParam("uploadFile") @Nullable MultipartFile uploadFile,
			@RequestParam("subject") String subject,
			@RequestParam("limitUser") int limitUser,
			@RequestParam("isPrivate") boolean isPrivate,
			@RequestParam("description") String description){
		GroupUpdateRequestDto reqeustDto = GroupUpdateRequestDto.builder()
											.subject(subject)
											.limitUser(limitUser)
											.isPrivate(isPrivate)
											.description(description)
											.uploadFile(uploadFile)
											.build();
		
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
