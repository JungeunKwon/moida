import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import "./permission";
import Element from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import "element-ui/lib/theme-chalk/display.css";
import VueMasonry from "vue-masonry-css";
import VueLazyload from "vue-lazyload";
import ImgInputer from 'vue-img-inputer'
import 'vue-img-inputer/dist/index.css'

Vue.use(VueMasonry);
Vue.use(VueLazyload, {
	preLoad: 1.3,
	error: "dist/error.png",
	loading: "dist/loading.gif",
	attempt: 1,
});
Vue.config.productionTip = false;
Vue.component('ImgInputer', ImgInputer);

new Vue({
	vuetify,
	router,
	store,
	render: h => h(App),
}).$mount("#app");