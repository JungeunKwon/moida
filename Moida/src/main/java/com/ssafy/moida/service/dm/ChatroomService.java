package com.ssafy.moida.service.dm;

import java.util.List;

import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.web.dto.dm.ChatroomDto;
import com.ssafy.moida.web.dto.dm.ChatroomUserDto;

public interface ChatroomService {
	List<ChatroomUserDto> findByAllAccountIn(String user) throws NumberFormatException, BaseException;
	List<ChatroomDto> selectByUser(String user);
	public ChatroomDto createRoom(String targetNickname) throws NumberFormatException, BaseException;
	public boolean updatedate(ChatroomDto room);
	public boolean delete(ChatroomDto room);
}
