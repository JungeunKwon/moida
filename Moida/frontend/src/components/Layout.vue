<template>
	<div id="layout">
		<div id="cover"></div>
		<div class="paper p1"></div>
		<div class="paper p2"></div>
		<div class="paper p3">
			<slot />
		</div>
		<router-link to="/" class="myTag t1">감쓰</router-link>
		<router-link to="/writediary" class="myTag t2">일기</router-link>
		<router-link to="/shared" class="myTag t3">공다</router-link>
		<router-link to="/myPage" class="myTag t4">my</router-link>
		<router-link to="/login" @click.native="logout" class="myTag t5">logout</router-link>
	</div>
</template>

<script>
export default {
	name: "Layout",
	components: {},
	mounted() {},
	methods: {
		async logout() {
			await this.$store.dispatch("user/logout");
			this.$router.push("/login");
		},
	},
};
</script>
<style>
#cover {
	z-index: 1;
	position: fixed;
	left: 0;
	bottom: 0;
	background-color: rgb(250, 223, 153);
	margin: 0 auto;
	height: calc(100% - 30px);
	width: calc(100% - 70px);
	border-radius: 0 20px 0 0;
	box-shadow: 1px 1px 5px gray;
}

.myTag {
	z-index: 3;
	position: fixed;
	right: 20px;
	width: 80px;
	height: 45px;
	box-shadow: 1px 1px 5px gray;
	text-decoration: none;
	color: rgb(52, 54, 54) !important;
	font-family: "KyoboHand", sans-serif;
	line-height: 45px;
	font-size: 17px;
	font-weight: bold;
	text-align: center;
	margin-left: 17px;
}

.myTag:hover {
	filter: brightness(80%);
}

.t1 {
	top: 100px;
	background-color: #fbd6c6;
}

.t2 {
	top: 160px;
	background-color: #f7ebc3;
}

.t3 {
	top: 220px;
	background-color: #c7ceea;
}

.t4 {
	top: 280px;
	background-color: #cde4db;
}

.t5 {
	top: 340px;
	background-color: #e2f0cb;
}

.paper {
	z-index: 2;
	position: fixed;
	left: 0;
	bottom: 0;
	background-color: white;
	box-shadow: 1px 1px 5px gray;
}

.p1 {
	z-index: 2;
	height: calc(100% - 60px);
	width: calc(100% - 90px);
}

.p2 {
	z-index: 3;
	height: calc(100% - 55px);
	width: calc(100% - 95px);
}

.p3 {
	z-index: 4;
	height: calc(100% - 50px);
	width: calc(100% - 100px);
	overflow: hidden;
}
</style>
