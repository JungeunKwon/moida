package com.ssafy.moida.service.diary;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.moida.component.UploadS3;
import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.account.AccountRepository;
import com.ssafy.moida.domain.diary.Diary;
import com.ssafy.moida.domain.diary.DiaryLikeRepository;
import com.ssafy.moida.domain.diary.DiaryLikes;
import com.ssafy.moida.domain.diary.DiaryRepository;
import com.ssafy.moida.domain.group.GroupTB;
import com.ssafy.moida.domain.group.GroupTBRepository;
import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.exception.EnumAccountException;
import com.ssafy.moida.service.account.AccountService;
import com.ssafy.moida.web.dto.diary.DiaryFindByGroupDayRequest;
import com.ssafy.moida.web.dto.diary.DiaryLikeSaveRequestDTO;
import com.ssafy.moida.web.dto.diary.DiaryResponseDTO;
import com.ssafy.moida.web.dto.diary.DiarySaveRequest;
import com.ssafy.moida.web.dto.diary.DiaryUpdateRequest;
import com.ssafy.moida.web.dto.diary.UploadFileDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DiaryServiceImpl implements DiaryService{
	
	private final DiaryRepository diaryRepository;
	private final AccountService accountService;
	private final AccountRepository accountRepository;
	private final GroupTBRepository groupTBRepository;
	private final DiaryLikeRepository diaryLikeRepository;
	private final UploadS3 uploadS3;
	
	
	public List<DiaryResponseDTO> makelikecount(List<Diary> diarylist) throws NumberFormatException, BaseException{
		
		Account account = accountService.getAccount();
		List<DiaryResponseDTO> list = diarylist.stream()
				.map(DiaryResponseDTO::new)
				.collect(Collectors.toList());
	
		
		List<DiaryLikes> diarylikelist;
		for(int i=0;i<list.size();i++) {
			diarylikelist = diarylist.get(i).getDiarylikelist();
			
			for(DiaryLikes d : diarylikelist) {
				list.get(i).setIsLike(false);
				if(d.getAccount().getId() == account.getId() && d.getDiary().getId() == list.get(i).getId()) {
					list.get(i).setIsLike(true);
				}
			}
		}
		
		return list;
		
	}
	
	
	@Transactional(readOnly = true)
	public List<DiaryResponseDTO> findAll(Pageable pageable) throws NumberFormatException, BaseException {
		
		List<Diary> diarylist = diaryRepository.find(accountService.getAccount().getId());
		
	
		
		return makelikecount(diarylist);
	}

	@Transactional
	public Long saveDiary(DiarySaveRequest dto) throws NumberFormatException, BaseException {
		dto.setAccount(accountService.getAccount());
		if(dto.getGroupid()!=null) {
			
			dto.setGroupTB(groupTBRepository.findById(dto.getGroupid()).get());
		}
		dto.setViewcount(0L);
		return diaryRepository.save(dto.toEntity()).getId();
	}

	@Transactional(readOnly = true)
	public Page<DiaryResponseDTO> findByGroupTB(Long id, Pageable pageable) {
		GroupTB group = groupTBRepository.findById(id).get();
		return diaryRepository.findByGroupTBAndDeleteDateIsNull(group, pageable)
				.map(DiaryResponseDTO::new);
	}

	@Transactional
	public DiaryResponseDTO updateinfo(DiaryUpdateRequest dto) {
		Diary diary = diaryRepository.findById(dto.getId()).get();
		diary.updateDiaryinfo(dto.getDescription(), dto.getMood(), dto.getImgurl(),dto.getIsPrivate(),dto.getInputDate());
		return DiaryResponseDTO.builder().diary(diary).build();
	}

	@Transactional
	public Long deleteDiary(Long id) {
		Diary diary = diaryRepository.findById(id).get();
		diary.deleteDiary(LocalDateTime.now());
		return id;
	}

	@Transactional(readOnly = true)
	public Page<DiaryResponseDTO> findByDescriptionAndBydeleteDateIsNull(String description, Pageable pageable) {
		return diaryRepository.findByDescriptionAndDeleteDateIsNull(description, pageable)
				.map(DiaryResponseDTO::new);
	}

	@Transactional(readOnly = true)
	public Page<DiaryResponseDTO> findByAccountAndBydeleteDateIsNull(Account account, Pageable pageable) {
		return diaryRepository.findByAccountAndDeleteDateIsNull(account, pageable)
				.map(DiaryResponseDTO::new);
	}

	@Transactional(readOnly = true)
	public Page<DiaryResponseDTO> findByMoodAndBydeleteDateIsNull(String mood, Pageable pageable) {
		return diaryRepository.findByMoodAndDeleteDateIsNull(mood, pageable)
				.map(DiaryResponseDTO::new);
	}

	@Transactional(readOnly = true)
	public Page<DiaryResponseDTO> findByDay( String datetime, Long groupid, Pageable pageable) {
		int year = Integer.parseInt(datetime.split("-")[0]);
		int month = Integer.parseInt(datetime.split("-")[1]);
		int day = Integer.parseInt(datetime.split("-")[2]);
		
		LocalDateTime date = LocalDateTime.now();
		date = date.withYear(year).withMonth(month).withDayOfMonth(day).withHour(0).withMinute(0).withSecond(0).withNano(0);
		
		
		GroupTB group = groupTBRepository.findById(groupid).get();
		System.out.println(date);
		return diaryRepository.findByGroupTBAndCreateDateLessThanAndCreateDateGreaterThanAndDeleteDateIsNull(group,date.plusDays(1),date, pageable)
				.map(DiaryResponseDTO::new);
	}

	@Transactional
	public String uploadImg2S3(MultipartFile uploadFile) throws NumberFormatException, IllegalArgumentException, IOException, BaseException {
		String uploadImgUrl;
		uploadImgUrl = uploadS3.uploadFile(uploadFile, "diary/" + accountService.getAccount().getEmail());
		return uploadImgUrl;
	}
	@Transactional
	public DiaryResponseDTO findById(Long diaryid) throws NumberFormatException, BaseException {
		Account account = accountService.getAccount();
		Diary diary = diaryRepository.findById(diaryid).get();
		diary.updateViewCount();
		
		DiaryResponseDTO responseDTO = DiaryResponseDTO.builder().diary(diary).build();
		Boolean isLike = false;
		
		if(0<diaryLikeRepository.countByDiaryAndAccount(diary, account)) {
			isLike = true;
		}
		
		responseDTO.setIsLike(isLike);
		
		return responseDTO;
	}

	@Transactional(readOnly = true)
	public Page<DiaryResponseDTO> findByNickname(String nickname, Pageable pageable) {
		
		Account account = accountRepository.findByNickname(nickname).get();
		
		return diaryRepository.findByAccount(account, pageable).map(DiaryResponseDTO::new);
	}

	@Transactional
	public Long likeDiary(Long diaryid) throws NumberFormatException, BaseException {
		Account account = accountService.getAccount();
		DiaryLikeSaveRequestDTO requestDTO = new DiaryLikeSaveRequestDTO();
		requestDTO.setAccount(account);
		requestDTO.setDiary(diaryRepository.findById(diaryid).get());
		Long id = 0L;
		if(diaryLikeRepository.countByDiaryAndAccount(requestDTO.getDiary(), account) == 0 ) {
			id = diaryLikeRepository.save(requestDTO.toEntity()).getId();
		}
		
	

		
		return diaryLikeRepository.countByDiary(requestDTO.getDiary());
	}

	@Transactional
	public Long deletelikeDiary(Long diaryid) throws NumberFormatException, BaseException {
		Account account = accountService.getAccount();
		Diary diary = diaryRepository.findById(diaryid).get();
		
		DiaryLikes deleteid = diaryLikeRepository.findByDiaryAndAccount(diary, account);
		diaryLikeRepository.deleteById(deleteid.getId());
		
		
		return diaryLikeRepository.countByDiary(diary);
	}


}
