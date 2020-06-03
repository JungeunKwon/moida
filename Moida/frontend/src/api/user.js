import request from "@/utils/request";

export function login(data) {
	return request({
		url: "/v1/signin",
		method: "post",
		data,
	});
}
export function searchByNickname(nickname) {
	return request({
		url: `/v1/search/nickname/${nickname}`,
		method: "get",
	});
}
