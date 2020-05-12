package com.ssafy.moida.domain.follow;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.group.Group;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Follow {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="follower_id")
	private Account followerid;
	
	@ManyToOne
	@JoinColumn(name="following_id")
	private Account followingid;
	
	@Builder
	public Follow(Long id, Account followerid, Account followingid) {
		this.id = id;
		this.followerid = followerid;
		this.followingid = followingid;
	}
	
	
	
}
