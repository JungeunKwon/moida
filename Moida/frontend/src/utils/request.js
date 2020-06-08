import axios from "axios";
import store from "@/store";

const service = axios.create({
	//baseURL: "http://192.168.43.239:8080", // url = base url + request url
	baseURL: "https://k02d1061.p.ssafy.io", // url = base url + request url
	timeout: 5000,
});

service.interceptors.request.use(
	config => {
		if (store.getters.token) {
			config.headers["X-AUTH-TOKEN"] = store.getters.token;
			config.headers["Access-Control-Allow-Origin"] = "*";
			config.headers["Content-Type"] = "application/json; charset=utf-8";
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
		console.log("response");
		console.log(res);
		if (response.status !== 200) {
			return Promise.reject(new Error(res.message || "Error"));
		} else {
			return response;
		}
	},
	error => {
		return Promise.reject(error);
	},
);

export default service;
