package com.ssafy.moida.domain.diary;



import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.common.BaseEntity;
import com.ssafy.moida.domain.group.GroupTB;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Diary extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@Column(nullable = true)
	private String description;
	
	@Column(nullable = true)
	private LocalDateTime deletedate;
	
	@Column(nullable = true)
	private String mood;
	
	@Column(nullable = true)
	private String imgurl;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="account_id")
	private Account account;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="groupTB_id")
	private GroupTB groupTB;
	
	@Builder
	public Diary(Long id, String description, LocalDateTime deletedate, String mood, String imgurl,
			Account account, GroupTB groupTB) {
		super();
		this.id = id;
		this.description = description;
		this.deletedate = deletedate;
		this.mood = mood;
		this.imgurl = imgurl;
		this.account = account;
		this.groupTB = groupTB;
	}
	
	public void updateDiaryinfo(String description, String mood, String imgurl) {
		this.description = description;
		this.mood = mood;
		this.imgurl = imgurl;
	}
	
	public void deleteDiary(LocalDateTime deletedate) {
		this.deletedate = deletedate;
	}
	
	
	
}
