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
						<v-icon color="pink lighten-4">{{ isLike ? "mdi-heart" : "mdi-heart-outline" }}</v-icon>
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
				다이어리 디테일
				{{ diaryid }}
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
			viewcount: 0,
			comment: "",
			likecount: 0,
			isLike: false,
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
	},
	methods: {
		...mapActions("diary", [
			"searchById",
			"deletetDiary",
			"diaryLike",
			"diaryDisLike",
		]),

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
					console.log(response);
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
