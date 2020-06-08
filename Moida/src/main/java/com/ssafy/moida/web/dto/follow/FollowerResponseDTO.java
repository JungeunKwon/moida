package com.ssafy.moida.web.dto.follow;

import java.time.LocalDateTime;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.follow.Follow;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class FollowerResponseDTO {
	private Long id;
	private String nickname;
	private String profileimg;
	

	
	public FollowerResponseDTO(Follow entity) {
		super();
		this.id = entity.getId();
		this.nickname = entity.getFollower().getNickname();
		this.profileimg = entity.getFollower().getProfileImg();
	}
	

}
