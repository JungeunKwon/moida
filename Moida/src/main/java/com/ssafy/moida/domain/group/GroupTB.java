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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Controller;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.common.BaseEntity;
import com.ssafy.moida.web.dto.group.GroupUpdateRequestDto;

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
	private String subject;
	
	@Column
	private int limitUser;
	
	@Column(nullable = true)
	private LocalDateTime deleteTime;
	
	@Column(nullable = true)
	private boolean isPrivate;
	
	@Column(nullable = true)
	private String imgUrl;
	
	@Column(nullable = true)
	@Lob
	private String description;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account host;
	
	@OneToMany(mappedBy = "groupTB")
	private List<AccountGroup> account = new ArrayList<>();

	public void updateDeleteDate(LocalDateTime deleteTime) {
		this.deleteTime = deleteTime;
	}
	
	public void updateGroup(String subject, int limitUser, boolean isPrivate, String description, String imgUrl) {
		subject = this.subject;
		limitUser = this.limitUser;
		isPrivate = this.isPrivate;
		description = this.description;
		imgUrl = this.imgUrl;
	}

	@Builder
	public GroupTB(Long id, String subject, int limitUser, LocalDateTime deleteTime, boolean isPrivate, String imgUrl,
			Account host, List<AccountGroup> account, String description) {
		this.id = id;
		this.subject = subject;
		this.limitUser = limitUser;
		this.deleteTime = deleteTime;
		this.isPrivate = isPrivate;
		this.imgUrl = imgUrl;
		this.host = host;
		this.account = account;
		this.description = description;
	}
}
