import request from "@/utils/request";

export function login(data) {
	return request({
		url: "/v1/signin",
		method: "post",
		data,
	});
}
export function getInfo() {
	return request({
		url: "/v1/account",
		method: "get",
	});
}

export function signUp(data) {
	return request({
		url: "/v1/signup",
		method: "post",
		data,
	});
}

export function checkNickname(data) {
	return request({
		url: `/v1/check/nickname/${data}`,
		method: "get",
	});
}

export function checkEmail(data) {
	return request({
		url: `/v1/check/email/${data}`,
		method: "get",
	});
}
