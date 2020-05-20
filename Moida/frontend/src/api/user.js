export function login(data) {
	return myRequest({
		url: "/v1/signin",
		method: "post",
		data,
	});
}
