<template>
	<v-dialog v-model="trashdialog" max-width="50%" style="padding:20px">
		<template v-slot:activator="{ on }">
			<div v-on="on">
				<slot />
			</div>
		</template>
		<v-card v-if="!innerdialog">
			<div>
				<v-img
					style="height:200px; width:100%; text-align:center;"
					src="https://media.giphy.com/media/jAYUbVXgESSti/giphy.gif"
					@click="closemodal"
				>
					<div style="margin-top: 10px;">
						<v-avatar left>
							<v-img :src="thumbnail"></v-img>
						</v-avatar>
						{{ musicname }}
					</div>
				</v-img>
			</div>
		</v-card>

		<div v-if="innerdialog">
			<div class="TrashMusicCardTitle" dark>음악 공유</div>
			<div class="TrashMusicCard">
				<div id="trashmusicinserttext">
					<p class="font-weight-bold">
						다른 유저들에게 자신만의 노래를 추천해보세요.
					</p>

					<v-card-text>
						<v-chip-group
							v-model="selection"
							active-class="deep-purple accent-4 white--text"
							column
						>
							<div style="margin: 0 auto; width:90%">
								<v-row align="center" justify="start">
									<v-col
										v-for="item in items"
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
						<div style="margin: 0 auto; width:70%">
							<p class="font-weight-bold">
								해당 감정에 자주 듣는 노래 url을
								입력해주세요.(유투브)
							</p>
							<div style="margin: 0 auto; width:100%;">
								<div
									style="display: inline-block; width: 80%; border:2px black"
								>
									<v-text-field
										v-model="url"
										@keyup.enter="insert"
										label="url"
										ref="url"
										required
										:rules="[youtube_parser]"
									/>
								</div>
								<v-btn
									text
									style="display: inline-block; width: 20%;"
									@click="inserttodack"
									>입력</v-btn
								>
							</div>
						</div>
					</v-card-text>
				</div>
			</div>
		</div>
	</v-dialog>
</template>
<script>
import axios from "axios";
import $ from "jquery";

import { getMusic, postMusic } from "../../../../src/api/music";

export default {
	name: "TrashDialog",
	props: {
		items: {},
	},
	data() {
		return {
			trashdialog: false,
			innerdialog: true,
			url: "",
			selection: null,
			thumbnail: "",
			musicname: "",
			videoid: "",
		};
	},
	watch: {
		trashdialog: function(newVal, oldVal) {
			if (!this.trashdialog) {
				this.innerdialog = true;
				this.url = "";
				this.thumbnail = "";
				this.musicname = "";
				this.selection = null;
				$("#trashmusicinserttext").css({
					"background-color": "#ffffff",
				});
			}
		},
		selection: function(newVal, oldVal) {
			if (this.selection == undefined) {
				$("#trashmusicinserttext").css({
					"background-color": "#ffffff",
				});
				return;
			}
			var item = this.items[this.selection];
			if (!this.trashdialog) return;
			for (var i = 0; i < this.items.length; i++) {
				if (this.items[i].text == item.text) {
					$("#trashmusicinserttext").css({
						"background-color": this.items[i].colorcode,
					});
					break;
				}
			}
		},
	},
	methods: {
		inserttodack() {
			var videoId = this.youtube_parser2(this.url);
			if (this.selection == null) {
				alert("감정을 선택해주세요.");
				return;
			}
			var item = this.items[this.selection];

			if (videoId == false) {
				alert("올바른 유투브 주소를 입력해주세요.");
				return;
			}
			this.innerdialog = false;
			axios
				.get("https://www.googleapis.com/youtube/v3/videos", {
					params: {
						key: "AIzaSyCYouLEkyCdI9_IIAACK8CLT8hH0Qsp7Lk",
						id: videoId,
						part: "id,snippet",
					},
				})
				.then(response => {
					this.musicname =
						response.data.items[0].snippet.localized.title;
					this.videoid =
						"https://www.youtube-nocookie.com/embed/" + videoId;
					(this.thumbnail =
						response.data.items[0].snippet.thumbnails.high.url),
						postMusic({
							likecount: 0,
							mood: item.text,
							thumbnail:
								response.data.items[0].snippet.thumbnails.high
									.url,
							musicname:
								response.data.items[0].snippet.localized.title,
							videoid: videoId,
						})
							.then(response => {
								if (response.data.code != undefined) {
									if (response.data.code == "031") {
										alert(response.data.msg);
										this.innerdialog = true;
										return;
									}
								}
								setTimeout(() => {
									this.innerdialog = true;
									this.trashdialog = false;
								}, 1500);
							})
							.catch(err => {
								alert("오류입니다!");
								console.log("err : " + err);
								this.innerdialog = true;
								//this.trashdialog = false;
							});
				})
				.catch(err => {
					alert("오류입니다!");
					console.log("err : " + err);
					this.innerdialog = true;
					//this.trashdialog = false;
				});
		},
		closemodal() {},
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
	},
};
</script>
<style>
.TrashMusicCard {
	margin: 0 auto;
	margin-top: -15px;
	border-radius: 10px;
	z-index: 1;
	background-color: white;
}
.TrashMusicCardTitle {
	width: 120px;
	height: 30px;
	z-index: 2;
	position: relative;
	background-color: #b2dfdb;

	border-radius: 5px;
	color: white;
	border: 1px solid rgba(192, 192, 192, 0.363);
}
</style>
