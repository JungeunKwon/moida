package com.ssafy.moida.web.dto.diary;

import org.springframework.data.domain.Pageable;

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
	Pageable pageable;
	
	@Builder
	public DiaryFindByGroupRequest(GroupTB group,Pageable pageable) {
		super();
		this.group = group;
		this.pageable = pageable;
	}
	
	

}
