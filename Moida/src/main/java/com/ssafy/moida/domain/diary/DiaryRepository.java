package com.ssafy.moida.domain.diary;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.group.GroupTB;

public interface DiaryRepository extends JpaRepository<Diary, Long>{
	
	Page<Diary> findByDescriptionAndDeletedateIsNull(String description,Pageable pageable);
	Page<Diary> findByAccountAndDeletedateIsNull(Account account,Pageable pageable);
	Page<Diary> findByMoodAndDeletedateIsNull(String mood,Pageable pageable);
	Page<Diary> findByGroupTBAndDeletedateIsNull(GroupTB groupTB,Pageable pageable);
	
	@Query("select * from diary where substring(createDate,1,4) = ?1 AND substring(createDate,6,2) = ?2 AND deletedate = NULL")
	Page<Diary> findByMood(String year,String month,Pageable pageable);
	
}
