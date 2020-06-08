<template>
	<div id="sharedDiary">
		<!-- 방장, 달력, 해빗트래커, 리스트 -->
		<div id="diarySubjectDiv">
			<div>{{ detail.subject }}</div>
			<SharedDiaryInfo :detail="detail">
				<img src="../../assets/icons/info.png" width="20px" />
			</SharedDiaryInfo>
		</div>

		<HabitTracker :sharedDiaryId="detail.id" id="ht"></HabitTracker>

		<div v-if="showCal">
			<v-date-picker id="sharedCal" v-model="picker" color="#fadf99" @change="getSharedDiaryByDate()" />
			<div id="sharedCalText" @click="getSharedDiaryAll">전체보기</div>
		</div>
		<img v-if="showCalImg" id="shardCalImg" @click="toggleCal()" src="../../assets/icons/cal.png" />

		<div id="sharedDiaryMain">
			<SharedDiaryItem v-for="(item, idx) in sharedDiarys" :key="idx" :sharedDiary="item" />
		</div>

		<div id="writeDiaryDiv" @click="openWrite">
			<v-icon large>mdi-pencil</v-icon>&nbsp;다이어리 쓰기
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
			sharedDiarys: [],
			originSD: [],
			showCal: true,
			showCalImg: true,
		};
	},
	mounted() {
		this.getSharedDiaryDetail(this.$route.params.id)
			.then(response => {
				this.detail = response.data;
				console.log(this.detail);
				this.getSD();
			})
			.catch(error => {
				console.log(error);
			});

		this.handleResize();
		window.addEventListener("resize", this.handleResize);
		this.getNow();
	},
	computed: {},
	methods: {
		...mapActions("sharedDiary", [
			"getSharedDiaryDetail",
			"getSharedDiary",
		]),
		...mapMutations("sharedDiary", ["TOGGLE_WRITINGSD", "SET_DIARYID"]),
		handleResize() {
			if (window.innerWidth < 1100) {
				this.showCal = false;
				this.showCalImg = true;
			} else {
				this.showCal = true;
				this.showCalImg = false;
			}
		},

		toggleCal() {
			this.showCal = !this.showCal;
		},
		openWrite() {
			this.TOGGLE_WRITINGSD(true);
			this.SET_DIARYID(this.detail.id);
			this.$router.push("/writediary");
		},
		getSD() {
			this.getSharedDiary(this.detail.id)
				.then(response => {
					this.originSD = response.data;
					this.sharedDiarys = response.data;
				})
				.catch(error => {
					console.log(error);
				});
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
		getSharedDiaryAll() {
			this.sharedDiarys = this.originSD;
		},
		getSharedDiaryByDate() {
			let tempList = [];

			for (let i = 0; i < this.originSD.length; i++) {
				if (this.originSD[i].inputDate.includes(this.picker)) {
					tempList.push(this.originSD[i]);
				}
			}
			console.log(tempList);
			this.sharedDiarys = tempList;
		},
	},
};
</script>

<style>
#sharedDiary {
	background-color: #c7ceea75;
	height: 100%;
	width: 100%;
	overflow: auto;
}

/* 공다 메인 */
#sharedDiaryMain {
	position: absolute;
	right: 0;
	height: 100%;
	width: 80%;
	padding: 20px;
	overflow: auto;
}

@media screen and (max-width: 1300px) {
	#sharedDiaryMain {
		width: 70%;
	}
}

@media screen and (max-width: 1100px) {
	#sharedDiaryMain {
		width: 90%;
	}
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
	z-index: 100;
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
	font-family: KyoboHand;
	font-size: 20px;
	bottom: 30px;
	right: -105px;
	width: 150px;
	background-color: white;
	height: 45px;
	padding: 5px;
	border-radius: 10px 0 0 10px;
	box-shadow: 1px 1px 5px rgba(128, 128, 128, 0.61);
	transition: 0.3s ease;
	cursor: pointer;
}

#writeDiaryDiv:hover {
	right: 0;
}

#shardCalImg {
	position: absolute;
	left: 15px;
	bottom: 15px;
	width: 30px;
	cursor: pointer;
	z-index: 101;
}

#shardCalImg:hover {
	opacity: 0.5;
}

#sharedCalText {
	position: absolute;
	text-align: right;
	left: 230px;
	bottom: 15px;
	width: fit-content;
	cursor: pointer;
	z-index: 101;
	font-weight: 500;
}

#sharedCalText:hover {
	opacity: 0.7;
}

#ht {
	position: absolute;
	left: 10px;
	top: 50px;
	width: 290px;
	height: 32%;
	z-index: 101;
	background-color: white;
	border-radius: 3px;
	box-shadow: 1px 1px 3px rgba(128, 128, 128, 0.286);
}
</style>
