package com.ssafy.moida.web.dto.etrash;



import java.time.LocalDateTime;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.etrash.Etrash;
import com.ssafy.moida.domain.music.Music;
import com.ssafy.moida.web.dto.account.AccountResponseDto;
import com.ssafy.moida.web.dto.music.MusicResponseDTO;

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
	private Long likecount;
	LocalDateTime deleteDate;
	LocalDateTime createDate;
	AccountResponseDto musicAccount;
	
	
	
	private MusicResponseDTO music;
	
	@Builder
	public EtrashResponseDto(Etrash entity) {
		this.id = entity.getId();
		this.description = entity.getDescription();
		this.mood = entity.getMood();
		this.likecount = entity.getLikecount();
		this.deleteDate = entity.getDeleteDate();
		this.music = MusicResponseDTO.builder().music(entity.getMusic()).build();
		this.createDate = entity.getCreateDate();
		
		this.musicAccount = AccountResponseDto.builder().nickname(this.music.getNickname()).build();
	}
	
	
	
}
