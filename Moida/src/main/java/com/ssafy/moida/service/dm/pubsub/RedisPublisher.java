package com.ssafy.moida.service.dm.pubsub;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import com.ssafy.moida.web.dto.dm.DirectMessageDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RedisPublisher {
	  private final RedisTemplate<String, Object> redisTemplate;
	    public void publish(ChannelTopic topic, DirectMessageDto message) {
	    	try {
	        redisTemplate.convertAndSend(topic.getTopic(), message);
	    	}catch (Exception e) {
				System.out.println("publish : " + e.getMessage());
			}
	    }
}
