<template>
	<div class="trashcontainer">
		<div class="trashtop">
			<TrashDialog :items="items">
				<div class="todack" dark>토닥토닥</div>
			</TrashDialog>
		</div>
		<div class="trashdrawer">
			<v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
		</div>
		<v-navigation-drawer v-model="drawer" absolute temporary>
			<v-list nav dense>
				<v-list-group value="true">
					<template v-slot:activator>
						<v-list-item-title>정렬</v-list-item-title>
					</template>
					<v-list-item-group v-model="group" active-class="deep-purple--text text--accent-4">
						<v-list-item v-for="item in sortlist" :key="item.id" @click="changesortSmall(item.text)">
							<v-list-item-title>{{ item.text }}</v-list-item-title>
						</v-list-item>
					</v-list-item-group>
				</v-list-group>
				<v-list-item-group v-model="group2" active-class="deep-purple--text text--accent-4">
					<v-list-item @click="getEtrashByMoodMain('전체보기')">
						<v-list-item-title>전체보기</v-list-item-title>
					</v-list-item>
					<v-list-item @click="changesortSmall('내가쓴글')">
						<v-list-item-title>내가쓴글</v-list-item-title>
					</v-list-item>
					<v-list-item v-for="item in items" :key="item.id" @click="getEtrashByMoodMain(item.text)">
						<v-list-item-title>{{ item.text }}</v-list-item-title>
					</v-list-item>
				</v-list-item-group>
			</v-list>
		</v-navigation-drawer>
		<div class="sort">
			<!-- <v-container id="dropdown-example" class="filtertoggle">
				<v-overflow-btn
					v-model="sorted"
					:items="sortlist"
					label="정렬"
					target="#dropdown-example"
					@change="changesort"
					class="sharedPaper mini"
				></v-overflow-btn>
			</v-container>-->
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
			<div class="sharedMenu min" @click="getEtrashByMoodMain('전체보기')">
				<img class="tape" src="../../assets/images/tape.png" />
				<div class="sharedPaper mini">전체보기</div>
			</div>
			<div class="sharedMenu min" @click="changesortSmall('내가쓴글')">
				<img class="tape" src="../../assets/images/tape.png" />
				<div class="sharedPaper mini">내가쓴글</div>
			</div>
			<div
				class="sharedMenu min"
				v-for="(item, index) in items"
				:key="item.id"
				@click="getEtrashByMoodMain(item.text)"
			>
				<img class="tape" src="../../assets/images/tape.png" />
				<div class="sharedPaper mini" :background-color="getcolor(index)">{{ item.text }}</div>
			</div>
		</div>
		<div class="trashmiddle">
			<div class="masonry" v-lazy-container="{ selector: 'card' }">
				<div v-for="(intrash, index) in trash" :key="intrash.id" class="card">
					<TrashCom
						:items="items"
						@load="rendered"
						@removeEtrash="removeEtrash"
						class="card-content"
						:trash="intrash"
						:index="index"
					/>
				</div>
			</div>
		</div>

		<div class="bottomtrash">
			<div class="bottomtrashbtn">
				<TrashInsertDialog :items="items" @getEtrashMain="getEtrashMain">
					<v-btn>
						<v-icon x-large>mdi-heart-box</v-icon>
					</v-btn>
				</TrashInsertDialog>
			</div>
		</div>
	</div>
</template>

