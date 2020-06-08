<template>
	<div class="commentcontainer">
		<div class="commentavatar" @click="gotouser(comment.nickname)">
			<img :src="comment.profileimg" alt="프사" />
		</div>

		<div class="commentcontent">
			<div>{{ comment.nickname }}</div>
			<div v-if="deleteDate == null">
				<div v-if="!isEdit">{{ inputcomment }}</div>
				<div v-else id="editComment">
					<input
						type="text"
						ref="inputcommenttext"
						v-model="inputcomment"
						name="input-10-2"
						color="#00000"
						@keyup.enter="editcomment"
						outlined
					/>
				</div>
			</div>
			<div v-if="deleteDate != null">삭제된 메세지 입니다.</div>
			<div style="text-align:right">
				{{ getLastDate(comment.modifiedDate) }}
			</div>
		</div>
		<div v-if="deleteDate == null">
			<div
				class="commenticon"
				v-if="$store.getters.nickname == comment.nickname && !isEdit"
			>
				<div class="diaryicons" @click="setdeletecomment">
					<v-icon>mdi-delete</v-icon>
				</div>
				<div class="diaryicons" @click="setFocus">
					<v-icon>mdi-pencil</v-icon>
				</div>
			</div>
			<div
				class="commenticon"
				v-if="$store.getters.nickname == comment.nickname && isEdit"
			>
				<div class="diaryicons" @click="editcomment">
					<v-icon>mdi-check</v-icon>
				</div>
			</div>
		</div>
	</div>
</template>
<script>
import { mapActions } from "vuex";

export default {
	name: "diarycomment",
	props: {
		comment: {
			type: Object,
			default: {},
		},
		index: {
			type: Number,
			default: 0,
		},
	},
	data() {
		return { isEdit: false, inputcomment: "", deleteDate: null };
	},

	created() {},
	mounted() {
		this.inputcomment = this.comment.description;
		this.deleteDate = this.comment.deleteDate;
	},
	methods: {
		...mapActions("comment", ["deletetComment", "putComment"]),

		gotouser(nickname) {
			this.$router.push("/myPage/" + nickname);
		},
		setFocus() {
			this.isEdit = true;
		},
		setdeletecomment() {
			this.deletetComment(this.comment.id)
				.then(response => {
					this.inputcomment = "삭제된 메세지 입니다.";
					this.deleteDate = "delete";
				})
				.catch(error => {
					console.log(error);
				});
		},
		editcomment() {
			if (this.inputcomment == "") {
				alert("댓글을 입력해주세요!!!");
				return;
			}
			this.putComment({
				id: this.comment.id,
				description: this.inputcomment,
			})
				.then(response => {
					this.isEdit = false;
				})
				.catch(error => {
					console.log(error);
				});
		},
		getLastDate(date) {
			if (date == undefined) return;
			return date.replace("T", " ").substring(0, 19);
		},
	},
};
</script>
<style scoped>
.commentavatar img {
	width: 45px;
}

.commentcontainer {
	display: inline-block;
	width: 100%;
	text-align: left;
	border-bottom: 1px solid rgba(192, 192, 192, 0.23);
	padding: 10px 10px 10px 10px;
}

.commentavatar {
	width: 45px;
	height: 45px;
	cursor: pointer;
	float: left;
	border-radius: 50%;
	overflow: hidden;
}

.commentcontent {
	width: calc(100% - 125px);
	float: left;
	margin-left: 10px;
}

.commentcontent div:first-child {
	font-weight: 500;
	font-size: 17px;
}

.commenticon {
	width: 70px;
	float: right;
}

.diaryicons {
	float: right;
	cursor: pointer;
}

.diaryicons:hover {
	opacity: 0.7;
}

#editComment {
	width: 100%;
	border: 2px solid rgb(250, 223, 153);
	padding: 5px;
}

#editComment input {
	outline: none;
}
</style>
