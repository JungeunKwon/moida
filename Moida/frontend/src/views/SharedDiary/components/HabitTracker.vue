<template>
	<div id="habitTracker">
		<div id="habitTitle">
			<CreateHabitTracker :sharedDiaryId="sharedDiaryId">
				<img style="float: right;" src="../../../assets/icons/plus.png" width="20px" />
			</CreateHabitTracker>

			<HabitTrackerList :sharedDiaryId="sharedDiaryId" :myHabitTrackers="myHabitTrackers">
				<div style="cursor: pointer; float: right; margin-right: 5px;">HabitTacker</div>
			</HabitTrackerList>
		</div>
		<div id="htItem">
			<HabitTrackerItem v-for="(item, idx) in myHabitTrackers" :key="idx" :habitTracker="item" />
		</div>
	</div>
</template>

<script>
import { mapActions, mapMutations } from "vuex";
import CreateHabitTracker from "./CreateHabitTracker";
import HabitTrackerItem from "./HabitTrackerItem";
import HabitTrackerList from "./HabitTrackerList";
export default {
	name: "habitTracker",
	props: { sharedDiaryId: {} },
	components: { CreateHabitTracker, HabitTrackerItem, HabitTrackerList },
	data() {
		return {
			myHabitTrackers: [],
			myHabitTrackerRecord: [],
			today: new Date().toISOString().substring(0, 10),
		};
	},
	mounted() {
		console.log("오늘" + this.today);
	},
	watch: {
		sharedDiaryId: function() {
			console.log("sharedDiaryId :: " + this.sharedDiaryId);
			this.getMyHT();
		},
	},
	methods: {
		...mapActions("habitTracker", [
			"getMyHabitTracker",
			"getMyHabitTrackerRecord",
		]),
		getMyHT() {
			this.getMyHabitTracker(this.sharedDiaryId)
				.then(async response => {
					console.log("내 해빗 트래커 목록");
					console.log(response.data);
					this.myHabitTrackers = response.data;
					this.getMyHTRecord();
				})
				.catch(error => {
					console.log(error);
				});
		},
		async getMyHTRecord(htId) {
			for (let i = 0; i < this.myHabitTrackers.length; i++) {
				await this.getMyHabitTrackerRecord(this.myHabitTrackers[i].id)
					.then(response => {
						console.log("내 해빗 트래커 기록");
						console.log(response.data);
						this.myHabitTrackerRecord = response.data;

						var result = false;
						for (
							let j = 0;
							j < this.myHabitTrackerRecord.length;
							j++
						) {
							if (
								this.myHabitTrackerRecord[j].cleardate ==
								this.today
							) {
								result = true;
							}
						}
						this.$set(this.myHabitTrackers[i], "isDone", result);
					})
					.catch(error => {
						console.log(error);
					});
			}
		},
	},
};
</script>

<style>
#habitTracker {
	text-align: left;
	height: 100%;
}

#habitTitle {
	position: absolute;
	top: -30px;
	right: 5px;
	width: 135px;
	height: 30px;
	text-align: right;
	line-height: 27px;
	background-color: #c7ceea;
	padding: 5px;
	border-radius: 10px 10px 0 0;
	box-shadow: 1px 1px 3px rgba(128, 128, 128, 0.486);
}

#habitTitle > div {
	margin-right: 3px;
	font-family: "KyoboHand";
	font-size: 18px;
}

#habitTitle img:hover {
	opacity: 0.5;
	cursor: pointer;
}

#htItem {
	height: 100%;
	overflow-y: auto;
}

#htItem::-webkit-scrollbar {
	display: none;
}
</style>
