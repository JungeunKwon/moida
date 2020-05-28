<template>
	<div style="padding: 20px; display: inline-block;">
		<SharedDiaryDetail :detail="detail">
			<v-card
				@click="openDiaryDetail()"
				id="sharedDiaryItem"
				class="mx-auto"
				max-width="300"
			>
				<v-img :src="item.imgUrl" height="200px" />
				<div id="diaryInfo">
					<div id="subjectDiv">
						<div id="roomId">{{ item.id }}</div>
						<div id="roomSubject">{{ item.subject }}</div>
					</div>
					<div id="hostDiv">
						<img id="hostIcon" src="@/assets/icons/host.png" />
						<div id="hostName">{{ item.host_nickname }}</div>
					</div>
					<div style="clear: both;" />
					<div id="desc">{{ item.description }}</div>
				</div>
			</v-card>
		</SharedDiaryDetail>
	</div>
</template>

<script>
import SharedDiaryDetail from "./SharedDiaryDetail";
export default {
	name: "SharedDiaryListItem",
	components: { SharedDiaryDetail },
	data() {
		return {
			detail: {
				id: 123450,
				subject:
					"3학년 1반 모여라 ~ 긴글 테스트 용임 %^^% 불만 있으신지? 3학년 1반 모여라 ~ 긴글 테스트 용임 %^^% 불만 있으신지?",
				host_id: 0,
				host_nickname: "방장",
				host_img: "https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
				isPrivate: true,
				imgUrl: "https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
				description:
					"오는 부끄러운 경, 벌써 위에도 봅니다. 써 별 아직 쉬이 딴은 별 이름을 지나고 북간도에 있습니다. 이름을 아스라히 마리아 까닭이요, 이국 거외다. 사람들의 하나에 계절이 헤일 묻힌 멀리 있습니다.",
				limit: 5,
				count: 3,
			},
		};
	},
	props: { item: {} },
	mounted() {},
	computed: {},
	methods: {
		getRoomDetail() {},
		openDiaryDetail() {
			// axios _ getRoomDetail
			this.getRoomDetail();
		},
	},
};
</script>

<style>
#sharedDiaryItem {
	border-radius: 5px;
	border: 1px solid rgba(192, 192, 192, 0.363);
	/* box-shadow: 0.5px 0.5px 3px rgb(192, 192, 192); */
}

@media screen and (max-width: 500px) {
	#sharedDiaryItem {
		width: 65vw;
	}
}

#sharedDiaryItem:hover {
	cursor: pointer;
	transition: 0.3s ease-in-out;
	filter: brightness(80%);
}

#diaryInfo {
	padding: 15px;
	text-align: left;
}

#desc {
	height: 50px;
	font-size: 12px;

	overflow: hidden;
	text-overflow: ellipsis;
	display: -webkit-box;
	-webkit-line-clamp: 2; /* 라인수 */
	-webkit-box-orient: vertical;
	word-wrap: break-word;
	line-height: 12px;
	height: 24px;
}

#subjectDiv {
	font-weight: 400;
	line-height: 20px;
	margin-right: 2px;
}

#roomId {
	float: left;
	padding: 1px 3px 1px 3px;
	border-radius: 7px;
	font-size: 12px;
	color: rgb(182, 136, 66);
	/* color: rgb(100, 100, 100); */
	margin-right: 10px;
	line-height: 20px;
	/* border: 1px solid silver; */
	box-shadow: 0.5px 0.5px 3px rgb(218, 218, 218);
}

#roomSubject {
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
	line-height: 20px;
}

#hostDiv {
	width: fit-content;
	height: 25px;
	float: right;
	margin-top: 5px;
}

#hostIcon {
	float: left;
	width: 13px;
}

#hostName {
	float: left;
	margin-left: 5px;
	font-size: 12px;
}
</style>
