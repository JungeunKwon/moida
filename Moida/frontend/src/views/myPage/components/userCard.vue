<template>
	<el-card class="us-root">
		<el-col :span="6" class="us-content">
			<span class="helper"></span>
			<img :src="$store.getters.profile_img" class="us-image" />
		</el-col>
		<el-col :span="18" class="us-content">
			<el-row style="height: 30%">
				<el-col class="us-nickname">{{$store.getters.nickname}}</el-col>
			</el-row>
			<el-row style="height: 70%">
				<el-col :span="12">팔로워:</el-col>
				<el-col :span="12">팔로잉:</el-col>
			</el-row>
		</el-col>
	</el-card>
</template>

<script>
export default {
	data() {
		return;
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
</style>