package com.ssafy.moida.domain.group;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface GroupTBRepository extends JpaRepository<GroupTB, Long>{

	@Query(value = "SELECT * FROM GroupTB WHERE delete_Time IS NOT NULL", nativeQuery = true)
	List<GroupTB> findAllGroupExcludeDeleted();
	
}