<script>
// @ is an alias to /src
import TrashDialog from "./components/TrashDialog";
import TrashInsertDialog from "./components/TrashInsertDialog";
import TrashCom from "./components/TrashComponent.vue";
import axios from "axios";
import {
	getEtrash,
	postEtrash,
	sentimentanalysis,
	getEtrashByMood,
} from "../../api/etrash";
export default {
	name: "Trash",
	components: {
		TrashCom,
		TrashDialog,
		TrashInsertDialog,
	},
	data() {
		return {
			trashdialog: false,
			imageCounter: 0,
			imagesCount: 0,
			trashcontent: "",
			sorted: "",
			drawer: false,
			group: null,
			group2: null,
			selection: 0,

			sortlist: [
				{ text: "좋아요순" },
				{ text: "최신순" },
				{ text: "남은시간순" },
			],
			url: "",
			items: [
				{
					//공포 보라 혐오 녹색
					id: 0,
					text: "기쁨",
					src: "https://cdn.vuetifyjs.com/images/john.png",
					colorcode: "#F48FB17F",
				},
				{
					id: 1,
					text: "신뢰",
					src: "https://cdn.vuetifyjs.com/images/john.png",
					colorcode: "#90CAF97F",
				},
				{
					id: 2,
					text: "공포",
					src: "https://cdn.vuetifyjs.com/images/john.png",
					colorcode: "#6161617F",
				},
				{
					id: 3,
					text: "기대",
					src: "https://cdn.vuetifyjs.com/images/john.png",
					colorcode: "#FFF59D7F",
				},
				{
					id: 4,
					text: "놀라움",
					src: "https://cdn.vuetifyjs.com/images/john.png",
					colorcode: "#FFAB917F",
				},
				{
					id: 5,
					text: "슬픔",
					src: "https://cdn.vuetifyjs.com/images/john.png",
					colorcode: "#CE93D87F",
				},
				{
					id: 6,
					text: "혐오",
					src: "https://cdn.vuetifyjs.com/images/john.png",
					colorcode: "#BCAAA47F",
				},
				{
					id: 7,
					text: "분노",
					src: "https://cdn.vuetifyjs.com/images/john.png",
					colorcode: "#EF9A9A7F",
				},
			],
			origintrash: [],
			trash: [],
		};
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
		this.getEtrashMain();
	},
	watch: {
		imagesCount: function() {
			if (this.imagesCount == this.imageCounter) {
				this.resizeAllMasonryItems();
			}
		},
		group() {
			this.drawer = false;
			this.group2 = null;
		},
		group2() {
			this.drawer = false;
			this.group = null;
		},
	},

	methods: {
		getcolor(select) {
			return this.items[select].colorcode;
		},

		removeEtrash(index) {
			let vm = this;
			console.log("지우기", index);
			this.trash.splice(index, 1);
			vm.resizeAllMasonryItems();
		},
		getEtrashByMoodMain(passmood) {
			this.sorted = "";

			if (passmood == "전체보기") {
				this.getEtrashMain();
			} else {
				let vm = this;
				getEtrashByMood(passmood)
					.then(response => {
						this.trash = response.data.content;
						this.origintrash = response.data.content;
					})
					.then(() => {
						vm.resizeAllMasonryItems();
					})
					.catch(error => {});
			}
		},
		getEtrashMain() {
			let vm = this;
			this.trashcontent = "";
			getEtrash()
				.then(response => {
					this.trash = response.data.content;
					this.origintrash = response.data.content;
				})
				.then(() => {
					vm.resizeAllMasonryItems();
				})
				.catch(error => {});
		},
		changesort() {
			if (this.sorted == "좋아요순") {
				this.sortedArrayByLike();
			} else if (this.sorted == "최신순") {
				this.sortedArrayByDate();
			} else if (this.sorted == "남은시간순") {
				this.sortedArrayByDeleteDate();
			} else if (this.sorted == "내가쓴글") {
				this.sortedArrayByMine();
			}
			this.resizeAllMasonryItems();
		},
		changesortSmall(sort) {
			if (sort == "좋아요순") {
				this.sortedArrayByLike();
			} else if (sort == "최신순") {
				this.sortedArrayByDate();
			} else if (sort == "남은시간순") {
				this.sortedArrayByDeleteDate();
			} else if (sort == "내가쓴글") {
				this.sortedArrayByMine();
			}

			this.resizeAllMasonryItems();
		},
		sortedArrayByLike() {
			this.trash = this.origintrash;
			return this.trash.sort((a, b) => b.likecount - a.likecount);
		},
		sortedArrayByDate() {
			this.trash = this.origintrash;

			return this.trash.sort(
				(a, b) => new Date(b.createDate) - new Date(a.createDate),
			);
		},
		sortedArrayByDeleteDate() {
			this.trash = this.origintrash;

			return this.trash.sort(
				(a, b) => new Date(a.deleteDate) - new Date(b.deleteDate),
			);
		},
		sortedArrayByMine() {
			var templist = [];
			var nowemail = this.$store.getters.email;
			for (var i = 0; i < this.trash.length; i++) {
				if (this.trash[i].account.email == nowemail) {
					templist.push(this.trash[i]);
				}
			}
			this.trash = templist;
			return templist;
		},
		calculateImageCount() {
			this.imagesCount = this.trash.length;
		},
		rendered() {
			this.imagesCount++;
		},

		resizeMasonryItem(item) {
			/* Get the grid object, its row-gap, and the size of its implicit rows */
			let grid = document.getElementsByClassName("masonry")[0],
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
				(item.querySelector(".card-content").getBoundingClientRect()
					.height +
					rowGap) /
					(rowHeight + rowGap),
			);

			/* Set the spanning as calculated above (S) */
			item.style.gridRowEnd = "span " + rowSpan;
		},
		resizeAllMasonryItems() {
			// Get all item class objects in one list
			let allItems = document.getElementsByClassName("card");

			/*
			 * Loop through the above list and execute the spanning function to
			 * each list-item (i.e. each masonry item)
			 */

			for (let i = 0; i < allItems.length; i++) {
				this.resizeMasonryItem(allItems[i]);
			}
		},
		upDateTrash(trash) {
			this.trash.push(trash);
		},
	},
};
</script>
<style>
.trashcontainer {
	height: 100%;
	margin: 0 auto;
	overflow-y: auto;
	overflow-x: hidden;
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
.trashmiddle {
	margin-top: 80px;
	z-index: 1;
	height: 100%;
}

.trashlogin_input {
	width: 100%;
	height: 60px;
	border: 1px solid silver;
}
.sharedMenu.min {
	cursor: pointer;
}

.sharedMenu.min:hover {
	transform: rotate(4deg);
}

.masonry {
	display: grid;
	grid-gap: 15px;
	grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
	grid-auto-rows: 0;
	padding-right: 20px;
	padding-left: 20px;
	border: none;
	margin-bottom: 100px;
}

.card-content:hover {
	opacity: 0.8;
}
.bottomtrash {
	position: absolute;
	bottom: 30px;
	right: 10px;
	width: 50px;
	height: 60px;
	margin: 0 auto;
}
.bottomtrashbtn {
	background-color: #fce4ec;
}
.todack {
	background-color: #b2dfdb;

	border-radius: 5px;
	color: white;
	border: 1px solid rgba(192, 192, 192, 0.363);
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
.trashdrawer {
	display: none;
}
@media screen and (max-width: 769px) {
	.masonry {
		display: grid;
		grid-gap: 15px;
		grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
		grid-auto-rows: 0;
		margin-bottom: 100px;
	}
}
@media screen and (max-width: 640px) {
	.masonry {
		display: grid;
		grid-gap: 15px;
		grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
		grid-auto-rows: 0;

		margin-bottom: 100px;
	}
}

@media screen and (max-width: 500px) {
	.masonry {
		display: grid;
		grid-gap: 15px;
		grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
		grid-auto-rows: 0;

		margin-bottom: 100px;
	}
}
@media screen and (max-width: 810px) {
	.trashmiddle {
		margin-top: 20px;
	}
	.trashdrawer {
		display: inline-block;
		position: absolute;
		left: 0px;
		padding: 5px;
	}

	.sort {
		display: none;
	}
}
.trashtop {
	position: fixed;
	top: 15px;
	left: 15px;
	width: 120px;
	height: 50px;
	cursor: pointer;
}

.filtertoggle {
	display: inline-block;
	width: 200px;
}
.filtermood {
	display: inline-block;
}

/* 정렬 부분 */
#app-view > div.paper.p3 > div > div.sort > div.sharedMenu.sel > div > div {
	padding: 0 0 0 0;
}

#app-view
	> div.paper.p3
	> div
	> div.sort
	> div.sharedMenu.sel
	> div
	> div
	> div
	> div.v-input__slot {
	padding: 5px;
}

#app-view > div.paper.p3 > div > div.sort > div.sharedMenu.sel > div {
	margin-top: -26px;
}
</style>
