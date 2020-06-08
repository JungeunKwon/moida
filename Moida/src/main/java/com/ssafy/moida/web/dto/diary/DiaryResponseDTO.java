package com.ssafy.moida.web.dto.diary;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.moida.domain.comment.Comment;
import com.ssafy.moida.domain.diary.Diary;
import com.ssafy.moida.domain.diary.DiaryLikes;

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
	private Long viewcount;
	private Long groupid;
	private String inputDate;
	private Boolean isLike;
	private int likecount;
	private Long hostid;
	private String nickname;
	private String profileurl;
	private Long commentcount;
	
	

	
	
	@Builder
	public DiaryResponseDTO(Diary diary) {
		super();
		this.id = diary.getId();
		this.description = diary.getDescription();
		this.deleteDate = diary.getDeleteDate();
		this.mood = diary.getMood();
		this.imgurl = diary.getImgurl();
		this.isPrivate = diary.getIsPrivate();
		this.viewcount = diary.getViewcount();
		this.hostid = diary.getAccount().getId();
		this.nickname = diary.getAccount().getNickname();
		this.profileurl = diary.getAccount().getProfileImg();
		this.inputDate = diary.getInputDate();
		
		if(diary.getGroupTB() !=null) {
			this.groupid = diary.getGroupTB().getId();
		}
		if(diary.getDiarylikelist() != null) {
			this.likecount = diary.getDiarylikelist().size();
		}
		
		this.commentcount = (long) diary.getCommentList().size();
		/*
		   for(Comment c :diary.getCommentList()) {
		   
		   if(c.getDeleteDate() != null) { this.commentcount--; } }
		 */
	}
	
	
	
	
}
