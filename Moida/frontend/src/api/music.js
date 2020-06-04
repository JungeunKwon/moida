import request from "@/utils/request";

export function getMusic() {
	return request({
		url: "/v1/music",
		method: "get",
	});
}
export function postMusic(data) {
	return request({
		url: "/v1/music",
		method: "post",
		data,
	});
}
export function findByMood(mood) {
	return request({
		url: "/v1/music/findByMood/" + mood,
		method: "get",
	});
}
export function selectMusic(data) {
	return request({
		url: "/v1/music/select",
		method: "post",
		data,
	});
}
