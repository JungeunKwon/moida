package com.ssafy.moida.service.dm;

import java.util.List;

import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.web.dto.dm.ChatroomDto;
import com.ssafy.moida.web.dto.dm.ChatroomResponseDto;
import com.ssafy.moida.web.dto.dm.ChatroomUserDto;

public interface ChatroomService {
	List<ChatroomUserDto> findByAllAccountIn(String user) throws NumberFormatException, BaseException;
	List<ChatroomDto> findByNickName(String nickname) throws BaseException;
	public ChatroomResponseDto createRoom(String targetNickname) throws NumberFormatException, BaseException;
	public boolean updatedate(String roomuuid) throws BaseException;
	public boolean delete(ChatroomDto room);
	ChatroomResponseDto isRoomExist(String hostName, String userName) throws BaseException;
}
