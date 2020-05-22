<template>
	<div id="layout">
		<div id="cover"></div>
		<div class="paper p1"></div>
		<div class="paper p2"></div>
		<div class="paper p3"><slot /></div>
		<router-link to="/trash" class="tag t1">감쓰</router-link>
		<router-link to="/about" class="tag t2">일기</router-link>
		<router-link to="/" class="tag t3">공다</router-link>
		<router-link to="/myPage" class="tag t4">my</router-link>
		<router-link to="/" class="tag t5">로그인</router-link>
	</div>
</template>

<script>
export default {
	name: "Layout",
};
</script>

<style>
@font-face {
	font-family: "MapoDPPA";
	src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/MapoDPPA.woff")
		format("woff");
	font-weight: normal;
	font-style: normal;
}

#cover {
	z-index: 1;
	position: fixed;
	left: 0;
	bottom: 0;
	background-color: rgb(241, 189, 55);
	margin: 0 auto;
	height: calc(100% - 30px);
	width: calc(100% - 70px);
	border-radius: 0 20px 0 0;
	box-shadow: 1px 1px 5px gray;
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
}

.tag {
	z-index: 3;
	position: fixed;
	right: 30px;
	width: 80px;
	height: 50px;
	box-shadow: 1px 1px 5px gray;
	text-decoration: none;
	color: black;
	font-family: "MapoDPPA";
	line-height: 50px;
	font-size: 17px;
	text-align: right;
	padding-right: 10px;
}

.t1 {
	top: 100px;
	background-color: rgb(181, 227, 245);
}

.t2 {
	top: 160px;
	background-color: rgb(181, 227, 245);
}

.t3 {
	top: 220px;
	background-color: rgb(181, 227, 245);
}

.t4 {
	top: 280px;
	background-color: rgb(181, 227, 245);
}

.t5 {
	top: 340px;
	background-color: rgb(181, 227, 245);
}
</style>
