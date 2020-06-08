package com.ssafy.moida.web.dto.group;

import java.time.LocalDateTime;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.group.GroupTB;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupResponseDto {
	private Long id;
	private String subject;
	private int limitUser;
	private LocalDateTime deleteTime;
	private boolean isPrivate;
	private String imgUrl;
	private String description;
	private Long hostId;
	private String hostNickname;
	private String hostProfileImg;
	private int curUser;
	private Boolean isJoin;
	
	public GroupResponseDto(GroupTB entity) {
		this.id = entity.getId();
		this.subject = entity.getSubject();
		this.limitUser = entity.getLimitUser();
		this.deleteTime = entity.getDeleteTime();
		this.isPrivate = entity.isPrivate();
		this.imgUrl = entity.getImgUrl();
		this.description = entity.getDescription();
		this.hostId = entity.getHost().getId();
		this.hostNickname = entity.getHost().getNickname();
		this.hostProfileImg = entity.getHost().getProfileImg();

		
	}
	
	@Builder
	public GroupResponseDto(Long id, String subject, int limitUser, LocalDateTime deleteTime, boolean isPrivate,
			String imgUrl, String description, Long hostId, String hostNickname, String hostProfileImg, int curUser,Boolean isJoin) {
		this.id = id;
		this.subject = subject;
		this.limitUser = limitUser;
		this.deleteTime = deleteTime;
		this.isPrivate = isPrivate;
		this.imgUrl = imgUrl;
		this.description = description;
		this.hostId = hostId;
		this.hostNickname = hostNickname;
		this.hostProfileImg = hostProfileImg;
		this.curUser = curUser;

	}
	
}
