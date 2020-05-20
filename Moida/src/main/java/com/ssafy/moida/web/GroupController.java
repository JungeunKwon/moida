package com.ssafy.moida.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.service.group.GroupService;
import com.ssafy.moida.web.dto.group.GroupResponseDto;
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
	@PostMapping(value = "/group")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<Long> SaveGroup(@RequestBody SaveGroupRequestDto requestDto) throws NumberFormatException, BaseException {
		return new ResponseEntity<Long>(groupService.saveGroup(requestDto), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "그룹참여", httpMethod = "POST", notes = "그룹 참여")
	@GetMapping(value = "/group/join")
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
	@ApiOperation(value = "모든 그룹 가져오기", httpMethod = "GET", notes = "모든 그룹 가져오기")
	@GetMapping(value = "/group")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<GroupResponseDto>> findAllGroup() throws BaseException{
		return new ResponseEntity<List<GroupResponseDto>>(groupService.findAllGroup(), HttpStatus.OK);
	}
}
