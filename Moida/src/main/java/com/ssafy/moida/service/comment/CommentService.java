package com.ssafy.moida.service.comment;

import java.util.List;

import com.ssafy.moida.domain.comment.Comment;
import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.web.dto.comment.CommentResponseDTO;
import com.ssafy.moida.web.dto.comment.CommentSaveRequestDTO;
import com.ssafy.moida.web.dto.comment.CommentUpdateRequestDTO;

public interface CommentService {
	
	public Long saveComment(CommentSaveRequestDTO requestDTO) throws NumberFormatException, BaseException;
	public List<CommentResponseDTO> findByDiaryId(Long diaryid);
	public CommentResponseDTO updateComment(CommentUpdateRequestDTO requestDTO);
	public Boolean deleteComment(Long commentid);
	
	
}
