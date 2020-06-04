
package com.ssafy.moida.web.dto.follow;


import org.springframework.data.domain.Pageable;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.follow.Follow;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FollowSeachRequest {

	private Account follower;
	private Account following;
	private Pageable pageable;
	
	public Follow toEntity() {
		
		return Follow.builder()
				.follower(follower)
				.following(following)
				.build();
	}
	
	@Builder
	public FollowSeachRequest(Account follower, Account following,Pageable pageable) {
		super();
		this.follower = follower;
		this.following = following;
		this.pageable = pageable;
	}
	
	

}
