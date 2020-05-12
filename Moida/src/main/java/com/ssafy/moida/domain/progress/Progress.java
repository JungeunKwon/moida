package com.ssafy.moida.domain.progress;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.goalplanner.Goalplanner;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Progress {
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
	public Progress(Long id, Account account, Goalplanner goalplanner) {
		super();
		this.id = id;
		this.account = account;
		this.goalplanner = goalplanner;
	}
	
	

}
