import {
	login,
	signUp,
	checkEmail,
	checkNickname,
	getInfo,
	searchByNickname,
} from "@/api/user";

const state = {
	isFirst: true,
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
	TOGGLE_ISFIRST: (state, flag) => {
		state.isFirst = flag;
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
	//searchByNickname
	searchByNickname({ commit }, nickname) {
		return new Promise((resolve, reject) => {
			searchByNickname(nickname)
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(error);
				});
		});
	},
	logout({ commit }) {
		commit("TOGGLE_ISFIRST", true);
		commit("SET_TOKEN", "");
	},
	// user login
	login({ commit }, userInfo) {
		console.log(userInfo);
		return new Promise((resolve, reject) => {
			login({
				email: userInfo.email.trim(),
				password: userInfo.password,
			})
				.then(response => {
					if (response.data.code == undefined) {
						commit("SET_TOKEN", response.data);
					}
					resolve(response);
				})
				.catch(error => {
					reject(error.response);
				});
		});
	},
	getInfo({ commit }, token) {
		return new Promise((resolve, reject) => {
			getInfo()
				.then(response => {
					const info = response.data;
					commit("SET_EMAIL", info.email);
					commit("SET_USERNAME", info.username);
					commit("SET_GENDER", info.gender);
					commit("SET_NICKNAME", info.nickname);
					commit("SET_PHONE", info.phone);
					commit("SET_PROFILE_IMG", info.profile_img);
					resolve();
				})
				.catch(error => reject());
		});
	},
	signUp({ commit }, signupForm) {
		const formData = new FormData();
		formData.append("email", signupForm.email);
		formData.append("password", signupForm.password);
		formData.append("phone", signupForm.phone);
		formData.append("gender", signupForm.gender);
		formData.append("username", signupForm.username);
		formData.append("nickname", signupForm.nickname);
		formData.append("uploadFile", signupForm.uploadFile);

		for (var key of formData.entries()) {
			console.log(key[0] + ", " + key[1]);
		} // 폼데이터 로그 출력법

		return new Promise((resolve, reject) => {
			signUp(formData)
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(error);
				});
		});
	},
	checkEmail({ commit }, email) {
		console.log("modules > user > action > checkEmail : " + email);
		return new Promise((resolve, reject) => {
			checkEmail(email)
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(error);
				});
		});
	},
	checkNickname({ commit }, nickname) {
		console.log("modules > user > action > checkNickname : " + nickname);
		return new Promise((resolve, reject) => {
			checkNickname(nickname)
				.then(response => {
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
