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
	private Account account;
	private GroupTB groupTB;
	private Long groupid;
	
	public Diary toEntity() {
		return Diary.builder()
				.description(description)
				.mood(mood)
				.imgurl(imgurl)
				.account(account)
				.groupTB(groupTB)
				.build();
	}
	
	@Builder
	public DiarySaveRequest(String description, String mood, String imgurl, Account account,
			GroupTB groupTB) {
		super();
		this.description = description;
		this.mood = mood;
		this.imgurl = imgurl;
		this.account = account;
		this.groupTB = groupTB;
	}
	
	
}
