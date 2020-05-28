import { login, signUp, checkEmail, checkNickname } from "@/api/user";

const state = {
	isFirst: true,
	token: "",
	email: "",
	username: "",
	gender: "",
	nickname: "Nickname",
	phone: "",
	profile_img:
		"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
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
	//test
	// setTest({
	// 	commit
	// }, text) {
	// 	commit("SET_USERNAME", text);
	// 	commit("SET_PHONE", text);
	// },
	logout({
		commit
	}) {
		commit("TOGGLE_ISFIRST", true);
		commit("SET_TOKEN", "");
	},
	// user login
	login({ commit }, userInfo) {
		userInfo;
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
