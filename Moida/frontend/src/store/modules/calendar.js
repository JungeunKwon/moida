const state = {
	currentMonth: 1,
	firstDay: 1,
};
const mutations = {
	SET_CURRENT_MONTH: (state, payload) => {
		state.currentMonth = payload;
	},
	SET_FIRST_DAY: (state, payload) => {
		state.firstDay = payload;
	},
};
const actions = {};
export default {
	namespaced: true,
	state,
	mutations,
	actions,
};
