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
		<v-card v-if="innerdialog">
			<v-card-text>
				<v-chip-group v-model="selection" active-class="deep-purple accent-4 white--text" column>
					<div style="margin: 0 auto; width:80%">
						<v-row align="center" justify="start">
							<v-col v-for="item in items" :key="item.text" class="shrink">
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
					<p class="font-weight-bold">해당 감정에 자주 듣는 노래 url을 입력해주세요.(유투브)</p>
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

						<v-btn text style="display: inline-block; width: 20%;" @click="inserttodack">입력</v-btn>
					</div>
				</div>
			</v-card-text>
		</v-card>
	</v-dialog>
</template>
<script>
import axios from "axios";
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
				alert("옳바른 유투브 주소를 입력해주세요.");
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
