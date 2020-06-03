package com.ssafy.moida.web.dto.diary;



import java.time.LocalDateTime;


import com.ssafy.moida.domain.diary.Diary;


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
	private int isPrivate;
	private Long viewconut;
	
	private Long accountid;
	private String nickname;
	private String profileurl;
	
	
	private LocalDateTime createDate;

	
	
	@Builder
	public DiaryResponseDTO(Diary diary) {
		super();
		this.id = diary.getId();
		this.description = diary.getDescription();
		this.deleteDate = diary.getDeleteDate();
		this.mood = diary.getMood();
		this.imgurl = diary.getImgurl();
		this.isPrivate = diary.getIsPrivate();
		this.createDate = diary.getCreateDate();
		this.viewconut = diary.getViewcount();
		this.accountid = diary.getAccount().getId();
		this.nickname = diary.getAccount().getNickname();
		this.profileurl = diary.getAccount().getProfileImg();
		
	}
	
	
}
