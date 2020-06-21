package com.ssafy.moida.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.moida.service.exception.ResponseService;
import com.ssafy.moida.service.response.CommonResult;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {
	
	public final ResponseService responseService;
	
	@ExceptionHandler(BaseException.class)
	@ResponseStatus(HttpStatus.OK)
	public CommonResult userNotFoundException(HttpServletRequest request, BaseException e) {
		return responseService.getFailResult(e);
	}
}
