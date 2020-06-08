<template>
	<div class="habitTrackerItem">
		<div class="itemDesc">
			<div class="itemDate">{{ startDate }} ~ {{ endDate }}</div>
			<div>
				<div class="itemTitle">{{ habitTracker.subject }}</div>
				<div>{{ habitTracker.description }}</div>
			</div>
		</div>
		<div @click="joinHT">
			{{ this.habitTracker.isJoin ? "관두기" : "JOIN" }}
		</div>
	</div>
</template>

<script>
import { mapActions } from "vuex";
export default {
	props: { habitTracker: {} },
	computed: {
		startDate: function() {
			return this.habitTracker.startDate.substring(0, 10);
		},
		endDate: function() {
			return this.habitTracker.endDate.substring(0, 10);
		},
	},
	methods: {
		...mapActions("habitTracker", [
			"joinHabitTracker",
			"leaveHabitTracker",
		]),
		joinHT() {
			if (!this.habitTracker.isJoin) {
				this.joinHabitTracker({ habitid: this.habitTracker.id })
					.then(response => {
						console.log(response.data);
					})
					.catch(error => {
						console.log(error);
					});
			} else {
				this.leaveHabitTracker(this.habitTracker.id)
					.then(response => {
						console.log(response.data);
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
.habitTrackerItem {
	display: inline-block;
	text-align: left;
	width: 95%;
	border-radius: 5px;
	box-shadow: 1px 1px 3px silver;
	margin: 10px;
	padding: 5px;
}

.habitTrackerItem > div:first-child {
	float: left;
}

.habitTrackerItem > div:last-child {
	float: right;
	font-family: "kyoboHand";
	font-size: 20px;
	padding: 20px;
}

.itemDate {
	font-size: 13px;
}

.itemTitle {
	font-weight: 400;
	font-size: 18px;
}

.habitTrackerItem > div:last-child:hover {
	opacity: 0.7;
	cursor: pointer;
}
</style>
