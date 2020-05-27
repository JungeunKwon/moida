package com.ssafy.moida.web.dto.etrash;



import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.etrash.Etrash;
import com.ssafy.moida.domain.music.Music;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EtrashResponseDto {
	
	private Long id;
	private String description;
	private String mood;
	private int likecount;
	private Account account;
	private Music music;
	
	@Builder
	public EtrashResponseDto(Etrash entity) {
		this.id = entity.getId();
		this.description = entity.getDescription();
		this.mood = entity.getMood();
		this.likecount = entity.getLikecount();
		this.account = entity.getAccount();
		this.music = entity.getMusic();
	}
	
	
	
}
