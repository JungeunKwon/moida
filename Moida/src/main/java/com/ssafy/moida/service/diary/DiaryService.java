package com.ssafy.moida.service.diary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.diary.Diary;
import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.web.dto.diary.DiaryFindByGroupDayRequest;
import com.ssafy.moida.web.dto.diary.DiaryFindByGroupRequest;
import com.ssafy.moida.web.dto.diary.DiaryResponseDTO;
import com.ssafy.moida.web.dto.diary.DiarySaveRequest;
import com.ssafy.moida.web.dto.diary.DiaryUpdateRequest;

public interface DiaryService {
	
	Long saveDiary(DiarySaveRequest dto) throws NumberFormatException, BaseException;
	Page<DiaryResponseDTO> findByGroupTB(Long id , Pageable pagealbe);
	DiaryResponseDTO updateinfo(DiaryUpdateRequest dto);
	Long deleteDiary(Long id);
	
	Page<DiaryResponseDTO> findAll(Pageable pageable);
	
	Page<DiaryResponseDTO> findByDescriptionAndByDeletedateIsNull(String description,Pageable pageable);
	Page<DiaryResponseDTO> findByAccountAndByDeletedateIsNull(Account account,Pageable pageable);
	Page<DiaryResponseDTO> findByMoodAndByDeletedateIsNull(String mood,Pageable pageable);
	Page<DiaryResponseDTO> findByMonth(DiaryFindByGroupDayRequest dto,Pageable pageable);
}
