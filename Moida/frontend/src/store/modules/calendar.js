import { getDiary } from "../../api/calendar";
import moment from "moment";
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
					console.log(response.data);
					let content = response.data.content;
					for (let idx = 0; idx < content.length; idx++) {
						let t = moment(content[idx].createDate);
						console.log(t.format("YYYY-MM-DD-HH-mm-ss"));
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
