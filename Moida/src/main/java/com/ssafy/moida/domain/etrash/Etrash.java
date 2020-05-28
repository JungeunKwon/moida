package com.ssafy.moida.domain.etrash;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.common.BaseEntity;
import com.ssafy.moida.domain.music.Music;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Etrash extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false, length = 10)
	private String mood;
	
	@Column(nullable = false)
	private int likecount;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;
	
	@ManyToOne
	@JoinColumn(name="music_id")
	private Music music;
	
	
	public void updateEtrashInfo(String description,String mood) {
		this.description = description;
		this.mood = mood;
	}
	
	public void updateEtrashLike() {
		this.likecount++;
	}
	
	public Long updateMusic(Music music) {
		this.music = music;
		return this.music.getId();
	}
	
	@Builder
	public Etrash(Long id, String description, String mood, int likecount, Account account, Music music) {
		this.id = id;
		this.description = description;
		this.mood = mood;
		this.likecount = likecount;
		this.account = account;
		this.music = music;
	}
	
	
	
	
	
}
