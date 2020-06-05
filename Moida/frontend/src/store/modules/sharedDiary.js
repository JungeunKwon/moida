import {
	getSharedDiaryDetail,
	getSharedDiaryUser,
	getSharedDiary,
} from "@/api/sharedDiary";

const state = {
	writingSD: false,
	diaryId: 0,
};

const mutations = {
	TOGGLE_WRITINGSD: (state, flag) => {
		state.writingSD = flag;
	},
	SET_DIARYID: (state, id) => {
		state.diaryId = id;
	},
};

const actions = {
	getSharedDiaryDetail({ commit }, diaryId) {
		return new Promise((resolve, reject) => {
			getSharedDiaryDetail(diaryId)
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(error);
				});
		});
	},
	getSharedDiary({ commit }, diaryId) {
		return new Promise((resolve, reject) => {
			getSharedDiary(diaryId)
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(error);
				});
		});
	},
	getSharedDiaryUser({ commit }, diaryId) {
		return new Promise((resolve, reject) => {
			getSharedDiaryUser(diaryId)
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
