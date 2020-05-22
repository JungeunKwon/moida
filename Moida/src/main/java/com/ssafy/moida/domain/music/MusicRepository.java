package com.ssafy.moida.domain.music;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moida.domain.account.Account;

public interface MusicRepository extends JpaRepository<Music, Long>{
	
	Page<Music> findByMoodOrderByLikecountDesc(String mood,Pageable pageable);
	Optional<Music> findByVideoid(String videoid);

}
