package com.ssafy.moida.service.habittracker;


import java.util.List;

import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.web.dto.habittracker.AccountHabittrackerSaveDTO;
import com.ssafy.moida.web.dto.habittracker.HabittrackerResponseDTO;
import com.ssafy.moida.web.dto.habittracker.HabittrackerSaveRequestDTO;
import com.ssafy.moida.web.dto.habittracker.HabittrackerUpdateRequestDTO;

public interface HabittrackerService {
	
	Long saveHabittracker(HabittrackerSaveRequestDTO requestDto) throws NumberFormatException, BaseException;
	Long updateinfo(HabittrackerUpdateRequestDTO requestDTO);
	Long deleteHabittracker(Long habitid) throws NumberFormatException, BaseException;
	
	Long joinHabittracker(AccountHabittrackerSaveDTO requestDTO) throws NumberFormatException, BaseException;
	Long leaveHabittracker(AccountHabittrackerSaveDTO requestDTO) throws NumberFormatException, BaseException;
	Long clearHabittracker(AccountHabittrackerSaveDTO requestDTO);
	
	HabittrackerResponseDTO findById(Long id);
	
	List<HabittrackerResponseDTO> findAll();
	List<HabittrackerResponseDTO> findByGroupTBAll(Long groupid);
	List<HabittrackerResponseDTO> findByGroupTB(Long groupid);
	List<HabittrackerResponseDTO> findByGroupTBAndAccount(AccountHabittrackerSaveDTO requestDTO);
	List<HabittrackerResponseDTO> findByAccount(Long accountid);

//	해빗 CRUD
	
//	해빗 가입하기(내정보 , 해빗정보)
//	해빗 탈퇴하기(내정보, 해빗정보)
//	해빗 완료하기(내정보, 해빗정보, 년월일 LocalDateTime) 

//	모든 해빗 불러오기
//	공다내의 모든해빗 불러오기 (공다)
//	공다내의 진행중 모든해빗 불러오기 (공다)  start~end 사이
//	공다내의 내가 가입한 진행중 모든해빗 불러오기 (공다 , 내정보) start~end 사이
//	내가 가입한 모든 해빗 불러오기 (내정보)
	
}
