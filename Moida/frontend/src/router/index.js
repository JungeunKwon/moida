import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
// import About from "../views/About.vue";
import SharedDiary from "../views/SharedDiary.vue";

Vue.use(VueRouter);

const About = () => import('../views/About.vue');
const routes = [{
		path: "/",
		name: "Home",
		component: Home
	},
	{
		path: "/about",
		name: "About",
		component: About,
	},
	{
		path: "/shared",
		name: "SharedDiary",
		component: SharedDiary
	}
];

const router = new VueRouter({
	mode: "history",
	base: process.env.BASE_URL,
	routes
});

export default router;