<template>
	<div class="us-root">
		<div class="profile">
			<img :src="user.profileImg" class="profile-img" />
			<div class="nickname">{{user.nickname}}</div>
		</div>
		<div class="follow-div">
			<div>팔로워 : 1111111</div>
			<div>팔로잉 : 1111111</div>
		</div>
	</div>
</template>
<script>
export default {
	props: { user: { type: Object, default: {} } },
	data() {
		return {};
	},
};
</script>

<style lang="scss">
.us-root {
	width: 100%;
	height: 100%;
	padding-top: 10px;
	background-color: rgb(255, 255, 242);

	.profile {
		display: flex;
		flex-wrap: wrap;
		justify-content: space-evenly;
		.profile-img {
			height: 100px;
			width: 100px;
			vertical-align: middle;
			border-radius: 50%;
			margin-bottom: 10px;
		}
		.nickname {
			display: inline;
			font-size: 2rem;
			display: table-cell;
			vertical-align: middle;
		}
	}

	#blank {
	}
	.follow-div {
		display: flex;
		flex-wrap: wrap;
		justify-content: space-evenly;
	}
}
</style>