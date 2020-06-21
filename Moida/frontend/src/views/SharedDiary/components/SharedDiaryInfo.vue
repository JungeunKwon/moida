<template>
	<v-dialog v-model="dialog" width="700">
		<template v-slot:activator="{ on }">
			<span v-on="on">
				<slot />
			</span>
		</template>
		<div id="roomDetail">
			<div id="detailLeft">
				<img id="detailImg" :src="detail.imgUrl" />
			</div>
			<div id="detailRight">
				<div id="detailSubject">
					{{ detail.subject }}
					<img v-if="isHost" id="setting" src="../../../assets/icons/setting.png" />
					<span id="detailCnt">{{ detail.curUser }} / {{ detail.limitUser }}</span>
					<div id="exitSharedDiaryDiv">
						<img
							id="exitSharedDiary"
							@click="exitSharedDiary(detail.id)"
							src="../../../assets/icons/exit.png"
						/>
					</div>
				</div>
				<div id="detailHostDiv">
					<div id="hostName">{{ detail.hostNickname }}</div>
					<img id="hostIcon" :src="detail.hostProfileImg" />
				</div>
				<div id="toggle">
					<div :style="!isMember ? toggleStyle : ''" @click="isMember = false">상세내용</div>
					<div :style="isMember ? toggleStyle : ''" @click="getMembers()">참여자</div>
				</div>
				<div v-if="isMember" id="detailMemberDiv">
					<div
						class="detailMember"
						v-for="(member, idx) in members"
						:key="idx"
						@click="goMypage(member.accountNickname)"
					>
						<img :src="member.accountProfileImg" />
						<div>{{ member.accountNickname }}</div>
						<img
							v-if="isHost && member.accountNickname != $store.getters.nickname"
							src="../../../assets/icons/ban.png"
							@click.stop="deleteMember(member.accountId)"
						/>
					</div>
				</div>
				<div v-else id="detailDesc">{{ detail.description }}</div>
			</div>
		</div>
	</v-dialog>
</template>

<script>
import { mapActions } from "vuex";
export default {
	name: "SharedDiaryInfo",
	props: { detail: {} },
	data() {
		return {
			isHost: false,
			dialog: false,
			isMember: false,
			toggleStyle: {
				backgroundColor: "rgb(250, 223, 153)",
				color: "white",
				textShadow: "1px 1px 2px rgba(128, 128, 128, 0.514)",
			},
			members: {},
		};
	},
	mounted() {},
	watch: {
		dialog: function() {
			if (this.dialog) {
				if (this.$store.getters.nickname == this.detail.hostNickname) {
					this.isHost = true;
					console.log("쥔인지?" + this.isHost);
				}
			}
		},
	},
	methods: {
		...mapActions("sharedDiary", ["getSharedDiaryUser"]),
		getMembers() {
			this.getSharedDiaryUser(this.detail.id)
				.then(response => {
					this.members = response.data;
				})
				.catch(error => {
					console.log(error);
				});
			this.isMember = true;
		},
		goMypage(nickname) {
			this.$router.push("/myPage/" + nickname);
		},
		deleteMember(memberId) {
			console.log(memberId);
			if (confirm("정말 내보내실거에요,,?")) {
			}
		},
		exitSharedDiary(diaryId) {
			if (confirm("정말 공다 나갈건지,,?")) {
			}
		},
	},
};
</script>

<style>
#roomDetail {
	background-color: white;
	width: 100%;
	height: 500px;
	position: relative;
}

#detailImg {
	height: 100%;
	object-fit: cover;
}

#detailLeft {
	float: left;
	width: 50%;
	height: 100%;
	overflow: hidden;
}

#detailRight {
	float: right;
	width: 50%;
	height: 100%;
	padding: 20px;
	box-shadow: inset 5px 0px 10px rgba(119, 119, 119, 0.438);
	text-align: left;
}

#detailSubject {
	font-weight: 400;
	font-size: 20px;
}

#setting {
	width: 17px;
	margin-right: 5px;
}

#detailCnt {
	font-weight: 300;
	font-size: 14px;
	margin-left: 2px;
	color: gray;
}

#detailDesc {
	margin-top: 5px;
	font-size: 15px;
	overflow: auto;
	height: 83%;
	padding: 5px;
}

#detailMemberDiv {
	height: 83%;
	overflow: auto;
	padding: 10px;
}

.detailMember {
	position: relative;
	display: inline-block;
	width: 100%;
	line-height: 50px;
	height: 60px;
	padding: 5px;
	border-radius: 10px;
	cursor: pointer;
}

.detailMember:hover {
	box-shadow: 1px 1px 5px rgba(119, 119, 119, 0.438);
}

.detailMember img:first-child {
	float: left;
	width: 50px;
	height: 50px;
	border-radius: 50%;
}

.detailMember img:last-child {
	float: right;
	width: 25px;
	height: 25px;

	position: absolute;
	margin: auto;
	top: 0;
	bottom: 0;
	right: 10px;
}

.detailMember img:last-child:hover {
	opacity: 0.5;
}

.detailMember div {
	float: left;
	margin-left: 10px;
}

#detailHostDiv {
	display: inline-block;
	width: 100%;
	height: 20px;
}

#detailHostDiv #hostName {
	font-size: 15px;
	margin: 0 0 0 3px;
}

#toggle {
	margin: 0 auto;
	text-align: center;
	box-shadow: 1.5px 1.5px 3px rgba(128, 128, 128, 0.3);
	width: fit-content;
	border-radius: 5px;
	overflow: hidden;
	margin-top: 10px;
}

#toggle div {
	padding: 2px 5px 2px 5px;
	font-size: 12px;
	float: left;
	cursor: pointer;
}

#exitSharedDiaryDiv {
	float: right;
	text-align: right;
}

#exitSharedDiary {
	width: 20px;
	cursor: pointer;
}

#exitSharedDiary:hover {
	opacity: 0.5;
}
</style>
