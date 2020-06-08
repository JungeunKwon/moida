<template>
	<div id="sdItem" @click="goSharedDiaryDetail()">
		<div>
			<div id="sdProfileDiv">
				<img id="sdProfile" :src="sharedDiary.profileurl" />
				<div id="sdNickname">{{ sharedDiary.nickname }}</div>
			</div>
		</div>
		<img id="sdImg" :src="sharedDiary.imgurl" />
		<div id="sdDesc">
			<div>{{sharedDiary.inputDate}}</div>
			<div>{{ sharedDiary.description }}</div>
		</div>
		<div id="sdBottom">
			<div @click.stop="like()">
				<v-icon
					color="pink lighten-4"
					id="sdLike"
				>{{ sharedDiary.isLike ? "mdi-heart" : "mdi-heart-outline" }}</v-icon>
				{{ sharedDiary.likecount }}
			</div>
			<div>
				<v-icon color="pink lighten-4">mdi-eye</v-icon>
				{{ sharedDiary.viewcount }}
			</div>
			<div>
				<v-icon color="pink lighten-4">mdi-message-reply-text</v-icon>
				{{ sharedDiary.commentcount }}
			</div>
		</div>
	</div>
</template>

<script>
import { mapActions } from "vuex";
export default {
	name: "sharedDiaryItem",
	props: {
		sharedDiary: {},
	},
	data() {
		return {
			isLike: false,
		};
	},
	mounted() {
		this.removeTags();
		if (this.sharedDiary.imgurl == null || this.sharedDiary.imgurl == "") {
			this.sharedDiary.imgurl = "../default.png";
		}
	},
	methods: {
		...mapActions("diary", ["diaryLike", "diaryDisLike"]),
		async removeTags() {
			this.sharedDiary.description = this.sharedDiary.description
				.replace(/(<([^>]+)>)/gi, "")
				.replace("&nbsp;", " ");
		},
		like() {
			let current = !this.sharedDiary.isLike;
			this.sharedDiary.isLike = current;
			if (!current) {
				this.diaryDisLike(this.sharedDiary.id)
					.then(response => {
						this.sharedDiary.likecount = response.data;
					})
					.catch(error => {
						console.log(error);
					});
			} else {
				this.diaryLike(this.sharedDiary.id)
					.then(response => {
						this.sharedDiary.likecount = response.data;
					})
					.catch(error => {
						console.log(error);
					});
			}
		},
		goSharedDiaryDetail() {
			this.$router.push("/detailDiary/" + this.sharedDiary.id);
		},
	},
};
</script>

<style>
#sdItem {
	display: inline-block;
	text-align: left;
	width: 45%;
	background-color: white;
	border-radius: 10px;
	box-shadow: 1px 1px 7px rgba(128, 128, 128, 0.596);
	margin: 10px;
	cursor: pointer;
}

@media screen and (max-width: 800px) {
	#sdItem {
		width: 100%;
	}
}

#sdImg {
	width: 100%;
	border-bottom: 1px solid silver;
}

#sdProfileDiv {
	width: 100%;
	height: 60px;
	line-height: 45px;
	padding: 10px 0 5px 10px;
	border-bottom: 1px solid silver;
}

#sdProfileDiv img,
#sdProfileDiv div {
	float: left;
}

#sdProfileDiv div {
	line-height: 45px;
	margin-left: 5px;
	font-size: 17px;
	font-weight: 400;
}

#sdProfileDiv img {
	width: 40px;
	border-radius: 50%;
}

#sdDesc {
	padding: 5px;
	border-bottom: 1px solid silver;
}
#sdDesc div:first-child {
	font-size: 12px;
	text-align: right;
}

#sdDesc div:last-child {
	font-size: 15px;
	white-space: normal;
	line-height: 1.5em;
	height: 7.5em;
	text-align: left;
	word-wrap: break-word;
	display: -webkit-box;
	-webkit-line-clamp: 5;
	-webkit-box-orient: vertical;
	overflow: hidden;
}

#sdBottom {
	padding: 5px;
	font-weight: 400;
	height: 35px;
}

#sdBottom div {
	float: left;
	margin-right: 7px;
}

#sdLike:hover {
	opacity: 0.7;
	cursor: pointer;
}
</style>