package com.ssafy.moida.domain.account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findByEmail(String email);
	Optional<Account> findById(Long id);
	Optional<Account> findByNickname(String nickname);
	
	short countByEmail(String email);
	short countByNickname(String nickname);
}
