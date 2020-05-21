package com.ssafy.moida.domain.music;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music, Long>{
	
	Page<Music> findByMoodOrderByLikecountDesc(String mood,Pageable pageable);
	

}
