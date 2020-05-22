import Vue from "vue";
import VueRouter from "vue-router";
import SharedDiary from "../views/SharedDiary/index.vue";
import Trash from "../views/Trash.vue";

Vue.use(VueRouter);

const routes = [

	{
		path: "/",
		name: "Trash",
		component: Trash,
	},

	{
		path: "/shared",
		name: "SharedDiary",
		component: SharedDiary,
	},


];

const router = new VueRouter({
	mode: "history",
	base: process.env.BASE_URL,
	routes,
});

export default router;