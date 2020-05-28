package com.ssafy.moida.web.dto.etrash;




import java.time.LocalDateTime;

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
	private Long likecount;
	private Long deleteTime;
	private Account account;
	private Music music;
	
	public Etrash toEntity() {
		LocalDateTime deletedate = LocalDateTime.now();
		
		
		return Etrash.builder()
				.description(description)
				.mood(mood)
				.likecount(likecount)
				.deletedate(deletedate.plusHours(deleteTime))
				.account(account)
				.music(music)
				.build();
	}
	
	@Builder
	public EtrashSaveRequestDto(String description, String mood,Long likecount, Long deleteTime, Account account,Music music) {
		super();
		this.description = description;
		this.mood = mood;
		this.likecount = likecount;
		this.account = account;
		this.music = music;
		this.deleteTime = deleteTime;
	}
	
	
	

}
