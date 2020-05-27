const state = {
	isFirst: true,
}

const mutations = {
	TOGGLE_ISFIRST: (state) => {
		state.isFirst = !state.isFirst;
	},
}

export default {
	namespaced: true,
	state,
	mutations
};