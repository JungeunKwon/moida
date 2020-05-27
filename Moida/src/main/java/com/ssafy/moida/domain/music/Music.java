package com.ssafy.moida.domain.music;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ssafy.moida.domain.account.Account;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Music {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 10)
	private String mood;
	
	@Column(nullable = false)
	private String musicname;
	
	@Column(nullable = false, length = 10)
	private Long likecount;
	
	@Column(nullable = false,unique = true, length = 1000)
	private String videoid;
	
	
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;

	@Builder
	public Music(Long id, String mood, String musicname, Long likecount, String videoid, Account account) {
		this.id = id;
		this.mood = mood;
		this.musicname = musicname;
		this.likecount = likecount;
		this.videoid = videoid;
		this.account = account;
	}
	
	
	
	
}
