package com.ssafy.moida.web;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.service.habittracker.HabittrackerService;
import com.ssafy.moida.web.dto.habittracker.HabittrackerResponseDTO;
import com.ssafy.moida.web.dto.habittracker.HabittrackerSaveRequestDTO;
import com.ssafy.moida.web.dto.habittracker.HabittrackerUpdateRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = {"8. habittracker"})
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class HabittrackerController {
	
	private final HabittrackerService habittrackerService;
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "해빗생성", httpMethod = "POST", notes = "해빗트래커 작성하는 부분입니다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@PostMapping(value = "/habit")
	public ResponseEntity<Long> saveHabit(@RequestBody HabittrackerSaveRequestDTO requestDTO
			) throws IllegalArgumentException, IOException, BaseException{
		
		return new ResponseEntity<Long>(habittrackerService.saveHabittracker(requestDTO), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "해빗수정", httpMethod = "PUT", notes = "해빗트래커 수정하는 부분입니다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@PutMapping(value = "/habit")
	public ResponseEntity<Long> updateHabit(@RequestBody HabittrackerUpdateRequestDTO requestDTO
			) throws IllegalArgumentException, IOException, BaseException{

		return new ResponseEntity<Long>(habittrackerService.updateinfo(requestDTO), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "해빗삭제", httpMethod = "GET", notes = "해빗트래커 수정하는 부분입니다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@GetMapping(value = "/habit/delete")
	public ResponseEntity<Long> deleteHabit(@PathVariable Long habitid
			) throws IllegalArgumentException, IOException, BaseException{
	
		return new ResponseEntity<Long>(habittrackerService.deleteHabittracker(habitid), HttpStatus.OK);
	}
	

}
