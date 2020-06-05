package com.ssafy.moida.domain.group;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.moida.web.dto.group.GroupResponseDto;


public interface GroupTBRepository extends JpaRepository<GroupTB, Long>{

	@Query(value = "SELECT * FROM grouptb WHERE delete_Time IS NULL", nativeQuery = true)
	List<GroupTB> findAllGroupExcludeDeleted();
	List<GroupTB> findBySubjectContaining(String subject);
	@Query(value = "SELECT * FROM grouptb g WHERE g.account_id IN (SELECT id FROM account WHERE nickname LIKE %:nickname%)", nativeQuery = true)
	List<GroupTB> findByNicknameLikeQuery(@Param("nickname") String nickname);
	List<GroupTB> findByDescriptionContaining(String description);
	List<GroupTB> findByDeleteTimeIsNull();
	
	@Query(value = "SELECT DISTINCT * FROM grouptb LEFT OUTER JOIN account_group ON account_group.group_id = grouptb.id WHERE account_group.account_id = ?1", nativeQuery = true)
	List<GroupTB> findByAccountid(Long accountid);
}
