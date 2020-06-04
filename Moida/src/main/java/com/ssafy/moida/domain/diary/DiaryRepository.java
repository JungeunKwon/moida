package com.ssafy.moida.domain.diary;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.group.GroupTB;

public interface DiaryRepository extends JpaRepository<Diary, Long>{
	
	@Query(value = "SELECT DISTINCT  diary.* FROM diary inner JOIN follow WHERE ISNULL(diary.grouptb_id) AND ( diary.is_private = 1 OR ( diary.is_private = 2 AND (SELECT COUNT(*) FROM follow WHERE follow.following_id =  diary.account_id AND follow.follower_id = ?1) > 0 AND (SELECT COUNT(*) FROM follow WHERE follow.following_id =  ?1 AND follow.follower_id = diary.account_id) > 0)   OR diary.account_id = ?1 )  ", nativeQuery = true)
	List<Diary> find(Long accountid);
	
	Page<Diary> findByAccount(Account account,Pageable pageable);
	
	Page<Diary> findByDescriptionAndDeleteDateIsNull(String description,Pageable pageable);
	Page<Diary> findByAccountAndDeleteDateIsNull(Account account,Pageable pageable);
	Page<Diary> findByMoodAndDeleteDateIsNull(String mood,Pageable pageable);
	Page<Diary> findByGroupTBAndDeleteDateIsNull(GroupTB groupTB,Pageable pageable);
	
	
	Page<Diary> findByGroupTBAndCreateDateLessThanAndCreateDateGreaterThanAndDeleteDateIsNull(GroupTB group, LocalDateTime dateplus1day, LocalDateTime datetime,Pageable pageable);
	
}
