package com.ssafy.moida.domain.comment;

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
import com.ssafy.moida.domain.common.BaseEntity;
import com.ssafy.moida.domain.diary.Diary;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = true)
	private LocalDateTime deleteDate;
	
	@OnDelete(action=OnDeleteAction.CASCADE) 
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;
	
	@OnDelete(action=OnDeleteAction.CASCADE) 
	@ManyToOne
	@JoinColumn(name="diary")
	private Diary diary;
	
	public void delete() {
		this.deleteDate = LocalDateTime.now();
	}
	
	public void updateinfo(String description) {
		this.description = description;
	}
	
	
	@Builder
	public Comment(Long id, String description, LocalDateTime deleteDate, Account account,
			Diary diary) {
		super();
		this.id = id;
		this.description = description;
		this.deleteDate = deleteDate;
		this.account = account;
		this.diary = diary;
	}
	
	
	
	

}
