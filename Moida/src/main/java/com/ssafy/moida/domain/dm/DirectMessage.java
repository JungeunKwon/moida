package com.ssafy.moida.domain.dm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.common.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class DirectMessage extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@Column(nullable = true)
	private String content;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account writer;
	
	@ManyToOne
	@JoinColumn(name="Chatroom_id")
	private Chatroom chatroom;
	
	@Column(nullable = false)
	private String type;

	@Builder
	public DirectMessage(Long id, String content, Account writer, Chatroom chatroom, String type) {
		this.id = id;
		this.content = content;
		this.writer = writer;
		this.chatroom = chatroom;
		this.type = type;
	}
}
