package com.ssafy.moida.service.dm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.account.AccountRepository;
import com.ssafy.moida.domain.dm.Chatroom;
import com.ssafy.moida.domain.dm.ChatroomRepository;
import com.ssafy.moida.domain.dm.DirectMessage;
import com.ssafy.moida.domain.dm.DirectMessageRepository;
import com.ssafy.moida.domain.dm.RedisDMRepository;
import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.exception.EnumAccountException;
import com.ssafy.moida.service.account.AccountService;
import com.ssafy.moida.web.dto.account.AccountResponseDto;
import com.ssafy.moida.web.dto.dm.ChatroomDto;
import com.ssafy.moida.web.dto.dm.ChatroomResponseDto;
import com.ssafy.moida.web.dto.dm.ChatroomUserDto;
import com.ssafy.moida.web.dto.dm.DirectMessageDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatroomServiceImpl implements ChatroomService{

	private final ChatroomRepository chatroomRepository;
	private final AccountRepository accountRepository;
	private final AccountService accountService;
	private final RedisDMRepository redisDMRepository;
	private final DirectMessageRepository directMessageRepository;
	private final DirectMessageService directMessagMessageService;
	
	public List<ChatroomUserDto> findByAllAccountIn(String user) throws NumberFormatException, BaseException {
		Account account = accountRepository.findByNickname(user).orElseThrow(()->new BaseException(EnumAccountException.USER_NOT_FOUND));
		List<Chatroom> rooms = chatroomRepository.queryAllAccountIn(account.getId(), account.getId());
		List<ChatroomUserDto> targetAccounts = new ArrayList<>();
		for(Chatroom c : rooms) {
			if(c.getChat_host().getNickname().equals(account.getNickname())) {
				targetAccounts.add(ChatroomUserDto.builder()
									.id(c.getId())
									.roomuuid(c.getRoomuuid())
									.account(c.getChat_user())
									.lastDate(c.getCreateDate())
									.lastSentence(directMessageRepository.findTop1ByRoomuuidOrderByIdDesc(c.getRoomuuid()).get().getContent())
									.build());
									
			}
			else if(c.getChat_user().getNickname().equals(account.getNickname())) {
				targetAccounts.add(ChatroomUserDto.builder()
						.id(c.getId())
						.roomuuid(c.getRoomuuid())
						.account(c.getChat_host())
						.lastDate(c.getCreateDate())
						.lastSentence(directMessageRepository.findTop1ByRoomuuidOrderByIdDesc(c.getRoomuuid()).get().getContent())
						.build());
			}
		}
		return targetAccounts;
	}

	public List<ChatroomDto> findByNickName(String nickname) throws BaseException {
		Account account = accountRepository.findByNickname(nickname).orElseThrow(()->new BaseException(EnumAccountException.USER_NOT_FOUND));
		return chatroomRepository.queryAllAccountIn(account.getId(), account.getId()).stream()
				.map(ChatroomDto :: new)
				.collect(Collectors.toList());
	}

	public ChatroomResponseDto createRoom(String targetNickname) throws NumberFormatException, BaseException {
		Account host = accountService.getAccount();
		Account user = accountRepository.findByNickname(targetNickname).orElseThrow(()->new BaseException(EnumAccountException.USER_NOT_FOUND));
		ChatroomDto room = ChatroomDto.builder()
					.host(host)
					.user(user)
					.build();
		ChatroomDto newroom = redisDMRepository.createChatRoom(room);
		chatroomRepository.save(newroom.toEntity());
		ChatroomResponseDto responseDto = ChatroomResponseDto.builder()
										.id(newroom.getId())
										.roomuuid(newroom.getRoomuuid())
										.hostNickname(newroom.getHost().getNickname())
										.hostProfileImg(newroom.getHost().getProfileImg())
										.userNickname(newroom.getUser().getNickname())
										.userProfileImg(newroom.getUser().getProfileImg())
										.build();
		DirectMessageDto message = DirectMessageDto.builder()
								    .roomuuid(newroom.getRoomuuid())
								    .type(DirectMessageDto.MessageType.TALK)
								    .writer(newroom.getHost().getNickname())
								    .content("방 개설 성공 ,DM을 시작해보세요!")
								    .build();
		directMessagMessageService.putMessage(message);
		return responseDto;
	}

	public boolean updatedate(String roomuuid) throws BaseException {
		Chatroom room = chatroomRepository.findByRoomuuid(roomuuid).get();
		room.updateCreateDate();
		return true;
	}

	public boolean delete(ChatroomDto room) {
		return false;
	}

	@Override
	public ChatroomDto isRoomExist(String hostName, String userName) throws BaseException {
		Account user1 = accountRepository.findByNickname(hostName).orElseThrow(()->new BaseException(EnumAccountException.USER_NOT_FOUND));
		Account user2 = accountRepository.findByNickname(userName).orElseThrow(()->new BaseException(EnumAccountException.USER_NOT_FOUND));
		List<Chatroom> list = chatroomRepository.isRoomExist(user1.getId(), user2.getId());
		if(list.size()==0) return null;
		else return ChatroomDto.builder().id(list.get(0).getId())
										.roomuuid(list.get(0).getRoomuuid())
										.host(list.get(0).getChat_host())
										.user(list.get(0).getChat_user())
										.build();
	}
}
