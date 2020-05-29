package com.ssafy.moida.domain.diary;


import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.group.GroupTB;

public interface DiaryRepository extends JpaRepository<Diary, Long>{
	
	Page<Diary> findByDescriptionAnddeleteDateIsNull(String description,Pageable pageable);
	Page<Diary> findByAccountAnddeleteDateIsNull(Account account,Pageable pageable);
	Page<Diary> findByMoodAnddeleteDateIsNull(String mood,Pageable pageable);
	Page<Diary> findByGroupTBAnddeleteDateIsNull(GroupTB groupTB,Pageable pageable);
	
	
	Page<Diary> findByGroupTBAndCreateDateLessThanAndCreateDateGreaterThanAnddeleteDateIsNull(GroupTB group, LocalDateTime dateplus1day, LocalDateTime datetime,Pageable pageable);
	
}
