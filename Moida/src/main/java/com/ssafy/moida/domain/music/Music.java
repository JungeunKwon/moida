package com.ssafy.moida.domain.music;

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
public class Music {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String mood;
	
	@Lob
	@Column(nullable = false)
	private String musicname;
	
	@Column(nullable = false)
	private Long likecount;
	
	@Lob
	@Column(nullable = false,unique = true)
	private String videoid;
	
	@Column(nullable = true)
	private String thumbnail;
	
	@OnDelete(action=OnDeleteAction.CASCADE) 
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;

	public void likecount(Long count) {
		this.likecount = count;
	}
	
	@Builder
	public Music(Long id, String mood, String musicname, Long likecount, String videoid, Account account,String thumbnail) {
		this.id = id;
		this.mood = mood;
		this.musicname = musicname;
		this.likecount = likecount;
		this.videoid = videoid;
		this.account = account;
		this.thumbnail = thumbnail;
	}
	
	
	
	
}
