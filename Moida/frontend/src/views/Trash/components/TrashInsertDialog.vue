<style>
.helper {
	display: inline-block;
	vertical-align: middle;
}
.musicCard {
	display: inline-block;
	vertical-align: middle;
	margin: 10px;
	width: 100%;
	margin-left: 0;
	height: 200px;
}
#musicList {
	width: 100%;
	height: 100%;
}
.music {
	z-index: 1;
	display: inline-block;
	white-space: nowrap;
	width: 100%;
	height: 250px;
	overflow-x: auto;
	overflow-y: hidden;
}
.music::-webkit-scrollbar {
	/* display: block; */
	height: 3px;
}
.music::-webkit-scrollbar-track {
	background-color: #5d5d5d0a;
	border-radius: 50%;
} /* 스크롤 바 밑의 배경 */
.music::-webkit-scrollbar-thumb {
	background: #9e9c9c9f;
} /* 실질적 스크롤 바 */
.music::-webkit-scrollbar-thumb:hover {
	background: #808080;
} /* 실질적 스크롤 바 위에 마우스를 올려다 둘 때 */
.music::-webkit-scrollbar-thumb:active {
	background: #808080;
} /* 실질적 스크롤 바를 클릭할 때 */
.music::-webkit-scrollbar-button {
	display: none;
} /* 스크롤 바 상 하단 버튼 */
</style>

<template>
	<v-dialog v-model="trashdialog" max-width="50%" style="padding:20px">
		<template v-slot:activator="{ on }">
			<div v-on="on">
				<slot />
			</div>
		</template>
		<v-card v-if="!innerdialog">
			<img
				src="https://media.giphy.com/media/1Ye9oNy0TAC0G7bN4t/giphy.gif"
			/>
		</v-card>
		<v-card v-if="innerdialog && !isMusic">
			<v-card-text>
				<p class="font-weight-bold">
					해당 감정이 맞나요? 아니면 다시 선택해주세요.
				</p>
				<v-chip>
					<v-avatar left>
						<v-img :src="moodsrc"></v-img>
					</v-avatar>
					{{ mood }}
				</v-chip>
				<v-chip-group
					v-model="selection"
					active-class="deep-purple accent-4 white--text"
					column
				>
					<div style="margin: 0 auto; width:80%">
						<v-row align="center" justify="start">
							<v-col
								v-for="(item, i) in items"
								:key="item.text"
								class="shrink"
							>
								<v-chip>
									<v-avatar left>
										<v-img :src="item.src"></v-img>
									</v-avatar>
									{{ item.text }}
								</v-chip>
							</v-col>
						</v-row>
					</div>
				</v-chip-group>
				<div style="margin: 0 auto; width:100%">
					<v-btn
						text
						style="display: inline-block; width: 20%;"
						@click="getmusic"
						>노래선택</v-btn
					>
				</div>
			</v-card-text>
		</v-card>
		<v-card v-if="innerdialog && isMusic">
			<p class="font-weight-bold">이 노래 한번 들어보세요.</p>
			<div id="musicList">
				<img id="back_arrow" src="./back.png" @click="moveLeft" />
				<div id="category" class="music">
					<v-item-group>
						<span class="helper"></span>
						<v-item
							v-for="music in musiclist"
							:key="music.id"
							class="musicCard"
							v-slot:default="{ active, toggle }"
						>
							<v-card
								:color="active ? 'primary' : 'grey lighten-1'"
								style="height:200px"
								@click="tempclick(toggle, active, music.id)"
							>
								<div style="width:100%; height:150px">
									<iframe
										width="100%"
										height="150"
										:src="geturl(music.videoid)"
										frameborder="0"
										allowfullscreen
										loading="lazy"
									></iframe>
								</div>
								<div
									style="width:100%; height:50px; text-align:center"
								>
									{{ music.musicname }}
								</div>
							</v-card>
						</v-item>
					</v-item-group>
				</div>
				<img id="next_arrow" src="./next.png" @click="moveRight" />
			</div>

			<div style="margin: 0 auto; width:100%">
				<v-btn
					text
					style="display: inline-block; width: 20%;"
					@click="inserttrash"
					>노래선택</v-btn
				>
			</div>
		</v-card>
	</v-dialog>
</template>
<script>
import $ from "jquery";

import { findByMood } from "../../../../src/api/music";
import { postEtrash } from "../../../../src/api/etrash";

export default {
	name: "TrashDialog",
	props: {
		items: {},
		mood: "",
		moodsrc: "",
		content: "",
	},
	data() {
		return {
			trashdialog: false,
			innerdialog: true,
			isMusic: false,
			url: "",
			selection: 0,
			musiclist: [],
			model: null,
			width: null,
			selectid: 0,
		};
	},
	mounted() {
		console.log(this.trashdialog);
	},
	watch: {
		trashdialog: function(newVal, oldVal) {
			if (!this.trashdialog) {
				this.innerdialog = true;
				this.isMusic = false;
			}
		},
	},
	methods: {
		getmusic() {
			var item = this.items[this.selection];
			console.log(item.text);
			findByMood(item.text)
				.then(response => {
					console.log(response);
					this.musiclist = response.data.content;
					this.isMusic = true;
				})
				.catch(error => {});
		},
		inserttrash() {
			var item = this.items[this.selection];
			this.innerdialog = false;
			postEtrash({
				likecount: 0,
				mood: item.text,
				description: this.content,
				music: {
					id: this.selectid,
				},
			})
				.then(response => {
					console.log(response);

					setTimeout(() => {
						this.trashdialog = false;
						this.innerdialog = true;
						this.isMusic = false;
						window.location.reload();
					}, 1000);
				})
				.catch(error => {
					alert("오류입니다!");
					console.log("err : " + error);
					this.innerdialog = true;
					this.isMusic = false;
				});
		},
		moveLeft() {
			var getwidth = document.getElementById("category").offsetWidth + 10;
			this.width = getwidth;
			$("#category").animate({ scrollLeft: "-=" + this.width });
		},
		moveRight() {
			var getwidth = document.getElementById("category").offsetWidth + 10;
			this.width = getwidth;

			$("#category").animate({ scrollLeft: "+=" + this.width });
		},
		geturl(url) {
			return "https://www.youtube-nocookie.com/embed/" + url;
		},
		tempclick(toggle, active, id) {
			//true 면 빈값

			toggle();
			this.selectid = id;
			console.log("selectedid", id);
		},
	},
};
</script>
<style>
#back_arrow,
#next_arrow {
	top: calc(50% - 30px);
	display: inline-block;
	z-index: 10;
	position: absolute;
	width: 30px;
	cursor: pointer;
	opacity: 50%;
}
#back_arrow:hover,
#next_arrow:hover {
	opacity: 70%;
}

#back_arrow {
	left: 0;
}

#next_arrow {
	right: 0;
}
</style>
