<template>
	<div class="diraydeatilcontainer">
		<div class="diarycontext">
			<div class="diarywriter" @click="gotouser(membernickname)">
				<div id="diarydetailMemberDiv">
					<div class="diarydetailMember">
						<img :src="memberimg" />
						<div>{{ membernickname }}</div>
					</div>
				</div>
			</div>
			<div class="diaryinfo">
				<div class="diaryicons">
					<div
						v-if="$store.getters.nickname == membernickname"
						style="display:inline-block; cursor:pointer"
						class="diaryicons"
						@click="editdiary"
					>
						<v-icon>mdi-pencil</v-icon>수정하기
					</div>
					<div
						v-if="$store.getters.nickname == membernickname"
						style="display:inline-block; cursor:pointer"
						class="diaryicons"
						@click="setdeletediary"
					>
						<v-icon>mdi-delete</v-icon>삭제하기
					</div>
				</div>
			</div>
			<div class="diarydetail">
				<div v-html="context"></div>
			</div>
			<div class="diaryinfobottom">
				<div class="diaryicons">
					<v-icon>mdi-calendar</v-icon>
					{{ createdate }}
				</div>

				<v-item v-slot:default="{ active, toggle }" class="diaryicons">
					<v-btn text @click="like(toggle, active, isLike)">
						<v-icon color="pink lighten-4">{{
							isLike ? "mdi-heart" : "mdi-heart-outline"
						}}</v-icon>
						{{ likecount }}
					</v-btn>
				</v-item>

				<div class="diaryicons">
					<v-icon>mdi-eye</v-icon>
					<b>{{ viewcount }}</b>
				</div>
				<v-divider></v-divider>
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
					here.createdate = response.data.inputDate.substring(0, 10);
					here.viewcount = response.data.viewcount;
					here.likecount = response.data.likecount;
					here.isLike = response.data.isLike;
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
	margin-top: 3px;
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
	margin-left: 5px;
	margin-right: 5px;
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
