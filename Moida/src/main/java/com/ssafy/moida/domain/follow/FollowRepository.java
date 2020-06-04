package com.ssafy.moida.domain.follow;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import com.ssafy.moida.web.dto.follow.FollowResponseDTO;

public interface FollowRepository extends JpaRepository<Follow, Long>{
	List<Follow> findByFollowerId(Long followerid);
	List<Follow> findByFollowingId(Long followingid);

}
