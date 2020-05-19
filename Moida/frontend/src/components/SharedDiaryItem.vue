<template>
	<div id="sharedDiaryItem">
		<div>{{ item.name }}</div>
		<div>글쓴이 : {{ item.host_nickname }}</div>
	</div>
</template>

<script>
export default {
	data() {
		return {};
	},
	props: { item: {} },
	mounted() {
		// console.log(this.item);
	},
	computed: {},
	methods: {},
};
</script>

<style>
#sharedDiaryItem {
	display: inline-block;
	width: 200px;
	margin: 5px;
	border-radius: 5px;
	padding: 10px;
	border: 1px solid rgba(192, 192, 192, 0.363);
	/* box-shadow: 0.5px 0.5px 3px rgb(192, 192, 192); */
}
</style>
