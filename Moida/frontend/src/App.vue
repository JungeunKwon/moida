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
#app {
	height: 100%;
	overflow: hidden;
	font-family: Avenir, Helvetica, Arial, sans-serif;
	text-align: center;
	color: #2c3e50;
}
</style>
