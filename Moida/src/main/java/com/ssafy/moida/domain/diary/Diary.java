package com.ssafy.moida.domain.diary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;


import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.group.Group;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Diary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = true, length = 20)
	private String deleteDate;
	
	@Column(nullable = true, length = 10)
	private int availability;
	
	@Column(nullable = true, length = 10)
	private int mood;
	
	@Lob
	@Column(nullable = true,name="diary_Img")
	private String diaryImg;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;
	
	@ManyToOne
	@JoinColumn(name="group_id")
	private Group group;
	

	public void updateDiaryinfo(String description, int availability, int mood, String diaryImg) {
		this.description = description;
		this.availability = availability;
		this.mood = mood;
		this.diaryImg = diaryImg;
	}



	public void deleteDiaryinfo(String deleteDate) {
		this.deleteDate = deleteDate;
	}

	@Builder
	public Diary(Long id, String description, int availability, int mood, String diaryImg,
			Account account, Group group) {
		this.id = id;
		this.description = description;
		this.availability = availability;
		this.mood = mood;
		this.diaryImg = diaryImg;
		this.account = account;
		this.group = group;
	}
	
	
	
}
