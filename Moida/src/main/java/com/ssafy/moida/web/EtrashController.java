package com.ssafy.moida.web;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.moida.domain.etrash.Etrash;
import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.service.etrash.EtrashService;
import com.ssafy.moida.web.dto.etrash.EtrashSaveRequestDto;
import com.ssafy.moida.web.dto.etrash.EtrashAllRequestDTO;
import com.ssafy.moida.web.dto.etrash.EtrashDescriptionDTO;
import com.ssafy.moida.web.dto.etrash.EtrashResponseDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = {"2. etrash"})
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class EtrashController {
	
	private final EtrashService etrashService;
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "감쓰생성", httpMethod = "POST", notes = "감정쓰레기피드 작성하는 부분입니다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@PostMapping(value = "/etrash")
	public ResponseEntity<Long> createEtrash(@RequestBody EtrashSaveRequestDto requestDto
			) throws IllegalArgumentException, IOException, BaseException{
	
		return new ResponseEntity<Long>(etrashService.saveEtrash(requestDto), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "감정분석", httpMethod = "POST", notes = "글내용 으로 감정을 분석한다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@PostMapping(value = "/etrash/sentimentanalysis")
	public ResponseEntity<String> sentimentanalysis(@RequestBody EtrashDescriptionDTO requestDto
			) throws IllegalArgumentException, IOException{
		
		return new ResponseEntity<String>(etrashService.sentimentanalysis(requestDto.getDescription()), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "모든감쓰", httpMethod = "GET", notes = "감정쓰레기 모든 피드를 가져온다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@GetMapping(value = "/etrash")
	public Page<EtrashResponseDto> findAll(Pageable pageable
			) throws IllegalArgumentException, IOException{
		return etrashService.findAll(new EtrashAllRequestDTO(pageable, null));
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "감쓰 무드로 검색", httpMethod = "GET", notes = "무드가 같은 감정쓰레기 피드를 가져온다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@GetMapping(value = "/etrash/{mood}")
	public Page<EtrashResponseDto> findByMood(@PathVariable String mood, Pageable pageable
			) throws IllegalArgumentException, IOException{
	
		return etrashService.findByMood(new EtrashAllRequestDTO(pageable,mood));
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "감쓰 좋아요 증가", httpMethod = "GET", notes = "감쓰의 좋아요 수를 증가 시킨다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@GetMapping(value = "/etrash/like/{id}")
	public ResponseEntity<Long> likecount(@PathVariable Long id
			) throws IllegalArgumentException, IOException{
	
		return new ResponseEntity<Long>(etrashService.likecount(id),HttpStatus.OK);
	}
	
	
}
