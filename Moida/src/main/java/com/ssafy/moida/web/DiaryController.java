package com.ssafy.moida.web;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.service.diary.DiaryService;
import com.ssafy.moida.web.dto.diary.DiaryFindByGroupRequest;
import com.ssafy.moida.web.dto.diary.DiaryResponseDTO;
import com.ssafy.moida.web.dto.diary.DiarySaveRequest;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = {"4. diary"})
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class DiaryController {
	private final DiaryService diaryService;
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "다이어리생성", httpMethod = "POST", notes = "다이어리피드 작성하는 부분입니다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@PostMapping(value = "/diary")
	public ResponseEntity<Long> createEtrash(@RequestBody DiarySaveRequest requestDto
			) throws IllegalArgumentException, IOException, BaseException{
	
		return new ResponseEntity<Long>(diaryService.saveDiary(requestDto), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "다이어리생성", httpMethod = "POST", notes = "다이어리피드 작성하는 부분입니다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@PostMapping(value = "/diary")
	public Page<DiaryResponseDTO> findByGroup(@RequestBody DiaryFindByGroupRequest requestDto
			) throws IllegalArgumentException, IOException, BaseException{
	
		return diaryService.findByGroupTB(requestDto);
	}
	
	
}
