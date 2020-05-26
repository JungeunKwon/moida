package com.ssafy.moida.domain.diary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;

import com.ssafy.moida.domain.account.Account;

public interface DiaryRepository {
	
	Page<Diary> findByDescription(String description,Pageable pageable);
	Page<Diary> findByAccount(Account account,Pageable pageable);
	Page<Diary> findByMood(String mood,Pageable pageable);
	
	@Query("select m from diary where substring(m.createDate,1,4) = ?1 AND substring(m.createDate,6,2) = ?2")
	Page<Diary> findByMonth(String year,String month,Pageable pageable);
}
