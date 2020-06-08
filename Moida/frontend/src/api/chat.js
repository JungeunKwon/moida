import request from "@/utils/request";

export function getRoom() {
	return request({
		url: "/chat/room",
		method: "get",
	});
}

export function enterRoom(roomuuid) {
	return request({
		url: `/chat/room/enter/${roomuuid}`,
		method: "get",
	});
}
export function getRoomInfo(roomuuid) {
	return request({
		url: `/chat/room/${roomuuid}`,
		method: "get",
	});
}
export function createRoom(targetNickname) {
	return request({
		url: `/chat/room/${targetNickname}`,
		method: "post",
	});
}
export function getAllRooms(user) {
	return request({
		url: `/chat/rooms/${user}`,
		method: "get",
	});
}
export function roomCheck(host, user) {
	return request({
		url: `/chat/room/${host}/${user}`,
		method: "get",
	});
}
