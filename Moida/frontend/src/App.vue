<template>
	<v-app id="app">
		<First v-if="isFirst" id="app-view">
			<router-view :key="$route.fullPath" />
		</First>
		<Layout v-else id="app-view">
			<v-fade-transition mode="out-in">
				<router-view :key="$route.fullPath" />
			</v-fade-transition>
			<GoTop />
		</Layout>
	</v-app>
</template>

<script>
import Layout from "@/components/Layout";
import GoTop from "@/components/GoTop";
import First from "@/components/First";
import { mapState, mapGetters, mapMutations } from "vuex";

export default {
	components: {
		Layout,
		GoTop,
		First,
	},
	mounted() {
		// console.log(this.$store.getters.isFirst);
		// // this.$store.commit("auth/TOGGLE_ISFIRST");
		// this.TOGGLE_ISFIRST();
		// console.log(this.$store.getters.isFirst);
	},
	computed: {
		// ...mapState("auth", ["isFirst"]),
		...mapGetters(["isFirst"]),
	},
	data() {
		return {};
	},
	methods: {},
};
</script>
<style>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap");
@font-face {
	font-family: "KyoboHand";
	src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.0/KyoboHand.woff")
		format("woff");
	font-weight: normal;
	font-style: normal;
}

@font-face {
	font-family: "Recipekorea";
	src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/Recipekorea.woff")
		format("woff");
	font-weight: normal;
	font-style: normal;
}
#app {
	/* background-image: url("./assets/images/background.png"); */
	background-repeat: no-repeat;
	background-size: cover;
	height: 100%;
	overflow: hidden;
	font-family: "Noto Sans KR", sans-serif;
	font-weight: 300;
	font-size: 15px;
	text-align: center;
}
::-webkit-scrollbar {
	width: 3px; /* 세로축 스크롤바 길이 */
}
::-webkit-scrollbar-track-piece {
	background-color: white;
}
::-webkit-scrollbar-thumb {
	border-radius: 8px;
	background-color: rgba(128, 128, 128, 0.295);
}
::-webkit-scrollbar-thumb:hover {
	background-color: rgba(128, 128, 128, 0.808);
}
</style>
