package com.ssafy.moida.service.follow;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.web.dto.follow.FollowingResponseDTO;
import com.ssafy.moida.web.dto.follow.FollowSaveRequest;
import com.ssafy.moida.web.dto.follow.FollowSeachRequest;
import com.ssafy.moida.web.dto.follow.FollowerResponseDTO;

public interface FollowService {
	public Long saveFollow(Long followingid) throws NumberFormatException, BaseException;
	public Boolean deleteFollow(Long followingid) throws NumberFormatException, BaseException;
	public List<FollowingResponseDTO> Followinglist(Long followerid) throws NumberFormatException, BaseException;
	public List<FollowerResponseDTO> Followerlist(Long followingid) throws NumberFormatException, BaseException;

}
