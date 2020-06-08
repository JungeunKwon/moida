package com.ssafy.moida.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.ssafy.moida.domain.dm.RedisDMRepository;
import com.ssafy.moida.service.dm.ChatroomService;
import com.ssafy.moida.service.dm.DirectMessageService;
import com.ssafy.moida.service.dm.pubsub.RedisPublisher;
import com.ssafy.moida.web.dto.dm.DirectMessageDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class DmController {

	private final RedisPublisher redisPublisher;
	private final ChatroomService chatRoomService;
	private final DirectMessageService directMessagMessageService;
	private final RedisDMRepository redisDMRepository;

	@MessageMapping("/chat/message")
	public void message(DirectMessageDto message) throws Exception {
		if (DirectMessageDto.MessageType.JOIN.equals(message.getType())) {
			redisDMRepository.enterChatRoom(message.getRoomuuid());
			List<DirectMessageDto> messages = directMessagMessageService.selectAll(message.getRoomuuid());
			redisPublisher.publish(redisDMRepository.getTopic(message.getRoomuuid()), message);
			for (int i = 0; i < messages.size(); i++) {
				DirectMessageDto inmessage = messages.get(i);
				System.out.println("LOG : 변경 전 부분 :" + inmessage.getType().toString());
				if (!DirectMessageDto.MessageType.SHARE.equals(inmessage.getType())) {
					inmessage.setType(DirectMessageDto.MessageType.LOG);
					System.out.println("LOG : 변경 부분 :" + inmessage.getType().toString());
				}
				inmessage.setLoguser(message.getWriter());
				redisPublisher.publish(redisDMRepository.getTopic(inmessage.getRoomuuid()), inmessage);
			}
		} else if (DirectMessageDto.MessageType.QUIT.equals(message.getType())) {
			redisPublisher.publish(redisDMRepository.getTopic(message.getRoomuuid()), message);

		} else {
			chatRoomService.updatedate(message.getRoomuuid());
			directMessagMessageService.putMessage(message);
			message.setLastDate(LocalDateTime.now().toString());
			redisPublisher.publish(redisDMRepository.getTopic(message.getRoomuuid()), message);
		}
	}
}
