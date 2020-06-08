package com.ssafy.moida.service.comment;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moida.component.UploadS3;
import com.ssafy.moida.domain.account.AccountRepository;
import com.ssafy.moida.domain.comment.Comment;
import com.ssafy.moida.domain.comment.CommentRepository;
import com.ssafy.moida.domain.diary.Diary;
import com.ssafy.moida.domain.diary.DiaryLikeRepository;
import com.ssafy.moida.domain.diary.DiaryRepository;
import com.ssafy.moida.domain.group.GroupTBRepository;
import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.service.account.AccountService;
import com.ssafy.moida.web.dto.comment.CommentResponseDTO;
import com.ssafy.moida.web.dto.comment.CommentSaveRequestDTO;
import com.ssafy.moida.web.dto.comment.CommentUpdateRequestDTO;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{
	
	private final CommentRepository commentRepository;
	private final AccountService accountService;
	private final DiaryRepository diaryRepository;
	
	@Transactional
	public Long saveComment(CommentSaveRequestDTO requestDTO) throws NumberFormatException, BaseException {
		requestDTO.setAccount(accountService.getAccount());
		requestDTO.setDiary(diaryRepository.findById(requestDTO.getDiaryid()).get());
		return commentRepository.save(requestDTO.toEntity()).getId();
	}

	@Transactional(readOnly=true)
	public List<CommentResponseDTO> findByDiaryId(Long diaryid) {
		Diary diary = diaryRepository.findById(diaryid).get();
		
		return commentRepository.findByDiary(diary).stream()
				.map(CommentResponseDTO :: new)
				.collect(Collectors.toList());
	}

	@Transactional
	public CommentResponseDTO updateComment(CommentUpdateRequestDTO requestDTO) {
		Comment comment = commentRepository.findById(requestDTO.getId()).get();
		comment.updateinfo(requestDTO.getDescription());
		return CommentResponseDTO.builder().comment(comment).build();
	}

	@Transactional
	public Boolean deleteComment(Long commentid) {
		Comment comment = commentRepository.findById(commentid).get();
		comment.delete();
		return true;
	}

}
