import axios from "axios";
import store from "@/store";

const service = axios.create({
	baseURL: "http://k02d1061.p.ssafy.io/", // url = base url + request url
	timeout: 5000,
});

service.interceptors.request.use(
	config => {
		if (store.getters.token) {
			config.headers["X-AUTH-TOKEN"] = getToken();
			config.headers["Access-Control-Allow-Origin"] = "*";
		}
		return config;
	},
	error => {
		return Promise.reject(error);
	},
);

service.interceptors.response.use(
	response => {
		const res = response.data;
		if (response.status !== 200) {
			return Promise.reject(new Error(res.message || "Error"));
		} else {
			return response;
		}
	},
	error => {
		// console.log('err' + error)
		return Promise.reject(error);
	},
);

export default service;
