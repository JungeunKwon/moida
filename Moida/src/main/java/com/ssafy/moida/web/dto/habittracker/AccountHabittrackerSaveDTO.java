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
	
	
	public AccountHabittracker toEntity(Account account,Habittracker habit) {
		return AccountHabittracker.builder()
				.account(account)
				.habittracker(habit)
				.build();
	}
	
	@Builder
	public AccountHabittrackerSaveDTO( Long habitid) {
		super();
		this.habitid = habitid;
	}
	
	
	

	

}
