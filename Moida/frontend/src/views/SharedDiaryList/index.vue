<template>
	<div id="SharedDiaryList">
		<div id="sharedListTop">
			<createSharedDiary>
				<div class="sharedMenu mini">
					<img class="tape" src="../../assets/images/tape.png" />
					<div class="sharedPaper mini">공다 만들기</div>
				</div>
			</createSharedDiary>
			<MySharedDiaryList :myDiaries="myDiaries">
				<div class="sharedMenu mini" @click="mySharedDiaryList()">
					<img class="tape" src="../../assets/images/tape.png" />
					<div class="sharedPaper mini">내 공다 목록</div>
				</div>
			</MySharedDiaryList>
			<div class="sharedMenu mini">
				<img class="tape" src="../../assets/images/tape.png" />
				<div class="sharedPaper mini">
					<v-select :items="sels" v-model="searchSel" label="검색조건" color="gray" dense solo />
				</div>
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
import { mapActions } from "vuex";
import createSharedDiary from "./components/CreateSharedDiary";
import MySharedDiaryList from "./components/MySharedDiaryList";
import SharedDiaryListItem from "./components/SharedDiaryListItem";
export default {
	name: "SharedDiaryList",
	components: {
		SharedDiaryListItem,
		createSharedDiary,
		MySharedDiaryList,
	},
	data() {
		return {
			searchText: "",
			diaries: [],
			myDiaries: [],
			sels: [
				{ text: "제목", value: 0 },
				{ text: "닉네임", value: 1 },
				{ text: "내용", value: 2 },
			],
			searchSel: "",
		};
	},
	mounted() {
		this.getSharedDiary()
			.then(response => {
				this.diaries = response.data;
			})
			.catch(error => {
				console.log(error);
			});
	},
	computed: {},
	methods: {
		...mapActions("sharedDiaryList", [
			"getSharedDiary",
			"searchBySubject",
			"searchByDesc",
			"searchByNickname",
			"getMySharedDiary",
		]),
		searchSharedDiary() {
			console.log(this.searchSel);
			if (this.searchText == "") return;
			let temp = this.searchSel;
			if (temp == 0) {
				this.searchBySubject(this.searchText)
					.then(response => {
						this.diaries = response.data;
					})
					.catch(error => {
						console.log(error);
					});
			} else if (temp == 1) {
				this.searchByNickname(this.searchText)
					.then(response => {
						this.diaries = response.data;
					})
					.catch(error => {
						console.log(error);
					});
			} else if (temp == 2) {
				this.searchByDesc(this.searchText)
					.then(response => {
						this.diaries = response.data;
					})
					.catch(error => {
						console.log(error);
					});
			}
		},
		mySharedDiaryList() {
			this.getMySharedDiary()
				.then(response => {
					console.log("엥");
					this.myDiaries = response.data;
					console.log(this.myDiaries);
				})
				.catch(error => {
					console.log(error);
				});
		},
	},
};
</script>

<style>
#sharedListTop > div.sharedMenu.mini > div > div > div > div.v-input__slot {
	box-shadow: none;
}

#SharedDiaryList {
	width: 100%;
	height: 100%;
	overflow-y: auto;
	overflow-x: hidden;
	padding-bottom: 5px;
}

#sharedListTop {
	position: absolute;
	width: 100%;
	height: 100px;
	z-index: 2;
	padding-left: 10px;
}

.sharedMenu {
	float: left;
	margin: 5px;
}

.sharedMenu.mini:hover {
	transform: rotate(4deg);
}

.sharedMenu > .tape {
	position: relative;
	z-index: 1;
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

#sharedListTop > div:nth-child(4) > div > div {
	margin: 0;
	padding: 0;
}

#sharedListTop > div:nth-child(4) > div > div > div > div.v-input__slot {
	padding-right: 0;
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
