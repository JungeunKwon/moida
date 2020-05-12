package com.ssafy.moida.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.moida.service.account.AccountService;
import com.ssafy.moida.web.dto.account.RegisterRequestDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = {"1. account"})
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class AccountController {

	private final AccountService accountService;
	
	@ApiOperation(value = "회원가입", httpMethod = "POST", notes = "회원가입하는 부분")
	@PostMapping(value = "/signup")
	public ResponseEntity<Long> register(@RequestBody RegisterRequestDto requestDto){
		return new ResponseEntity<Long>(accountService.register(requestDto), HttpStatus.OK);
	}
}
