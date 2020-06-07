package com.ssafy.moida.domain.dm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Repository;

import com.ssafy.moida.service.dm.pubsub.RedisSubscriber;
import com.ssafy.moida.web.dto.dm.ChatroomDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Repository
public class RedisDMRepository {
	
	private final RedisMessageListenerContainer redisMessageListener;

	private final RedisSubscriber redisSubscriber;
	// Redis
	private static final String CHAT_ROOMS = "CHAT_ROOM";
	private final RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String, String, ChatroomDto> opsHashChatRoom;
	
	private Map<String, ChannelTopic> topics;

	@PostConstruct
	private void init() {
		opsHashChatRoom = redisTemplate.opsForHash();
		topics = new HashMap<>();
	}

	public void reset(List<ChatroomDto> rooms) {
		opsHashChatRoom = redisTemplate.opsForHash();
		topics = new HashMap<>();
		for (int i = 0; i < rooms.size(); i++) {
			opsHashChatRoom.put(CHAT_ROOMS, rooms.get(i).getRoomuuid(), rooms.get(i));
			topics.put(rooms.get(i).getRoomuuid(), new ChannelTopic(rooms.get(i).getRoomuuid()));
		}
	}

	public List<ChatroomDto> findAllRoom() {
		return opsHashChatRoom.values(CHAT_ROOMS);
	}

	public ChatroomDto findRoomById(String id) {
		return opsHashChatRoom.get(CHAT_ROOMS, id);
	}

	
	public ChatroomDto createChatRoom(ChatroomDto room) {
		room.setRoomuuid(UUID.randomUUID().toString());
		opsHashChatRoom.put(CHAT_ROOMS, room.getRoomuuid(), room);
		return room;
	}


	public void enterChatRoom(String roomId) {
		ChannelTopic topic = topics.get(roomId);
		if (topic == null) {
			topic = new ChannelTopic(roomId);
		}
		redisMessageListener.addMessageListener(redisSubscriber, topic);
		topics.put(roomId, topic);

	}

	public ChannelTopic getTopic(String roomId) {
		return topics.get(roomId);
	}
}
