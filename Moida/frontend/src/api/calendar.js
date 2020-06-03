import request from "@/utils/request";

export function getDiary(nickname) {
	return request({
		url: `/v1/diary/search/nickname/${nickname}`,
		method: "get",
	});
}
