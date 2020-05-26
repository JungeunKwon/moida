package com.ssafy.moida.web;

import java.io.IOException;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.service.account.AccountService;
import com.ssafy.moida.web.dto.account.AccountResponseDto;
import com.ssafy.moida.web.dto.account.AccountUpdateRequestDto;
import com.ssafy.moida.web.dto.account.RegisterRequestDto;
import com.ssafy.moida.web.dto.account.SignInRequestDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = {"1. account"})
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class AccountController {
	
	private final AccountService accountService;

	@ApiOperation(value = "회원가입", httpMethod = "POST", notes = "회원가입하는 부분입니다.")
	@PostMapping(value = "/signup", consumes = "multipart/form-data")
	public ResponseEntity<Long> register(@Valid @RequestParam("uploadFile") @Nullable MultipartFile uploadFile,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("username") String username,
			@RequestParam("nickname") String nickname,
			@RequestParam("gender") int gender,
			@RequestParam("phone") String phone
			) throws IllegalArgumentException, IOException{
		RegisterRequestDto requestDto = RegisterRequestDto.builder()
										.email(email)
										.password(password)
										.username(username)
										.nickname(nickname)
										.gender(gender)
										.phone(phone)
										.uploadFile(uploadFile)
										.build();
		return new ResponseEntity<Long>(accountService.register(requestDto), HttpStatus.OK);
	}
	
	@ApiOperation(value = "로그인", httpMethod = "POST", notes = "로그인 하는 부분")
	@PostMapping(value = "/signin")
	public ResponseEntity<String> signIn(@RequestBody SignInRequestDto requestDto) throws BaseException{
		return new ResponseEntity<String>(accountService.signIn(requestDto), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "회원조회", httpMethod = "GET", notes = "모든 회원 조회")
	@GetMapping(value = "/account")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	public ResponseEntity<AccountResponseDto> findByAccount() throws NumberFormatException, BaseException{
		return new ResponseEntity<AccountResponseDto>(accountService.findByAccount(), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "회원수정", httpMethod = "POST", notes = "회원 수정")
	@PostMapping(value = "/account", consumes = "multipart/form-data")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<HttpStatus> updateAccount(@RequestParam("nickname") String nickname,
			@RequestParam("phone") String phone,
			@RequestParam("password") String password,
			@RequestParam("uploadFile") @Nullable MultipartFile uploadFile) throws IOException, BaseException{
		AccountUpdateRequestDto requestDTO = new AccountUpdateRequestDto(password, phone, uploadFile, nickname);
		accountService.updateAccount(requestDTO);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "회원탈퇴", httpMethod = "DELETE", notes = "회원 탈퇴")
	@DeleteMapping(value = "/account")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<HttpStatus> deleteAccount() throws NumberFormatException, BaseException{
		accountService.deleteAccount();
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "이메일 중복 체크", httpMethod = "GET", notes = "DB에 기존의 Email이 있는지 검사 있으면 FALSE 없으면 TRUE")
	@GetMapping("/check/email/{email}")
	public ResponseEntity<Boolean> checkEmail(@PathVariable String email){
		return new ResponseEntity<Boolean>(accountService.checkEmail(email), HttpStatus.OK);
	}
	
	@ApiOperation(value = "닉네임 중복 체크", httpMethod = "GET", notes = "DB에 기존의 닉네임이 있는지 검사 있으면 FALSE 없으면 TRUE")
	@GetMapping("/check/nickname/{nickname}")
	public ResponseEntity<Boolean> checkNickname(@PathVariable String nickname){
		return new ResponseEntity<Boolean>(accountService.checkNickname(nickname), HttpStatus.OK);
	}
}
