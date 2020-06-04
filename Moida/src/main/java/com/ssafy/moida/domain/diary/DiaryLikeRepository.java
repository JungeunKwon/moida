package com.ssafy.moida.domain.diary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moida.domain.account.Account;

public interface DiaryLikeRepository extends JpaRepository<DiaryLikes, Long>{


	DiaryLikes findByDiaryAndAccount(Diary diary, Account account);
	
}
