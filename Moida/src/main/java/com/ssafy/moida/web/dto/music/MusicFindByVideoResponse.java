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
	private String sumnail;
	
	
	@Builder
	public MusicFindByVideoResponse(Long id, String mood, String musicname, Long likecount, String videoid, Account account,String sumnail) {
		this.id = id;
		this.musicname = musicname;
		this.likecount = likecount;
		this.videoid = videoid;
		this.account = account;
		this.mood = mood;
		this.sumnail = sumnail;
	}
	

	
	
}
