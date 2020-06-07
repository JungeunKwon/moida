package com.ssafy.moida.web.dto.habittracker;



import com.ssafy.moida.domain.habittracker.DoHabit;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DohabitResponseDTO {

	private Long id;
	private String cleardate;
	private String description;

	
	public DohabitResponseDTO(DoHabit dohabit) {
		super();
		this.id = dohabit.getId();
		this.cleardate = dohabit.getCleardate();
		this.description = dohabit.getDescription();
		
	}
	
	

	
}
