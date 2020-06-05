<template>
	<div class="diraydeatilcontainer">
		<div class="diarycontext">
			<!-- <div class="diarywriter" @click="gotouser(membernickname)"> -->
			<div class="diarydetailMember">
				<img :src="memberimg" @click="gotouser(membernickname)" />
				<div @click="gotouser(membernickname)">
					{{ membernickname }}
				</div>
			</div>
			<!-- </div> -->
			<div class="diaryinfo">
				<div id="left">
					<div>
						<v-icon>mdi-eye</v-icon>&nbsp;
						<b>{{ viewcount }}</b>
					</div>
					<div>
						<v-icon>mdi-calendar</v-icon>
						&nbsp;
						{{ createdate }}
					</div>
				</div>
				<div id="right">
					<div
						v-if="$store.getters.nickname == membernickname"
						@click="editdiary"
					>
						<v-icon>mdi-pencil</v-icon>&nbsp;수정하기
					</div>
					<div
						v-if="$store.getters.nickname == membernickname"
						@click="setdeletediary"
					>
						<v-icon>mdi-delete</v-icon>&nbsp;삭제하기
					</div>
				</div>
			</div>
			<div class="diarydetail">
				<div v-html="context"></div>
			</div>
			<div class="diaryinfobottom">
				<div style="float: right;">
					<div style="display: inline-block;">
						<v-icon>mdi-message-reply-text</v-icon>
						<span style="font-size: 14px;"
							>&nbsp;{{ commentcount }}</span
						>
					</div>
					<div style="display: inline-block;">
						<v-item v-slot:default="{ active, toggle }">
							<v-btn text @click="like(toggle, active, isLike)">
								<v-icon color="pink lighten-4">{{
									isLike ? "mdi-heart" : "mdi-heart-outline"
								}}</v-icon>
								<span style="font-weight: 300 !important"
									>&nbsp;{{ likecount }}</span
								>
							</v-btn>
						</v-item>
					</div>
				</div>
			</div>
		</div>
		<div class="diarycomment">
			<div class="diarycommentdetail">
				<div
					style="width: 100%;"
					v-for="(comment, index) in comments"
					:key="comment.id"
				>
					<diarycomment :comment="comment" :index="index" @ />
				</div>
			</div>
			<div class="diarycommentinput">
				<div class="diarycomment_input">
					<v-text-field
						v-model="inputcomment"
						name="input-10-2"
						label="댓글"
						color="#00000"
						@keyup.enter="addcomment"
						class="input-group--focused"
						outlined
						style="float: left; width: calc(100% - 50px);"
					/>
					<button class="diarycomment_btn" @click="addcomment">
						작성
					</button>
				</div>
			</div>
		</div>
	</div>
</template>
<script>
import { mapActions } from "vuex";
import $ from "jquery";
import moment from "moment";

