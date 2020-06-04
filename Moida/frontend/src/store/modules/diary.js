import {
	getDiary,
	postDiary,
	putDiary,
	deletetDiary,
	searchById,
	searchByDay,
	searchByGroup,
	uploadimg,
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
	putDiary(diaryinfo) {
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
	deletetDiary(id) {
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
};

export default {
	namespaced: true,
	state,
	mutations,
	actions,
};
