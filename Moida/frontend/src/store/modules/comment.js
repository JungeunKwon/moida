import {
	getCommentById,
	postComment,
	putComment,
	deletetComment,
} from "@/api/comment";

const state = {};
const mutations = {};

const actions = {
	getCommentById({ commit }, diaryid) {
		return new Promise((resolve, reject) => {
			getCommentById(diaryid)
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(error);
				});
		});
	},
	postComment({ commit }, commentinfo) {
		return new Promise((resolve, reject) => {
			postComment(commentinfo)
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(error);
				});
		});
	},
	putComment({ commit }, commentinfo) {
		return new Promise((resolve, reject) => {
			putComment(commentinfo)
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(error);
				});
		});
	},
	deletetComment({ commit }, diaryid) {
		return new Promise((resolve, reject) => {
			deletetComment(diaryid)
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
