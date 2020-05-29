package com.ssafy.moida.web.dto.diary;



import java.time.LocalDateTime;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.diary.Diary;
import com.ssafy.moida.domain.group.GroupTB;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiaryResponseDTO {

	private Long id;
	private String description;
	private LocalDateTime deleteDate;
	private String mood;
	private String imgurl;
	private Account account;
	private GroupTB groupTB;
	private LocalDateTime createDate;
	private LocalDateTime modifiedDate;
	
	
	@Builder
	public DiaryResponseDTO(Diary diary) {
		super();
		this.id = diary.getId();
		this.description = diary.getDescription();
		this.deleteDate = diary.getDeleteDate();
		this.mood = diary.getMood();
		this.imgurl = diary.getImgurl();
		this.account = diary.getAccount();
		this.groupTB = diary.getGroupTB();
		this.createDate = diary.getCreateDate();
		this.modifiedDate = diary.getModifiedDate();
	}
	
	
}
