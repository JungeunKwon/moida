package com.ssafy.moida.web.dto.music;

import org.springframework.data.domain.Pageable;

import com.ssafy.moida.domain.account.Account;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicFindByMoodRequestDTO {
	
	private String mood;
	Pageable pageable;
	
	@Builder
	public MusicFindByMoodRequestDTO(String mood,Pageable pageable) {
		this.mood = mood;
		this.pageable = pageable;
	}
	
	

}
