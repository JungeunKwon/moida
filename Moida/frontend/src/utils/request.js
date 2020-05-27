import axios from "axios";
import store from "@/store";

const service = axios.create({
	// baseURL: "http://192.168.77.80:8080", // url = base url + request url
	baseURL: "http://192.168.77.10:8080", // url = base url + request url
	timeout: 5000,
});

service.interceptors.request.use(
	config => {
		if (store.getters.token) {
			config.headers["X-AUTH-TOKEN"] = "";
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