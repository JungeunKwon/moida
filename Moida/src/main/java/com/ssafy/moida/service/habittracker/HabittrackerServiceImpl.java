package com.ssafy.moida.service.habittracker;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.group.GroupTBRepository;
import com.ssafy.moida.domain.habittracker.AccountHabittracker;
import com.ssafy.moida.domain.habittracker.AccountHabittrackerRepository;
import com.ssafy.moida.domain.habittracker.Habittracker;
import com.ssafy.moida.domain.habittracker.HabittrackerRepository;
import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.exception.EnumHabittrackerException;
import com.ssafy.moida.service.account.AccountService;
import com.ssafy.moida.web.dto.group.GroupResponseDto;
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
	private final GroupTBRepository groupTBRepository;

	@Transactional
	public Long saveHabittracker(HabittrackerSaveRequestDTO requestDto) throws NumberFormatException, BaseException {
		Account account = accountService.getAccount();
		requestDto.setAccount(account);
		requestDto.setGroupTB(groupTBRepository.findById(requestDto.getGroupid()).get());
		LocalDateTime startDate = LocalDateTime.now();
		LocalDateTime endDate = LocalDateTime.now();
		String date = requestDto.getStartDate();
		int year = Integer.parseInt(date.split("-")[0]);
		int month = Integer.parseInt(date.split("-")[1]);
		int day = Integer.parseInt(date.split("-")[2]);
		startDate = startDate.withYear(year).withMonth(month).withDayOfMonth(day);
		
		date = requestDto.getEndDate();
		year = Integer.parseInt(date.split("-")[0]);
		month = Integer.parseInt(date.split("-")[1]);
		day = Integer.parseInt(date.split("-")[2]);
		endDate = startDate.withYear(year).withMonth(month).withDayOfMonth(day);
		

		Long habitid = habittrackerRepository.save(requestDto.toEntity(startDate,endDate)).getId();
		AccountHabittrackerSaveDTO accounthabit = new AccountHabittrackerSaveDTO();
		accounthabit.setHabitid(habitid);
		
		Habittracker habit = habittrackerRepository.findById(habitid).get();
		accountHabittrackerRepository.save(accounthabit.toEntity(account, habit));
		
		return habitid;
	}

	@Transactional
	public Long updateinfo(HabittrackerUpdateRequestDTO requestDTO) {
		

		LocalDateTime startDate = LocalDateTime.now();
		LocalDateTime endDate = LocalDateTime.now();
		String date = requestDTO.getStartDate();
		int year = Integer.parseInt(date.split("-")[0]);
		int month = Integer.parseInt(date.split("-")[1]);
		int day = Integer.parseInt(date.split("-")[2]);
		startDate = startDate.withYear(year).withMonth(month).withDayOfMonth(day);
		
		date = requestDTO.getEndDate();
		year = Integer.parseInt(date.split("-")[0]);
		month = Integer.parseInt(date.split("-")[1]);
		day = Integer.parseInt(date.split("-")[2]);
		endDate = startDate.withYear(year).withMonth(month).withDayOfMonth(day);
		
		
		habittrackerRepository.findById(requestDTO.getId()).get()
		.updateinfo(requestDTO.getSubject(), requestDTO.getDescription(),
				startDate, endDate);
		Habittracker habit = habittrackerRepository.findById(requestDTO.getId()).get();
		
		
		return requestDTO.getId();
	}
	
	@Transactional
	public HabittrackerResponseDTO findById(Long id){
		Habittracker habit = habittrackerRepository.findById(id).get();
			
		return HabittrackerResponseDTO.builder()
				.habit(habit)
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

	@Transactional(readOnly=true)
	public List<HabittrackerResponseDTO> findAll() {
		
		return habittrackerRepository.findAll().stream()
				.map(HabittrackerResponseDTO :: new)
				.collect(Collectors.toList());
	}

	@Transactional
	public List<HabittrackerResponseDTO> findByGroupTBAll(Long groupid) {
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
