// import router from "./router";
// import store from "./store";

// const whiteList = ["/login"]; // no redirect whitelist

// router.beforeEach(async (to, from, next) => {
// 	const hasToken = store.getters.token;
// 	if (hasToken) {
// 		if (to.path === "/login") {
// 			next({ path: "/" });
// 		} else {
// 			try {
// 				//to-do token유효성 검사
// 				await store.dispatch("user/getInfo");
// 				next({ ...to, replace: true });
// 			} catch (error) {
// 				//유효성 검사를 통과 못한 경우 -> token값을 지우고 login페이지로 다시 보내야함
// 				await store.dispatch("user/resetToken");
// 				next("/login");
// 			}
// 		}
// 	} else {
// 		//토큰이 없을 때
// 		if (whiteList.indexOf(to.path) !== -1) {
// 			//토큰이 없어도 접근할 수 있는 페이지의 경우 그냥 보내준다
// 			next();
// 		} else {
// 			//없으면 다시 로그인하러 가야지
// 			next("/login");
// 		}
// 	}
// });

// router.afterEach(() => {});
