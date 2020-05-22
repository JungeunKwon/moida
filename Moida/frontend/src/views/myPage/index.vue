<template>
	<div class="mp-main-container">
		<el-row type="flex" class="mp-first-row" justify="space-between">
			<el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
				<div class="mp-content mp-myInfo">User Info</div>
			</el-col>
			<el-col :xs="12" :sm="12" :md="12" :lg="6" :xl="6">
				<div class="mp-content mp-joinedDiary">
					내가 참여한 다이어리들
				</div>
			</el-col>
		</el-row>
		<el-row type="flex" class="mp-second-row" justify="start">
			<el-col :xs="24" :sm="24" :md="24" :lg="23" :xl="23"
				><div class="mp-content mp-Calendar">달력컴포넌트</div></el-col
			>
		</el-row>
	</div>
</template>

<script>
export default {
	name: "myPage",
	data() {
		return {};
	},
};
</script>

<style>
.el-row {
	margin-bottom: 20px;
}
.mp-main-container {
	width: 100%;
	height: 100%;
	background: #99a9bf;
	overflow: auto;
}
.mp-content {
	border-radius: 4px;
	min-height: 50px;
}
.mp-myInfo {
	background: red;
	height: 100%;
}
.mp-joinedDiary {
	background: blue;
	height: 100%;
}
.mp-first-row {
	height: 30%;
}
.mp-Calendar {
	background: purple;
	height: 100%;
	min-height: 620px;
}
.mp-second-row {
	height: 65%;
}
</style>
