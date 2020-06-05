import request from "@/utils/request";

export function getCommentById(diaryid) {
	return request({
		url: "/v1/comment/" + diaryid,
		method: "get",
	});
}
export function postComment(data) {
	return request({
		url: "/v1/comment",
		method: "post",
		data,
	});
}
export function putComment(data) {
	return request({
		url: "/v1/comment",
		method: "put",
		data,
	});
}
export function deletetComment(commentid) {
	return request({
		url: "/v1/comment/" + commentid,
		method: "delete",
	});
}
