package com.ssafy.moida.service.etrash;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ssafy.moida.domain.etrash.Etrash;
import com.ssafy.moida.domain.etrash.EtrashRepository;
import com.ssafy.moida.domain.music.Music;
import com.ssafy.moida.web.dto.etrash.EtrashAllRequestDTO;
import com.ssafy.moida.web.dto.etrash.EtrashResponseDto;
import com.ssafy.moida.web.dto.etrash.EtrashSaveRequestDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class EtrashServiceImpl implements EtrashService{
	
	private final EtrashRepository etrashRepository;

	@Transactional(readOnly = true)
	public Page<EtrashResponseDto> findByMood(EtrashAllRequestDTO requestDto) {
		
		return etrashRepository.findByMood(requestDto.getMood(),requestDto.getPageable())
				.map(EtrashResponseDto::new);
			
	}

	@Transactional(readOnly = true)
	public Page<EtrashResponseDto> findAll(EtrashAllRequestDTO requestDto) {
		
		return etrashRepository.findAll(requestDto.getPageable())
				.map(EtrashResponseDto::new);
	}


	@Transactional
	public Long saveEtrash(EtrashSaveRequestDto dto) {
		return etrashRepository.save(dto.toEntity()).getId();
	}



	@Override
	public String sentimentanalysis(String description) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Music> musicrecommend(String mood) {
		// TODO Auto-generated method stub
		return null;
	}

}
