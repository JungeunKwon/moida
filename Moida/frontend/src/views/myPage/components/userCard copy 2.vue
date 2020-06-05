<template>
	<div class="us-root">
		<img :src="user.profileImg" class="us-image" />
		{{
		user.nickname
		}}
		<el-col :span="12" class="us-follow">팔로워:</el-col>
		<el-col :span="12" class="us-follow">팔로잉:</el-col>
	</div>
</template>

<script>
export default {
	props: {
		user: {
			type: Object,
			default: {},
		},
	},
	data() {
		return {};
	},
};
</script>

<style>
.us-root {
	height: 100%;
	width: 100%;
	text-align: left;
}
.el-card__body {
	height: 100%;
	width: 100%;
}
.us-image {
	height: 50%;
	vertical-align: middle;
	border-radius: 50%;
}
.us-content {
	height: 100%;
}
.helper {
	display: inline-block;
	height: 100%;
	vertical-align: middle;
}
.us-nickname {
	font-size: 2.5rem;
	font-weight: 900;
	text-align: left;
}
.us-follow {
	font-size: 1.5rem;
	font-weight: 600;
}
</style>
