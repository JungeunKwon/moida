package com.ssafy.moida.service.diary;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.diary.Diary;
import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.web.dto.diary.DiaryFindByGroupDayRequest;
import com.ssafy.moida.web.dto.diary.DiaryFindByGroupRequest;
import com.ssafy.moida.web.dto.diary.DiaryResponseDTO;
import com.ssafy.moida.web.dto.diary.DiarySaveRequest;
import com.ssafy.moida.web.dto.diary.DiaryUpdateRequest;
import com.ssafy.moida.web.dto.diary.UploadFileDto;

public interface DiaryService {
	
	
	DiaryResponseDTO findById(Long diaryid) throws NumberFormatException, BaseException;
	
	Long likeDiary(Long diaryid) throws NumberFormatException, BaseException;
	Long deletelikeDiary(Long diaryid) throws NumberFormatException, BaseException;
	
	Long saveDiary(DiarySaveRequest dto) throws NumberFormatException, BaseException;
	Page<DiaryResponseDTO> findByGroupTB(Long id , Pageable pagealbe);
	DiaryResponseDTO updateinfo(DiaryUpdateRequest dto);
	Long deleteDiary(Long id) throws NumberFormatException, BaseException;
	
	
	Page<DiaryResponseDTO> findByNickname(String nickname,Pageable pageable);
	
	List<DiaryResponseDTO> findAll(Pageable pageable) throws NumberFormatException, BaseException;
	Page<DiaryResponseDTO> findByDescriptionAndBydeleteDateIsNull(String description,Pageable pageable);
	Page<DiaryResponseDTO> findByAccountAndBydeleteDateIsNull(Account account,Pageable pageable);
	Page<DiaryResponseDTO> findByMoodAndBydeleteDateIsNull(String mood,Pageable pageable);
	Page<DiaryResponseDTO> findByDay( String datetime, Long groupid ,Pageable pageable);
	String uploadImg2S3(MultipartFile uploadFile) throws NumberFormatException, IllegalArgumentException, IOException, BaseException;
}
