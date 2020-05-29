package com.ssafy.moida.service.habittracker;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.moida.domain.habittracker.Habittracker;
import com.ssafy.moida.domain.habittracker.HabittrackerRepository;
import com.ssafy.moida.web.dto.habittracker.HabittrackerResponseDTO;
import com.ssafy.moida.web.dto.habittracker.HabittrackerSaveRequestDTO;
import com.ssafy.moida.web.dto.habittracker.HabittrackerUpdateRequestDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HabittrackerServiceImpl implements HabittrackerService{
	
	private final HabittrackerRepository habittrackerRepository;

	@Transactional
	public Long saveHabittracker(HabittrackerSaveRequestDTO requestDto) {
		return habittrackerRepository.save(requestDto.toEntity()).getId();
	}

	@Transactional
	public HabittrackerResponseDTO updateinfo(HabittrackerUpdateRequestDTO requestDTO) {
		habittrackerRepository.findById(requestDTO.getId()).get()
		.updateinfo(requestDTO.getSubject(), requestDTO.getDescription(),
				 requestDTO.getStartDate(), requestDTO.getEndDate());

		return null;
	}
	
	@Transactional
	public HabittrackerResponseDTO findById(Long id){
		Habittracker habit = habittrackerRepository.findById(id).get();
			
		return HabittrackerResponseDTO.builder()
				.id(habit.getId())
				.subject(habit.getSubject())
				.description(habit.getDescription())
				.startDate(habit.getStartDate())
				.endDate(habit.getEndDate())
				.account(habit.getAccount())
				.groupTB(habit.getGroupTB())
				.build();
	}
	
	@Transactional
	public Long deleteHabittracker(Long habitid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public Long joinHabittracker(Long accountid, Long habitid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public Long leaveHabittracker(Long accountid, Long habitid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public Long clearHabittracker(Long accountid, Long habitid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<HabittrackerResponseDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<HabittrackerResponseDTO> findByGroupTBAll(Long groupid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<HabittrackerResponseDTO> findByGroupTB(Long groupid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<HabittrackerResponseDTO> findByGroupTBAndAccount(Long groupid, Long accountid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<HabittrackerResponseDTO> findByAccount(Long accountid) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
