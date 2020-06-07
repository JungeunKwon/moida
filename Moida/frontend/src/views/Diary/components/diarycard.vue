<template>
	<div>
		<v-card max-width="450" class="mx-auto" style="margin:10px">
			<v-list-item @click="gotouser(diary.nickname)">
				<v-list-item-avatar color="grey">
					<img :src="diary.profileurl" alt="프사" />
				</v-list-item-avatar>
				<v-list-item-content>
					<v-list-item-subtitle style="text-align:left">{{ diary.nickname }}</v-list-item-subtitle>
				</v-list-item-content>
			</v-list-item>
			<v-divider></v-divider>
			<div @click="diarydetail(diary.id)">
				<v-img :src="diary.imgurl" height="194"></v-img>
				<v-divider></v-divider>

				<v-card-text>{{ getrealContent(diary.description) }}</v-card-text>
			</div>
			<v-divider></v-divider>
			<v-item-group>
				<v-item v-slot:default="{ active, toggle }">
					<v-col @click="like(toggle, active, isLike)">
						<v-btn text>
							<v-icon color="pink lighten-4">{{ isLike ? "mdi-heart" : "mdi-heart-outline" }}</v-icon>
							{{ likecout }}
						</v-btn>
						<v-btn text>
							<v-icon color="pink lighten-4">mdi-eye</v-icon>
							{{ diary.viewcount }}
						</v-btn>
						<v-btn text>
							<v-icon color="pink lighten-4">mdi-message-reply-text</v-icon>
							{{ diary.commentcount }}
						</v-btn>
					</v-col>
				</v-item>
			</v-item-group>
		</v-card>
	</div>
</template>
<script>
import { mapActions } from "vuex";

export default {
	name: "diarycard",
	props: {
		diary: {
			type: Object,
			default: {},
		},
	},
	data() {
		return { diaryid: "", likecout: 0, isLike: false };
	},
	created() {
		if (this.diary.imgurl == null || this.diary.imgurl == "") {
			this.diary.imgurl = "./default.png";
		}
	},
	mounted() {
		this.likecout = this.diary.likecount;
		this.isLike = this.diary.isLike;
	},
	methods: {
		...mapActions("diary", ["diaryLike", "diaryDisLike"]),
		getrealContent(content) {
			if (content == null || content == "") return;
			return content.replace(/(<([^>]+)>)/gi, "").replace("&nbsp;", " ");
		},
		diarydetail(id) {
			this.$router.push("/detailDiary/" + id);
		},
		gotouser(nickname) {
			this.$router.push("/myPage/" + nickname);
		},
		like(toggle, active, isLike) {
			//true 면 빈값
			console.log("LIKE", this.diary.id);

			this.isLike = !isLike;
			toggle();
			if (this.isLike == false) {
				this.diaryDisLike(this.diary.id)
					.then(response => {
						this.likecout = response.data;
					})
					.catch(error => {
						console.log(error);
					});
			} else {
				this.diaryLike(this.diary.id)
					.then(response => {
						this.likecout = response.data;
					})
					.catch(error => {
						console.log(error);
					});
			}
			// 	http.post("store/review/like/", {
			// 		id: review.id,
			// 		email: this.nowuseremail,
			// 	})
			// 		.then(response => {
			// 			console.log(
			// 				"response : ",
			// 				JSON.stringify(response, null, 2),
			// 			);
			// 		})
			// 		.catch(error => {
			// 			console.log("failed", error);
			// 		});

			// 	if (review.likeCheck == false) {
			// 		review.likes = review.likes - 1;
			// 	} else {
			// 		review.likes = review.likes + 1;
			// 	}
			// },
		},
	},
};
</script>
