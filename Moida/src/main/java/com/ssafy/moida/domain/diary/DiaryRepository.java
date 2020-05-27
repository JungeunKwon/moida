package com.ssafy.moida.domain.diary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;

import com.ssafy.moida.domain.account.Account;

public interface DiaryRepository {
	
	Page<Diary> findByDescriptionAndByDeletedateIsNull(String description,Pageable pageable);
	Page<Diary> findByAccountAndByDeletedateIsNull(Account account,Pageable pageable);
	Page<Diary> findByMoodAndByDeletedateIsNull(String mood,Pageable pageable);
	
	@Query("select * from diary where substring(createDate,1,4) = ?1 AND substring(createDate,6,2) = ?2 AND deletedate = NULL")
	Page<Diary> findByMonth(String year,String month,Pageable pageable);
	
}
