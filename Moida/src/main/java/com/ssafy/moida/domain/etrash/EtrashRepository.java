package com.ssafy.moida.domain.etrash;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moida.domain.etrash.Etrash;



public interface EtrashRepository extends JpaRepository<Etrash, Long>{
	
	
	Page<Etrash> findByMoodAnddeleteDateGreaterThan(String mood,LocalDateTime now, Pageable pageable);
	Page<Etrash> findAllBydeleteDateGreaterThan(LocalDateTime deleteDate, Pageable pageable);
	
}
