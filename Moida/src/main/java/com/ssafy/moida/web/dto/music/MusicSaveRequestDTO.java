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
public class MusicSaveRequestDTO {

	private String musicname;
	private Long likecount;
	private String videoid;
	private Account account;
	private String mood;
	
	@Builder
	public MusicSaveRequestDTO(String musicname, Long likecount, String videoid, Account account,String mood) {
		super();
		this.musicname = musicname;
		this.likecount = likecount;
		this.videoid = videoid;
		this.account = account;
		this.mood = mood;
	}
	
	public Music toEntity() {
		return Music.builder()
				.musicname(musicname)
				.likecount(likecount)
				.videoid(videoid)
				.account(account)
				.mood(mood)
				.build();
	}
	
	
	

}
