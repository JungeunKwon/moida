package com.ssafy.moida.web.dto.comment;

import java.time.LocalDateTime;


import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.comment.Comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDTO {

	private Long id;
	private String description;
	private LocalDateTime deleteDate;
	private LocalDateTime createDate;
	private LocalDateTime modifiedDate;
	
	private Long accountid;
	private String nickname;
	private String profileimg;
	
	@Builder
	public CommentResponseDTO(Comment comment) {
		super();
		this.id = comment.getId();
		this.description = comment.getDescription();
		this.deleteDate = comment.getDeleteDate();
		this.createDate = comment.getCreateDate();
		this.modifiedDate = comment.getModifiedDate();
		
		this.accountid = comment.getAccount().getId();
		this.nickname = comment.getAccount().getNickname();
		this.profileimg = comment.getAccount().getProfileImg();
	}
	
	
	
	
	
	
}
