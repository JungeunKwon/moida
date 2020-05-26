package com.ssafy.moida.service.etrash;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ssafy.moida.domain.etrash.Etrash;
import com.ssafy.moida.domain.music.Music;
import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.web.dto.etrash.EtrashAllRequestDTO;
import com.ssafy.moida.web.dto.etrash.EtrashDescriptionDTO;
import com.ssafy.moida.web.dto.etrash.EtrashResponseDto;
import com.ssafy.moida.web.dto.etrash.EtrashSaveRequestDto;

public interface EtrashService {
	
	Long saveEtrash(EtrashSaveRequestDto dto) throws NumberFormatException, BaseException;
	Long updateEtrashMusic(Etrash etrash, Music music);
	String sentimentanalysis(String description);
	Page<Music> musicrecommend(String mood);
	Page<EtrashResponseDto> findByMood(EtrashAllRequestDTO requestDto);
	Page<EtrashResponseDto> findAll(EtrashAllRequestDTO requestDto);
}
