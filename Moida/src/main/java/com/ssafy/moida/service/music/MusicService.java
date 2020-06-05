package com.ssafy.moida.service.music;

import org.springframework.data.domain.Page;

import com.ssafy.moida.domain.music.Music;
import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.web.dto.music.MusicFindByVideoidRequestDTO;
import com.ssafy.moida.web.dto.music.MusicSaveRequestDTO;
import com.ssafy.moida.web.dto.music.MusicSelcetMusicRequest;
import com.ssafy.moida.web.dto.music.MusicFindByMoodRequestDTO;
import com.ssafy.moida.web.dto.music.MusicResponseDTO;
import com.ssafy.moida.web.dto.music.MusicFindByVideoResponse;

public interface MusicService {
	
	public Long saveMusic(MusicSaveRequestDTO requestDTO) throws NumberFormatException, BaseException;
	public Page<MusicResponseDTO> findByMood(MusicFindByMoodRequestDTO requestDto);
	public Long selectMusic(MusicSelcetMusicRequest requestDto);
	public MusicResponseDTO findByVideoid(MusicFindByVideoidRequestDTO requestDTO);

}
