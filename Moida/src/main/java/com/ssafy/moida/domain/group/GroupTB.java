package com.ssafy.moida.domain.group;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.common.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class GroupTB extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = true, length = 30)
	private String name;
	
	@Column(nullable = true, length=300)
	private String password;
	
	@Column
	private int limitUser;
	
	@Column
	private LocalDateTime deleteTime;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account host;
	
	@OneToMany(mappedBy = "groupTB")
	private List<AccountGroup> account = new ArrayList<>();

	public void updateDeleteDate() {
		this.deleteTime = LocalDateTime.now();
	}
	
	@Builder
	public GroupTB(Long id, String name, String password, int limitUser, Account host, List<AccountGroup> account) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.limitUser = limitUser;
		this.host = host;
		this.account = account;
	}
}
