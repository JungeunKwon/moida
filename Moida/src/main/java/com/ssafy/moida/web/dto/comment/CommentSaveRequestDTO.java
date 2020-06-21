package com.ssafy.moida.web.dto.comment;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.comment.Comment;
import com.ssafy.moida.domain.diary.Diary;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentSaveRequestDTO {

	private String description;
	private Long diaryid;
	private Account account;
	private Diary diary;
	
	public Comment toEntity() {
		return Comment.builder()
				.description(description)
				.account(account)
				.diary(diary)
				.build();
	}
	
	
	@Builder
	public CommentSaveRequestDTO(String description, Long diaryid) {
		super();
		this.description = description;
		this.diaryid = diaryid;
	}
	
	
		

}
