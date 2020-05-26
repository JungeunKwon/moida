<template>
	<v-dialog v-model="trashdialog" max-width="50%" style="padding:20px">
		<template v-slot:activator="{ on }">
			<div v-on="on">
				<slot />
			</div>
		</template>
		<v-card v-if="!innerdialog">
			<img src="https://media.giphy.com/media/1Ye9oNy0TAC0G7bN4t/giphy.gif" />
		</v-card>
		<v-card v-if="innerdialog">
			<v-card-text>
				<v-chip-group v-model="selection" active-class="deep-purple accent-4 white--text" column>
					<div style="margin: 0 auto; width:80%">
						<v-row align="center" justify="start">
							<v-col v-for="(item, i) in items" :key="item.text" class="shrink">
								<v-chip>
									<v-avatar left>
										<v-img :src="item.src"></v-img>
									</v-avatar>
									{{item.text}}
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
			selection: {},
		};
	},
	methods: {
		inserttodack() {
			var videoId = this.youtube_parser2(this.url);
			var item = this.items[this.selection];
			if (videoId == false) {
				alert("옳바른 유투브 주소를 입력해주세요.");
				return;
			}
			axios
				.get("https://www.googleapis.com/youtube/v3/videos", {
					params: {
						key: "AIzaSyCYouLEkyCdI9_IIAACK8CLT8hH0Qsp7Lk",
						id: videoId,
						part: "id,snippet",
					},
				})
				.then(response => {
					console.log(
						response.data.items[0].snippet.thumbnails.high.url,
					);
					postMusic({
						likecount: 0,
						mood: item.text,
						thumbnails:
							response.data.items[0].snippet.thumbnails.high.url,
						musicname:
							response.data.items[0].snippet.localized.title,
						videoid: videoId,
					}).then(response => {
						console.log(response);
					});
				})
				.catch(err => {
					alert("오류입니다!");
					console.log("err : " + err);
				});

			this.innerdialog = false;
			setTimeout(() => {
				this.innerdialog = true;
				this.trashdialog = false;
			}, 3000);
		},
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