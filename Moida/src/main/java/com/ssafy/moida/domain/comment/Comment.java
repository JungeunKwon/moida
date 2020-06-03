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
	
	@Column(nullable = false)
	private Long likecount;
	
	@Column(nullable = true)
	private LocalDateTime deleteDate;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;
	
	@ManyToOne
	@JoinColumn(name="diary")
	private Diary diary;

	@Builder
	public Comment(Long id, String description, Long likecount, LocalDateTime deleteDate, Account account,
			Diary diary) {
		super();
		this.id = id;
		this.description = description;
		this.likecount = likecount;
		this.deleteDate = deleteDate;
		this.account = account;
		this.diary = diary;
	}
	
	
	
	

}
