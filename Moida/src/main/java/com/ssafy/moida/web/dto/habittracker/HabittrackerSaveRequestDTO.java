package com.ssafy.moida.web.dto.habittracker;

import java.time.LocalDateTime;



import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.group.GroupTB;
import com.ssafy.moida.domain.habittracker.Habittracker;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class HabittrackerSaveRequestDTO {
	
	private String subject;
	private String description;
	private Long groupid;
	
	
	private String startDate;
	private String endDate;
	private Account account;
	private GroupTB groupTB;
	
	
	public Habittracker toEntity(LocalDateTime start, LocalDateTime end) {
		
		return Habittracker.builder()
				.subject(subject)
				.description(description)
				.startDate(start)
				.endDate(end)
				.account(account)
				.groupTB(groupTB)
				.build();
	}

	@Builder
	public HabittrackerSaveRequestDTO(String subject, String description, Long groupid, String startDate,
			String endDate, Account account, GroupTB groupTB) {
		super();
		this.subject = subject;
		this.description = description;
		this.groupid = groupid;
		this.startDate = startDate;
		this.endDate = endDate;
		this.account = account;
		this.groupTB = groupTB;
	}
	
	
	
	
	

}
