package com.ssafy.moida.web.dto.habittracker;

import java.time.LocalDateTime;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.group.GroupTB;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class HabittrackerResponseDTO {
	private Long id;
	private String subject;
	private String description;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Account account;
	private GroupTB groupTB;
	
	@Builder
	public HabittrackerResponseDTO(Long id, String subject, String description, LocalDateTime startDate,
			LocalDateTime endDate, Account account, GroupTB groupTB) {
		super();
		this.id = id;
		this.subject = subject;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.account = account;
		this.groupTB = groupTB;
	}
	
	

}
