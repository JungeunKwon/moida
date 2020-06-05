package com.ssafy.moida.domain.habittracker;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.group.GroupTB;
import com.ssafy.moida.web.dto.habittracker.HabittrackerResponseDTO;

public interface HabittrackerRepository extends JpaRepository<Habittracker, Long>{
	List<Habittracker> findByGroupTB(Long groupid);
	List<Habittracker> findByGroupTBAndStartDateLessThanAndEndDateGreaterThan(GroupTB group,LocalDateTime now,LocalDateTime now2);
	
	@Query(value="SELECT DISTINCT habittracker.* , account_habittracker.account_id\r\n" + 
			"FROM habittracker\r\n" + 
			"LEFT OUTER JOIN account_habittracker ON account_habittracker.habittracker_id = habittracker.id\r\n" + 
			"WHERE grouptb_id = ?1 AND account_habittracker.account_id = ?2",nativeQuery=true)
	List<Habittracker> findByGroupTBAndAccountAndStartDateLessThanAndEndDateGreaterThan(GroupTB group,Account account,LocalDateTime now,LocalDateTime now2);
	List<Habittracker> findByAccount(Account account);
	
	

}
