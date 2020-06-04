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
function getClass(isPrivate, groupid) {
	if (groupid) return "group";
	return isPrivate === 1 ? "public" : isPrivate === 2 ? "friend" : "private";
}
const actions = {
	getDiary({ commit }, nickname) {
		return new Promise((resolve, reject) => {
			getDiary(nickname)
				.then(response => {
					let result = [];
					let content = response.data.content;
					for (let idx = 0; idx < content.length; idx++) {
						let t = moment(content[idx].createDate);
						result.push({
							id: content[idx].id,
							day: moment(content[idx].createDate),
							content: content[idx].description,
							cssClass: getClass(
								content[idx].isPrivate,
								content[idx].groupid,
							),
						});
					}
					console.log(result);
					resolve(result);
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
