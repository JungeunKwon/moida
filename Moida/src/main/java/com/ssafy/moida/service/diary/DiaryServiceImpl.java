package com.ssafy.moida.service.diary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.diary.Diary;
import com.ssafy.moida.domain.diary.DiaryRepository;
import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.service.account.AccountService;
import com.ssafy.moida.web.dto.diary.DiaryFindByGroupRequest;
import com.ssafy.moida.web.dto.diary.DiaryResponseDTO;
import com.ssafy.moida.web.dto.diary.DiarySaveRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DiaryServiceImpl implements DiaryService{
	
	private final DiaryRepository diaryRepository;
	private final AccountService accountService;

	@Transactional
	public Long saveDiary(DiarySaveRequest dto) throws NumberFormatException, BaseException {
		dto.setAccount(accountService.getAccount());
		return diaryRepository.save(dto.toEntity()).getId();
	}

	@Transactional(readOnly = true)
	public Page<DiaryResponseDTO> findByGroupTB(DiaryFindByGroupRequest dto) {
		return diaryRepository.findByGroupTBAndDeletedateIsNull(dto.getGroup(), dto.getPageable())
				.map(DiaryResponseDTO::new);
	}

	@Transactional
	public Long updateinfo(DiaryFindByGroupRequest dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public Long deleteDiary(DiaryFindByGroupRequest dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	public Page<DiaryResponseDTO> findByDescriptionAndByDeletedateIsNull(String description, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	public Page<DiaryResponseDTO> findByAccountAndByDeletedateIsNull(Account account, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	public Page<DiaryResponseDTO> findByMoodAndByDeletedateIsNull(String mood, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	public Page<DiaryResponseDTO> findByMonth(String year, String month, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
