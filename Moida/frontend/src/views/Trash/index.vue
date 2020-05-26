<template>
	<div class="trashcontainer">
		<div class="trashtop">
			<TrashDialog :items="items">
				<div class="todack" dark>토닥토닥</div>
			</TrashDialog>
		</div>
		<div class="masonry" v-lazy-container="{ selector: 'card' }">
			<div v-for="(intrash, index) in trash" :key="intrash.id" class="card">
				<TrashCom @load="rendered" class="card-content" :trash="intrash" :index="index" />
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
						mood="슬픔"
						moodsrc="https://cdn.vuetifyjs.com/images/john.png"
						:content="trashcontent"
					>
						<v-btn text id="bottomtrashbtn">
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
import { getEtrash, postEtrash } from "../../api/etrash";
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
			selection: 0,
			url: "",
			items: [
				{
					id: 0,
					text: "화남",
					src: "https://cdn.vuetifyjs.com/images/john.png",
				},
				{
					id: 1,
					text: "슬픔",
					src: "https://cdn.vuetifyjs.com/images/john.png",
				},
				{
					id: 2,
					text: "짜증",
					src: "https://cdn.vuetifyjs.com/images/john.png",
				},
				{
					id: 3,
					text: "우울",
					src: "https://cdn.vuetifyjs.com/images/john.png",
				},
				{
					id: 4,
					text: "행복",
					src: "https://cdn.vuetifyjs.com/images/john.png",
				},
				{
					id: 5,
					text: "기쁨",
					src: "https://cdn.vuetifyjs.com/images/john.png",
				},
			],
			trash: [
				// {
				// 	id: 0,
				// 	description: "죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고",
				// 	mood: "슬픔",
				// 	musicid: 0,
				// 	date: "2020-05-20 12:00:00",
				// 	likes: 25,
				// 	videoid: "cXMcfCeMvKg",
				// 	musictitle: "San E - Tour Bus",
				// },
				// {
				// 	id: 1,
				// 	description:
				// 		"죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다",
				// 	mood: "슬픔",
				// 	musicid: 0,
				// 	date: "2020-05-20 13:00:00",
				// 	likes: 20,
				// 	videoid: "oUUhO5rO01k",
				// 	musictitle:
				// 		"추억의 걸그룹 노래들로 싹쓸이 해왔어 😘 | PLAYLIST",
				// },
				// {
				// 	id: 2,
				// 	description:
				// 		"죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고",
				// 	mood: "슬픔",
				// 	musicid: 0,
				// 	date: "2020-05-20 14:00:00",
				// 	likes: 19,
				// 	videoid: "wMgGxo9yppA",
				// 	musictitle:
				// 		"[MV] Here I Am Again (다시 난, 여기) - Baek Yerin (백예린) | Crash Landing on You (사랑의 불시착) OST Pt. 4 [ENG]",
				// },
				// {
				// 	id: 3,
				// 	description: "죽고싶죽고싶다죽고싶다죽고싶다죽고싶다죽고다",
				// 	mood: "슬픔",
				// 	musicid: 0,
				// 	date: "2020-05-20 14:00:00",
				// 	likes: 19,
				// 	videoid: "wMgGxo9yppA",
				// 	musictitle:
				// 		"[MV] Here I Am Again (다시 난, 여기) - Baek Yerin (백예린) | Crash Landing on You (사랑의 불시착) OST Pt. 4 [ENG]",
				// },
				// {
				// 	id: 4,
				// 	description:
				// 		"죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고",
				// 	mood: "슬픔",
				// 	musicid: 0,
				// 	date: "2020-05-20 14:00:00",
				// 	likes: 19,
				// 	videoid: "wMgGxo9yppA",
				// 	musictitle:
				// 		"[MV] Here I Am Again (다시 난, 여기) - Baek Yerin (백예린) | Crash Landing on You (사랑의 불시착) OST Pt. 4 [ENG]",
				// },
				// {
				// 	id: 5,
				// 	description:
				// 		"죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고",
				// 	mood: "슬픔",
				// 	musicid: 0,
				// 	date: "2020-05-20 14:00:00",
				// 	likes: 19,
				// 	videoid: "wMgGxo9yppA",
				// 	musictitle:
				// 		"[MV] Here I Am Again (다시 난, 여기) - Baek Yerin (백예린) | Crash Landing on You (사랑의 불시착) OST Pt. 4 [ENG]",
				// },
				// {
				// 	id: 6,
				// 	description:
				// 		"죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고",
				// 	mood: "슬픔",
				// 	musicid: 0,
				// 	date: "2020-05-20 14:00:00",
				// 	likes: 19,
				// 	videoid: "wMgGxo9yppA",
				// 	musictitle:
				// 		"[MV] Here I Am Again (다시 난, 여기) - Baek Yerin (백예린) | Crash Landing on You (사랑의 불시착) OST Pt. 4 [ENG]",
				// },
				// {
				// 	id: 7,
				// 	description: "죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고",
				// 	mood: "슬픔",
				// 	musicid: 0,
				// 	date: "2020-05-20 12:00:00",
				// 	likes: 25,
				// 	videoid: "cXMcfCeMvKg",
				// 	musictitle: "San E - Tour Bus",
				// },
				// {
				// 	id: 8,
				// 	description:
				// 		"죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다",
				// 	mood: "슬픔",
				// 	musicid: 0,
				// 	date: "2020-05-20 13:00:00",
				// 	likes: 20,
				// 	videoid: "oUUhO5rO01k",
				// 	musictitle:
				// 		"추억의 걸그룹 노래들로 싹쓸이 해왔어 😘 | PLAYLIST",
				// },
				// {
				// 	id: 9,
				// 	description:
				// 		"죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고",
				// 	mood: "슬픔",
				// 	musicid: 0,
				// 	date: "2020-05-20 14:00:00",
				// 	likes: 19,
				// 	videoid: "wMgGxo9yppA",
				// 	musictitle:
				// 		"[MV] Here I Am Again (다시 난, 여기) - Baek Yerin (백예린) | Crash Landing on You (사랑의 불시착) OST Pt. 4 [ENG]",
				// },
				// {
				// 	id: 10,
				// 	description: "죽고싶죽고싶다죽고싶다죽고싶다죽고싶다죽고다",
				// 	mood: "슬픔",
				// 	musicid: 0,
				// 	date: "2020-05-20 14:00:00",
				// 	likes: 19,
				// 	videoid: "wMgGxo9yppA",
				// 	musictitle:
				// 		"[MV] Here I Am Again (다시 난, 여기) - Baek Yerin (백예린) | Crash Landing on You (사랑의 불시착) OST Pt. 4 [ENG]",
				// },
				// {
				// 	id: 11,
				// 	description:
				// 		"죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고",
				// 	mood: "슬픔",
				// 	musicid: 0,
				// 	date: "2020-05-20 14:00:00",
				// 	likes: 19,
				// 	videoid: "wMgGxo9yppA",
				// 	musictitle:
				// 		"[MV] Here I Am Again (다시 난, 여기) - Baek Yerin (백예린) | Crash Landing on You (사랑의 불시착) OST Pt. 4 [ENG]",
				// },
				// {
				// 	id: 12,
				// 	description:
				// 		"죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고",
				// 	mood: "슬픔",
				// 	musicid: 0,
				// 	date: "2020-05-20 14:00:00",
				// 	likes: 19,
				// 	videoid: "wMgGxo9yppA",
				// 	musictitle:
				// 		"[MV] Here I Am Again (다시 난, 여기) - Baek Yerin (백예린) | Crash Landing on You (사랑의 불시착) OST Pt. 4 [ENG]",
				// },
				// {
				// 	id: 13,
				// 	description:
				// 		"죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고",
				// 	mood: "슬픔",
				// 	musicid: 0,
				// 	date: "2020-05-20 14:00:00",
				// 	likes: 19,
				// 	videoid: "wMgGxo9yppA",
				// 	musictitle:
				// 		"[MV] Here I Am Again (다시 난, 여기) - Baek Yerin (백예린) | Crash Landing on You (사랑의 불시착) OST Pt. 4 [ENG]",
				// },
			],
		};
	},
	created() {
		// let masonryEvents = ["load", "resize"];
		// let vm = this;
		// masonryEvents.forEach(function(event) {
		// 	window.addEventListener(event, vm.resizeAllMasonryItems);
		// });
	},
	mounted() {
		getEtrash()
			.then(response => {
				this.trash = response.data.content;
				console.log(response.data);
			})
			.catch(error => {});
		let masonryEvents = ["load", "resize"];
		let vm = this;
		masonryEvents.forEach(function(event) {
			window.addEventListener(event, vm.resizeAllMasonryItems);
		});
		vm.resizeAllMasonryItems();
	},
	watch: {
		imagesCount: function() {
			if (this.imagesCount == this.imageCounter) {
				this.resizeAllMasonryItems();
			}
		},
	},

	methods: {
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
.masonry {
	display: grid;
	grid-gap: 15px;
	grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
	grid-auto-rows: 0;
	height: 90%;
	overflow-y: auto;
	overflow-x: hidden;
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
