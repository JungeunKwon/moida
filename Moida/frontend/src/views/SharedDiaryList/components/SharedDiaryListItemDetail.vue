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
					<span id="detailCnt"
						>{{ detail.curUser }} / {{ detail.limitUser }}</span
					>
				</div>
				<div id="detailHostDiv">
					<div id="hostName">{{ detail.hostNickname }}</div>
					<img id="hostIcon" :src="detail.hostProfileImg" />
				</div>
				<div id="detailDesc">{{ detail.description }}</div>
				<div id="openDiary" @click="openSharedDiary">참가하기</div>
			</div>
		</div>
	</v-dialog>
</template>

<script>
import { mapActions, mapMutations } from "vuex";
export default {
	name: "SharedDiaryListItemDetail",
	props: { detail: {} },
	data() {
		return {
			dialog: false,
		};
	},
	mounted() {},
	methods: {
		...mapActions("sharedDiaryList", ["joinSharedDiary"]),
		openSharedDiary() {
			this.$router.push(`/shared/${this.detail.id}`);
			// if (confirm("정말 참여하시겠어요?")) {
			// 	this.joinSharedDiary({ groupId: this.detail.id })
			// 		.then(response => {
			// 		})
			// 		.catch(error => {
			// 			console.log(error);
			// 		});
			// }
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
	height: 72%;
	padding: 5px;
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

#openDiary {
	position: absolute;
	height: 50px;
	width: calc(50% - 60px);
	bottom: 15px;
	background-color: rgb(250, 223, 153);
	margin-top: 10px;
	padding: 5px 10px 5px 10px;
	border-radius: 5px;
	cursor: pointer;
	line-height: 40px;
	text-align: center;
	margin: 10px;
}
</style>
