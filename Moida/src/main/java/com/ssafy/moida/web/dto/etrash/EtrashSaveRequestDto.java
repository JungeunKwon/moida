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
public class EtrashSaveRequestDto {
	private String description;
	private String mood;
	private int likes;
	private Account account;
	private Music music;
	
	public Etrash toEntity() {
		return Etrash.builder()
				.description(description)
				.mood(mood)
				.likes(likes)
				.account(account)
				.music(music)
				.build();
	}
	
	@Builder
	public EtrashSaveRequestDto(String description, String mood,int likes, Account account,Music music) {
		super();
		this.description = description;
		this.mood = mood;
		this.likes = likes;
		this.account = account;
		this.music = music;
	}
	
	
	

}
