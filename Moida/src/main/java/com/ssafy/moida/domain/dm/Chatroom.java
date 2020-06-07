package com.ssafy.moida.domain.dm;

import java.time.LocalDateTime;

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

@Entity
@Getter
@NoArgsConstructor
public class Chatroom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String roomuuid;
	
	@ManyToOne
	@JoinColumn(name="host_id")
	private Account chat_host;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Account chat_user;

	@Builder
	public Chatroom(Long id, String roomuuid, Account chat_host, Account chat_user) {
		this.id = id;
		this.roomuuid = roomuuid;
		this.chat_host = chat_host;
		this.chat_user = chat_user;
	}
}
