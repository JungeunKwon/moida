package com.ssafy.moida.domain.habittracker;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ssafy.moida.domain.account.Account;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class AccountHabittracker {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;
	
	@ManyToOne
	@JoinColumn(name="grouptb_id")
	private Habittracker habittracker;
	
	@Builder
	public AccountHabittracker(Long id, Account account, Habittracker habittracker) {
		super();
		this.id = id;
		this.account = account;
		this.habittracker = habittracker;
	}
	
	
}
