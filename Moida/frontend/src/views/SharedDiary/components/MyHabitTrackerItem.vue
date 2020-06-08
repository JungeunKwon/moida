<template>
	<div id="myHabitTrackerItem">
		<div id="dDay">D-{{ dDay }}</div>
		<div id="htiSubject">{{ habitTracker.subject }}</div>
		<div id="current" @click="doHT">
			{{ habitTracker.isDone ? "했음^-^" : "안했음ㅠ" }}
		</div>
	</div>
</template>

<script>
import { mapActions } from "vuex";
import moment from "moment";
export default {
	name: "MyHabitTrackerItem",
	props: { habitTracker: {} },
	data() {
		return {
			dDay: 0,
		};
	},
	mounted() {
		let diff =
			new Date(this.habitTracker.endDate.substr(0, 10)) -
			new Date(this.habitTracker.startDate.substr(0, 10));
		this.dDay = Math.ceil(diff / (1000 * 3600 * 24));
	},
	methods: {
		...mapActions("habitTracker", [
			"doHabitTracker",
			"deleteDoHabitTracker",
		]),
		doHT() {
			if (!this.habitTracker.isDone) {
				console.log({
					habitid: this.habitTracker.id,
					clearDate: moment()
						.locale("ko")
						.format("YYYY-MM-DD"),
					description: "완료",
				});
				this.doHabitTracker({
					habitid: this.habitTracker.id,
					clearDate: moment()
						.locale("ko")
						.format("YYYY-MM-DD"),
					description: "완료",
				})
					.then(response => {
						this.habitTracker.isDone = true;
						console.log("아듸!!!!" + response.data);
						this.habitTracker.doHabitId = response.data;
					})
					.catch(error => {
						console.log(error);
					});
			} else {
				console.log(this.habitTracker);
				this.deleteDoHabitTracker(this.habitTracker.doHabitId)
					.then(response => {
						this.habitTracker.isDone = false;
						this.habitTracker.doHabitId = -1;
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
#dDay,
#current {
	font-family: "KyoboHand";
}

#myHabitTrackerItem {
	display: inline-block;
	width: 100%;
	padding: 10px;
	border-bottom: 1px solid rgba(192, 192, 192, 0.432);
	line-height: 30px;
}

#dDay,
#htiSubject {
	float: left;
}

#htiSubject {
	width: 160px;
	white-space: nowrap;
	text-overflow: ellipsis;
	overflow: hidden;
	text-align: left;
}

#dDay {
	font-size: 22px;
}

#current {
	float: right;
	font-size: 17px;
	cursor: pointer;
}

#current:hover {
	opacity: 0.5;
}

#myHabitTrackerItem div:first-child {
	margin-right: 8px;
}
</style>
