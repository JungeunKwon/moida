package com.ssafy.moida.domain.habittracker;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoHabitRepository extends JpaRepository<DoHabit, Long>{
	
	List<DoHabit> findByHabittrackerIdAndAccountId(Long habitid,Long accountid);

}
