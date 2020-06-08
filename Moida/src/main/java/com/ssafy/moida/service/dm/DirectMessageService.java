package com.ssafy.moida.service.dm;

import java.util.List;

import com.ssafy.moida.web.dto.dm.DirectMessageDto;

public interface DirectMessageService {
	List<DirectMessageDto> selectAll(String roomid);
	public boolean putMessage(DirectMessageDto message);
}
