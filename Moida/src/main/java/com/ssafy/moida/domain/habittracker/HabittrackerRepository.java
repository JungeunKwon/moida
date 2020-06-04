package com.ssafy.moida.domain.habittracker;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moida.web.dto.habittracker.HabittrackerResponseDTO;

public interface HabittrackerRepository extends JpaRepository<Habittracker, Long>{
	List<Habittracker> findByGroupTB(Long groupid);
	List<Habittracker> findByGroupTBAndStartDateLessThanAndEndDateGreaterThan(Long groupid,LocalDateTime now,LocalDateTime now2);
	
	

}
