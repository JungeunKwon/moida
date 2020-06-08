package com.ssafy.moida.service.habittracker;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.account.AccountRepository;
import com.ssafy.moida.domain.group.GroupTB;
import com.ssafy.moida.domain.group.GroupTBRepository;
import com.ssafy.moida.domain.habittracker.AccountHabittracker;
import com.ssafy.moida.domain.habittracker.AccountHabittrackerRepository;
import com.ssafy.moida.domain.habittracker.DoHabit;
import com.ssafy.moida.domain.habittracker.DoHabitRepository;
import com.ssafy.moida.domain.habittracker.Habittracker;
import com.ssafy.moida.domain.habittracker.HabittrackerRepository;
import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.exception.EnumHabittrackerException;
import com.ssafy.moida.service.account.AccountService;
import com.ssafy.moida.web.dto.group.GroupResponseDto;
import com.ssafy.moida.web.dto.habittracker.AccountHabittrackerSaveDTO;
import com.ssafy.moida.web.dto.habittracker.DohabitResponseDTO;
import com.ssafy.moida.web.dto.habittracker.DohabitSaveRequestDTO;
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
	private final DoHabitRepository doHabitRepository;

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
		

		Habittracker habit = habittrackerRepository.save(requestDto.toEntity(startDate,endDate));
		AccountHabittrackerSaveDTO accounthabit = new AccountHabittrackerSaveDTO();
		accounthabit.setHabit(habit);
		accounthabit.setAccount(account);
		accountHabittrackerRepository.save(accounthabit.toEntity());
		
		return habit.getId();
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
		Account account = accountService.getAccount();
		Habittracker habit = habittrackerRepository.findById(requestDTO.getHabitid()).get();
		
		requestDTO.setAccount(account);
		requestDTO.setHabit(habittrackerRepository.findById(requestDTO.getHabitid()).get());
		
		if(accountHabittrackerRepository.countByAccountAndHabittracker(account, habit) > 0) {
			return 0L;
		}
		
		return accountHabittrackerRepository.save(requestDTO.toEntity()).getId();
	}

	@Transactional
	public Long leaveHabittracker(AccountHabittrackerSaveDTO requestDTO) throws NumberFormatException, BaseException {

		Account account = accountService.getAccount();
		requestDTO.setAccount(account);
		requestDTO.setHabit(habittrackerRepository.findById(requestDTO.getHabitid()).get());
		
		accountHabittrackerRepository.delete(requestDTO.toEntity());
		return requestDTO.getHabitid();
	
	}

	@Transactional
	public Long clearHabittracker(DohabitSaveRequestDTO requestDTO) throws NumberFormatException, BaseException {
		requestDTO.setAccount(accountService.getAccount());
		requestDTO.setHabittracker(habittrackerRepository.findById(requestDTO.getHabitid()).get());
		
		if(0<doHabitRepository.countByAccountAndCleardateAndHabittracker(requestDTO.getAccount(), requestDTO.getClearDate(), requestDTO.getHabittracker())) {
			return 0L;
		}
		
		return doHabitRepository.save(requestDTO.toEntity()).getId();
	}
	
	@Transactional
	public Boolean deleteclearHabittracker(Long dohabitid) throws NumberFormatException, BaseException {
	
		doHabitRepository.deleteById(dohabitid);
		
		return true;
	}

	@Transactional(readOnly=true)
	public List<HabittrackerResponseDTO> findAll() {
		
		return habittrackerRepository.findAll().stream()
				.map(HabittrackerResponseDTO :: new)
				.collect(Collectors.toList());
	}

	@Transactional
	public List<HabittrackerResponseDTO> findByGroupTBAll(Long groupid) {
		return habittrackerRepository.findAll().stream()
				.map(HabittrackerResponseDTO :: new)
				.collect(Collectors.toList());
	}

	@Transactional
	public List<HabittrackerResponseDTO> findByGroupTB(Long groupid) {
		GroupTB group = groupTBRepository.findById(groupid).get();
		return habittrackerRepository.findByGroupTBAndStartDateLessThanAndEndDateGreaterThan(group, LocalDateTime.now(), LocalDateTime.now())
				.stream().map(HabittrackerResponseDTO :: new)
				.collect(Collectors.toList());
	}

	@Transactional
	public List<HabittrackerResponseDTO> findByGroupTBAndAccount(Long groupid) throws NumberFormatException, BaseException {
		GroupTB group = groupTBRepository.findById(groupid).get();
		Account account = accountService.getAccount();
		return habittrackerRepository.findByGroupTBAndAccountAndStartDateLessThanAndEndDateGreaterThan(group.getId(), account.getId(), LocalDateTime.now(),LocalDateTime.now())
				.stream().map(HabittrackerResponseDTO :: new).collect(Collectors.toList());
	}

	@Transactional
	public List<HabittrackerResponseDTO> findByAccount(Long accountid) throws NumberFormatException, BaseException {
		Account account = accountService.getAccount();
		return habittrackerRepository.findByAccount(account)
				.stream().map(HabittrackerResponseDTO :: new).collect(Collectors.toList());
	}

	@Override
	public List<DohabitResponseDTO> findByHabit(Long habitid) throws NumberFormatException, BaseException {
		Account account = accountService.getAccount();
		List<DohabitResponseDTO> reponseDTO = doHabitRepository.findByHabittrackerIdAndAccountId(habitid,account.getId()).stream()
				.map(DohabitResponseDTO::new).collect(Collectors.toList());
		return reponseDTO;
	}



}
