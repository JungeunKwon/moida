package com.ssafy.moida.service.habittracker;


import java.util.List;

import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.web.dto.habittracker.AccountHabittrackerSaveDTO;
import com.ssafy.moida.web.dto.habittracker.HabittrackerResponseDTO;
import com.ssafy.moida.web.dto.habittracker.HabittrackerSaveRequestDTO;
import com.ssafy.moida.web.dto.habittracker.HabittrackerUpdateRequestDTO;

public interface HabittrackerService {
	
	Long saveHabittracker(HabittrackerSaveRequestDTO requestDto);
	Long updateinfo(HabittrackerUpdateRequestDTO requestDTO);
	Long deleteHabittracker(Long habitid) throws NumberFormatException, BaseException;
	
	Long joinHabittracker(AccountHabittrackerSaveDTO requestDTO) throws NumberFormatException, BaseException;
	void leaveHabittracker(AccountHabittrackerSaveDTO requestDTO) throws NumberFormatException, BaseException;
	Long clearHabittracker(AccountHabittrackerSaveDTO requestDTO);
	
	List<HabittrackerResponseDTO> findAll();
	HabittrackerResponseDTO findById(Long id);
	List<HabittrackerResponseDTO> findByGroupTBAll(Long groupid);
	List<HabittrackerResponseDTO> findByGroupTB(Long groupid);
	List<HabittrackerResponseDTO> findByGroupTBAndAccount(AccountHabittrackerSaveDTO requestDTO);
	List<HabittrackerResponseDTO> findByAccount(Long accountid);
	
}
