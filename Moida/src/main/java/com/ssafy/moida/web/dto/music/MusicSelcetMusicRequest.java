package com.ssafy.moida.web.dto.music;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MusicSelcetMusicRequest {
	private Long etrashid;
	private Long musicid;
	
	@Builder
	public MusicSelcetMusicRequest(Long etrashid, Long musicid) {
		this.etrashid = etrashid;
		this.musicid = musicid;
	}
	
	
	
}
