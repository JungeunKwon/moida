<template>
	<div id="sharedDiary">
		<!-- 방장, 달력, 해빗트래커, 리스트 -->
		<div id="diarySubjectDiv">
			<div>{{ detail.subject }}</div>
			<SharedDiaryInfo :detail="detail">
				<img src="../../assets/icons/info.png" width="20px" />
			</SharedDiaryInfo>
		</div>
		<HabitTracker></HabitTracker>
		<v-date-picker id="sharedCal" v-model="picker" color="purple lighten-3" @change="test()"></v-date-picker>

		<div id="writeDiaryDiv">
			<v-btn @click="openWrite">
				<v-icon x-large>mdi-pencil</v-icon>
			</v-btn>
		</div>
	</div>
</template>
<script>
import { mapActions, mapMutations } from "vuex";
import SharedDiaryInfo from "./components/SharedDiaryInfo";
import HabitTracker from "./components/HabitTracker";
export default {
	components: {
		SharedDiaryInfo,
		HabitTracker,
	},
	data() {
		return {
			detail: {},
			picker: "",
			timestamp: "",
		};
	},
	mounted() {
		this.getSharedDiaryDetail(this.$route.params.id)
			.then(response => {
				this.detail = response.data;
				this.getSD();
			})
			.catch(error => {
				console.log(error);
			});
		this.getNow();
	},
	computed: {},
	methods: {
		...mapActions("sharedDiary", [
			"getSharedDiaryDetail",
			"getSharedDiary",
		]),
		...mapMutations("sharedDiary", ["TOGGLE_WRITINGSD", "SET_DIARYID"]),
		openWrite() {
			this.TOGGLE_WRITINGSD(true);
			this.SET_DIARYID(this.detail.id);
			console.log("아듸 세팅 " + this.detail.id);
			this.$router.push("/writediary");
		},
		getSD() {
			this.getSharedDiary(this.detail.id)
				.then(response => {
					console.log("여기여기!!!!!공다여기!!!!!!!!!");
					console.log(response.data);
				})
				.catch(error => {
					console.log(error);
				});
		},
		test() {
			console.log(this.picker);
		},
		getNow() {
			const today = new Date();
			const date =
				today.getFullYear() +
				"-" +
				(today.getMonth() + 1 < 10 ? "0" : "") +
				(today.getMonth() + 1) +
				"-" +
				(today.getDate() < 10 ? "0" : "") +
				today.getDate();
			this.picker = date;
			// const time =
			// 	(today.getHours() < 10 ? "0" : "") +
			// 	today.getHours() +
			// 	":" +
			// 	(today.getMinutes() < 10 ? "0" : "") +
			// 	today.getMinutes() +
			// 	":" +
			// 	(today.getSeconds() < 10 ? "0" : "") +
			// 	today.getSeconds();
			// const dateTime = date + " " + time;
			// this.timestamp = dateTime;
			// console.log(this.timestamp);
		},
	},
};
</script>

<style>
/* 달력 색상 변경 */
.v-picker__title {
	color: black !important;
}

#sharedCal {
	position: absolute;
	left: 10px;
	top: 50px;
}

#sharedCal > div.v-picker__title.purple.lighten-3 {
	background-color: white !important;
}

#diarySubjectDiv {
	position: fixed;
	font-family: KyoboHand;
	font-size: 20px;
	background-color: white;
	padding: 10px 15px 10px 15px;
	top: 35px;
	left: 10px;
	border-radius: 5px;
	box-shadow: 1px 1px 5px rgba(128, 128, 128, 0.61);
}

#diarySubjectDiv div {
	float: left;
}

#diarySubjectDiv img {
	margin-left: 5px;
	float: left;
	cursor: pointer;
	margin-top: 3.5px;
}

#diarySubjectDiv img:hover {
	opacity: 0.5;
}

#writeDiaryDiv {
	position: absolute;
	bottom: 30px;
	right: 10px;
	width: 50px;
}

#writeDiaryDiv > button {
	padding: 10px;
}
</style>
