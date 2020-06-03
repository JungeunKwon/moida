import Vue from "vue";
import VueRouter from "vue-router";
import SharedDiary from "../views/SharedDiary/index.vue";
import Login from "../components/Login.vue";
import SignUp from "../components/SignUp.vue";
import WriteDiary from "../views/Diary/writediary.vue";
import Diary from "../views/Diary/index.vue";
Vue.use(VueRouter);

const routes = [
	{
		path: "/login",
		name: "Login",
		component: Login,
	},
	{
		path: "/signUp",
		name: "SignUp",
		component: SignUp,
	},
	{
		path: "/shared",
		name: "SharedDiary",
		component: SharedDiary,
	},
	{
		path: "/diary",
		name: "Diary",
		component: Diary,
	},
	{
		path: "/writediary",
		name: "WriteDiary",
		component: WriteDiary,
	},
	{
		path: "/",
		name: "Trash",
		component: () =>
			import(/* webpackChunkName: "trash" */ "../views/Trash/index.vue"),
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
