package com.ssafy.moida.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.ssafy.moida.domain.dm.ChatroomRepository;
import com.ssafy.moida.domain.dm.RedisDMRepository;
import com.ssafy.moida.service.dm.ChatroomService;
import com.ssafy.moida.service.dm.DirectMessageService;
import com.ssafy.moida.service.dm.pubsub.RedisPublisher;
import com.ssafy.moida.web.dto.dm.ChatroomDto;
import com.ssafy.moida.web.dto.dm.DirectMessageDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class DmController {
	
	private final RedisPublisher redisPublisher;
	private final ChatroomService chatRoomService;
	private final DirectMessageService directMessagMessageService;
	private final RedisDMRepository redisDMRepository;

	/**
	 * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
	 */
	@MessageMapping("/chat/message")
	public void message(DirectMessageDto message) throws Exception {
		if (DirectMessageDto.MessageType.JOIN.equals(message.getType())) {
			redisDMRepository.enterChatRoom(message.getRoomuuid());
			List<DirectMessageDto> messages = directMessagMessageService.selectAll(message.getRoomuuid());
			redisPublisher.publish(redisDMRepository.getTopic(message.getRoomuuid()), message);
			for(int i = 0; i < messages.size(); i ++) {
				DirectMessageDto inmessage = messages.get(i);
				if(!DirectMessageDto.MessageType.SHARE.equals(inmessage.getType()))
				{
					inmessage.setType(DirectMessageDto.MessageType.LOG);	
				}
				inmessage.setLoguser(message.getWriter().getNickname());
				redisPublisher.publish(redisDMRepository.getTopic(inmessage.getRoomuuid()),inmessage);
			}
		}else if(DirectMessageDto.MessageType.QUIT.equals(message.getType())) {
			redisPublisher.publish(redisDMRepository.getTopic(message.getRoomuuid()), message);

		}
		else {
			ChatroomDto room = ChatroomDto.builder()
					.roomuuid(message.getRoomuuid())
					.build();
			
			System.out.println(message);
			chatRoomService.updatedate(room);
			directMessagMessageService.putMessage(message);
			redisPublisher.publish(redisDMRepository.getTopic(message.getRoomuuid()), message);
		}
		// Websocket에 발행된 메시지를 redis로 발행한다(publish)
	}
}
