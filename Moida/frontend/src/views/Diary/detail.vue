<template>
	<div class="diraydeatilcontainer">
		<div class="diarycontext">
			<div class="diarywriter">
				<div id="diarydetailMemberDiv">
					<div class="diarydetailMember">
						<img :src="memberimg" />
						<div>{{membernickname}}</div>
					</div>
				</div>
			</div>
			<div class="diaryinfo">
				<div class="diaryicons">
					<div
						v-if="$store.getters.nickname == membernickname"
						style="display:inline-block; cursor:pointer"
						@click="editdiary"
					>
						<v-icon>mdi-pencil</v-icon>수정하기
					</div>
				</div>
			</div>
			<div class="diarydetail">
				<div v-html="context"></div>
			</div>
			<div class="diaryinfobottom">
				<div class="diaryicons">
					<v-icon>mdi-calendar</v-icon>
					{{createdate}}
				</div>
				<v-item-group v-slot:default="{ active, toggle }" class="diaryicons">
					<v-item>
						<v-btn text @click="like(toggle, active, isLike)">
							<v-icon color="pink lighten-4">
								{{
								isLike
								? "mdi-heart"
								: "mdi-heart-outline"
								}}
							</v-icon>
							{{likecount }}
						</v-btn>
					</v-item>
				</v-item-group>
				<div class="diaryicons">
					<v-icon>mdi-eye</v-icon>
					{{viewcount}}
				</div>
			</div>
		</div>
		<div class="diarycomment">
			<div class="diarycommentdetail">
				다이어리 디테일
				{{diaryid}}
			</div>
			<div class="diarycommentinput">
				<div class="diarycomment_input">
					<v-text-field
						v-model="comment"
						name="input-10-2"
						label="댓글"
						color="#00000"
						class="input-group--focused"
						outlined
						style="float: left; width: calc(100% - 50px);"
					/>
					<button class="diarycomment_btn">작성</button>
				</div>
			</div>
		</div>
	</div>
</template>
<script>
import { mapActions } from "vuex";
import $ from "jquery";

export default {
	name: "detailDiary",
	components: {},

	data() {
		return {
			diaryid: "",
			context: "",
			memberimg: "",
			membernickname: "",
			createdate: "",
			viewcount: "",
			comment: "",
			likecount: "",
		};
	},
	mounted() {
		this.diaryid = this.$route.params.id;
		this.getsearchById(this.diaryid);
	},
	methods: {
		...mapActions("diary", ["searchById"]),

		like(toggle, active, isLike) {
			//true 면 빈값
			isLike = !isLike;

			toggle();
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
		getsearchById(id) {
			var here = this;
			console.log(id);
			this.searchById(id)
				.then(response => {
					console.log(response);
					here.context = response.data.description;
					here.memberimg = response.data.profileurl;
					here.membernickname = response.data.nickname;
					here.createdate = response.data.createDate.substring(0, 10);
					here.viewcount = response.data.viewconut;
					here.likecount = response.data.likecount;
				})
				.catch(error => {
					console.log(error);
				});
		},
		editdiary() {
			this.$router.push("/editDiary/" + this.diaryid);
		},
	},
};
</script>
<style>
.diraydeatilcontainer {
	width: 100%;
	height: 100%;
}

.diarydetail {
	text-align: left;
	padding: 20px;
	height: 90%;
	overflow: auto;
	padding-bottom: 100px;
}
.diarywriter {
	background-color: whitesmoke;
}
.diarycontext {
	position: relative;
	display: inline-block;
	height: 100%;
	width: 50%;
	overflow: hidden;
	border-right: 1px solid black;
	background-image: url("../../assets/images/note.png");
	background-size: cover;
}
.diarycomment {
	position: relative;
	overflow: hidden;
	display: inline-block;
	width: 50%;
	height: 100%;
}
.diarycommentdetail {
	width: 100%;
	height: 85%;
	overflow: auto;
	display: block;
}
.diarycommentinput {
	background-color: whitesmoke;
	padding: 10px;
	display: block;
	height: 80px;
	position: absolute;
	width: 100%;
	bottom: 0;
}
#diarydetailMemberDiv {
	height: 85%;
	overflow: auto;
	padding: 10px;
}
.diaryinfo {
	text-align: right;
	padding-right: 10px;
	background-color: white;
}

.diaryinfobottom {
	text-align: right;
	padding-right: 10px;
	background-color: white;
	position: absolute;
	width: 100%;
	bottom: 0;
	right: 0;
}
@media screen and (max-width: 774px) {
	.diraydeatilcontainer {
		width: 100%;
		height: 100%;
		overflow: auto;
	}

	.diarywriter {
		height: 80px;
		display: block;
		background-color: whitesmoke;
		overflow: hidden;
	}
	.diarydetail {
		text-align: left;
		padding: 10px;
		padding-bottom: 100px;
	}
	#diarydetailMemberDiv {
		height: 100%;
		overflow: hidden;
	}

	.diarycontext {
		display: block;
		height: auto;
		width: 100%;
		border: none;
		background-image: url("../../assets/images/note.png");
		background-size: cover;
	}
	.diarycomment {
		width: 100%;
		display: block;
		height: 300px;
	}

	.diarycommentinput {
		background-color: whitesmoke;
		padding: 10px;
		display: block;
		width: 100%;
		position: absolute;
		bottom: 0;
		height: 100px;
	}
}
.diarydetailMember {
	display: inline-block;
	width: 100%;
	line-height: 50px;
	height: 60px;
	padding: 5px;
	border-radius: 10px;
	cursor: pointer;
}
.diaryicons {
	display: inline-block;
}
.diarydetailMember img {
	float: left;
	width: 50px;
	height: 50px;
	border-radius: 50%;
}

.diarydetailMember div {
	float: left;
	margin-left: 10px;
}

.diarycomment_input {
	display: block;
	height: 60px;
	width: 100%;
}
.diarycomment_btn {
	height: 60px;
	text-align: center;
}
</style>