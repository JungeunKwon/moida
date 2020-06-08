<template>
	<div class="us-root">
		<div class="profile">
			<div class="image">
				<img :src="user.profileImg" class="profile-img" />
			</div>
			<div class="userDesc">
				<div class="nickname">{{ user.nickname }}</div>
				<div
					v-if="user.nickname === $store.getters.nickname"
					class="setting-btn"
				>
					<img src="@/assets/icons/setting.png" />
				</div>
				<div class="follow-div">
					<follow-modal :data="follower">
						<div>팔로워 {{ follower.length }}</div>
					</follow-modal>
					<div>·</div>
					<follow-modal :data="following">
						<div>팔로잉 {{ following.length }}</div>
					</follow-modal>
				</div>
				<div
					class="btn-div"
					v-if="user.nickname !== $store.getters.nickname"
				>
					<div @click="gochat">
						<img src="@/assets/icons/chat.png" />
					</div>
					<div v-if="!isFollower" @click="addFollow">
						<img src="@/assets/icons/plus_c.png" />
					</div>
					<div v-else class="check_btn" @click="deleteFollow">
						<img src="@/assets/icons/check_w.png" />
					</div>
				</div>
			</div>
		</div>
	</div>
</template>
<script>
import { deleteFollow, addFollow } from "../../../../api/follow";
import { mapMutations } from "vuex";
export default {
	props: {
		user: { type: Object, default: {} },
		following: { type: Array, default: [] },
		follower: { type: Array, default: [] },
	},
	data() {
		return {
			modalData: [],
		};
	},
	components: {
		followModal: () => import("./components/followModal"),
	},
	computed: {
		isFollower() {
			if (!this.follower === undefined) return false;
			for (let i = 0; i < this.follower.length; i++) {
				if (this.follower[i].nickname === this.$store.getters.nickname)
					return true;
			}
			return false;
		},
	},
	data() {
		return {};
	},
	methods: {
		...mapMutations("chat", ["SET_TARGET_NICKNAME"]),
		addFollow() {
			addFollow(this.user.id);
			if (
				this.follower.filter(
					data => this.$store.getters.nickname === data.nickname,
				).length == 0
			)
				this.follower.push({ nickname: this.$store.getters.nickname });
		},
		deleteFollow() {
			deleteFollow(this.user.id);
			for (let i = 0; i < this.follower.length; i++) {
				if (
					this.follower[i].nickname === this.$store.getters.nickname
				) {
					this.follower.splice(i, 1);
					break;
				}
			}
		},
		gochat() {
			this.SET_TARGET_NICKNAME(this.user.nickname);
			this.$router.push("/chat");
		},
	},
};
</script>

<style lang="scss">
.us-root {
	width: fit-content;
	height: 200px;
	padding: 15px;
	margin: 30px;
	background-color: white;
	border-radius: 10px;
	box-shadow: 1px 1px 5px rgba(128, 128, 128, 0.397);
	.profile {
		.image {
			width: 150px;
			height: 150px;
			overflow: hidden;
			border-radius: 50%;
			float: left;
			margin-left: 20px;
			.profile-img {
				height: 150px;
				width: 150px;
			}
		}

		.userDesc {
			padding: 20px;
			float: left;
			height: 150px;
			text-align: left;
		}
		.nickname {
			font-size: 30px;
			font-weight: 500;
			display: inline-block;
		}
		.follow-div {
			div {
				float: left;
				margin-right: 5px;
				&:nth-child(2n + 1) {
					&:hover {
						opacity: 0.5;
						cursor: pointer;
					}
				}
			}
		}
		.setting-btn {
			display: inline-block;
			margin-top: 5px;
			margin-left: 20px;
			background-color: transparent;
			width: 60px;
			height: 40px;
			padding: 5px 15px 5px 15px;
			border-radius: 10px;
			box-shadow: 1px 1px 3px rgba(128, 128, 128, 0.363);
			img {
				width: 30px;
			}
			&:hover {
				opacity: 0.7;
				background-color: red;
				cursor: pointer;
			}
		}
		.btn-div {
			display: inline-block;
			margin-top: 5px;
			div {
				float: left;
				margin-right: 10px;
				background-color: white;
				width: 60px;
				height: 40px;
				padding: 5px 15px 5px 15px;
				border-radius: 10px;
				box-shadow: 1px 1px 3px rgba(128, 128, 128, 0.363);
				&:hover {
					opacity: 0.7;
					cursor: pointer;
				}
				&.check_btn {
					background-color: black;
				}
			}
			img {
				width: 30px;
			}
		}
		@media (min-width: 750px) and (max-width: 1000px) {
			.profile-img {
				width: 100px;
				display: block;
			}
		}
		@media (max-width: 750px) {
			.profile-img {
				width: 50px;
			}
		}
	}
}
</style>
