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
public class DiaryFindByGroupRequest {
	GroupTB group;
	
	@Builder
	public DiaryFindByGroupRequest(GroupTB group) {
		super();
		this.group = group;
	}
	
	

}
