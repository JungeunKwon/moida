<template>
	<div class="trashcontainer">
		<div class="trashtop">
			<div class="todack" dark @click="trashdialog = true">
				토닥토닥
			</div>
			<v-dialog
				v-model="trashdialog"
				max-width="50%"
				style="padding:20px"
			>
				<v-card>
					<v-card-text>
						<v-chip-group
							v-model="selection"
							active-class="deep-purple accent-4 white--text"
							column
						>
							<div style="margin: 0 auto; width:80%">
								<v-chip>
									<v-avatar left>
										<v-img
											src="https://cdn.vuetifyjs.com/images/john.png"
										></v-img> </v-avatar
									>화남
								</v-chip>

								<v-chip>
									<v-avatar left>
										<v-img
											src="https://cdn.vuetifyjs.com/images/john.png"
										></v-img> </v-avatar
									>슬픔
								</v-chip>

								<v-chip>
									<v-avatar left>
										<v-img
											src="https://cdn.vuetifyjs.com/images/john.png"
										></v-img> </v-avatar
									>짜증
								</v-chip>

								<v-chip>
									<v-avatar left>
										<v-img
											src="https://cdn.vuetifyjs.com/images/john.png"
										></v-img> </v-avatar
									>우울
								</v-chip>

								<v-chip>
									<v-avatar left>
										<v-img
											src="https://cdn.vuetifyjs.com/images/john.png"
										></v-img> </v-avatar
									>행복
								</v-chip>
								<v-chip>
									<v-avatar left>
										<v-img
											src="https://cdn.vuetifyjs.com/images/john.png"
										></v-img> </v-avatar
									>기쁨
								</v-chip>
							</div>
						</v-chip-group>
						<div style="margin: 0 auto; width:70%">
							<p class="font-weight-bold">
								해당 감정에 자주 듣는 노래 url을
								입력해주세요.(유투브)
							</p>
							<div style="margin: 0 auto; width:100%">
								<v-text-field
									v-model="url"
									@keyup.enter="insert"
									label="url"
									ref="url"
									required
									:rules="[youtube_parser]"
									style="display: inline-block; width: 80%;"
								/>

								<v-btn
									text
									style="display: inline-block; width: 20%;"
									@click="insert"
									>입력</v-btn
								>
							</div>
						</div>
					</v-card-text>
				</v-card>
			</v-dialog>
		</div>
		<div class="masonry" v-lazy-container="{ selector: 'card' }">
			<div
				v-for="(intrash, index) in trash"
				:key="intrash.id"
				class="card"
			>
				<TrashCom
					@load="rendered"
					class="card-content"
					:trash="intrash"
					:index="index"
				/>
			</div>
		</div>
		<v-divider></v-divider>

		<div class="bottomtrash">
			<div class="bottombottomtrash">
				<v-text-field
					v-model="url"
					@keyup.enter="trashinsert"
					ref="url"
					required
					style="display: inline-block; width: 80%; "
				/>

				<v-btn text id="bottomtrashbtn" style="" @click="trashinsert">
					<v-icon x-large>mdi-heart-box</v-icon>
				</v-btn>
			</div>
		</div>
	</div>
</template>

<script>
// @ is an alias to /src
import TrashCom from "./components/TrashComponent.vue";
import axios from "axios";

export default {
	name: "Trash",
	components: {
		TrashCom,
	},
	data() {
		return {
			trashdialog: false,
			selection: 0,
			imageCounter: 0,
			imagesCount: 0,
			url: "",
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
				{
					id: 7,
					description: "죽고싶다죽고싶다죽고싶다죽고싶다죽고싶다죽고",
					mood: "슬픔",
					musicid: 0,
					date: "2020-05-20 12:00:00",
					likes: 25,
					videoid: "cXMcfCeMvKg",
					musictitle: "San E - Tour Bus",
				},
				{
					id: 8,
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
					id: 9,
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
					id: 10,
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
					id: 11,
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
					id: 12,
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
					id: 13,
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
		// let masonryEvents = ["load", "resize"];
		// let vm = this;
		// masonryEvents.forEach(function(event) {
		// 	window.addEventListener(event, vm.resizeAllMasonryItems);
		// });
	},
	mounted() {
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
		youtube_parser2(url) {
			var regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/;

			var match = url.match(regExp);

			return match && match[7].length == 11 ? match[7] : false;
		},
		youtube_parser() {
			var regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/;

			var match = this.url.match(regExp);
			if (match && match[7].length == 11) {
				return true;
			} else {
				return "youtube 주소가 아닙니다.";
			}
		},
		calculateImageCount() {
			this.imagesCount = this.trash.length;
		},
		rendered() {
			this.imagesCount++;
		},
		insert() {
			var videoid = this.youtube_parser2(this.url);
			alert(videoid);
		},
		trashinsert() {
			axios
				.post("http://192.168.77.80:8080/v1/signup", formData)
				.then(response => {
					console.log(
						"response : ",
						JSON.stringify(response, null, 2),
					);
					if (response.status == 200) {
						alert("회원 가입 성공");
						this.$router.push("/");
					} else {
						alert("회원 가입 실패");
						resetForm();
					}
				})
				.catch(error => {
					console.log("failed", error);
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
