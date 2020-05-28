package com.ssafy.moida.web.dto.group;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupUpdateRequestDto {
	private Long groupId;
	private String subject;
	private int limitUser;
	private boolean isPrivate;
	private String description;
	private MultipartFile uploadFile;
	
	@Builder
	public GroupUpdateRequestDto(Long groupId, String subject, int limitUser, boolean isPrivate, String description, MultipartFile uploadFile) {
		this.groupId = groupId;
		this.subject = subject;
		this.limitUser = limitUser;
		this.isPrivate = isPrivate;
		this.description = description;
		this.uploadFile = uploadFile;
	}
}
