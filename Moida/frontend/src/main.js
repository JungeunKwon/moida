import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import "./permission";
import Element from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import "element-ui/lib/theme-chalk/display.css";
Vue.config.productionTip = false;
Vue.use(Element);
new Vue({
	vuetify,
	router,
	store,
	render: h => h(App),
}).$mount("#app");
