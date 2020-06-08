import {
	getRoom,
	enterRoom,
	getRoomInfo,
	createRoom,
	getAllRooms,
	roomCheck,
} from "../../api/chat";
const state = {
	targetNickname: "",
	tartgetImgUrl: "",
	isShare: false,
	groupid: 0,
};

const mutations = {
	SET_TARGET_NICKNAME: (state, data) => {
		state.targetNickname = data;
	},
	SET_TARGET_IMG_URL: (state, data) => {
		state.tartgetImgUrl = data;
	},
	SET_IS_SHARE: (state, data) => {
		state.isShare = data;
	},
	SET_GROUP_ID: (state, data) => {
		state.groupid = data;
	},
};

const actions = {
	getRoom({ commit }) {
		return new Promise((resolve, reject) => {
			getRoom()
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(reject);
				});
		});
	},
	enterRoom({ commit }, roomuuid) {
		return new Promise((resolve, reject) => {
			enterRoom(roomuuid)
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(reject);
				});
		});
	},
	getRoomInfo({ commit }, roomuuid) {
		return new Promise((resolve, reject) => {
			getRoomInfo(roomuuid)
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(reject);
				});
		});
	},
	createRoom({ commit }, targetNickname) {
		return new Promise((resolve, reject) => {
			createRoom(targetNickname)
				.then(response => {
					if (response.data.code != undefined) {
						alert(response.data.msg);
						return;
					}
					resolve(response);
				})
				.catch(error => {
					reject(reject);
				});
		});
	},
	getAllRooms({ commit }, user) {
		return new Promise((resolve, reject) => {
			getAllRooms(user)
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(reject);
				});
		});
	},
	roomCheck({ commit }, data) {
		return new Promise((resolve, reject) => {
			roomCheck(data.host, data.user)
				.then(response => {
					if (response.data.code != undefined) {
						alert(response.data.msg);
						return;
					}
					resolve(response);
				})
				.catch(error => {
					reject(reject);
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
