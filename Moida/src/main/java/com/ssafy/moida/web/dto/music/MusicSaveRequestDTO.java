package com.ssafy.moida.web.dto.music;



import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.music.Music;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicSaveRequestDTO {

	private String musicname;
	private Long likecount;
	private String videoid;
	private Account account;
	
	@Builder
	public MusicSaveRequestDTO(String musicname, Long likecount, String videoid, Account account) {
		this.musicname = musicname;
		this.likecount = likecount;
		this.videoid = videoid;
		this.account = account;
	}
	
	public Music toEntity(String mood, String musicname, Long likecount, String videoid, Account account) {
		return Music.builder()
				.musicname(musicname)
				.likecount(likecount)
				.videoid(videoid)
				.account(account)
				.mood(mood)
				.build();
	}
	
	
	

}
