package com.ssafy.moida.domain.follow;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.common.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Follow extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OnDelete(action=OnDeleteAction.CASCADE) 
	@ManyToOne
	@JoinColumn(name="follower_id")
	private Account follower;
	
	@OnDelete(action=OnDeleteAction.CASCADE) 
	@ManyToOne
	@JoinColumn(name = "following_id")
	private Account following;

	@Builder
	public Follow(Long id, Account follower, Account following) {
		this.id = id;
		this.follower = follower;
		this.following = following;
	}
}
