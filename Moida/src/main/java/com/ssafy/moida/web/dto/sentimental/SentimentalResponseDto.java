package com.ssafy.moida.web.dto.sentimental;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SentimentalResponseDto {
	private String sentimental;
	private double score;
	private int score2;
	
	@Builder
	public SentimentalResponseDto(String sentimental, double score, int score2) {
		super();
		this.sentimental = sentimental;
		this.score = score;
		this.score2 = score2;
	}
	
}
