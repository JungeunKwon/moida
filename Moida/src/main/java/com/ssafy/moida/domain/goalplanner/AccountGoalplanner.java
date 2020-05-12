package com.ssafy.moida.domain.goalplanner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.ssafy.moida.domain.account.Account;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class AccountGoalplanner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;
	
	@ManyToOne
	@JoinColumn(name="goalplanner_id")
	private Goalplanner goalplanner;
	
	@Builder
	public AccountGoalplanner(Long id, Account account, Goalplanner goalplanner) {
		super();
		this.id = id;
		this.account = account;
		this.goalplanner = goalplanner;
	}
	
	
}
