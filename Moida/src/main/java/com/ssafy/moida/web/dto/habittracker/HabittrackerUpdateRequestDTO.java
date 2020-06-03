package com.ssafy.moida.web.dto.habittracker;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HabittrackerUpdateRequestDTO {
	
	private Long id;
	private String subject;
	private String description;
	private String startDate;
	private String endDate;
	
	@Builder
	public HabittrackerUpdateRequestDTO(Long id, String subject, String description, String startDate,
			String endDate) {
		super();
		this.id = id;
		this.subject = subject;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	
	
}
