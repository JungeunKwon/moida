package com.ssafy.moida.web.dto.etrash;


import org.springframework.data.domain.Pageable;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EtrashAllRequestDTO {

	private Pageable pageable;
	

	
	@Builder
	public EtrashAllRequestDTO(Pageable pageable ) {

		this.pageable = pageable;
	}
	
	
	

}
