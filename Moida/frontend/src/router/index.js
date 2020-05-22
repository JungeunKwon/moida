import Vue from "vue";
import VueRouter from "vue-router";
// import About from "../views/About.vue";
import SharedDiary from "../views/SharedDiary/index.vue";
//import Trash from "../views/Trash/index.vue";

Vue.use(VueRouter);

const routes = [
	{
		path: "/shared",
		name: "SharedDiary",
		component: SharedDiary,
	},
	{
		path: "/trash",
		name: "Trash",
		component: () =>
			import(/* webpackChunkName: "trash" */ "../views/Trash/index.vue"),
	},
];

const router = new VueRouter({
	mode: "history",
	base: process.env.BASE_URL,
	routes,
});

export default router;
