<template>
	<div id="app">
		<Layout>
			<router-view />
		</Layout>
	</div>
</template>
<script>
import Layout from "@/components/Layout.vue";
export default {
	name: "App",
	components: {
		Layout,
	},
};
</script>
<style>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap");

#app {
	height: 100%;
	overflow: hidden;
	font-family: "Noto Sans KR", sans-serif;
	font-weight: Normal;
	font-size: 15px;
	text-align: center;
}
</style>
