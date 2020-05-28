package com.ssafy.moida.web.dto.group;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.group.GroupTB;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaveGroupRequestDto {
	private String subject;
	private Account host;
	private int limitUser;
	private boolean isPrivate;
	private String imgUrl;
	private String description;
	private MultipartFile uploadFile;
	
	public GroupTB toEntity() {
		return GroupTB.builder()
				.subject(subject)
				.host(host)
				.limitUser(limitUser)
				.isPrivate(isPrivate)
				.description(description)
				.imgUrl(imgUrl)
				.build();
	}

	@Builder
	public SaveGroupRequestDto(String subject, Account host, int limitUser, boolean isPrivate, MultipartFile uploadFile,
			String imgUrl, String description) {
		this.subject = subject;
		this.host = host;
		this.limitUser = limitUser;
		this.isPrivate = isPrivate;
		this.uploadFile = uploadFile;
		this.imgUrl = imgUrl;
		this.description = description;
	}
}
