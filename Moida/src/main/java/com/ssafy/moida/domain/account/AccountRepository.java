package com.ssafy.moida.domain.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findByEmail(String email);
	Optional<Account> findById(Long id);
	short countByEmail(String email);
	short countByNickname(String nickname);
}
