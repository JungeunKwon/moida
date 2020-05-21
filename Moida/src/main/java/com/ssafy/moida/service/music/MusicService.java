package com.ssafy.moida.service.music;

import org.springframework.data.domain.Page;

import com.ssafy.moida.domain.music.Music;

public interface MusicService {
	
	public Page<Music> findByMood();
	public Long addLikecount();
	public Long saveMusic();
	public Music findByVideoid();

}
