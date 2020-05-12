package com.ssafy.moida.domain.cheermessage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.goalplanner.Goalplanner;
import com.ssafy.moida.domain.music.Music;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class CheerMessage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@Column(nullable = false)
	private String message;

	@Lob
	@Column(nullable = false)
	private int count;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;

	public void Count() {
		this.count++;
	}
	
	@Builder
	public CheerMessage(Long id, String message, Account account) {
		this.id = id;
		this.message = message;
		this.account = account;
	}
	
	
	
}
