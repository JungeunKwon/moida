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
import { mapActions } from "vuex";
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
			diaries: [],
		};
	},
	mounted() {
		this.getSharedDiary()
			.then(response => {
				console.log(response);
				this.diaries = response.data;
			})
			.catch(error => {
				console.log(error);
			});
	},
	computed: {},
	methods: {
		...mapActions("sharedDiary", ["getSharedDiary"]),
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
	padding-bottom: 5px;
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
