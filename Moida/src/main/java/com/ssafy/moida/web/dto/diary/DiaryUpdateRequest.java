package com.ssafy.moida.web.dto.diary;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.group.GroupTB;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiaryUpdateRequest {
	
	private String description;
	private String mood;
	private String imgurl;
	
	@Builder
	public DiaryUpdateRequest(String description, String mood, String imgurl) {
		super();
		this.description = description;
		this.mood = mood;
		this.imgurl = imgurl;
	}
	
	

}
