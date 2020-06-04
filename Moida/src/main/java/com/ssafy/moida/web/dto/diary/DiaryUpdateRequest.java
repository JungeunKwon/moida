package com.ssafy.moida.web.dto.diary;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiaryUpdateRequest {
	private Long id;
	private String description;
	private String mood;
	private String imgurl;
	private int isPrivate;
	
	@Builder
	public DiaryUpdateRequest(Long id,String description, String mood, String imgurl, int isPrivate) {
		super();
		this.id = id;
		this.description = description;
		this.mood = mood;
		this.imgurl = imgurl;
		this.isPrivate = isPrivate;
	}
	
	

}
