package com.ssafy.moida.domain.habittracker;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.ssafy.moida.domain.account.Account;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class DoHabit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String cleardate;
	
	@Lob
	@Column(nullable = true)
	private String description;
	
	@OnDelete(action=OnDeleteAction.CASCADE) 
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;
	
	@OnDelete(action=OnDeleteAction.CASCADE) 
	@ManyToOne
	@JoinColumn(name="habittracker_id")
	private Habittracker habittracker;
	
	@Builder
	public DoHabit(Long id, String cleardate, Account account, Habittracker habittracker,String description) {
		super();
		this.id = id;
		this.cleardate = cleardate;
		this.account = account;
		this.habittracker = habittracker;
		this.description = description;
	}
	
	
	

}
