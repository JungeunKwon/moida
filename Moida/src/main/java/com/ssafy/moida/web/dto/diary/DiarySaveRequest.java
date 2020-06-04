package com.ssafy.moida.web.dto.diary;



import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.diary.Diary;
import com.ssafy.moida.domain.group.GroupTB;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiarySaveRequest {
	
	private String description;
	private String mood;
	private String imgurl;
	private int isPrivate;
	private Account account;
	private GroupTB groupTB;
	private Long viewcount;

	
	public Diary toEntity() {
		return Diary.builder()
				.description(description)
				.mood(mood)
				.imgurl(imgurl)
				.isPrivate(isPrivate)
				.account(account)
				.groupTB(groupTB)
				.viewcount(viewcount)
				.build();
	}
	
	@Builder
	public DiarySaveRequest(String description, String mood, String imgurl, Account account, int isPrivate,
			GroupTB groupTB,Long viewcount) {
		super();
		this.description = description;
		this.mood = mood;
		this.imgurl = imgurl;
		this.isPrivate = isPrivate;
		this.account = account;
		this.groupTB = groupTB;
		this.viewcount = viewcount;
	}
	
	
}
