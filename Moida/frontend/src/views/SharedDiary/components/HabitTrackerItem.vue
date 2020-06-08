<template>
	<div id="habitTrackerItem">
		<div id="dDay">D-{{dDay}}</div>
		<div id="htiSubject">{{habitTracker.subject}}</div>
		<div id="current" @click="doHT">{{habitTracker.isDone ? "했음^-^" : "안했음ㅠ"}}</div>
	</div>
</template>

<script>
import { mapActions } from "vuex";
export default {
	name: "HabitTrackerItem",
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
		...mapActions("habitTracker", ["doHabitTracker"]),
		doHT() {
			console.log({
				habitid: this.habitTracker.id,
				clearDate: new Date().toISOString().substring(0, 10),
				hdescription: "완료",
			});
			this.doHabitTracker({
				habitid: this.habitTracker.id,
				clearDate: new Date().toISOString().substring(0, 10),
				hdescription: "완료",
			})
				.then(response => {
					resolve(response);
				})
				.catch(error => {
					reject(error);
				});
		},
	},
};
</script>

<style>
#dDay,
#current {
	font-family: "KyoboHand";
}

#habitTrackerItem {
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

#habitTrackerItem div:first-child {
	margin-right: 8px;
}
</style>