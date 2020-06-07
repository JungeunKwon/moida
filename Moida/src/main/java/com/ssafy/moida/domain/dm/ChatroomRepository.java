package com.ssafy.moida.domain.dm;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;


import io.lettuce.core.dynamic.annotation.Param;

public interface ChatroomRepository extends JpaRepository<Chatroom, Long>{
	
	@Query("SELECT c FROM Chatroom c WHERE c.chat_host = :account_id or c.chat_user = :account_id ORDER BY c.create_date DESC")
	List<Chatroom> findByAllAccountIn(@Param("account_id") Long id);
	
}
