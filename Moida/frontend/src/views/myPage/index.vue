<template>
	<div class="mp-main-container">
		<el-row type="flex" class="mp-first-row">
			<el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
				<user-card :user="user" />
			</el-col>
			<el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
				<shared-diary-list />
			</el-col>
		</el-row>
		<el-row type="flex" class="mp-second-row" justify="center">
			<el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
				<calendar :nickname="user.nickname" :events="events" />
			</el-col>
		</el-row>
	</div>
</template>

<script>
import { mapActions } from "vuex";
export default {
	name: "myPage",
	components: {
		userCard: () => import("./components/userCard/index"),
		sharedDiaryList: () => import("./components/sharedDiaryList/index"),
		calendar: () => import("./components/calendar/index"),
	},
	data() {
		return {
			user: {},
			events: [],
		};
	},
	async mounted() {
		try {
			this.user = await this.searchByNickname(
				this.$route.params.nickname,
			);
			this.events = await this.getDiary(this.user.nickname);
		} catch (error) {
			console.log(error);
		}
	},
	methods: {
		...mapActions("calendar", ["getDiary"]),
		...mapActions("user", ["searchByNickname"]),
	},
};
</script>

<style>
.el-row {
	margin-bottom: 20px;
}
.mp-main-container {
	width: 100%;
	height: 100%;
	background: #60c76e;
	overflow: auto;
}
.mp-myInfo {
	background: red;
	height: 100%;
}
.mp-first-row {
	height: 30%;
}
.mp-Calendar {
	background: purple;
	height: 100%;
	min-height: 620px;
}
.mp-second-row {
	height: 65%;
	margin: 0;
}
</style>
