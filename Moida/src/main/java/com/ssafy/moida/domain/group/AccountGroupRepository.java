package com.ssafy.moida.domain.group;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moida.domain.account.Account;

public interface AccountGroupRepository extends JpaRepository<AccountGroup, Long>{

	Optional<AccountGroup> findByAccountAndGroupTB(Long accountid, GroupTB GroupTB);
	List<AccountGroup> findByGroupTB(GroupTB group);

	List<AccountGroup> findByAccount(Account account);
	int countByGroupTB(GroupTB group);
	int countByGroupTBAndAccountId(GroupTB group, Long accountId);
	int countByGroupTBAndAccount(GroupTB group, Account account);
	
	void deleteByAccountIdAndGroupTB(Long accountid, GroupTB group);
}
