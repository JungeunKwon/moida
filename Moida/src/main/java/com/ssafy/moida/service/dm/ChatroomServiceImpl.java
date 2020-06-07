package com.ssafy.moida.service.dm;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ssafy.moida.domain.account.Account;
import com.ssafy.moida.domain.account.AccountRepository;
import com.ssafy.moida.domain.dm.Chatroom;
import com.ssafy.moida.domain.dm.ChatroomRepository;
import com.ssafy.moida.domain.dm.RedisDMRepository;
import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.exception.EnumAccountException;
import com.ssafy.moida.service.account.AccountService;
import com.ssafy.moida.web.dto.dm.ChatroomDto;
import com.ssafy.moida.web.dto.dm.ChatroomResponseDto;
import com.ssafy.moida.web.dto.dm.ChatroomUserDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatroomServiceImpl implements ChatroomService{

	private final ChatroomRepository chatroomRepository;
	private final AccountRepository accountRepository;
	private final AccountService accountService;
	private final RedisDMRepository redisDMRepository;
	
	public List<ChatroomUserDto> findByAllAccountIn(String user) throws NumberFormatException, BaseException {
		Account account = accountRepository.findByNickname(user).orElseThrow(()->new BaseException(EnumAccountException.USER_NOT_FOUND));
		List<Chatroom> rooms = chatroomRepository.findByAllAccountIn(account.getId());
		List<ChatroomUserDto> targetAccounts = new ArrayList<>();
		for(Chatroom c : rooms) {
			if(c.getChat_host().getNickname().equals(account.getNickname())) {
				targetAccounts.add(ChatroomUserDto.builder()
									.id(c.getId())
									.roomuuid(c.getRoomuuid())
									.account(c.getChat_user())
									.build());
									
			}
			else if(c.getChat_user().getNickname().equals(account.getNickname())) {
				targetAccounts.add(ChatroomUserDto.builder()
						.id(c.getId())
						.roomuuid(c.getRoomuuid())
						.account(c.getChat_host())
						.build());
			}
		}
		return targetAccounts;
	}

	public List<ChatroomResponseDto> findByNickName(String nickname) throws BaseException {
		Account account = accountRepository.findByNickname(nickname).orElseThrow(()->new BaseException(EnumAccountException.USER_NOT_FOUND));
		return chatroomRepository.findByAllAccountIn(account.getId()).stream()
				.map(ChatroomResponseDto :: new)
				.collect(Collectors.toList());
	}

	public ChatroomDto createRoom(String targetNickname) throws NumberFormatException, BaseException {
		Account host = accountService.getAccount();
		Account user = accountRepository.findByNickname(targetNickname).orElseThrow(()->new BaseException(EnumAccountException.USER_NOT_FOUND));
		ChatroomDto room = ChatroomDto.builder()
					.host(host)
					.user(user)
					.build();
		ChatroomDto newroom = redisDMRepository.createChatRoom(room);
		chatroomRepository.save(newroom.toEntity());
		return newroom;
	}

	public boolean updatedate(ChatroomDto room) {
		return false;
	}

	public boolean delete(ChatroomDto room) {
		return false;
	}
}
