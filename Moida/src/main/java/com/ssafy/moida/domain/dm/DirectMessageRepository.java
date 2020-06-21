package com.ssafy.moida.domain.dm;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface DirectMessageRepository extends JpaRepository<DirectMessage, Long> {
	
	@Query("SELECT c FROM DirectMessage c WHERE c.roomuuid = :roomid")
	List<DirectMessage> findByRoomId(@Param("roomid") String roomid);
	
    Optional<DirectMessage> findTop1ByRoomuuidOrderByIdDesc(String roomuuid);
}
