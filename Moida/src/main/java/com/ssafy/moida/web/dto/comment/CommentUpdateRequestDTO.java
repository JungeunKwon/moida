package com.ssafy.moida.web.dto.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentUpdateRequestDTO {
	private Long id;
	private String description;
	
	@Builder
	public CommentUpdateRequestDTO(Long id,String description) {
		super();
		this.id = id;
		this.description = description;
	}
	
	
	
}
