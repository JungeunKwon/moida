package com.ssafy.moida.web.dto.music;

import com.ssafy.moida.domain.account.Account;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicSearchMoodDTO {
	private Long id;
	private String musicname;
	private Long likecount;
	private String videoid;
	private Account account;
	
	@Builder
	public MusicSearchMoodDTO(Long id, String musicname, Long likecount, String videoid, Account account) {
		this.id = id;
		this.musicname = musicname;
		this.likecount = likecount;
		this.videoid = videoid;
		this.account = account;
	}
	
	

}
