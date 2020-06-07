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

		<v-date-picker id="sharedCal" v-model="picker" color="#fadf99" @change="test()" />

		<div id="sharedDiaryMain">
			<SharedDiaryItem v-for="(item, idx) in sharedDiarys" :key="idx" :sharedDiary="item" />
		</div>

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
import SharedDiaryItem from "./components/SharedDiaryItem";
export default {
	components: {
		SharedDiaryInfo,
		HabitTracker,
		SharedDiaryItem,
	},
	data() {
		return {
			detail: {},
			picker: "",
			sharedDiarys: {},
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
			this.$router.push("/writediary");
		},
		getSD() {
			this.getSharedDiary(this.detail.id)
				.then(response => {
					this.sharedDiarys = response.data.content;
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
#sharedDiary {
	height: 100%;
}

/* 공다 메인 */
#sharedDiaryMain {
	float: right;
	background-color: blanchedalmond;
	height: 100%;
	width: 75%;
}

/* 달력 색상 변경 */
.v-picker__title {
	color: black !important;
}

#sharedCal > div.v-picker__title {
	background-color: white !important;
}

#sharedCal {
	position: absolute;
	left: 10px;
	bottom: 10px;
}

/* 공다 제목 */
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

/* 글쓰기 버튼 */
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
