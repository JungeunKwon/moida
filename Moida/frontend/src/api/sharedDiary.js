import request from "@/utils/request";

export function getSharedDiaryDetail(data) {
	return request({
		url: `/v1/group/${data}`,
		method: "get",
	});
}

export function getSharedDiaryUser(data) {
	return request({
		url: `/v1/group/user/${data}`,
		method: "get",
	});
}

export function getSharedDiary(data) {
	return request({
		url: `/v1/diary/search/group/${data}`,
		method: "get",
	});
}