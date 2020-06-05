package com.ssafy.moida.service.follow;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moida.domain.account.AccountRepository;
import com.ssafy.moida.domain.follow.FollowRepository;
import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.service.account.AccountService;
import com.ssafy.moida.web.dto.follow.FollowResponseDTO;
import com.ssafy.moida.web.dto.follow.FollowSaveRequest;
import com.ssafy.moida.web.dto.follow.FollowSeachRequest;
import com.ssafy.moida.web.dto.group.GroupResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FollowServiceImpl implements FollowService{
	
	private final FollowRepository followRepository;
	private final AccountService accountService;
	private final AccountRepository accountRepository;
	
	@Transactional
	public Long saveFollow(Long followingid) throws NumberFormatException, BaseException {
		FollowSaveRequest requestDTO = new FollowSaveRequest();
		requestDTO.setFollower(accountService.getAccount());
		requestDTO.setFollowing(accountRepository.findById(followingid).get());
		
		if(0<followRepository.countByFollowingIdAndFollowerId(followingid, requestDTO.getFollower().getId())) {
			return 0L;
		}
		
		return followRepository.save(requestDTO.toEntity()).getId();
	}

	@Transactional
	public Boolean deleteFollow(Long followingid) throws NumberFormatException, BaseException {
		FollowSaveRequest requestDTO = new FollowSaveRequest();
		requestDTO.setFollower(accountService.getAccount());
		requestDTO.setFollowing(accountRepository.findById(followingid).get());
		
		
		followRepository.delete(requestDTO.toEntity());
		
		return true;
	}

	@Transactional(readOnly=true)
	public List<FollowResponseDTO> Followinglist() throws NumberFormatException, BaseException {
		
		return followRepository.findByFollowingId(accountService.getAccount().getId())
				.stream()
				.map(FollowResponseDTO :: new)
				.collect(Collectors.toList());
	}

	@Transactional(readOnly=true)
	public List<FollowResponseDTO> Followerlist() throws NumberFormatException, BaseException {
		return followRepository.findByFollowerId(accountService.getAccount().getId()).stream()
				.map(FollowResponseDTO :: new)
				.collect(Collectors.toList());
	}
	

}
