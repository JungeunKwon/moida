import request from "@/utils/request";

export function getDiary() {
	return request({
		url: "/v1/diary",
		method: "get",
	});
}
export function postDiary(data) {
	return request({
		url: "/v1/diary",
		method: "post",
		data,
	});
}
export function putDiary(data) {
	return request({
		url: "/v1/diary",
		method: "put",
		data,
	});
}
export function deletetDiary(id) {
	return request({
		url: "/v1/diary/" + id,
		method: "delete",
	});
}
export function searchById(id) {
	return request({
		url: "/v1/diary/" + id,
		method: "get",
	});
}
export function searchByDay(datetime, groupid) {
	return request({
		url: "/v1/diary/search/day/" + datetime + "/" + groupid,
		method: "get",
	});
}
export function searchByGroup(groupid) {
	return request({
		url: "/v1/diary/search/group/" + groupid,
		method: "get",
	});
}
export function uploadimg(data) {
	return request({
		url: "/v1/upload",
		method: "post",
		data,
	});
}
export function diaryLike(id) {
	return request({
		url: "/v1/diary/like/" + id,
		method: "post",
	});
}

export function diaryDisLike(id) {
	return request({
		url: "/v1/diary/like/" + id,
		method: "delete",
	});
}
