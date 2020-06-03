import { getDiary } from "../../api/calendar";

const state = {
	locale: "ko",
	currentMonth: 0,
	firstDay: 1,
};
const mutations = {
	//emitChangeMonth
	SET_CURRENT_MONTH: (state, payload) => {
		state.currentMonth = payload;
	},
	SET_FIRST_DAY: (state, payload) => {
		state.firstDay = payload;
	},
};
const actions = {
	getDiary({ commit }, nickname) {
		return new Promise((resolve, reject) => {
			getDiary(nickname)
				.then(response => {
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
