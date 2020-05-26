package com.ssafy.moida.web.dto.diary;

import com.ssafy.moida.domain.group.GroupTB;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class DiaryFindByMonthGroupRequest {
	GroupTB group;
	String year;
	String month;
	
	@Builder
	public DiaryFindByMonthGroupRequest(GroupTB group, String year, String month) {
		super();
		this.group = group;
		this.year = year;
		this.month = month;
	}
	
	
}
