package com.ssafy.moida.service.habittracker;


import java.util.List;

import com.ssafy.moida.web.dto.habittracker.HabittrackerResponseDTO;
import com.ssafy.moida.web.dto.habittracker.HabittrackerSaveRequestDTO;
import com.ssafy.moida.web.dto.habittracker.HabittrackerUpdateRequestDTO;

public interface HabittrackerService {
	
	Long saveHabittracker(HabittrackerSaveRequestDTO requestDto);
	HabittrackerResponseDTO updateinfo(HabittrackerUpdateRequestDTO requestDTO);
	Long deleteHabittracker(Long habitid);
	
	Long joinHabittracker(Long accountid,Long habitid);
	Long leaveHabittracker(Long accountid,Long habitid);
	Long clearHabittracker(Long accountid,Long habitid);
	
	List<HabittrackerResponseDTO> findAll();
	HabittrackerResponseDTO findById(Long id);
	List<HabittrackerResponseDTO> findByGroupTBAll(Long groupid);
	List<HabittrackerResponseDTO> findByGroupTB(Long groupid);
	List<HabittrackerResponseDTO> findByGroupTBAndAccount(Long groupid, Long accountid);
	List<HabittrackerResponseDTO> findByAccount(Long accountid);
	
}
