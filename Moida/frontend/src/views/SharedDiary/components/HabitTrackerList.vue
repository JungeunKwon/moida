<template>
	<v-dialog v-model="dialog" width="500">
		<template v-slot:activator="{ on }">
			<div v-on="on">
				<slot />
			</div>
		</template>
		<v-card style="padding: 10px;">
			<HabitTrackerItem
				v-for="(item, idx) in habitTrackers"
				:habitTracker="item"
				:key="idx"
			/>
		</v-card>
	</v-dialog>
</template>

<script>
import { mapActions, mapMutations } from "vuex";
import HabitTrackerItem from "./HabitTrackerItem";
export default {
	props: { sharedDiaryId: {}, myHabitTrackers: {} },
	components: { HabitTrackerItem },
	data() {
		return {
			dialog: false,
			habitTrackers: [],
		};
	},
	watch: {
		sharedDiaryId: function() {
			this.getHT();
		},
	},
	computed: {
		// 계산된 getter
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
			for (let i = 0; i < this.habitTrackers.length; i++) {
				let flag = false;
				for (let j = 0; j < this.myHabitTrackers.length; j++) {
					if (
						this.habitTrackers[i].id == this.myHabitTrackers[j].id
					) {
						flag = true;
					}
				}
				this.$set(this.habitTrackers[i], "isJoin", flag);
			}
		},
	},
};
</script>

<style lang="scss" scoped></style>
