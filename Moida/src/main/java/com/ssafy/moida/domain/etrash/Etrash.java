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
import com.ssafy.moida.domain.music.Music;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Etrash {
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

	
	@ManyToOne
	@JoinColumn(name="music_id")
	private Music music;

	@Builder
	public Etrash(Long id, int mood, String musicurl, String description, Music music) {
		this.id = id;
		this.mood = mood;
		this.musicurl = musicurl;
		this.description = description;
		this.music = music;
	}
	
	

}
