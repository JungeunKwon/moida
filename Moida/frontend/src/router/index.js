import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
// import About from "../views/About.vue";
import SharedDiary from "../views/SharedDiary/index.vue";
import Trash from "../views/Trash.vue";

Vue.use(VueRouter);

const routes = [
	{
		path: "/trash",
		name: "Trash",
		component: Trash,
	},

	{
		path: "/shared",
		name: "SharedDiary",
		component: SharedDiary,
	},
	{
		path: "/trash",
		name: "Trash",
		component: () =>
			import(/* webpackChunkName: "trash" */ "../views/Trash.vue"),
	},
	{
		path: "/myPage",
		name: "MyPage",
		component: () => import("../views/myPage/index.vue"),
	},
];

const router = new VueRouter({
	mode: "history",
	base: process.env.BASE_URL,
	routes,
});

export default router;
