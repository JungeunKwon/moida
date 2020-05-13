package com.ssafy.moida.domain.goalplanner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.ssafy.moida.domain.account.Account;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Goalplanner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="host_id")
	private Account host;
	
	@Column(nullable = false, length = 30)
	private String subject;
	
	@Column(nullable = false, length = 30)
	private int limit;
	
	@Column(nullable = false, length = 30)
	private String password;
	
	@Column(nullable = false, length = 30)
	private String startDate;
	
	@Column(nullable = false, length = 30)
	private String endDate;
	
	@Builder
	public Goalplanner(Long id, Account host, String subject, int limit, String password, String startDate,
			String endDate) {
		this.id = id;
		this.host = host;
		this.subject = subject;
		this.limit = limit;
		this.password = password;
		this.startDate = startDate;
		this.endDate = endDate;
	}
}
