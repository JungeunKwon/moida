<template>
	<div id="SharedDiary">
		<!-- 방장, 달력, 해빗트래커, 리스트 -->
		<div id="diarySubjectDiv">
			<div>{{ detail.subject }}</div>
			<SharedDiaryInfo :detail="detail">
				<img src="../../assets/icons/info.png" width="20px" />
			</SharedDiaryInfo>
		</div>
		<HabitTracker></HabitTracker>
	</div>
</template>
<script>
import { mapActions } from "vuex";
import SharedDiaryInfo from "./components/SharedDiaryInfo";
import HabitTracker from "./components/HabitTracker";
export default {
	name: "SharedDiary",
	components: { SharedDiaryInfo, HabitTracker },
	data() {
		return {
			detail: {},
		};
	},
	mounted() {
		this.getSharedDiaryDetail(this.$route.params.id)
			.then(response => {
				this.detail = response.data;
			})
			.catch(error => {
				console.log(error);
			});
	},
	computed: {},
	methods: {
		...mapActions("sharedDiary", [
			"getSharedDiaryDetail",
			"getSharedDiary",
		]),
	},
};
</script>

<style>
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
</style>
