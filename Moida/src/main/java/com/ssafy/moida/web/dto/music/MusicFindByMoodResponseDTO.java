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
public class MusicFindByMoodResponseDTO {
	private Long id;
	private String musicname;
	private Long likecount;
	private String videoid;
	private String nickname;
	private String mood;
	private String thumbnail;
	
	@Builder
	public MusicFindByMoodResponseDTO(Music music) {
		this.id = music.getId();
		this.musicname = music.getMusicname();
		this.likecount = music.getLikecount();
		this.videoid = music.getVideoid();
		this.nickname = music.getAccount().getNickname();
		this.mood = music.getMood();
		this.thumbnail = music.getThumbnail();
	}
	
	

}
