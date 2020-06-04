<template>
	<v-dialog v-model="dialog" width="700">
		<template v-slot:activator="{ on }">
			<span v-on="on">
				<slot />
			</span>
		</template>
		<div>
			<div v-for="(diary, idx) in myDiaries" :key="idx">{{diary}}</div>
		</div>
	</v-dialog>
</template>

<script>
export default {
	name: "MySharedDiaryList",
	props: { myDiaries: {} },
	data() {
		return {
			dialog: false,
		};
	},
};
</script>

<style>
</style>