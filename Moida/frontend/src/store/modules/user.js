import { login } from "@/api/user";

const state = {
	token: "",
	email: "",
	username: "",
	gender: "",
	nickname: "",
	phone: "",
	profile_img: "",
};

const mutations = {
	SET_TOKEN: (state, token) => {
		state.token = token;
	},
	SET_EMAIL: (state, email) => {
		state.email = email;
	},
	SET_USERNAME: (state, username) => {
		state.username = username;
	},
	SET_GENDER: (state, gender) => {
		state.gender = gender;
	},
	SET_NICKNAME: (state, nickname) => {
		state.nickname = nickname;
	},
	SET_PHONE: (state, phone) => {
		state.phone = phone;
	},
	SET_PROFILE_IMG: (state, profile_img) => {
		state.profile_img = profile_img;
	},
};

const actions = {
	// user login
	login({ commit }, userInfo) {
		const { email, password } = userInfo;
		return new Promise((resolve, reject) => {
			login({
				email: username.trim(),
				password: password,
			})
				.then(response => {
					commit("SET_TOKEN", response.data);
					resolve(response);
				})
				.catch(error => {
					reject(error);
				});
		});
	},
};

export default {
	namespaced: true,
	state,
	mutations,
	actions,
};
