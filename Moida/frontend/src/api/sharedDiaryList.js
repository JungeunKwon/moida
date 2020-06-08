import request from "@/utils/request";

export function createSharedDiary(data) {
	return request({
		url: "/v1/group",
		method: "post",
		data,
	});
}

export function joinSharedDiary(data) {
	return request({
		url: "/v1/group/join",
		method: "post",
		data,
	});
}

export function getSharedDiary() {
	return request({
		url: "/v1/group/All",
		method: "get",
	});
}

export function getSharedDiaryDetail(data) {
	return request({
		url: `/v1/group/${data}`,
		method: "get",
	});
}

export function searchBySubject(data) {
	return request({
		url: `/v1/group/search/subject/${data}`,
		method: "get",
	});
}

export function searchByNickname(data) {
	return request({
		url: `/v1/group/search/nickname/${data}`,
		method: "get",
	});
}

export function searchByDesc(data) {
	return request({
		url: `/v1/group/search/description/${data}`,
		method: "get",
	});
}

export function getMySharedDiary() {
	return request({
		url: "/v1/group",
		method: "get",
	});
}

export function searchByMember(nickname) {
	return request({
		url: `/v1/group/join/${nickname}`,
		method: "get",
	});
}
