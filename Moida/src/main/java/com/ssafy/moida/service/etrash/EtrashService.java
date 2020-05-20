package com.ssafy.moida.service.etrash;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ssafy.moida.domain.etrash.Etrash;
import com.ssafy.moida.domain.music.Music;
import com.ssafy.moida.web.dto.etrash.EtrashAllRequestDTO;
import com.ssafy.moida.web.dto.etrash.EtrashResponseDto;
import com.ssafy.moida.web.dto.etrash.EtrashSaveRequestDto;

public interface EtrashService {
	
	Long saveEtrash(EtrashSaveRequestDto dto);
	String sentimentanalysis(String description);
	List<Music> musicrecommend(String mood);
	List<EtrashResponseDto> findByMood(String mood);
	Page<EtrashResponseDto> findAll(EtrashAllRequestDTO requestDto);
}
