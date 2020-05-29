<template>
	<div id="SharedDiary">
		<div id="sharedListTop">
			<createSharedDiary>
				<div class="sharedMenu mini">
					<img class="tape" src="../../assets/images/tape.png" />
					<div class="sharedPaper mini">공다 만들기</div>
				</div>
			</createSharedDiary>
			<div class="sharedMenu mini">
				<img class="tape" src="../../assets/images/tape.png" />
				<div class="sharedPaper mini">내 공다 목록</div>
			</div>
			<div class="sharedMenu large">
				<img class="tape" src="../../assets/images/tape.png" />
				<div class="sharedPaper large">
					<div id="searchTextDiv">
						<input type="text" id="searchText" v-model="searchText" />
					</div>
					<img id="searchImg" src="../../assets/icons/search.png" @click="searchSharedDiary" />
				</div>
			</div>
		</div>
		<div id="sharedListBottom">
			<SharedDiaryListItem v-for="(item, idx) in diaries" :key="idx" :item="item" />
		</div>
	</div>
</template>
<script>
import createSharedDiary from "./components/CreateSharedDiary";
import SharedDiaryListItem from "./components/SharedDiaryListItem";
export default {
	name: "SharedDiary",
	components: {
		SharedDiaryListItem,
		createSharedDiary,
	},
	data() {
		return {
			searchText: "",
			diaries: [
				{
					id: 123450,
					subject:
						"3학년 1반 모여라 ~ 긴글 테스트 용임 %^^% 불만 있으신지? 3학년 1반 모여라 ~ 긴글 테스트 용임 %^^% 불만 있으신지?",
					host_id: 0,
					host_nickname: "방장",
					isPrivate: true,
					imgUrl:
						"https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
					description:
						"오는 부끄러운 경, 벌써 위에도 봅니다. 써 별 아직 쉬이 딴은 별 이름을 지나고 북간도에 있습니다. 이름을 아스라히 마리아 까닭이요, 이국 거외다. 사람들의 하나에 계절이 헤일 묻힌 멀리 있습니다. 풀이 위에도 강아지, 딴은 잠, 써 어머니, 겨울이 나는 봅니다. 말 북간도에 지나고 내일 가득 그러나 이름과 별 거외다. 나는 멀듯이, 써 쓸쓸함과 릴케 풀이 나는 보고, 봅니다. 노새, 내린 하나에 불러 이런 하나에 버리었습니다. 경, 가슴속에 라이너 버리었습니다. 그리고 계절이 이런 별 이웃 같이 부끄러운 잠, 청춘이 까닭입니다. 그리고 하나에 책상을 버리었습니다.",
				},
				{
					id: 123450,
					subject: "~ 영대 컴공 14학번 모임 ~",
					host_id: 0,
					host_nickname: "방장",
					isPrivate: false,
					imgUrl:
						"https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
					description:
						"오는 부끄러운 경, 벌써 위에도 봅니다. 써 별 아직 쉬이 딴은 별 이름을 지나고 북간도에 있습니다. 이름을 아스라히 마리아 까닭이요, 이국 거외다. 사람들의 하나에 계절이 헤일 묻힌 멀리 있습니다. 풀이 위에도 강아지, 딴은 잠, 써 어머니, 겨울이 나는 봅니다. 말 북간도에 지나고 내일 가득 그러나 이름과 별 거외다. 나는 멀듯이, 써 쓸쓸함과 릴케 풀이 나는 보고, 봅니다. 노새, 내린 하나에 불러 이런 하나에 버리었습니다. 경, 가슴속에 라이너 버리었습니다. 그리고 계절이 이런 별 이웃 같이 부끄러운 잠, 청춘이 까닭입니다. 그리고 하나에 책상을 버리었습니다.",
				},
				{
					id: 123450,
					subject: "3학년 1반 모여라 ~",
					host_id: 0,
					host_nickname: "방장",
					isPrivate: false,
					imgUrl:
						"https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
					description:
						"오는 부끄러운 경, 벌써 위에도 봅니다. 써 별 아직 쉬이 딴은 별 이름을 지나고 북간도에 있습니다. 이름을 아스라히 마리아 까닭이요, 이국 거외다. 사람들의 하나에 계절이 헤일 묻힌 멀리 있습니다. 풀이 위에도 강아지, 딴은 잠, 써 어머니, 겨울이 나는 봅니다. 말 북간도에 지나고 내일 가득 그러나 이름과 별 거외다. 나는 멀듯이, 써 쓸쓸함과 릴케 풀이 나는 보고, 봅니다. 노새, 내린 하나에 불러 이런 하나에 버리었습니다. 경, 가슴속에 라이너 버리었습니다. 그리고 계절이 이런 별 이웃 같이 부끄러운 잠, 청춘이 까닭입니다. 그리고 하나에 책상을 버리었습니다.",
				},
				{
					id: 123450,
					subject: "~ 영대 컴공 14학번 모임 ~",
					host_id: 0,
					host_nickname: "방장",
					isPrivate: false,
					imgUrl:
						"https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
					description:
						"오는 부끄러운 경, 벌써 위에도 봅니다. 써 별 아직 쉬이 딴은 별 이름을 지나고 북간도에 있습니다. 이름을 아스라히 마리아 까닭이요, 이국 거외다. 사람들의 하나에 계절이 헤일 묻힌 멀리 있습니다. 풀이 위에도 강아지, 딴은 잠, 써 어머니, 겨울이 나는 봅니다. 말 북간도에 지나고 내일 가득 그러나 이름과 별 거외다. 나는 멀듯이, 써 쓸쓸함과 릴케 풀이 나는 보고, 봅니다. 노새, 내린 하나에 불러 이런 하나에 버리었습니다. 경, 가슴속에 라이너 버리었습니다. 그리고 계절이 이런 별 이웃 같이 부끄러운 잠, 청춘이 까닭입니다. 그리고 하나에 책상을 버리었습니다.",
				},
				{
					id: 123450,
					subject: "3학년 1반 모여라 ~",
					host_id: 0,
					host_nickname: "방장",
					isPrivate: false,
					imgUrl:
						"https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
					description:
						"오는 부끄러운 경, 벌써 위에도 봅니다. 써 별 아직 쉬이 딴은 별 이름을 지나고 북간도에 있습니다. 이름을 아스라히 마리아 까닭이요, 이국 거외다. 사람들의 하나에 계절이 헤일 묻힌 멀리 있습니다. 풀이 위에도 강아지, 딴은 잠, 써 어머니, 겨울이 나는 봅니다. 말 북간도에 지나고 내일 가득 그러나 이름과 별 거외다. 나는 멀듯이, 써 쓸쓸함과 릴케 풀이 나는 보고, 봅니다. 노새, 내린 하나에 불러 이런 하나에 버리었습니다. 경, 가슴속에 라이너 버리었습니다. 그리고 계절이 이런 별 이웃 같이 부끄러운 잠, 청춘이 까닭입니다. 그리고 하나에 책상을 버리었습니다.",
				},
				{
					id: 123450,
					subject: "~ 영대 컴공 14학번 모임 ~",
					host_id: 0,
					host_nickname: "방장",
					isPrivate: false,
					imgUrl:
						"https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
					description:
						"오는 부끄러운 경, 벌써 위에도 봅니다. 써 별 아직 쉬이 딴은 별 이름을 지나고 북간도에 있습니다. 이름을 아스라히 마리아 까닭이요, 이국 거외다. 사람들의 하나에 계절이 헤일 묻힌 멀리 있습니다. 풀이 위에도 강아지, 딴은 잠, 써 어머니, 겨울이 나는 봅니다. 말 북간도에 지나고 내일 가득 그러나 이름과 별 거외다. 나는 멀듯이, 써 쓸쓸함과 릴케 풀이 나는 보고, 봅니다. 노새, 내린 하나에 불러 이런 하나에 버리었습니다. 경, 가슴속에 라이너 버리었습니다. 그리고 계절이 이런 별 이웃 같이 부끄러운 잠, 청춘이 까닭입니다. 그리고 하나에 책상을 버리었습니다.",
				},
				{
					id: 123450,
					subject: "3학년 1반 모여라 ~",
					host_id: 0,
					host_nickname: "방장",
					isPrivate: false,
					imgUrl:
						"https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
					description:
						"오는 부끄러운 경, 벌써 위에도 봅니다. 써 별 아직 쉬이 딴은 별 이름을 지나고 북간도에 있습니다. 이름을 아스라히 마리아 까닭이요, 이국 거외다. 사람들의 하나에 계절이 헤일 묻힌 멀리 있습니다. 풀이 위에도 강아지, 딴은 잠, 써 어머니, 겨울이 나는 봅니다. 말 북간도에 지나고 내일 가득 그러나 이름과 별 거외다. 나는 멀듯이, 써 쓸쓸함과 릴케 풀이 나는 보고, 봅니다. 노새, 내린 하나에 불러 이런 하나에 버리었습니다. 경, 가슴속에 라이너 버리었습니다. 그리고 계절이 이런 별 이웃 같이 부끄러운 잠, 청춘이 까닭입니다. 그리고 하나에 책상을 버리었습니다.",
				},
				{
					id: 123450,
					subject: "~ 영대 컴공 14학번 모임 ~",
					host_id: 0,
					host_nickname: "방장",
					isPrivate: false,
					imgUrl:
						"https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
					description:
						"오는 부끄러운 경, 벌써 위에도 봅니다. 써 별 아직 쉬이 딴은 별 이름을 지나고 북간도에 있습니다. 이름을 아스라히 마리아 까닭이요, 이국 거외다. 사람들의 하나에 계절이 헤일 묻힌 멀리 있습니다. 풀이 위에도 강아지, 딴은 잠, 써 어머니, 겨울이 나는 봅니다. 말 북간도에 지나고 내일 가득 그러나 이름과 별 거외다. 나는 멀듯이, 써 쓸쓸함과 릴케 풀이 나는 보고, 봅니다. 노새, 내린 하나에 불러 이런 하나에 버리었습니다. 경, 가슴속에 라이너 버리었습니다. 그리고 계절이 이런 별 이웃 같이 부끄러운 잠, 청춘이 까닭입니다. 그리고 하나에 책상을 버리었습니다.",
				},
				{
					id: 123450,
					subject: "3학년 1반 모여라 ~",
					host_id: 0,
					host_nickname: "방장",
					isPrivate: false,
					imgUrl:
						"https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
					description:
						"오는 부끄러운 경, 벌써 위에도 봅니다. 써 별 아직 쉬이 딴은 별 이름을 지나고 북간도에 있습니다. 이름을 아스라히 마리아 까닭이요, 이국 거외다. 사람들의 하나에 계절이 헤일 묻힌 멀리 있습니다. 풀이 위에도 강아지, 딴은 잠, 써 어머니, 겨울이 나는 봅니다. 말 북간도에 지나고 내일 가득 그러나 이름과 별 거외다. 나는 멀듯이, 써 쓸쓸함과 릴케 풀이 나는 보고, 봅니다. 노새, 내린 하나에 불러 이런 하나에 버리었습니다. 경, 가슴속에 라이너 버리었습니다. 그리고 계절이 이런 별 이웃 같이 부끄러운 잠, 청춘이 까닭입니다. 그리고 하나에 책상을 버리었습니다.",
				},
				{
					id: 123450,
					subject: "~ 영대 컴공 14학번 모임 ~",
					host_id: 0,
					host_nickname: "방장",
					isPrivate: false,
					imgUrl:
						"https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
					description:
						"오는 부끄러운 경, 벌써 위에도 봅니다. 써 별 아직 쉬이 딴은 별 이름을 지나고 북간도에 있습니다. 이름을 아스라히 마리아 까닭이요, 이국 거외다. 사람들의 하나에 계절이 헤일 묻힌 멀리 있습니다. 풀이 위에도 강아지, 딴은 잠, 써 어머니, 겨울이 나는 봅니다. 말 북간도에 지나고 내일 가득 그러나 이름과 별 거외다. 나는 멀듯이, 써 쓸쓸함과 릴케 풀이 나는 보고, 봅니다. 노새, 내린 하나에 불러 이런 하나에 버리었습니다. 경, 가슴속에 라이너 버리었습니다. 그리고 계절이 이런 별 이웃 같이 부끄러운 잠, 청춘이 까닭입니다. 그리고 하나에 책상을 버리었습니다.",
				},
				{
					id: 123450,
					subject: "3학년 1반 모여라 ~",
					host_id: 0,
					host_nickname: "방장",
					isPrivate: false,
					imgUrl:
						"https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
					description:
						"오는 부끄러운 경, 벌써 위에도 봅니다. 써 별 아직 쉬이 딴은 별 이름을 지나고 북간도에 있습니다. 이름을 아스라히 마리아 까닭이요, 이국 거외다. 사람들의 하나에 계절이 헤일 묻힌 멀리 있습니다. 풀이 위에도 강아지, 딴은 잠, 써 어머니, 겨울이 나는 봅니다. 말 북간도에 지나고 내일 가득 그러나 이름과 별 거외다. 나는 멀듯이, 써 쓸쓸함과 릴케 풀이 나는 보고, 봅니다. 노새, 내린 하나에 불러 이런 하나에 버리었습니다. 경, 가슴속에 라이너 버리었습니다. 그리고 계절이 이런 별 이웃 같이 부끄러운 잠, 청춘이 까닭입니다. 그리고 하나에 책상을 버리었습니다.",
				},
				{
					id: 123450,
					subject: "~ 영대 컴공 14학번 모임 ~",
					host_id: 0,
					host_nickname: "방장",
					isPrivate: false,
					imgUrl:
						"https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
					description:
						"오는 부끄러운 경, 벌써 위에도 봅니다. 써 별 아직 쉬이 딴은 별 이름을 지나고 북간도에 있습니다. 이름을 아스라히 마리아 까닭이요, 이국 거외다. 사람들의 하나에 계절이 헤일 묻힌 멀리 있습니다. 풀이 위에도 강아지, 딴은 잠, 써 어머니, 겨울이 나는 봅니다. 말 북간도에 지나고 내일 가득 그러나 이름과 별 거외다. 나는 멀듯이, 써 쓸쓸함과 릴케 풀이 나는 보고, 봅니다. 노새, 내린 하나에 불러 이런 하나에 버리었습니다. 경, 가슴속에 라이너 버리었습니다. 그리고 계절이 이런 별 이웃 같이 부끄러운 잠, 청춘이 까닭입니다. 그리고 하나에 책상을 버리었습니다.",
				},
				{
					id: 123450,
					subject: "3학년 1반 모여라 ~",
					host_id: 0,
					host_nickname: "방장",
					isPrivate: false,
					imgUrl:
						"https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
					description:
						"오는 부끄러운 경, 벌써 위에도 봅니다. 써 별 아직 쉬이 딴은 별 이름을 지나고 북간도에 있습니다. 이름을 아스라히 마리아 까닭이요, 이국 거외다. 사람들의 하나에 계절이 헤일 묻힌 멀리 있습니다. 풀이 위에도 강아지, 딴은 잠, 써 어머니, 겨울이 나는 봅니다. 말 북간도에 지나고 내일 가득 그러나 이름과 별 거외다. 나는 멀듯이, 써 쓸쓸함과 릴케 풀이 나는 보고, 봅니다. 노새, 내린 하나에 불러 이런 하나에 버리었습니다. 경, 가슴속에 라이너 버리었습니다. 그리고 계절이 이런 별 이웃 같이 부끄러운 잠, 청춘이 까닭입니다. 그리고 하나에 책상을 버리었습니다.",
				},
				{
					id: 123450,
					subject: "~ 영대 컴공 14학번 모임 ~",
					host_id: 0,
					host_nickname: "방장",
					isPrivate: false,
					imgUrl:
						"https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
					description:
						"오는 부끄러운 경, 벌써 위에도 봅니다. 써 별 아직 쉬이 딴은 별 이름을 지나고 북간도에 있습니다. 이름을 아스라히 마리아 까닭이요, 이국 거외다. 사람들의 하나에 계절이 헤일 묻힌 멀리 있습니다. 풀이 위에도 강아지, 딴은 잠, 써 어머니, 겨울이 나는 봅니다. 말 북간도에 지나고 내일 가득 그러나 이름과 별 거외다. 나는 멀듯이, 써 쓸쓸함과 릴케 풀이 나는 보고, 봅니다. 노새, 내린 하나에 불러 이런 하나에 버리었습니다. 경, 가슴속에 라이너 버리었습니다. 그리고 계절이 이런 별 이웃 같이 부끄러운 잠, 청춘이 까닭입니다. 그리고 하나에 책상을 버리었습니다.",
				},
				{
					id: 123450,
					subject: "3학년 1반 모여라 ~",
					host_id: 0,
					host_nickname: "방장",
					isPrivate: false,
					imgUrl:
						"https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
					description:
						"오는 부끄러운 경, 벌써 위에도 봅니다. 써 별 아직 쉬이 딴은 별 이름을 지나고 북간도에 있습니다. 이름을 아스라히 마리아 까닭이요, 이국 거외다. 사람들의 하나에 계절이 헤일 묻힌 멀리 있습니다. 풀이 위에도 강아지, 딴은 잠, 써 어머니, 겨울이 나는 봅니다. 말 북간도에 지나고 내일 가득 그러나 이름과 별 거외다. 나는 멀듯이, 써 쓸쓸함과 릴케 풀이 나는 보고, 봅니다. 노새, 내린 하나에 불러 이런 하나에 버리었습니다. 경, 가슴속에 라이너 버리었습니다. 그리고 계절이 이런 별 이웃 같이 부끄러운 잠, 청춘이 까닭입니다. 그리고 하나에 책상을 버리었습니다.",
				},
				{
					id: 123450,
					subject: "~ 영대 컴공 14학번 모임 ~",
					host_id: 0,
					host_nickname: "방장",
					isPrivate: false,
					imgUrl:
						"https://cdn.vuetifyjs.com/images/cards/sunshine.jpg",
					description:
						"오는 부끄러운 경, 벌써 위에도 봅니다. 써 별 아직 쉬이 딴은 별 이름을 지나고 북간도에 있습니다. 이름을 아스라히 마리아 까닭이요, 이국 거외다. 사람들의 하나에 계절이 헤일 묻힌 멀리 있습니다. 풀이 위에도 강아지, 딴은 잠, 써 어머니, 겨울이 나는 봅니다. 말 북간도에 지나고 내일 가득 그러나 이름과 별 거외다. 나는 멀듯이, 써 쓸쓸함과 릴케 풀이 나는 보고, 봅니다. 노새, 내린 하나에 불러 이런 하나에 버리었습니다. 경, 가슴속에 라이너 버리었습니다. 그리고 계절이 이런 별 이웃 같이 부끄러운 잠, 청춘이 까닭입니다. 그리고 하나에 책상을 버리었습니다.",
				},
			],
		};
	},
	mounted() {},
	computed: {},
	methods: {
		searchSharedDiary() {},
		createSharedDiary() {},
	},
};
</script>

