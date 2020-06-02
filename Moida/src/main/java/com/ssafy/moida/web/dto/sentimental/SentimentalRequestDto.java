package com.ssafy.moida.web.dto.sentimental;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SentimentalRequestDto {
	private String description;

	@Builder
	public SentimentalRequestDto(String description) {
		this.description = description;
	}
	
	
}
