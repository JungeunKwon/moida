package com.ssafy.moida.service.dm;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ssafy.moida.domain.dm.DirectMessageRepository;
import com.ssafy.moida.web.dto.dm.DirectMessageDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DirectMessageServiceImpl implements DirectMessageService{

	private final DirectMessageRepository directMessageRepository;
	
	
	public List<DirectMessageDto> selectAll(String roomid) {
		return directMessageRepository.findByRoomId(roomid).stream()
				.map(DirectMessageDto :: new)
				.collect(Collectors.toList());
	}

	public boolean putMessage(DirectMessageDto message) {
		directMessageRepository.save(message.toEntity());
		return true;
	}

}
