import request from "@/utils/request";

export function getEtrash() {
	return request({
		url: "/v1/etrash",
		method: "get",
	});
}
export function postEtrash(data) {
	return request({
		url: "/v1/etrash",
		method: "post",
		data,
	});
}
export function sentimentanalysis(data) {
	return request({
		url: "/v1/etrash/sentimentanalysis",
		method: "post",
		data,
	});
}
export function getEtrashByMood(mood) {
	return request({
		url: "/v1/etrash/" + mood,
		method: "get",
	});
}
