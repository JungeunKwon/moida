<template>
	<div id="sharedDiaryItem">{{ sharedDiary }}</div>
</template>

<script>
export default {
	name: "sharedDiaryItem",
	props: {
		sharedDiary: {},
	},
	mounted() {
		this.removeTags();
	},
	methods: {
		async removeTags() {
			this.sharedDiary.description = this.sharedDiary.description
				.replace(/(<([^>]+)>)/gi, "")
				.replace("&nbsp;", " ");
		},
	},
};
</script>

<style>
</style>