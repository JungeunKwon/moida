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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.moida.domain.etrash.Etrash;
import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.service.etrash.EtrashService;
import com.ssafy.moida.service.follow.FollowService;
import com.ssafy.moida.web.dto.etrash.EtrashSaveRequestDto;
import com.ssafy.moida.web.dto.follow.FollowingResponseDTO;
import com.ssafy.moida.web.dto.follow.FollowSaveRequest;
import com.ssafy.moida.web.dto.follow.FollowerResponseDTO;
import com.ssafy.moida.web.dto.etrash.EtrashAllRequestDTO;
import com.ssafy.moida.web.dto.etrash.EtrashDescriptionDTO;
import com.ssafy.moida.web.dto.etrash.EtrashResponseDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = {"7. follow"})
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class FollowController {
	
	private final FollowService followService;
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "팔로우 추가", httpMethod = "POST", notes = "팔로우 추가하는 부분입니다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@PostMapping(value = "/follow/{followingid}")
	public ResponseEntity<Long> createEtrash(@PathVariable Long followingid
			) throws IllegalArgumentException, IOException, BaseException{
	
		return new ResponseEntity<Long>(followService.saveFollow(followingid), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "팔로우 취소", httpMethod = "DELETE", notes = "팔로우 취소하는 부분입니다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@DeleteMapping(value = "/follow/{followingid}")
	public ResponseEntity<Boolean> deleteEtrash(@PathVariable Long followingid
			) throws IllegalArgumentException, IOException, BaseException{
	
		return new ResponseEntity<Boolean>(followService.deleteFollow(followingid), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "팔로잉 찾기", httpMethod = "GET", notes = "팔로잉 찾는 부분입니다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@GetMapping(value = "/following/{followerid}")
	public ResponseEntity<List<FollowingResponseDTO>> findfollowing(@PathVariable Long followerid, Pageable pageable
			) throws IllegalArgumentException, IOException, BaseException{
		return new ResponseEntity<List<FollowingResponseDTO>>(followService.Followinglist(followerid), HttpStatus.OK);

	}
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "팔로워 찾기", httpMethod = "GET", notes = "팔로워 찾는 부분입니다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@GetMapping(value = "/follower/{followingid}")
	public ResponseEntity<List<FollowerResponseDTO>> findfollower(@PathVariable Long followingid, Pageable pageable
			) throws IllegalArgumentException, IOException, BaseException{
		return new ResponseEntity<List<FollowerResponseDTO>>( followService.Followerlist(followingid), HttpStatus.OK);

	}
	
}
