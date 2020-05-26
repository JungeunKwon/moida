package com.ssafy.moida.web.dto.music;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.music.Music;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MusicFindByVideoResponse {
	
	private Long id;
	private String mood;
	private String musicname;
	private Long likecount;
	private String videoid;
	private Account account;
	
	
	
	@Builder
	public MusicFindByVideoResponse(Long id, String mood, String musicname, Long likecount, String videoid, Account account) {
		this.id = id;
		this.musicname = musicname;
		this.likecount = likecount;
		this.videoid = videoid;
		this.account = account;
		this.mood = mood;
	}
	
	
	
//	public MusicFindByVideoResponse(Music entity) {
//		this.id = entity.getId();
//		this.musicname = entity.getMusicname();
//		this.likecount = entity.getLikecount();
//		this.videoid = entity.getVideoid();
//		this.account = entity.getAccount();
//		this.mood = entity.getMood();
//	}
	
	
	
	
	
	
}
