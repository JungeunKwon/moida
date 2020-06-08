import request from "@/utils/request";

export function deleteFollow(id) {
	return request({
		url: `/v1/follow/${id}`,
		method: "delete",
	});
}
export function addFollow(id) {
	return request({
		url: `/v1/follow/${id}`,
		method: "post",
	});
}
export function getFollower(id) {
	return request({
		url: `/v1/follower/${id}`,
		method: "get",
	});
}
export function getFollowing(id) {
	return request({
		url: `/v1/following/${id}`,
		method: "get",
	});
}
