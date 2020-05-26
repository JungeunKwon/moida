package com.ssafy.moida.domain.diary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ssafy.moida.domain.account.Account;

public interface DiaryRepository {
	
	Page<Diary> findByDescription(String description,Pageable pageable);
}
