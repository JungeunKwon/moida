package com.ssafy.moida.web.dto.etrash;

import com.ssafy.moida.domain.etrash.Etrash;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EtrashDescriptionDTO {
	private String description;
	
	public Etrash toEntity() {
		return Etrash.builder().description(description).build();
	}
	

	public EtrashDescriptionDTO(Etrash entity) {
		this.description = entity.getDescription();
	}
	
	
}
