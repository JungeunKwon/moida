package com.ssafy.moida.web.dto.habittracker;

import java.time.LocalDateTime;

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
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	
	@Builder
	public HabittrackerUpdateRequestDTO(Long id, String subject, String description, LocalDateTime startDate,
			LocalDateTime endDate) {
		super();
		this.id = id;
		this.subject = subject;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	
	
}
