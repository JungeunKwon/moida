import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
	state: {
		nickname: "",
	},
	mutations: {
		changeTest: function(state, payload) {
			state.nickname = payload;
		},
	},
	actions: {},
	modules: {},
});
