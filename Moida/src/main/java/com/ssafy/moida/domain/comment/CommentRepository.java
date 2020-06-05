package com.ssafy.moida.domain.comment;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moida.domain.diary.Diary;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	public List<Comment> findByDiaryAndDeleteDateIsNull(Diary diary);

}
