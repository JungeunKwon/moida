package com.ssafy.moida.domain.dm;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


	
public interface ChatroomRepository extends JpaRepository<Chatroom, Long>{
	
	@Query(value = "SELECT * FROM chatroom WHERE host_id = :host OR user_id = :user ORDER BY create_date DESC", nativeQuery = true)
	List<Chatroom> queryAllAccountIn(@Param("host") Long host_id, @Param("user") Long user_id);
	
	Optional<Chatroom> findByRoomuuid(String roomuuid);
	
	@Query(value = "SELECT * FROM chatroom WHERE (host_id = :host AND user_id = :user) OR (host_id = :user AND  user_id = :host)", nativeQuery = true)
	List<Chatroom> isRoomExist(@Param("host") Long host_id, @Param("user") Long user_id);
}
