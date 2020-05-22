<template>
	<div id="sharedDiaryItem">
		<div @click="openRoomInfo()">
			<div id="title">{{ item.name }}</div>
			<div id="desc">
				<img id="password" src="@/assets/icons/key.png" />
				<div id="hostDiv">
					<img id="hostIcon" src="@/assets/icons/host.png" />
					<div id="hostName">{{ item.host_nickname }}</div>
				</div>
			</div>
		</div>
		<v-dialog v-model="dialog" width="500">
			<v-card>
				<div>안녕</div>
			</v-card>
		</v-dialog>
	</div>
</template>

<script>
export default {
	data() {
		return {
			dialog: false,
		};
	},
	props: { item: {} },
	mounted() {},
	computed: {},
	methods: {
		openRoomInfo() {
			this.dialog = true;
		},
	},
};
</script>

<style>
#sharedDiaryItem {
	display: inline-block;
	width: 400px;
	margin: 5px;
	border-radius: 5px;
	padding: 20px 10px 10px 10px;
	border: 1px solid rgba(192, 192, 192, 0.363);
	/* box-shadow: 0.5px 0.5px 3px rgb(192, 192, 192); */
}

@media screen and (max-width: 500px) {
	#sharedDiaryItem {
		width: 65vw;
	}
}

#sharedDiaryItem:hover {
	cursor: pointer;
	box-shadow: 0.5px 0.5px 3px rgb(192, 192, 192);
}

#title {
	font-weight: 400;
	line-height: 20px;
	margin-right: 2px;
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
}

#desc {
	margin-top: 10px;
	height: 20px;
}

#password {
	float: left;
	width: 15px;
	margin: 2.5px;
}

#hostDiv {
	float: right;
	height: 20px;
}

#hostIcon {
	float: left;
	margin-top: 1.5px;
	height: 13px;
}

#hostName {
	float: left;
	margin-left: 5px;
	line-height: 20px;
	font-size: 12px;
}
</style>
