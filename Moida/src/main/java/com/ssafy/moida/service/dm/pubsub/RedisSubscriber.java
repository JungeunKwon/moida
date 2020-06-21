package com.ssafy.moida.service.dm.pubsub;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.moida.web.dto.dm.DirectMessageDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisSubscriber implements MessageListener {

	private final ObjectMapper objectMapper;
	private final RedisTemplate<?, ?> redisTemplate;
	private final SimpMessageSendingOperations messagingTemplate;

	@Override
	public void onMessage(Message message, byte[] pattern) {
		try {

			String publishMessage = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());

			DirectMessageDto roomMessage = objectMapper.readValue(publishMessage, DirectMessageDto.class);

			messagingTemplate.convertAndSend("/sub/chat/room/" + roomMessage.getRoomuuid(), roomMessage);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}