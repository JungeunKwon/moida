package com.ssafy.moida.service.habittracker;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.moida.domain.habittracker.AccountHabittracker;
import com.ssafy.moida.domain.habittracker.AccountHabittrackerRepository;
import com.ssafy.moida.domain.habittracker.Habittracker;
import com.ssafy.moida.domain.habittracker.HabittrackerRepository;
import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.exception.EnumHabittrackerException;
import com.ssafy.moida.service.account.AccountService;
import com.ssafy.moida.web.dto.habittracker.AccountHabittrackerSaveDTO;
import com.ssafy.moida.web.dto.habittracker.HabittrackerResponseDTO;
import com.ssafy.moida.web.dto.habittracker.HabittrackerSaveRequestDTO;
import com.ssafy.moida.web.dto.habittracker.HabittrackerUpdateRequestDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HabittrackerServiceImpl implements HabittrackerService{
	
	private final HabittrackerRepository habittrackerRepository;
	private final AccountHabittrackerRepository accountHabittrackerRepository;
	private final AccountService accountService;

	@Transactional
	public Long saveHabittracker(HabittrackerSaveRequestDTO requestDto) {
		return habittrackerRepository.save(requestDto.toEntity()).getId();
	}

	@Transactional
	public Long updateinfo(HabittrackerUpdateRequestDTO requestDTO) {
		habittrackerRepository.findById(requestDTO.getId()).get()
		.updateinfo(requestDTO.getSubject(), requestDTO.getDescription(),
				 requestDTO.getStartDate(), requestDTO.getEndDate());
		Habittracker habit = habittrackerRepository.findById(requestDTO.getId()).get();
		
		
		return requestDTO.getId();
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
	public Long deleteHabittracker(Long habitid) throws NumberFormatException, BaseException {
		Long loginid = accountService.getAccount().getId();
		Habittracker habit = habittrackerRepository.findById(habitid).get();
		if(loginid == habit.getAccount().getId()) {
			habit.delete(LocalDateTime.now());
		}
		else {
			throw new BaseException(EnumHabittrackerException.USER_NOT_OWN);
		}
		return habitid;
	}

	@Transactional
	public Long joinHabittracker(AccountHabittrackerSaveDTO requestDTO) throws NumberFormatException, BaseException {
		Habittracker habittracker = habittrackerRepository.findById(requestDTO.getHabitid()).get();

		
		return accountHabittrackerRepository.save(requestDTO.toEntity(accountService.getAccount(), habittracker)).getId();
	}

	@Transactional
	public void leaveHabittracker(AccountHabittrackerSaveDTO requestDTO) throws NumberFormatException, BaseException {
		Habittracker habittracker = habittrackerRepository.findById(requestDTO.getHabitid()).get();
		
		accountHabittrackerRepository.delete(requestDTO.toEntity(accountService.getAccount(), habittracker));
	
	}

	@Transactional
	public Long clearHabittracker(AccountHabittrackerSaveDTO requestDTO) {

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
	public List<HabittrackerResponseDTO> findByGroupTBAndAccount(AccountHabittrackerSaveDTO requestDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<HabittrackerResponseDTO> findByAccount(Long accountid) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
