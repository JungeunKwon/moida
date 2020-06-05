import {
	getDiary,
	postDiary,
	putDiary,
	deletetDiary,
	searchById,
	searchByDay,
	searchByGroup,
	uploadimg,
	diaryLike,
	diaryDisLike,
} from "@/api/diary";

const state = {};
const mutations = {};

const actions = {
	getDiary() {
		return new Promise((resolve, reject) => {
			getDiary()
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(error);
				});
		});
	},
	postDiary({ commit }, diaryinfo) {
		return new Promise((resolve, reject) => {
			postDiary(diaryinfo)
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(error);
				});
		});
	},
	putDiary({ commit }, diaryinfo) {
		return new Promise((resolve, reject) => {
			putDiary(diaryinfo)
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(error);
				});
		});
	},
	deletetDiary({ commit }, id) {
		return new Promise((resolve, reject) => {
			deletetDiary(id)
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(error);
				});
		});
	},
	searchById({ commit }, id) {
		return new Promise((resolve, reject) => {
			searchById(id)
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(error);
				});
		});
	},
	searchByDay(datetime, groupid) {
		return new Promise((resolve, reject) => {
			searchByDay(datetime, groupid)
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(error);
				});
		});
	},
	searchByGroup(groupid) {
		return new Promise((resolve, reject) => {
			searchByGroup(groupid)
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(error);
				});
		});
	},
	uploadimg({ commit }, data) {
		const formData = new FormData();
		formData.append("uploadFile", data.uploadFile);
		return new Promise((resolve, reject) => {
			uploadimg(formData)
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(error);
				});
		});
	},
	diaryLike({ commit }, id) {
		return new Promise((resolve, reject) => {
			diaryLike(id)
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(error);
				});
		});
	},
	diaryDisLike({ commit }, id) {
		return new Promise((resolve, reject) => {
			diaryDisLike(id)
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
