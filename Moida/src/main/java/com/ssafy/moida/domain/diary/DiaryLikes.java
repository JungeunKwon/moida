package com.ssafy.moida.domain.diary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.diary.Diary;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class DiaryLikes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OnDelete(action=OnDeleteAction.CASCADE) 
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;
	
	@OnDelete(action=OnDeleteAction.CASCADE) 
	@ManyToOne
	@JoinColumn(name="diary_id")
	private Diary diary;
	
	@Builder
	public DiaryLikes(Long id, Account account, Diary diary) {
		super();
		this.id = id;
		this.account = account;
		this.diary = diary;
	}
	
	
	
	
}
