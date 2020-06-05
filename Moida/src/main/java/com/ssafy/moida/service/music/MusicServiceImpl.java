package com.ssafy.moida.service.music;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moida.domain.etrash.EtrashRepository;
import com.ssafy.moida.domain.music.Music;
import com.ssafy.moida.domain.music.MusicRepository;
import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.exception.EnumAccountException;
import com.ssafy.moida.exception.EnumMusicException;
import com.ssafy.moida.service.account.AccountService;
import com.ssafy.moida.service.etrash.EtrashService;
import com.ssafy.moida.web.dto.music.MusicFindByMoodRequestDTO;
import com.ssafy.moida.web.dto.music.MusicResponseDTO;
import com.ssafy.moida.web.dto.music.MusicFindByVideoResponse;
import com.ssafy.moida.web.dto.music.MusicFindByVideoidRequestDTO;
import com.ssafy.moida.web.dto.music.MusicSaveRequestDTO;
import com.ssafy.moida.web.dto.music.MusicSelcetMusicRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MusicServiceImpl implements MusicService{

	private final MusicRepository musicRepository;
	private final EtrashService etrashService;
	private final EtrashRepository etrashRepository;
	private final AccountService accountservice;
	
	@Transactional
	public Long saveMusic(MusicSaveRequestDTO requestDTO) throws NumberFormatException, BaseException {
		requestDTO.setAccount(accountservice.getAccount());
		if(musicRepository.countByVideoid(requestDTO.getVideoid()) != 0) {
			throw new BaseException(EnumMusicException.MUSIC_DUPLICATE);
		}
		return musicRepository.save(requestDTO.toEntity()).getId();
	}

	@Transactional(readOnly = true)
	public Page<MusicResponseDTO> findByMood(MusicFindByMoodRequestDTO requestDto) {
		
		return musicRepository.findByMoodOrderByLikecountDesc(requestDto.getMood(), requestDto.getPageable())
				.map(MusicResponseDTO::new);
	}

	@Transactional
	public Long selectMusic(MusicSelcetMusicRequest requestDto) {
		Long count = musicRepository.findById(requestDto.getMusicid()).get().getLikecount();
		musicRepository.findById(requestDto.getMusicid()).get().likecount(count+1);
		return etrashService.updateEtrashMusic(etrashRepository.findById(requestDto.getEtrashid()).get(), musicRepository.findById(requestDto.getMusicid()).get());
	}



	@Transactional(readOnly = true)
	public MusicResponseDTO findByVideoid(MusicFindByVideoidRequestDTO requestDTO) {
		Music music = musicRepository.findByVideoid(requestDTO.getVideoid()).get();
		
		return MusicResponseDTO.builder()
				.music(music)
				.build();
	}
	
	

	

}
