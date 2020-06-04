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
public class FollowResponseDTO {
	private Long id;
	private Account following;
	
	
	
	
	public FollowResponseDTO(Follow entity) {
		super();
		this.id = entity.getId();
		this.following = entity.getFollowing();
	}
	

}
