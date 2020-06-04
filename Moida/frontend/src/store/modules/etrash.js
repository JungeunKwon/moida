import {
	getEtrash,
	postEtrash,
	sentimentanalysis,
	getEtrashByMood,
	etrashLike,
} from "@/api/etrash";

const state = {};

const mutations = {};

const actions = {
	getEtrash({ commit }) {
		return new Promise((resolve, reject) => {
			getEtrash()
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(error);
				});
		});
	},
	postEtrash({ commit }, trash) {
		return new Promise((resolve, reject) => {
			postEtrash(trash)
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(error);
				});
		});
	},
	sentimentanalysis({ commit }, trash) {
		return new Promise((resolve, reject) => {
			sentimentanalysis(trash)
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(error);
				});
		});
	},
	getEtrashByMood({ commit }, mood) {
		return new Promise((resolve, reject) => {
			getEtrashByMood(mood)
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(error);
				});
		});
	},
	etrashLike({ commit }, id) {
		return new Promise((resolve, reject) => {
			etrashLike(id)
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
