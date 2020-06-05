package com.ssafy.moida.web;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.service.account.AccountService;
import com.ssafy.moida.service.comment.CommentService;
import com.ssafy.moida.web.dto.comment.CommentResponseDTO;
import com.ssafy.moida.web.dto.comment.CommentSaveRequestDTO;
import com.ssafy.moida.web.dto.comment.CommentUpdateRequestDTO;
import com.ssafy.moida.web.dto.diary.DiarySaveRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = {"9. comment"})
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class CommentController {
	
	private final CommentService commentService;
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "댓글생성", httpMethod = "POST", notes = "댓글 작성하는 부분입니다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@PostMapping(value = "/comment")
	public ResponseEntity<Long> saveComment(@RequestBody CommentSaveRequestDTO requestDTO
			) throws IllegalArgumentException, IOException, BaseException{
	
		return new ResponseEntity<Long>(commentService.saveComment(requestDTO) , HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "댓글 diaryid로 찾기", httpMethod = "GET", notes = "댓글 찾는 부분입니다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@GetMapping(value = "/comment/{diaryid}")
	public ResponseEntity<List<CommentResponseDTO>> findComment(@PathVariable Long diaryid
			) throws IllegalArgumentException, IOException, BaseException{
	
		return new ResponseEntity<List<CommentResponseDTO>>(commentService.findByDiaryId(diaryid) , HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "댓글수정", httpMethod = "PUT", notes = "id랑  description만 보내면됨")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@PutMapping(value = "/comment")
	public ResponseEntity<CommentResponseDTO> updateComment(@RequestBody CommentUpdateRequestDTO requestDTO
			) throws IllegalArgumentException, IOException, BaseException{
	
		return new ResponseEntity<CommentResponseDTO>(commentService.updateComment(requestDTO), HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "댓글 id로 삭제", httpMethod = "DELETE", notes = "댓글 삭제 부분입니다.")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") 
	@DeleteMapping(value = "/comment/{commentid}")
	public ResponseEntity<Boolean> deleteComment(@PathVariable Long commentid
			) throws IllegalArgumentException, IOException, BaseException{
	
		return new ResponseEntity<Boolean>(commentService.deleteComment(commentid) , HttpStatus.OK);
	}
}
