<template>
	<v-dialog v-model="dialog" width="500">
		<template v-slot:activator="{ on }">
			<div v-on="on">
				<slot />
			</div>
		</template>
		<v-card>
			<div v-for="(item, idx) in habitTrackers" :key="idx">{{item}}</div>
		</v-card>
	</v-dialog>
</template>

<script>
import { mapActions, mapMutations } from "vuex";
export default {
	props: { sharedDiaryId: {}, myHabitTrackers: {} },
	data() {
		return {
			dialog: false,
			habitTrackers: [],
		};
	},
	watch: {
		sharedDiaryId: function() {
			console.log("띠로롱 :: " + this.sharedDiaryId);
			this.getHT();
		},
	},
	mounted() {},
	methods: {
		...mapActions("habitTracker", ["getHabitTracker"]),
		async getHT() {
			await this.getHabitTracker(this.sharedDiaryId)
				.then(response => {
					console.log("해빗 트래커 목록");
					console.log(response.data);
					this.habitTrackers = response.data;
				})
				.catch(error => {
					console.log(error);
				});

			this.isJoin();
		},
		isJoin() {
			console.log("낶거");
			console.log(this.myHabitTrackers);
			console.log("전체");
			console.log(this.habitTrackers);

			for (let i = 0; i < this.habitTrackers.length; i++) {
				let flag = false;
				for (let j = 0; j < this.myHabitTrackers.length; j++) {
					if (
						this.habitTrackers[i].id == this.myHabitTrackers[j].id
					) {
						flag = true;
					}
				}
				his.$set(this.myHabitTrackers[i], "isJoin", flag);
			}
			console.log("꺄핳");
			console.log(this.habitTrackers);
		},
	},
};
</script>

<style lang="scss" scoped>
</style>