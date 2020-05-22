package com.ssafy.moida.domain.etrash;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moida.domain.etrash.Etrash;
import com.ssafy.moida.web.dto.etrash.EtrashAllRequestDTO;


public interface EtrashRepository extends JpaRepository<Etrash, Long>{
	
	
	List<Etrash> findByMoodLike(String mood);
	
}
