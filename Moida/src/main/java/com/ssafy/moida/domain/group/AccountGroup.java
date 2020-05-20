package com.ssafy.moida.domain.group;

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
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class AccountGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;
	
	@ManyToOne
	@JoinColumn(name="groupTB_id")
	private GroupTB groupTB;

	@Builder
	public AccountGroup(Long id, Account account, GroupTB groupTB) {
		this.id = id;
		this.account = account;
		this.groupTB  = groupTB;
	}
}
