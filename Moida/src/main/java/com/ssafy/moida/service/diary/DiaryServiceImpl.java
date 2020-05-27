package com.ssafy.moida.service.diary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.diary.Diary;
import com.ssafy.moida.web.dto.diary.DiaryFindByGroupRequest;
import com.ssafy.moida.web.dto.diary.DiarySaveRequest;

public class DiaryServiceImpl implements DiaryService{

	@Override
	public Long saveDiary(DiarySaveRequest dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long findByGroupTB(DiaryFindByGroupRequest dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long updateinfo(DiaryFindByGroupRequest dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long deleteDiary(DiaryFindByGroupRequest dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Diary> findByDescriptionAndByDeletedateIsNull(String description, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Diary> findByAccountAndByDeletedateIsNull(Account account, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Diary> findByMoodAndByDeletedateIsNull(String mood, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Diary> findByMonth(String year, String month, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
