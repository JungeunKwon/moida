<template>
	<div class="diarycontainer">
		<div class="diraydrawer">
			<v-app-bar-nav-icon @click.stop="diarydrawer = !diarydrawer"></v-app-bar-nav-icon>
		</div>
		<v-navigation-drawer v-model="diarydrawer" absolute temporary>
			<v-list nav dense>
				<v-list-group value="true">
					<v-list-item-group v-model="group" active-class="deep-purple--text text--accent-4">
						<v-list-item @click="getDiaryByfilter('전체보기')">
							<v-list-item-title>전체보기</v-list-item-title>
						</v-list-item>
						<v-list-item @click="getDiaryByfilter('친구공개')">
							<v-list-item-title>친구공개</v-list-item-title>
						</v-list-item>
						<v-list-item @click="getDiaryByfilter('내가쓴글')">
							<v-list-item-title>내가쓴글</v-list-item-title>
						</v-list-item>
					</v-list-item-group>
				</v-list-group>
			</v-list>
		</v-navigation-drawer>
		<div class="sort">
			<div class="sharedMenu sel">
				<div class="sharedMenu min" @click="getDiaryByfilter('전체보기')">
					<img class="tape" src="../../assets/images/tape.png" />
					<div class="sharedPaper mini">전체보기</div>
				</div>
				<div class="sharedMenu min" @click="getDiaryByfilter('친구공개')">
					<img class="tape" src="../../assets/images/tape.png" />
					<div class="sharedPaper mini">친구공개</div>
				</div>
				<div class="sharedMenu min" @click="getDiaryByfilter('내가쓴글')">
					<img class="tape" src="../../assets/images/tape.png" />
					<div class="sharedPaper mini">내가쓴글</div>
				</div>
			</div>
		</div>
		<div class="middlediary">
			<div class="diarymasonry" v-lazy-container="{ selector: 'diarycard' }">
				<div v-for="(diary) in diaries" :key="diary.id" class="diarycard">
					<DiaryCard :diary="diary" @load="rendered" class="diary-card-content" />
				</div>
			</div>
		</div>
		<div class="bottomdiary">
			<div class="bottomdiray">
				<v-btn @click="openwrite">
					<v-icon x-large>mdi-pencil</v-icon>
				</v-btn>
			</div>
		</div>
	</div>
</template>
<script>
import { mapActions } from "vuex";
import DiaryCard from "./components/diarycard.vue";
export default {
	name: "Diary",
	components: { DiaryCard },
	data() {
		return {
			diarydrawer: false,
			group: null,
			diaries: [],
			imagesCount: 0,
			originaldiary: [],
		};
	},
	created() {},
	mounted() {
		let masonryEvents = ["load", "resize"];
		let vm = this;
		masonryEvents.forEach(function(event) {
			window.addEventListener(event, vm.resizeAllMasonryItems);
		});
		this.getAllDiary();
	},
	watch: {
		imagesCount: function() {
			if (this.imagesCount == this.imageCounter) {
				this.resizeAllMasonryItems();
			}
		},
		group() {
			this.diarydrawer = false;
		},
	},
	methods: {
		...mapActions("diary", [
			"getDiary",
			"postDiary",
			"searchById",
			"putDiary",
		]),
		async getAllDiary() {
			await this.getDiary()
				.then(response => {
					this.diaries = response.data;
					this.originaldiary = response.data;
				})
				.catch(error => {
					console.log(error);
				});
			await this.resizeAllMasonryItems();
		},
		getDiaryByfilter(filter) {
			if (filter == "전체보기") {
				this.diaries = this.originaldiary;
			} else if (filter == "친구공개") {
				this.sortedArrayByFriends();
			} else if (filter == "내가쓴글") {
				this.sortedArrayByMine();
			}
			this.resizeAllMasonryItems();
		},
		sortedArrayByFriends() {
			var templist = [];
			var nownickname = this.$store.getters.nickname;
			for (var i = 0; i < this.originaldiary.length; i++) {
				if (this.originaldiary[i].isPrivate == 2) {
					templist.push(this.originaldiary[i]);
				}
			}
			this.diaries = templist;
		},
		sortedArrayByMine() {
			var templist = [];
			this.diaries = this.originaldiary;
			var nownickname = this.$store.getters.nickname;
			for (var i = 0; i < this.originaldiary.length; i++) {
				if (this.originaldiary[i].nickname == nownickname) {
					templist.push(this.originaldiary[i]);
				}
			}
			this.diaries = templist;
		},
		openwrite() {
			this.$router.push("/writeDiary");
		},
		resizeMasonryItem(item) {
			/* Get the grid object, its row-gap, and the size of its implicit rows */
			let grid = document.getElementsByClassName("diarymasonry")[0],
				rowGap = parseInt(
					window
						.getComputedStyle(grid)
						.getPropertyValue("grid-row-gap"),
				),
				rowHeight = parseInt(
					window
						.getComputedStyle(grid)
						.getPropertyValue("grid-auto-rows"),
				);

			/*
			 * Spanning for any brick = S
			 * Grid's row-gap = G
			 * Size of grid's implicitly create row-track = R
			 * Height of item content = H
			 * Net height of the item = H1 = H + G
			 * Net height of the implicit row-track = T = G + R
			 * S = H1 / T
			 */

			let rowSpan = Math.ceil(
				(item
					.querySelector(".diary-card-content")
					.getBoundingClientRect().height +
					rowGap) /
					(rowHeight + rowGap),
			);

			/* Set the spanning as calculated above (S) */
			item.style.gridRowEnd = "span " + rowSpan;
		},

		rendered() {
			this.imagesCount++;
		},
		resizeAllMasonryItems() {
			// Get all item class objects in one list
			let allItems = document.getElementsByClassName("diarycard");

			/*
			 * Loop through the above list and execute the spanning function to
			 * each list-item (i.e. each masonry item)
			 */

			for (let i = 0; i < allItems.length; i++) {
				this.resizeMasonryItem(allItems[i]);
			}
		},
	},
};
</script>
<style >
.diarycontainer {
	height: 100%;
	margin: 0 auto;
	overflow-y: auto;
	overflow-x: hidden;
}
.middlediary {
	margin-top: 80px;
	z-index: 1;
	height: 100%;
	padding: 20px;
}
.bottomdiary {
	position: absolute;
	bottom: 30px;
	right: 10px;
	width: 50px;
	height: 60px;
	margin: 0 auto;
}
.bottomdiray {
	background-color: #fce4ec;
}

.diraydrawer {
	display: none;
}
.diarymasonry {
	display: grid;
	grid-gap: 15px;
	grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
	grid-auto-rows: 0;
	padding-right: 20px;
	padding-left: 20px;
	border: none;
	margin-bottom: 100px;
}

.diary-card-content:hover {
	opacity: 0.8;
}

.sort {
	height: 80px;
	display: block;
	width: 100%;
	padding-left: 20px;
	position: absolute;
	top: 0;
	z-index: 2;
	/* box-shadow: 1px 1px 5px gray; */
}
@media screen and (max-width: 500px) {
	.diraydrawer {
		display: inline-block;
		position: absolute;
		left: 0px;
		padding: 5px;
	}

	.sort {
		display: none;
	}
}

.sharedMenu.min {
	cursor: pointer;
}

.sharedMenu.min:hover {
	transform: rotate(4deg);
}
</style>