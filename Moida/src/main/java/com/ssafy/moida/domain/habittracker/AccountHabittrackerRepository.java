package com.ssafy.moida.domain.habittracker;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moida.domain.account.Account;



public interface AccountHabittrackerRepository extends JpaRepository<AccountHabittracker, Long>{
	public int countByAccountAndHabittracker(Account account,Habittracker habit);
	
}
