package com.ssafy.moida.web.dto.diary;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.diary.Diary;
import com.ssafy.moida.domain.diary.DiaryLikes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiaryLikeSaveRequestDTO {
	Diary diary;
	Account account;
	
	public DiaryLikes toEntity() {
		
		return DiaryLikes.builder().account(account).diary(diary).build();
	}
	
	
	public DiaryLikeSaveRequestDTO(Diary diary, Account account) {
		super();
		this.diary = diary;
		this.account = account;
	}
	
	

}
