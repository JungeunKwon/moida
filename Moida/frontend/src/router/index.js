import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
// import About from "../views/About.vue";
import SharedDiary from "../views/SharedDiary.vue";
import Trash from "../views/Trash.vue";
Vue.use(VueRouter);

const routes = [
	{
		path: "/",
		name: "Home",
		component: Home,
	},
	{
		path: "/about",
		name: "About",
		component: () =>
			import(/* webpackChunkName: "about" */ "../views/About.vue"),
	},
	{
		path: "/shared",
		name: "SharedDiary",
		component: SharedDiary,
	},
	{
		path: "/trash",
		name: "Trash",
		component: Trash,
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
