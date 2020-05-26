import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import ImgInputer from 'vue-img-inputer'
import 'vue-img-inputer/dist/index.css'

Vue.config.productionTip = false;
Vue.component('ImgInputer', ImgInputer);

new Vue({
	vuetify,
	router,
	store,
	render: h => h(App),
}).$mount("#app");