<style>
#SharedDiary {
	width: 100%;
	height: 100%;
	overflow-y: auto;
	overflow-x: hidden;
}

#sharedListTop {
	position: absolute;
	/* background-color: white; */
	width: 100%;
	height: 100px;
	z-index: 2;
	padding-left: 10px;
	/* box-shadow: 1px 1px 5px gray; */
}

.sharedMenu {
	float: left;
	margin: 5px;
}

.sharedMenu.mini:hover {
	transform: rotate(4deg);
}

.sharedMenu > .tape {
	width: 45px;
	transform: rotate(20deg);
	opacity: 0.5;
}

.sharedMenu > .sharedPaper {
	margin-top: -22px;
	background-color: white;
	height: 40px;
	line-height: 40px;
	font-family: "KyoboHand";
	font-size: 17px;
	box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.288);
}

.sharedMenu > .sharedPaper.mini,
.sharedMenu.mini {
	width: 85px;
	cursor: pointer;
}
.sharedMenu > .sharedPaper.large,
.sharedMenu.large {
	width: 300px;
}

#searchTextDiv {
	float: left;
	width: calc(100% - 30px);
}

#searchText {
	width: calc(100% - 30px);
	outline: none;
	margin: 0 5px 0 5px;
}

#searchImg {
	width: 20px;
	float: left;
	margin: 10px 0 0 0;
	cursor: pointer;
}

#searchImg:hover {
	opacity: 0.5;
}

#sharedListBottom {
	z-index: 1;
	margin-top: 80px;
	width: 100%;
}
</style>
