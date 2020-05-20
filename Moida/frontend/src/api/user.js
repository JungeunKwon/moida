import request from "@/utils/request";

export function login(data) {
	return request({
		url: "/v1/signin",
		method: "post",
		data,
	});
}
