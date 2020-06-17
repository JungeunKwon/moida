<template>
	<div class="sharedDiaryList">
		<v-app-bar-nav-icon class="sharedListTopMini" @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
		<v-navigation-drawer v-model="drawer" absolute temporary>
			<v-list nav dense>
				<createSharedDiary @reload="reload">
					<v-list-item link>
						<v-list-item-icon>
							<i class="material-icons">menu_book</i>
						</v-list-item-icon>

						<v-list-item-content>
							<v-list-item-title>공다 만들기</v-list-item-title>
						</v-list-item-content>
					</v-list-item>
				</createSharedDiary>
				<v-list-item link @click="toggleDiaryList()">
					<v-list-item-icon>
						<i class="material-icons">list</i>
					</v-list-item-icon>

					<v-list-item-content>
						<v-list-item-title>{{ myDiaryText }}</v-list-item-title>
					</v-list-item-content>
				</v-list-item>
				<v-list-item>
					<v-list-item-content>
						<v-select :items="sels" v-model="searchSel" label="검색조건" color="gray" dense />

						<div class="searchDiv">
							<div class="searchTextDiv">
								<input type="text" v-model="searchText" />
							</div>
							<img src="../../assets/icons/search.png" @click="searchSharedDiary" />
						</div>
					</v-list-item-content>
				</v-list-item>
			</v-list>
		</v-navigation-drawer>
		<div class="sharedListTop">
			<createSharedDiary @reload="reload">
				<div class="sharedMenu mini">
					<img class="tape" src="../../assets/images/tape.png" />
					<div class="sharedPaper mini">공다 만들기</div>
				</div>
			</createSharedDiary>
			<div class="sharedMenu mini" @click="toggleDiaryList()">
				<img class="tape" src="../../assets/images/tape.png" />
				<div class="sharedPaper mini">{{ myDiaryText }}</div>
			</div>
			<div class="sharedMenu mini">
				<img class="tape" src="../../assets/images/tape.png" />
				<div class="sharedPaper mini">
					<v-select :items="sels" v-model="searchSel" label="검색조건" color="gray" dense solo />
				</div>
			</div>
			<div class="sharedMenu large">
				<img class="tape" src="../../assets/images/tape.png" />
				<div class="sharedPaper large">
					<div class="searchDiv">
						<div class="searchTextDiv">
							<input type="text" v-model="searchText" />
						</div>
						<img src="../../assets/icons/search.png" @click="searchSharedDiary" />
					</div>
				</div>
			</div>
		</div>
		<div class="sharedListBottom">
			<SharedDiaryListItem
				v-for="(item, idx) in diaries"
				:key="idx"
				:isMyDiaryList="isMyDiaryList"
				:item="item"
			/>
		</div>
	</div>
</template>
<script>
import { mapActions } from "vuex";
import createSharedDiary from "./components/CreateSharedDiary";
import SharedDiaryListItem from "./components/SharedDiaryListItem";
export default {
	name: "SharedDiaryList",
	components: {
		SharedDiaryListItem,
		createSharedDiary,
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
			isMyDiaryList: false,
			myDiaryText: "내 공다 목록",
			drawer: false,
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
		reload() {
			location.reload();
		},
		searchSharedDiary() {
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
		toggleDiaryList() {
			if (!this.isMyDiaryList) {
				this.getMySharedDiary()
					.then(response => {
						this.isMyDiaryList = true;
						this.myDiaryText = "전체보기";
						this.diaries = response.data;
						console.log(this.myDiaries);
					})
					.catch(error => {
						console.log(error);
					});
			} else {
				this.getSharedDiary()
					.then(response => {
						this.isMyDiaryList = false;
						this.myDiaryText = "내 공다 목록";
						this.diaries = response.data;
					})
					.catch(error => {
						console.log(error);
					});
			}
		},
	},
};
</script>

<style lang="scss">
@import url("../../utils/css/memo.css");
.sharedDiaryList {
	background-color: #c7ceea75;
	width: 100%;
	height: 100%;
	overflow-y: auto;
	overflow-x: hidden;
	padding-bottom: 5px;

	.sharedListTopMini {
		display: none;
	}

	.sharedListTop {
		position: absolute;
		width: 100%;
		height: 100px;
		z-index: 2;
		padding-left: 10px;

		& > div.sharedMenu.mini > div > div > div > div.v-input__slot {
			box-shadow: none;
		}

		& > div:nth-child(4) > div > div {
			margin: 0;
			padding: 0;
		}
		& > div:nth-child(4) > div > div > div > div.v-input__slot {
			padding-right: 0;
		}
	}

	.searchDiv {
		.searchTextDiv {
			float: left;
			width: calc(100% - 30px);

			input {
				width: calc(100% - 30px);
				outline: none;
				margin: 0 5px 0 5px;
			}
		}
		img {
			width: 20px;
			float: left;
			margin: 10px 0 0 0;
			cursor: pointer;
		}

		img:hover {
			opacity: 0.5;
		}
	}

	.sharedListBottom {
		z-index: 1;
		margin-top: 80px;
		width: 100%;
	}

	@media screen and (max-width: 750px) {
		.sharedListTop {
			display: none;
		}

		.sharedListTopMini {
			display: block;
		}

		.sharedListBottom {
			margin-top: 0px;
		}

		.searchDiv {
			border: 1px solid black;
			height: 42px;
			line-height: 38px;
			margin-top: -10px;

			.searchTextDiv {
				input {
					height: 30px;
				}
			}
		}
	}
}

#app-view
	> div.paper.p3
	> div
	> aside
	> div.v-navigation-drawer__content
	> div
	> div:nth-child(4)
	> div.v-list-item__content
	> div
	> div
	> div.v-text-field__details {
	display: none;
}

#app-view
	> div.paper.p3
	> div
	> aside
	> div.v-navigation-drawer__content
	> div
	> div:nth-child(4)
	> div
	> div.v-input.v-input--is-label-active.v-input--is-dirty.v-input--dense.theme--light.v-text-field.v-text-field--is-booted.v-select
	> div
	> div.v-input__slot
	> div.v-select__slot
	> label {
	font-size: 13px;
}

#app-view
	> div.paper.p3
	> div
	> aside
	> div.v-navigation-drawer__content
	> div
	> div:nth-child(4)
	> div
	> div.v-input.v-input--is-label-active.v-input--is-dirty.v-input--dense.theme--light.v-text-field.v-text-field--is-booted.v-select
	> div
	> div.v-input__slot
	> div.v-select__slot
	> div.v-select__selections
	> div {
	font-size: 15px;
}
</style>
