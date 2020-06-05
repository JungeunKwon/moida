package com.ssafy.moida.web.dto.habittracker;



import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.group.GroupTB;
import com.ssafy.moida.domain.habittracker.AccountHabittracker;
import com.ssafy.moida.domain.habittracker.Habittracker;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AccountHabittrackerSaveDTO {


	private Long habitid;
	private Account account;
	private Habittracker habit;
	
	
	public AccountHabittracker toEntity() {
		return AccountHabittracker.builder()
				.account(account)
				.habittracker(habit)
				.build();
	}
	
	@Builder
	public AccountHabittrackerSaveDTO( Long habitid,Account account, Habittracker habit) {
		super();
		this.habitid = habitid;
		this.account = account;
		this.habit = habit;
		
	}
	
	
	

	

}
