<template>
	<div class="trashcontainer">
		<div class="sort">
			<v-container id="dropdown-example">
				<v-overflow-btn
					v-model="sorted"
					:items="sortlist"
					label="정렬"
					target="#dropdown-example"
					@change="changesort"
				></v-overflow-btn>
			</v-container>
		</div>
		<div class="trashtop">
			<TrashDialog :items="items">
				<div class="todack" dark>토닥토닥</div>
			</TrashDialog>
		</div>
		<div class="masonry" v-lazy-container="{ selector: 'card' }">
			<div v-for="(intrash, index) in trash" :key="intrash.id" class="card">
				<TrashCom :items="items" @load="rendered" class="card-content" :trash="intrash" :index="index" />
			</div>
		</div>
		<v-divider></v-divider>

		<div class="bottomtrash">
			<div class="bottombottomtrash">
				<v-text-field
					v-model="trashcontent"
					@keyup.enter="trashinsert"
					ref="url"
					required
					style="display: inline-block; width: 80%; "
				/>
				<div style="display: inline-block; width: 20%; ">
					<TrashInsertDialog
						:items="items"
						:mood="mood"
						:trash="trash"
						moodsrc="https://cdn.vuetifyjs.com/images/john.png"
						:content="trashcontent"
					>
						<v-btn text id="bottomtrashbtn" @click="getmood">
							<v-icon x-large>mdi-heart-box</v-icon>
						</v-btn>
					</TrashInsertDialog>
				</div>
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
import { getEtrash, postEtrash, sentimentanalysis } from "../../api/etrash";
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
			mood: "슬픔",
			selection: 0,
			sortlist: [
				{ text: "좋아요순" },
				{ text: "최신순" },
				{ text: "남은시간순" },
				{ text: "내가쓴글" },
			],
			url: "",
			items: [
				{
					id: 0,
					text: "화남",
					src: "https://cdn.vuetifyjs.com/images/john.png",
					colorcode: "#88EF9A7F",
				},
				{
					id: 1,
					text: "슬픔",
					src: "https://cdn.vuetifyjs.com/images/john.png",
					colorcode: "#B39DDB7F",
				},
				{
					id: 2,
					text: "짜증",
					src: "https://cdn.vuetifyjs.com/images/john.png",
					colorcode: "#E6EE9C7F",
				},
				{
					id: 3,
					text: "우울",
					src: "https://cdn.vuetifyjs.com/images/john.png",
					colorcode: "#90CAF97F",
				},
				{
					id: 4,
					text: "행복",
					src: "https://cdn.vuetifyjs.com/images/john.png",
					colorcode: "#F48FB17F",
				},
				{
					id: 5,
					text: "기쁨",
					src: "https://cdn.vuetifyjs.com/images/john.png",
					colorcode: "#FFF59D7F",
				},
			],
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
		getEtrash()
			.then(response => {
				this.trash = response.data.content;
				console.log(response.data.content);
			})
			.then(() => {
				vm.resizeAllMasonryItems();
			})
			.catch(error => {});
	},
	watch: {
		imagesCount: function() {
			if (this.imagesCount == this.imageCounter) {
				this.resizeAllMasonryItems();
			}
		},
	},

	methods: {
		getmood() {
			if (this.content == "") {
				alert("내용을 적어주세요!!!");
				return;
			}
			sentimentanalysis({
				description: this.content,
			})
				.then(response => {
					//this.mood = response.data.mood;
					console.log(this.mood);
				})
				.catch(error => {});
			this.mood = "행복";
		},
		changesort() {
			console.log(this.sorted);
			if (this.sorted == "좋아요순") {
				this.sortedArrayByLike();
			} else if (this.sorted == "최신순") {
				this.sortedArrayByDate();
			} else if (this.sorted == "남은시간순") {
				this.sortedArrayByDeleteDate();
			} else if (this.sorted == "내가쓴글") {
				this.sortedArrayByMine();
			}
		},
		sortedArrayByLike() {
			function compare(a, b) {
				if (a.likecount < b.likecount) return -1;
				if (a.likecount > b.likecount) return 1;
				return 0;
			}
			return this.trash.sort(compare);
		},
		sortedArrayByDate() {
			return this.trash.sort(
				(a, b) => new Date(a.createdate) - new Date(b.createdate),
			);
		},
		sortedArrayByDeleteDate() {
			return this.trash.sort(
				(a, b) => new Date(a.deletedate) - new Date(b.deletedate),
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
			return templist;
		},
		calculateImageCount() {
			this.imagesCount = this.trash.length;
		},
		rendered() {
			this.imagesCount++;
		},

		trashinsert() {
			postEtrash({
				description: this.trashcontent,
				mood: "슬픔",
			})
				.then(response => {
					console.log(response);
				})
				.catch(error => {
					reject(error);
				});
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
	},
};
</script>
<style>
.sort {
	height: 80px;
	display: block;
	width: 200px;
}
.masonry {
	display: grid;
	grid-gap: 15px;
	grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
	grid-auto-rows: 0;
	height: 80%;
	overflow-y: auto;
	overflow-x: hidden;
}
.card-content:hover {
	opacity: 0.8;
}
.bottomtrash {
	height: 10%;
	margin: 0 auto;
	background-color: #fce4ec;
}
.todack {
	background-color: #b2dfdb;

	border-radius: 5px;
	color: white;
	border: 1px solid rgba(192, 192, 192, 0.363);
}

#bottomtrashbtn {
	display: inline-block;
}

@media screen and (max-width: 500px) {
	.masonry {
		grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
	}
	.bottombottomtrash {
		width: 100%;
	}
	#bottomtrashbtn {
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
.bottombottomtrash {
	width: 80%;
	margin: 0 auto;
}
.trashcontainer {
	height: 100%;
	margin: 0 auto;

	margin-bottom: 30px;
}
</style>
