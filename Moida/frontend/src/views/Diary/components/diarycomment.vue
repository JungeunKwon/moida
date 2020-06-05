<template>
	<div class="commentcontainer">
		<div class="commentavatar" @click="gotouser(comment.nickname)">
			<img :src="comment.profileimg" alt="프사" />
		</div>

		<div class="commentcontent">
			<div>
				{{ comment.nickname }}
			</div>
			<div v-if="!isEdit">
				{{ comment.description }}
			</div>
			<div v-else>
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
		<div
			class="commenticon"
			v-if="$store.getters.nickname == comment.nickname && !isEdit"
		>
			<div class="diaryicons" @click="setFocus">
				<v-icon>mdi-pencil</v-icon>
			</div>
			<div class="diaryicons" @click="setdeletecomment">
				<v-icon>mdi-delete</v-icon>
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
		return { isEdit: false, inputcomment: "" };
	},

	created() {},
	mounted() {
		this.inputcomment = this.comment.description;
	},
	methods: {
		...mapActions("comment", ["deletetComment", "putComment"]),

		gotouser(nickname) {
			this.$router.push("/myPage/" + nickname);
		},
		setFocus() {
			this.isEdit = true;
			this.$refs.inputcommenttext.focus();
		},
		setdeletecomment() {
			this.deletetComment(this.comment.id)
				.then(response => {
					this.$emit("deletecomment", this.index);
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
	},
};
</script>
<style scoped>
.commentavatar img {
	width: 40px;
	border-radius: 50%;
}

.commentcontainer {
	display: inline-block;
	width: 100%;
	text-align: left;
	border-bottom: 1px solid silver;
	padding: 10px;
}

.commentavatar {
	width: 45px;
	cursor: pointer;
	float: left;
}

.commentcontent {
	width: calc(100% - 125px);
	float: left;
}
.commenticon {
	width: 70px;
	float: right;
}

.diaryicons:hover {
	opacity: 0.7;
}
</style>
