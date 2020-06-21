import request from "@/utils/request";

export function getDiary(nickname) {
	return request({
		url: `/v1/diary/find/nickname/${nickname}`,
		method: "get",
	});
}
