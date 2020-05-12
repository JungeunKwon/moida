package com.ssafy.moida.domain.music;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
	
	@Column(nullable = false, length = 30)
	private int mood;
	
	@Column(nullable = false, length = 30, name="music_url")
	private String musicurl;
	
	@Lob
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false, length = 30)
	private int like;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;
	
	@Builder
	public Music(Long id, int mood, String musicurl, String description, int like, Account account) {
		this.id = id;
		this.mood = mood;
		this.musicurl = musicurl;
		this.description = description;
		this.like = like;
		this.account = account;
	}
	
	
}
