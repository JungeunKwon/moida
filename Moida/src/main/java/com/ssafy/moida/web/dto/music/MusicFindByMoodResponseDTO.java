package com.ssafy.moida.web.dto.music;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.music.Music;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicFindByMoodResponseDTO {
	private Long id;
	private String musicname;
	private Long likecount;
	private String videoid;
	private Account account;
	private String mood;
	private String sumnail;
	
	@Builder
	public MusicFindByMoodResponseDTO(Music music) {
		this.id = music.getId();
		this.musicname = music.getMusicname();
		this.likecount = music.getLikecount();
		this.videoid = music.getVideoid();
		this.account = music.getAccount();
		this.mood = music.getMood();
		this.sumnail = music.getSumnail();
	}
	
	

}
