package com.ssafy.moida.domain.group;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moida.domain.account.Account;

public interface AccountGroupRepository extends JpaRepository<AccountGroup, Long>{

	Optional<AccountGroup> findByAccountAndGroupTB(Long accountid, Long grouptbId);
	List<AccountGroup> findByGroupTB(GroupTB group);
	List<AccountGroup> findByGroupId(Long groupId);
	List<AccountGroup> findByAccount(Account account);
	int countByGroupId(Long groupId);
}
