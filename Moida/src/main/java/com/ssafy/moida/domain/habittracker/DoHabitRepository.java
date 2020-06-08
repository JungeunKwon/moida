package com.ssafy.moida.domain.habittracker;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moida.domain.account.Account;

public interface DoHabitRepository extends JpaRepository<DoHabit, Long>{
	
	public List<DoHabit> findByHabittrackerIdAndAccountId(Long habitid,Long accountid);
	
	public int countByAccountAndCleardateAndHabittracker(Account account, String clearDate, Habittracker habit); 
	Optional<DoHabit> findByAccountAndCleardateAndHabittracker(Account account, String clearDate, Habittracker habit); 
	
}
