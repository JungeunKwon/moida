package com.ssafy.moida.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.moida.domain.dm.ChatroomRepository;
import com.ssafy.moida.domain.dm.RedisDMRepository;
import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.service.dm.ChatroomService;
import com.ssafy.moida.service.dm.DirectMessageService;
import com.ssafy.moida.web.dto.dm.ChatroomDto;
import com.ssafy.moida.web.dto.dm.ChatroomRequestDto;
import com.ssafy.moida.web.dto.dm.ChatroomUserDto;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api(tags = {"9. DM"})
@RequiredArgsConstructor
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/chat")
public class ChatroomController {
	
	private final DirectMessageService direcMessageService;
	private final ChatroomService chatroomService;
	private final RedisDMRepository redisDMRepository;

	@GetMapping("/room")
	public String rooms(Model model) {
		return "/chat/room";
	}


	@GetMapping("/rooms/{user}")
	@ResponseBody
	public List<ChatroomUserDto> room(@PathVariable String user) throws Exception {
		List<ChatroomUserDto> rooms = chatroomService.findByAllAccountIn(user);
		List<ChatroomDto> room = chatroomService.selectByUser(user);
		redisDMRepository.reset(room);
		return rooms;
	}


	@PostMapping("/room")
	@ResponseBody
	public ChatroomDto createRoom(@PathVariable String targetNickname) throws NumberFormatException, BaseException {
		return chatroomService.createRoom(targetNickname);
	}


	@GetMapping("/room/enter/{roomuuid}")
	public String roomDetail(Model model, @PathVariable String roomuuid) {
		model.addAttribute("roomuuid", roomuuid);
		return "/chat/roomdetail";
	}

	@GetMapping("/room/{roomuuid}")
	@ResponseBody
	public ChatroomDto roomInfo(@PathVariable String roomuuid) {
		return redisDMRepository.findRoomById(roomuuid);
	}
}
