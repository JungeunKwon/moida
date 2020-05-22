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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.moida.domain.etrash.Etrash;
import com.ssafy.moida.domain.music.MusicRepository;
import com.ssafy.moida.service.etrash.EtrashService;
import com.ssafy.moida.service.music.MusicService;
import com.ssafy.moida.web.dto.etrash.EtrashSaveRequestDto;
import com.ssafy.moida.web.dto.music.MusicFindByMoodRequestDTO;
import com.ssafy.moida.web.dto.music.MusicFindByMoodResponseDTO;
import com.ssafy.moida.web.dto.music.MusicSaveRequestDTO;
import com.ssafy.moida.web.dto.music.MusicSelcetMusicRequest;
import com.ssafy.moida.web.dto.etrash.EtrashAllRequestDTO;
import com.ssafy.moida.web.dto.etrash.EtrashDescriptionDTO;
import com.ssafy.moida.web.dto.etrash.EtrashResponseDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = {"3. music"})
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class MusicController {
	
	private final MusicService musicService;
	private final MusicRepository musicRepository;
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "음악생성", httpMethod = "POST", notes = "음악 추가하는 부분입니다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@PostMapping(value = "/music")
	public ResponseEntity<Long> saveMusic(@RequestBody MusicSaveRequestDTO requestDto
			) throws IllegalArgumentException, IOException{
		
		return new ResponseEntity<Long>(musicService.saveMusic(requestDto), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "음악검색", httpMethod = "GET", notes = "무드로 음악 검색.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@GetMapping(value = "/music/findByMood/{mood}")
	public Page<MusicFindByMoodResponseDTO> findByMood(@PathVariable String mood,Pageable pageable
			) throws IllegalArgumentException, IOException{
		MusicFindByMoodRequestDTO requestDto = new MusicFindByMoodRequestDTO(mood, pageable);

		return musicService.findByMood(requestDto);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "음악선택", httpMethod = "POST", notes = "음악 선택.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@PostMapping(value = "/music/select")
	public ResponseEntity<Long> selectMusic(@RequestBody MusicSelcetMusicRequest requestDto
			) throws IllegalArgumentException, IOException{
		
		System.out.println(requestDto.getEtrashid()+"     "+requestDto.getMusicid());
		return new ResponseEntity<Long>(musicService.selectMusic(requestDto), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "전체음악", httpMethod = "GET", notes = "음악 전체 목록.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@GetMapping(value = "/music")
	public Page<MusicFindByMoodResponseDTO> searchByAll(Pageable pageable
			) throws IllegalArgumentException, IOException{
		

		return musicRepository.findAll(pageable).map(MusicFindByMoodResponseDTO::new);
	}
	
}
