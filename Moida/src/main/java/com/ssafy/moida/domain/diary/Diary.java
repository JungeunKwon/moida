package com.ssafy.moida.domain.diary;



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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.comment.Comment;
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
	private LocalDateTime deleteDate;
	
	@Column(nullable = true)
	private String inputDate;
	
	@Column(nullable = true)
	private String mood;
	
	@Lob
	@Column(nullable = true)
	private String imgurl;
	
	@Column(nullable = false)
	private int isPrivate;
	
	@Column(nullable = false)
	private Long viewcount;
	
	@OnDelete(action=OnDeleteAction.CASCADE) 
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;
	
	@OnDelete(action=OnDeleteAction.CASCADE) 
	@ManyToOne
	@JoinColumn(name="groupTB_id")
	private GroupTB groupTB;
	
	@OneToMany(mappedBy = "diary")
	private List<Comment> commentList = new ArrayList<>();
	
	@OneToMany(mappedBy = "diary")
	private List<DiaryLikes> diarylikelist = new ArrayList<>();
	
	
	public void updateViewCount() {
		this.viewcount++;
	}
	
	public void updateDiaryinfo(String description, String mood, String imgurl, int isPrivate, String inputDate) {
		this.description = description;
		this.mood = mood;
		this.imgurl = imgurl;
		this.isPrivate = isPrivate;
		this.inputDate = inputDate;
	}
	
	public void deleteDiary(LocalDateTime deleteDate) {
		this.deleteDate = deleteDate;
	}
	
	@Builder
	public Diary(Long id, String description, LocalDateTime deleteDate, String mood, String imgurl, int isPrivate,
			Account account, GroupTB groupTB,Long viewcount,String inputDate) {
		super();
		this.id = id;
		this.description = description;
		this.deleteDate = deleteDate;
		this.mood = mood;
		this.imgurl = imgurl;
		this.isPrivate = isPrivate;
		this.account = account;
		this.groupTB = groupTB;
		this.viewcount = viewcount;
		this.inputDate = inputDate;
	}
	
	
	
}
