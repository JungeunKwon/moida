<template>
	<v-app id="app">
		<First v-if="isFirst" id="app-view">
			<router-view :key="$route.fullPath" />
		</First>
		<Layout v-else id="app-view">
			<v-fade-transition mode="out-in">
				<router-view :key="$route.fullPath" />
			</v-fade-transition>
		</Layout>
	</v-app>
</template>

<script>
import Layout from "@/components/Layout";
import First from "@/components/First";
import { mapState, mapGetters, mapMutations } from "vuex";

export default {
	components: {
		Layout,
		First,
	},
	mounted() {},
	computed: {
		...mapGetters(["isFirst"]),
	},
	data() {
		return {};
	},
	methods: {},
};
</script>
<style>
@import "./assets/font/font.css";
#app {
	background-image: url("./assets/images/background_2.jpg");
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
