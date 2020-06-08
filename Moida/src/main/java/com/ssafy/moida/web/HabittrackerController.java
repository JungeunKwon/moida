package com.ssafy.moida.web;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.service.habittracker.HabittrackerService;
import com.ssafy.moida.web.dto.habittracker.AccountHabittrackerSaveDTO;
import com.ssafy.moida.web.dto.habittracker.DohabitResponseDTO;
import com.ssafy.moida.web.dto.habittracker.DohabitSaveRequestDTO;
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
	@ApiOperation(value = "해빗삭제", httpMethod = "DELETE", notes = "해빗트래커 수정하는 부분입니다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@DeleteMapping(value = "/habit/{habitid}")
	public ResponseEntity<Long> deleteHabit(@PathVariable Long habitid
			) throws IllegalArgumentException, IOException, BaseException{
	
		return new ResponseEntity<Long>(habittrackerService.deleteHabittracker(habitid), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "해빗가입", httpMethod = "POST", notes = "해빗트래커 가입하는 부분입니다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@PostMapping(value = "/habit/join/{habitid}")
	public ResponseEntity<Long> joinHabit(@PathVariable Long habitid
			) throws IllegalArgumentException, IOException, BaseException{
		AccountHabittrackerSaveDTO requestDTO = new AccountHabittrackerSaveDTO();
		requestDTO.setHabitid(habitid);
		return new ResponseEntity<Long>(habittrackerService.joinHabittracker(requestDTO), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "해빗탈퇴", httpMethod = "DELETE", notes = "해빗트래커 탈퇴하는 부분입니다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@DeleteMapping(value = "/habit/leave/{habitid}")
	public ResponseEntity<Long> leaveHabit(@PathVariable Long habitid
			) throws IllegalArgumentException, IOException, BaseException{
		AccountHabittrackerSaveDTO requestDTO = new AccountHabittrackerSaveDTO();
		requestDTO.setHabitid(habitid);
		return new ResponseEntity<Long>(habittrackerService.leaveHabittracker(requestDTO), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "해빗 오늘치 완료", httpMethod = "POST", notes = "해빗트래커 do 해버리는 부분입니다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@PostMapping(value = "/habit/dohbit")
	public ResponseEntity<Long> doHabit(@RequestBody DohabitSaveRequestDTO requestDTO
			) throws IllegalArgumentException, IOException, BaseException{
		

		return new ResponseEntity<Long>(habittrackerService.clearHabittracker(requestDTO), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "해빗 오늘치 삭제", httpMethod = "DELETE", notes = "해빗트래커 do 삭제 해버리는 부분입니다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@DeleteMapping(value = "/habit/dohbit/{dohabitid}")
	public ResponseEntity<Boolean> deletedoHabit(@PathVariable Long dohabitid
			) throws IllegalArgumentException, IOException, BaseException{
		

		return new ResponseEntity<Boolean>(habittrackerService.deleteclearHabittracker(dohabitid), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "전체 해빗 트래커(지워진거 포함)", httpMethod = "GET", notes = "전체 해빗 부분입니다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@GetMapping(value = "/habit")
	public ResponseEntity<List<HabittrackerResponseDTO>> findAll(
			) throws IllegalArgumentException, IOException, BaseException{
		

		return new ResponseEntity<List<HabittrackerResponseDTO>>(habittrackerService.findAll(), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "공다에 포함된 해빗 모든 해빗(지워진거 포함)", httpMethod = "GET", notes = "groupid로 해빗 검색")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@GetMapping(value = "/habit/all/{groupid}")
	public ResponseEntity<List<HabittrackerResponseDTO>> findByGroupTBAll(@PathVariable Long groupid
			) throws IllegalArgumentException, IOException, BaseException{
		

		return new ResponseEntity<List<HabittrackerResponseDTO>>(habittrackerService.findByGroupTBAll(groupid), HttpStatus.OK);
	}
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "공다에 포함된 해빗 모든 해빗(지워진거 미포함)", httpMethod = "GET", notes = "groupid로 해빗 검색")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@GetMapping(value = "/habit/{groupid}")
	public ResponseEntity<List<HabittrackerResponseDTO>> findByGroupTB(@PathVariable Long groupid
			) throws IllegalArgumentException, IOException, BaseException{
		

		return new ResponseEntity<List<HabittrackerResponseDTO>>(habittrackerService.findByGroupTB(groupid), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "그룹내 내가 가입한 해빗", httpMethod = "GET", notes = "그룹내 내가 가입한 해빗.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@GetMapping(value = "/habit/in/{groupid}")
	public ResponseEntity<List<HabittrackerResponseDTO>> findByGroupTBAndAccount(@PathVariable Long groupid
			) throws IllegalArgumentException, IOException, BaseException{
		

		return new ResponseEntity<List<HabittrackerResponseDTO>>(habittrackerService.findByGroupTBAndAccount(groupid), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "해빗내 나의 활동", httpMethod = "GET", notes = "해빗내 내가 활동한 내역.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@GetMapping(value = "/habit/dohabit/{habitid}")
	public ResponseEntity<List<DohabitResponseDTO>> findByHabit(@PathVariable Long habitid
			) throws IllegalArgumentException, IOException, BaseException{
		

		return new ResponseEntity<List<DohabitResponseDTO>>(habittrackerService.findByHabit(habitid), HttpStatus.OK);
	}
	
}
