<template>
	<v-dialog v-model="dialog" width="700">
		<template v-slot:activator="{ on }">
			<span v-on="on">
				<slot />
			</span>
		</template>
		<div id="roomDetail">
			<div id="detailLeft">
				<img id="detailImg" :src="detail.imgUrl" />
			</div>
			<div id="detailRight">
				<div id="detailSubject">
					{{ detail.subject }}
					<span id="detailCnt">{{ detail.count }} / {{ detail.limit }}</span>
				</div>

				<div id="detailDesc">{{ detail.description }}</div>
				<div id="openDiary">펼쳐보기</div>
			</div>
		</div>
	</v-dialog>
</template>

<script>
export default {
	props: { detail: {} },
	data() {
		return {
			dialog: false,
		};
	},
	mounted() {
		// this.getSharedDiaryDetail();
	},
};
</script>

<style>
#roomDetail {
	background-color: white;
	width: 100%;
	height: 500px;
	position: relative;
}

#detailLeft {
	float: left;
	width: 50%;
	height: 100%;
	overflow: hidden;
}

#detailRight {
	float: right;
	width: 50%;
	height: 100%;
	padding: 20px;
	box-shadow: inset 5px 0px 10px rgba(119, 119, 119, 0.438);
	text-align: left;
}

#detailSubject {
	font-weight: 400;
	font-size: 20px;
}

#detailCnt {
	font-size: 15px;
	margin-left: 2px;
	color: gray;
}

#detailDesc {
	margin-top: 10px;
	font-size: 15px;
}

#openDiary {
	position: absolute;
	height: 50px;
	width: calc(50% - 60px);
	bottom: 15px;
	background-color: rgb(250, 223, 153);
	margin-top: 10px;
	padding: 5px 10px 5px 10px;
	border-radius: 5px;
	cursor: pointer;
	line-height: 40px;
	text-align: center;
	margin: 10px;
}
</style>
