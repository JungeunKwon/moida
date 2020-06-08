<template>
	<div class="diarycontainer">
		<div class="diraydrawer">
			<v-app-bar-nav-icon
				@click.stop="diarydrawer = !diarydrawer"
			></v-app-bar-nav-icon>
		</div>

		<v-navigation-drawer v-model="diarydrawer" absolute temporary>
			<v-list nav dense>
				<v-list-group value="true">
					<template v-slot:activator>
						<v-list-item-title>정렬</v-list-item-title>
					</template>
					<v-list-item-group
						v-model="group"
						active-class="deep-purple--text text--accent-4"
					>
						<v-list-item
							v-for="item in sortlist"
							:key="item.id"
							@click="changesortSmall(item.text)"
						>
							<v-list-item-title>{{
								item.text
							}}</v-list-item-title>
						</v-list-item>
					</v-list-item-group>
				</v-list-group>
				<v-list-item-group
					v-model="group2"
					active-class="deep-purple--text text--accent-4"
				>
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
			</v-list>
		</v-navigation-drawer>
		<div class="diarysort">
			<div class="sharedMenu sel">
				<img class="tape" src="../../assets/images/tape.png" />
				<div class="sharedPaper mini" style="width:100px">
					<v-select
						:items="sortlist"
						@change="changesort"
						color="gray"
						label="정렬"
						v-model="sorted"
						outline
					></v-select>
				</div>
			</div>
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

		<div class="middlediary">
			<div
				class="diarymasonry"
				v-lazy-container="{ selector: 'diarycard' }"
			>
				<div v-for="diary in diaries" :key="diary.id" class="diarycard">
					<DiaryCard
						:diary="diary"
						@load="rendered"
						class="diary-card-content"
					/>
				</div>
			</div>
		</div>
		<div id="writeDiaryDiv" @click="openwrite">
			<v-icon large>mdi-pencil</v-icon>&nbsp;다이어리 쓰기
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
			group2: null,
			diaries: [],
			imagesCount: 0,
			originaldiary: [],
			imageCounter: 0,
			sortlist: [
				{ text: "좋아요순" },
				{ text: "최신순" },
				{ text: "조회수" },
				{ text: "댓글순" },
			],
			sorted: "",
		};
	},
	updated() {
		this.resizeAllMasonryItems();
	},
	created() {
		let masonryEvents = ["load", "resize"];
		let vm = this;
		masonryEvents.forEach(function(event) {
			window.addEventListener(event, vm.resizeAllMasonryItems);
		});
		vm.resizeAllMasonryItems();
	},
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
			this.group2 = null;
		},
		group2() {
			this.diarydrawer = false;
			this.group = null;
		},
	},
	methods: {
		...mapActions("diary", [
			"getDiary",
			"postDiary",
			"searchById",
			"putDiary",
		]),
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
			this.sorted = "";
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
			this.imagesCount = templist.length;
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
			this.imagesCount = templist.length;
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
		changesort() {
			if (this.sorted == "좋아요순") {
				this.sortedArrayByLike();
			} else if (this.sorted == "최신순") {
				this.sortedArrayByDate();
			} else if (this.sorted == "조회수") {
				this.sortedArrayByViews();
			} else if (this.sorted == "댓글순") {
				this.sortedArrayByComment();
			}
			this.resizeAllMasonryItems();
		},
		changesortSmall(sort) {
			if (sort == "좋아요순") {
				this.sortedArrayByLike();
			} else if (sort == "최신순") {
				this.sortedArrayByDate();
			} else if (sort == "조회수") {
				this.sortedArrayByViews();
			}

			this.resizeAllMasonryItems();
		},
		sortedArrayByLike() {
			return this.diaries.sort((a, b) => b.likecount - a.likecount);
		},
		sortedArrayByComment() {
			return this.diaries.sort((a, b) => b.commentcount - a.commentcount);
		},
		sortedArrayByDate() {
			return this.diaries.sort(
				(a, b) => new Date(b.inputDate) - new Date(a.inputDate),
			);
		},
		sortedArrayByViews() {
			return this.diaries.sort(
				(a, b) => new Date(b.viewcount) - new Date(a.viewcount),
			);
		},
		calculateImageCount() {
			this.imagesCount = this.diaries.length;
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
<style>
#app-view
	> div.paper.p3
	> div
	> div.diarysort
	> div.sharedMenu.sel
	> div
	> div {
	padding: 0;
}

#app-view
	> div.paper.p3
	> div
	> div.diarysort
	> div.sharedMenu.sel
	> div
	> div
	> div
	> div.v-input__slot {
	padding: 5px;
}

#app-view > div.paper.p3 > div > div.diarysort > div.sharedMenu.sel > div {
	margin-top: -25px;
}

.diarycontainer {
	height: 100%;
	margin: 0 auto;
	overflow-y: auto;
	overflow-x: hidden;
	background-color: #f7ebc375;
}
.middlediary {
	margin-top: 80px;
	z-index: 1;
	height: 100%;
	padding: 20px;
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
	cursor: pointer;
	opacity: 0.8;
}

.diarysort {
	display: block;
	height: 80px;
	width: 100%;
	padding-left: 20px;
	position: absolute;
	top: 0;
	z-index: 2;
	/* box-shadow: 1px 1px 5px gray; */
}
@media screen and (max-width: 504px) {
	.diraydrawer {
		display: inline-block;
		position: absolute;
		left: 0px;
		padding: 5px;
	}

	.middlediary {
		margin-top: 80px;
		z-index: 1;
		height: 100%;
		padding: 2px;
	}

	.diarymasonry {
		display: grid;
		grid-gap: 15px;
		grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
		grid-auto-rows: 0;

		border: none;
		margin-bottom: 100px;
	}
	.diarysort {
		display: none;
	}
}
#writeDiaryDiv {
	position: absolute;
	font-family: KyoboHand;
	font-size: 20px;
	bottom: 30px;
	right: -105px;
	width: 150px;
	background-color: white;
	height: 45px;
	padding: 5px;
	border-radius: 10px 0 0 10px;
	box-shadow: 1px 1px 5px rgba(128, 128, 128, 0.61);
	transition: 0.3s ease;
	cursor: pointer;
}

#writeDiaryDiv:hover {
	right: 0;
}
.sharedMenu.min {
	cursor: pointer;
}
.v-text-field.v-input--is-focused > .v-input__control > .v-input__slot:after {
	display: inline-block;
	border: 0;
	align-items: center;
	text-align: center;
}
.theme--light.v-text-field > .v-input__control > .v-input__slot:before {
	display: inline-block;
	border: 0;
	align-items: center;
	text-align: center;
}
.v-text-field > .v-input__control > .v-input__slot {
	padding-left: 10px;
	padding-bottom: 10px;
}
.sharedMenu.min:hover {
	transform: rotate(4deg);
}
</style>
