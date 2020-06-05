package com.ssafy.moida.web.dto.habittracker;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.habittracker.DoHabit;
import com.ssafy.moida.domain.habittracker.Habittracker;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DohabitSaveRequestDTO {
	
	private Long habitid;
	private String description;
	private String clearate;
	
	private Account account;
	private Habittracker habittracker;
	
	public DoHabit toEntity() {
		return DoHabit.builder()
				.account(account)
				.cleardate(clearate)
				.habittracker(habittracker)
				.description(description)
				.build();
		
	}
	
	@Builder
	public DohabitSaveRequestDTO(Long habitid, String description, String date) {
		super();
		this.habitid = habitid;
		this.description = description;
		this.clearate = date;
	}
	
	
	

}
