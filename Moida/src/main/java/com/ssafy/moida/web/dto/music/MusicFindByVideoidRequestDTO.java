package com.ssafy.moida.web.dto.music;



import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicFindByVideoidRequestDTO {
	
	private String videoid;
	
	@Builder
	public MusicFindByVideoidRequestDTO(String videoid) {
		this.videoid = videoid;
	}
	
	

}
