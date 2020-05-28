package com.ssafy.moida.web.dto.etrash;


import org.springframework.data.domain.Pageable;

import com.ssafy.moida.domain.common.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EtrashAllRequestDTO extends BaseEntity{

	private Pageable pageable;
	private String mood;
	

	
	@Builder
	public EtrashAllRequestDTO(Pageable pageable, String mood ) {

		this.pageable = pageable;
		this.mood = mood;
	}
	
	
	

}