import diarycomment from "./components/diarycomment";
export default {
	name: "detailDiary",
	components: { diarycomment },

	data() {
		return {
			diaryid: "",
			context: "",
			memberimg: "",
			membernickname: "",
			commentcount: 0,
			createdate: "",
			viewcount: 0,
			inputcomment: "",
			likecount: 0,
			isLike: false,
			comments: [],
		};
	},
	// computed: {
	// 	dd() {
	// 		return this.viewcount;
	// 	},
	// },
	mounted() {
		this.diaryid = this.$route.params.id;
		this.getsearchById(this.diaryid);
		this.getsearchcommentById(this.diaryid);
	},
	methods: {
		...mapActions("diary", [
			"searchById",
			"deletetDiary",
			"diaryLike",
			"diaryDisLike",
		]),
		...mapActions("comment", ["getCommentById", "postComment"]),

		like(toggle, active, isLike) {
			//true 면 빈값
			this.isLike = !isLike;

			toggle();
			if (isLike == false) {
				this.diaryDisLike(this.diaryid)
					.then(response => {
						this.likecount = response.data;
					})
					.catch(error => {
						console.log(error);
					});
			} else {
				this.diaryLike(this.diaryid)
					.then(response => {
						this.likecount = response.data;
					})
					.catch(error => {
						console.log(error);
					});
			}
		},
		gotouser(nickname) {
			this.$router.push("/myPage/" + nickname);
		},
		getsearchById(id) {
			var here = this;
			this.searchById(id)
				.then(response => {
					here.context = response.data.description;
					here.memberimg = response.data.profileurl;
					here.membernickname = response.data.nickname;
					here.createdate = response.data.inputDate;
					here.viewcount = response.data.viewcount;
					here.likecount = response.data.likecount;
					here.isLike = response.data.isLike;
					here.commentcount = response.data.commentcount;
				})
				.catch(error => {
					console.log(error);
				});
		},
		getsearchcommentById(id) {
			var here = this;
			this.getCommentById(id)
				.then(response => {
					console.log("댓글", response);
					here.comments = response.data;
				})
				.catch(error => {
					console.log(error);
				});
		},
		editdiary() {
			this.$router.push("/editDiary/" + this.diaryid);
		},
		setdeletediary() {
			this.deletetDiary(this.diaryid)
				.then(response => {
					console.log();
					alert("삭제 완료!!!");
					this.$router.push("/diary");
				})
				.catch(error => {
					console.log(error);
				});
		},
		addcomment() {
			if (this.inputcomment == null || this.inputcomment == "") {
				alert("댓글을 입력해주세요.");
				return;
			}
			var date =
				new Date().toISOString().substring(0, 10) +
				" " +
				moment()
					.local("ko")
					.format("HH:mm");
			var data = {
				diaryid: this.diaryid,
				description: this.inputcomment,
				profileimg: this.$store.getters.profileImg,
				nickname: this.$store.getters.nickname,
				modifiedDate: date,
				likecount: 0,
			};
			console.log("DATE", data);
			this.postComment(data)
				.then(response => {
					console.log();
					this.comments.push(data);
				})
				.catch(error => {
					console.log(error);
				});
		},
		deletecomment(index) {
			this.comments.splice(index, 1);
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

.diarycontext {
	position: relative;
	display: inline-block;
	height: 100%;
	width: 50%;
	overflow: hidden;
	border-right: 1px solid silver;
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

.diaryinfo {
	display: inline-block;
	width: 100%;
	background-color: white;
	padding: 7px 5px 0 5px;
	height: 30px;
	z-index: 2;
	position: relative;
}

.diaryinfo #left {
	float: left;
}

.diaryinfo #right {
	float: right;
}

.diaryinfo #right div,
.diaryinfo #left div {
	float: left;
	margin: 2px;
}

.diaryinfobottom {
	position: absolute;
	width: 100%;
	height: 35px;
	background-color: white;
	padding-right: 10px;
	bottom: 0;
	right: 0;
}

.diarydetailMember {
	background-color: white;
	box-shadow: 1px 1px 5px rgba(192, 192, 192, 0.425);
	width: 100%;
	line-height: 50px;
	height: 60px;
	padding: 10px;
	z-index: 10;
	position: relative;
}

.diarydetailMember img {
	float: left;
	width: 45px;
	height: 45px;
	border-radius: 50%;
	cursor: pointer;
}

.diarydetailMember div {
	float: left;
	margin-left: 10px;
	font-size: 17px;
	cursor: pointer;
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

@media screen and (max-width: 774px) {
	.diraydeatilcontainer {
		width: 100%;
		height: 100%;
		overflow: auto;
	}

	.diarywriter {
		height: 60px;
		display: block;
		overflow: hidden;
	}
	.diarydetail {
		text-align: left;
		padding: 10px;
		padding-bottom: 100px;
	}

	.diarycontext {
		display: block;
		height: auto;
		width: 100%;
		border: none;
		background-image: url("../../assets/images/note.png");
		background-size: cover;
		background-color: rgba(255, 255, 255, 0.9);
	}
	.diarycomment {
		width: 100%;
		display: block;
		height: 400px;
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
</style>
