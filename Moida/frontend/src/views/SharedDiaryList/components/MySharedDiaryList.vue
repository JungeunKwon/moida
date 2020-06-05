<template>
	<v-dialog v-model="dialog" width="700">
		<template v-slot:activator="{ on }">
			<span v-on="on">
				<slot />
			</span>
		</template>
		<div id="mySharedDiaryList">
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
	mounted() {},
};
</script>

<style>
#mySharedDiaryList {
	background-color: white;
	padding: 20px;
}
</style>