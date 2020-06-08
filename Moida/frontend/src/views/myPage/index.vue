<template>
	<div class="mp-main-container">
		<el-row type="flex" class="mp-first-row">
			<el-col :xs="18" :sm="18" :md="18" :lg="18" :xl="18">
				<user-card
					:user="user"
					:follower="follower"
					:following="following"
				/>
			</el-col>
			<el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
				<shared-diary-list :diaries.sync="diaries" :user="user" />
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
import { getFollower, getFollowing } from "../../api/follow";
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
			diaries: [],
			follower: [],
			following: [],
		};
	},
	async mounted() {
		try {
			this.user = await this.searchByNickname(
				this.$route.params.nickname,
			);
			// this.events = await this.getDiary(this.user.nickname);
			this.events = await this.getDiaryap();
			let payload = await this.searchByMember(this.user.nickname);
			this.diaries = payload.data;
			payload = await getFollower(this.user.id);
			this.follower = payload.data;
			// console.log("follower");
			// console.log(this.follower);
			payload = await getFollowing(this.user.id);
			this.following = payload.data;
			// console.log("following");
			// console.log(this.following);
		} catch (error) {
			console.log(error);
		}
	},
	methods: {
		...mapActions("calendar", ["getDiary"]),
		...mapActions("user", ["searchByNickname"]),
		...mapActions("sharedDiaryList", {
			searchByMember: "searchByMember",
		}),
		...mapActions("diary", ["getDiaryap"]),
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
	background: #e2f0cb;
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
