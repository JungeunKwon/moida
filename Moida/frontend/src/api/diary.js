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
export function deletetDiary(data) {
	return request({
		url: "/v1/diary",
		method: "delete",
		data,
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
