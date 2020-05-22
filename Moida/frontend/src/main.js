import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import VueMasonry from "vue-masonry-css";
import VueLazyload from "vue-lazyload";

Vue.use(VueMasonry);
Vue.use(VueLazyload, {
	preLoad: 1.3,
	error: "dist/error.png",
	loading: "dist/loading.gif",
	attempt: 1,
});
Vue.config.productionTip = false;

new Vue({
	vuetify,
	router,
	store,
	render: h => h(App),
}).$mount("#app");