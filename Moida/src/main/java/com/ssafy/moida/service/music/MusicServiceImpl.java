package com.ssafy.moida.service.music;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moida.domain.etrash.EtrashRepository;
import com.ssafy.moida.domain.music.Music;
import com.ssafy.moida.domain.music.MusicRepository;
import com.ssafy.moida.service.etrash.EtrashService;
import com.ssafy.moida.web.dto.music.MusicFindByMoodRequestDTO;
import com.ssafy.moida.web.dto.music.MusicFindByMoodResponseDTO;
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
	
	@Transactional
	public Long saveMusic(MusicSaveRequestDTO requestDTO) {
		return musicRepository.save(requestDTO.toEntity()).getId();
	}

	@Transactional(readOnly = true)
	public Page<MusicFindByMoodResponseDTO> findByMood(MusicFindByMoodRequestDTO requestDto) {

		return musicRepository.findByMoodOrderByLikecountDesc(requestDto.getMood(), requestDto.getPageable())
				.map(MusicFindByMoodResponseDTO::new);
	}

	@Transactional
	public Long selectMusic(MusicSelcetMusicRequest requestDto) {
		
		return etrashService.updateEtrashMusic(etrashRepository.findById(requestDto.getEtrashid()).get(), musicRepository.findById(requestDto.getMusicid()).get());
	}



	@Transactional(readOnly = true)
	public MusicFindByVideoResponse findByVideoid(MusicFindByVideoidRequestDTO requestDTO) {
		Music music = musicRepository.findByVideoid(requestDTO.getVideoid()).get();
		
		return MusicFindByVideoResponse.builder()
				.id(music.getId())
				.mood(music.getMood())
				.musicname(music.getMusicname())
				.likecount(music.getLikecount())
				.videoid(music.getVideoid())
				.account(music.getAccount())
				.build();
	}
	
	

	

}
