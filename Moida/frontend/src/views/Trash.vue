<template>
	<div class="trashcontainer">
		<!-- <masonry
			:cols="{
				default: 1,
				1500: 1,
				1024: 3,
				1000: 3,
				900: 3,
				800: 2,
				700: 2,
				600: 1,
				400: 1,
			}"
			:gutter="{ default: '15px', 700: '15px' }"
			style="text-align:left"
		>
			<TrashCom
				:trash="intrash"
				v-for="(intrash, index) in trash"
				:key="intrash.id"
				:index="index"
			/>
		</masonry> -->
		<div class="masonry">
			<div
				v-for="(intrash, index) in trash"
				:key="intrash.id"
				class="card"
			>
				<TrashCom
					class="card-content"
					:trash="intrash"
					:index="index"
				/>
			</div>
		</div>
		<div>
			<v-btn class="ma-2" dark @click="trashdialog = true">
				Open Dialog 2
			</v-btn>
			<v-dialog v-model="trashdialog" max-width="70%">
				<v-card>
					<v-card-text>
						<v-chip-group
							v-model="selection"
							active-class="deep-purple accent-4 white--text"
							column
						>
							<v-chip>
								<v-avatar left>
									<v-img
										src="https://cdn.vuetifyjs.com/images/john.png"
									></v-img>
								</v-avatar>
								화남</v-chip
							>

							<v-chip>
								<v-avatar left>
									<v-img
										src="https://cdn.vuetifyjs.com/images/john.png"
									></v-img> </v-avatar
								>슬픔</v-chip
							>

							<v-chip>
								<v-avatar left>
									<v-img
										src="https://cdn.vuetifyjs.com/images/john.png"
									></v-img> </v-avatar
								>짜증</v-chip
							>

							<v-chip>
								<v-avatar left>
									<v-img
										src="https://cdn.vuetifyjs.com/images/john.png"
									></v-img> </v-avatar
								>우울</v-chip
							>

							<v-chip>
								<v-avatar left>
									<v-img
										src="https://cdn.vuetifyjs.com/images/john.png"
									></v-img> </v-avatar
								>행복</v-chip
							>
							<v-chip>
								<v-avatar left>
									<v-img
										src="https://cdn.vuetifyjs.com/images/john.png"
									></v-img> </v-avatar
								>기쁨</v-chip
							>
						</v-chip-group>
					</v-card-text>
				</v-card>
			</v-dialog>
		</div>
	</div>
</template>

<script>
// @ is an alias to /src
import TrashCom from "@/components/TrashComponent.vue";

export default {
	name: "Trash",
	components: {
		TrashCom,
	},
	data() {
		return {
			trashdialog: false,
			selection: 0,

			trash: [
				{
					id: 0,
					description: "죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고",
					mood: "슬픔",
					musicid: 0,
					date: "2020-05-20 12:00:00",
					likes: 25,
					videoid: "cXMcfCeMvKg",
					musictitle: "San E - Tour Bus",
				},
				{
					id: 1,
					description:
						"죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다",
					mood: "슬픔",
					musicid: 0,
					date: "2020-05-20 13:00:00",
					likes: 20,
					videoid: "oUUhO5rO01k",
					musictitle:
						"추억의 걸그룹 노래들로 싹쓸이 해왔어 😘 | PLAYLIST",
				},
				{
					id: 2,
					description:
						"죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고",
					mood: "슬픔",
					musicid: 0,
					date: "2020-05-20 14:00:00",
					likes: 19,
					videoid: "wMgGxo9yppA",
					musictitle:
						"[MV] Here I Am Again (다시 난, 여기) - Baek Yerin (백예린) | Crash Landing on You (사랑의 불시착) OST Pt. 4 [ENG]",
				},
				{
					id: 3,
					description: "죽고싶죽고싶다죽고싶다죽고싶다죽고싶다죽고다",
					mood: "슬픔",
					musicid: 0,
					date: "2020-05-20 14:00:00",
					likes: 19,
					videoid: "wMgGxo9yppA",
					musictitle:
						"[MV] Here I Am Again (다시 난, 여기) - Baek Yerin (백예린) | Crash Landing on You (사랑의 불시착) OST Pt. 4 [ENG]",
				},
				{
					id: 4,
					description:
						"죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고",
					mood: "슬픔",
					musicid: 0,
					date: "2020-05-20 14:00:00",
					likes: 19,
					videoid: "wMgGxo9yppA",
					musictitle:
						"[MV] Here I Am Again (다시 난, 여기) - Baek Yerin (백예린) | Crash Landing on You (사랑의 불시착) OST Pt. 4 [ENG]",
				},
				{
					id: 5,
					description:
						"죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고",
					mood: "슬픔",
					musicid: 0,
					date: "2020-05-20 14:00:00",
					likes: 19,
					videoid: "wMgGxo9yppA",
					musictitle:
						"[MV] Here I Am Again (다시 난, 여기) - Baek Yerin (백예린) | Crash Landing on You (사랑의 불시착) OST Pt. 4 [ENG]",
				},
				{
					id: 6,
					description:
						"죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고죽고싶다죽고싶다죽고싶다죽고싶다죽고",
					mood: "슬픔",
					musicid: 0,
					date: "2020-05-20 14:00:00",
					likes: 19,
					videoid: "wMgGxo9yppA",
					musictitle:
						"[MV] Here I Am Again (다시 난, 여기) - Baek Yerin (백예린) | Crash Landing on You (사랑의 불시착) OST Pt. 4 [ENG]",
				},
			],
		};
	},
	created() {
		let masonryEvents = ["load", "resize"];
		let vm = this;
		masonryEvents.forEach(function(event) {
			window.addEventListener(event, vm.resizeAllMasonryItems);
		});
	},
	watch: {
		imagesCount: function() {
			this.resizeAllMasonryItems();
		},
	},
	methods: {
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
}

@media screen and (max-width: 400px) {
	.masonry {
		grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
	}
}

.trashcontainer {
	height: 100%;
	margin: 0 auto;
	overflow-y: auto;
	overflow-x: hidden;
}
</style>
