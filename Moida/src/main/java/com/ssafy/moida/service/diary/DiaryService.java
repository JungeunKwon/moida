package com.ssafy.moida.service.diary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.diary.Diary;
import com.ssafy.moida.web.dto.diary.DiaryFindByGroupRequest;
import com.ssafy.moida.web.dto.diary.DiarySaveRequest;

public interface DiaryService {
	
	Long saveDiary(DiarySaveRequest dto);
	Long findByGroupTB(DiaryFindByGroupRequest dto);
	Long updateinfo(DiaryFindByGroupRequest dto);
	Long deleteDiary(DiaryFindByGroupRequest dto);
	
	Page<Diary> findByDescriptionAndByDeletedateIsNull(String description,Pageable pageable);
	Page<Diary> findByAccountAndByDeletedateIsNull(Account account,Pageable pageable);
	Page<Diary> findByMoodAndByDeletedateIsNull(String mood,Pageable pageable);
	Page<Diary> findByMonth(String year,String month,Pageable pageable);
}
