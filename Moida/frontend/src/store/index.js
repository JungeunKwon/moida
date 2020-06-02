import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
import * as Cookies from "js-cookie";
import getters from "./getters";

Vue.use(Vuex);

const modulesFiles = require.context("./modules", true, /\.js$/);

const modules = modulesFiles.keys().reduce((modules, modulePath) => {
	const moduleName = modulePath.replace(/^\.\/(.*)\.\w+$/, "$1");
	const value = modulesFiles(modulePath);
	modules[moduleName] = value.default;
	return modules;
}, {});

const store = new Vuex.Store({
	plugins: [
		createPersistedState({
			paths: ["user"],
			getState: key => Cookies.getJSON(key),
			setState: (key, state) =>
				Cookies.set(key, state, {
					expires: 3,
					secure: false,
				}), //나중에 https통신되면 secure를 true로 바꿔주면됨
		}),
	],
	modules,
	getters,
});

export default store;
