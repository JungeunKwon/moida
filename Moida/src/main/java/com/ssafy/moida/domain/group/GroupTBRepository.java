package com.ssafy.moida.domain.group;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.moida.domain.account.Account;


public interface GroupTBRepository extends JpaRepository<GroupTB, Long>{

	@Query(value = "SELECT * FROM GroupTB WHERE delete_Time IS NULL", nativeQuery = true)
	List<GroupTB> findAllGroupExcludeDeleted();
	List<GroupTB> findBySubjectContaining(String subject);
	@Query(value = "SELECT * FROM GROUPTB g WHERE g.account_id IN (SELECT account_id FROM ACCOUNT WHERE nickname LIKE %?1%)", nativeQuery = true)
	List<GroupTB> findByNicknameLikeQuery(String nickname);
	List<GroupTB> findByDescriptionContaining(String description);
	List<GroupTB> findByDeleteTimeIsNull();
}
