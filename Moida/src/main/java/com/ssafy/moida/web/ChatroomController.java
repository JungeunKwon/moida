package com.ssafy.moida.web;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.moida.domain.dm.RedisDMRepository;
import com.ssafy.moida.exception.BaseException;
import com.ssafy.moida.service.dm.ChatroomService;
import com.ssafy.moida.web.dto.dm.ChatroomDto;
import com.ssafy.moida.web.dto.dm.ChatroomUserDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;

@Api(tags = {"9. DM"})
@RequiredArgsConstructor
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/chat")
public class ChatroomController {
	
	private final ChatroomService chatroomService;
	private final RedisDMRepository redisDMRepository;

	@GetMapping("/room")
	public String rooms(Model model) {
		return "/chat/room";
	}

	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@GetMapping("/rooms/{user}")
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public List<ChatroomUserDto> room(@PathVariable String user) throws Exception {
		List<ChatroomUserDto> rooms = chatroomService.findByAllAccountIn(user);
		List<ChatroomDto> room = chatroomService.findByNickName(user);
		redisDMRepository.reset(room);
		return rooms;
	}


	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@PostMapping("/room/{targetNickname}")
	@ResponseBody
	public ChatroomDto createRoom(@PathVariable String targetNickname) throws NumberFormatException, BaseException {
		return chatroomService.createRoom(targetNickname);
	}


	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@GetMapping("/room/enter/{roomuuid}")
	public String roomDetail(Model model, @PathVariable String roomuuid) {
		model.addAttribute("roomuuid", roomuuid);
		return "/chat/roomdetail";
	}

	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@GetMapping("/room/{roomuuid}")
	@ResponseBody
	public ChatroomDto roomInfo(@PathVariable String roomuuid) {
		return redisDMRepository.findRoomById(roomuuid);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@GetMapping("/room/{hostName}/{userName}")
	@ResponseBody
	public ChatroomDto roomCheck(@RequestParam("hostName") String hostName, @RequestParam("userName") String userName) throws BaseException {
		return chatroomService.isRoomExist(hostName, userName);
	}
}
