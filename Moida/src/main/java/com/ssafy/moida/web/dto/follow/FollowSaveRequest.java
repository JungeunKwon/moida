package com.ssafy.moida.web.dto.follow;


import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.follow.Follow;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FollowSaveRequest {

	private Account follower;
	private Account following;
	
	public Follow toEntity() {
		
		return Follow.builder()
				.follower(follower)
				.following(following)
				.build();
	}
	
	@Builder
	public FollowSaveRequest(Account follower, Account following) {
		super();
		this.follower = follower;
		this.following = following;

	}
	
	

}
