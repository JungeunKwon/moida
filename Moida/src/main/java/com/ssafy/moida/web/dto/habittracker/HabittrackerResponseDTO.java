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
public class HabittrackerResponseDTO {
	private Long id;
	private String subject;
	private String description;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Long hostid;
	private String hostnickname;
	
	@Builder
	public HabittrackerResponseDTO(Habittracker habit) {
		super();
		this.id = habit.getId();
		this.subject = habit.getSubject();
		this.description = habit.getDescription();
		this.startDate = habit.getStartDate();
		this.endDate = habit.getEndDate();
		this.hostid = habit.getAccount().getId();
		this.hostnickname = habit.getAccount().getNickname();
	}
	
	

}
