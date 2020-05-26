package com.ssafy.moida.domain.etrash;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moida.domain.etrash.Etrash;



public interface EtrashRepository extends JpaRepository<Etrash, Long>{
	
	
	Page<Etrash> findByMood(String mood,Pageable pageable);
	
}
