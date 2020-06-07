package com.ssafy.moida.web;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.service.diary.DiaryService;
import com.ssafy.moida.web.dto.diary.DiaryResponseDTO;
import com.ssafy.moida.web.dto.diary.DiarySaveRequest;
import com.ssafy.moida.web.dto.diary.DiaryUpdateRequest;
import com.ssafy.moida.web.dto.diary.UploadFileDto;

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
	@ApiOperation(value = "다이어리 id 검색", httpMethod = "GET", notes = "다이어리 id로 검색.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@GetMapping(value = "/diary/{diaryid}")
	public ResponseEntity<DiaryResponseDTO> findByid(@PathVariable Long diaryid
			) throws IllegalArgumentException, IOException, BaseException{
		
		
		return new ResponseEntity<DiaryResponseDTO>(diaryService.findById(diaryid), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "다이어리그룹조회", httpMethod = "GET", notes = "다이어리그룹으로 검색하는 부분입니다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@GetMapping(value = "/diary/search/group/{groupid}")
	public ResponseEntity<List<DiaryResponseDTO>> findByGroup(@PathVariable Long groupid,Pageable pageable
			) throws IllegalArgumentException, IOException, BaseException{
	
		return new ResponseEntity<List<DiaryResponseDTO>>(diaryService.findByGroupTB(groupid,pageable), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "모든 다이어리 검색", httpMethod = "GET", notes = "다이어리 피드 전부(공개/친구/내) 검색 부분입니다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/diary")
	public ResponseEntity<List<DiaryResponseDTO>> findAll(Pageable pageable
			) throws IllegalArgumentException, IOException, BaseException{
	
		return new ResponseEntity<List<DiaryResponseDTO>>(diaryService.findAll(pageable), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "다이어리 수정", httpMethod = "PUT", notes = "다이어리 피드 수정 부분입니다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@PutMapping(value = "/diary")
	public ResponseEntity<DiaryResponseDTO> update(@RequestBody DiaryUpdateRequest requestDto
			) throws IllegalArgumentException, IOException, BaseException{
	
		return new ResponseEntity<DiaryResponseDTO>(diaryService.updateinfo(requestDto), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "다이어리 삭제", httpMethod = "DELETE", notes = "id 값으로 다이어리 삭제하는 부분입니다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@DeleteMapping(value = "/diary/{diaryid}")
	public ResponseEntity<Long> deleteByid(@PathVariable Long diaryid
			) throws IllegalArgumentException, IOException, BaseException{
	
		return new ResponseEntity<Long>(diaryService.deleteDiary(diaryid), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "다이어리 그룹 날짜 검색", httpMethod = "GET", notes = "다이어리 그룹에서 날짜로 검색.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@GetMapping(value = "/diary/search/day/{datetime}/{groupid}")
	public Page<DiaryResponseDTO> findByGroupDay(@PathVariable String datetime, @PathVariable Long groupid, Pageable pageable
			) throws IllegalArgumentException, IOException, BaseException{
	
		return diaryService.findByDay(datetime,groupid,pageable);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "다이어리 이미지 업로드", httpMethod = "POST", notes = "다이어리 이미지 S3에 업로드")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@PostMapping(value = "/upload", consumes = "multipart/form-data")
	public ResponseEntity<String> uploadImg2S3(@Valid @RequestParam("uploadFile") MultipartFile uploadFile) throws NumberFormatException, IllegalArgumentException, IOException, BaseException{
		return new ResponseEntity<String>(diaryService.uploadImg2S3(uploadFile), HttpStatus.OK);
	}
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
    @ApiOperation(value = "다이어리 닉네임 찾기", httpMethod = "GET", notes = "다이어리 닉네임으로 찾기.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@GetMapping(value = "/diary/find/nickname/{nickname}")
	public Page<DiaryResponseDTO> findByNickname(@PathVariable String nickname, Pageable pageable
			) throws IllegalArgumentException, IOException, BaseException{
	
		return diaryService.findByNickname(nickname, pageable);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "다이어리 좋아요 추가", httpMethod = "POST", notes = "")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@PostMapping(value = "/diary/like/{diaryid}")
	public ResponseEntity<Long> saveLikeDiary(@PathVariable Long diaryid, Pageable pageable
			) throws IllegalArgumentException, IOException, BaseException{
	
		return new ResponseEntity<Long>(diaryService.likeDiary(diaryid), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "다이어리 좋아요 취소", httpMethod = "DELETE", notes = "")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@DeleteMapping(value = "/diary/like/{diaryid}")
	public ResponseEntity<Long> delteLikeDiary(@PathVariable Long diaryid, Pageable pageable
			) throws IllegalArgumentException, IOException, BaseException{
		
		return new ResponseEntity<Long>(diaryService.deletelikeDiary(diaryid), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "다이어리 무드 검색", httpMethod = "GET", notes = "")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@GetMapping(value = "/diary/search/{mood}")
	public Page<DiaryResponseDTO> searchByMood(@PathVariable String mood, Pageable pageable
			) throws IllegalArgumentException, IOException, BaseException{
		
		return diaryService.findByMoodAndBydeleteDateIsNull(mood, pageable);
	}
}
