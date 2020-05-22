<template>
	<div id="roomDetail">
		<img id="detailLeft" :src="detail.imgUrl" />
		<div id="detailRight">
			<div id="detailSubject">{{ detail.subject }}</div>
			<div>{{ detail.count }} / {{ detail.limit }}</div>
			<div>{{ detail.description }}</div>
			<div id="openDiary">펼쳐보기</div>
		</div>
	</div>
</template>

<script>
export default {
	props: { detail: {} },
};
</script>

<style>
#roomDetail {
}

#detailLeft {
	float: left;
	width: 50%;

	height: 100%;
}

#detailRight {
	float: right;
	width: 50%;
}

#detailTitle {
	font-weight: 400;
}

#openDiary {
	height: 50px;
	width: 100%;
	background-color: rgb(250, 223, 153);
	margin-top: 10px;
	padding: 5px 10px 5px 10px;
	border-radius: 5px;
	cursor: pointer;
	line-height: 40px;
}
</style>
