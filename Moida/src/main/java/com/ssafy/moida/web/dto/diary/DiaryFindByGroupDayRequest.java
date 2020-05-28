package com.ssafy.moida.web.dto.diary;



import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiaryFindByGroupDayRequest {
	private Long groupid;
	private String year;
	private String month;
	private String day;
	
	@Builder
	public DiaryFindByGroupDayRequest(Long groupid, String year, String month, String day) {
		super();
		this.groupid = groupid;
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	

}
