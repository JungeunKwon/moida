<template>
	<div class="trashwrapper" max-width="300" outlined>
		<div class="trashdescription">
			<div style="margin:0 auto">
				{{ trash.description }}
				{{ trash.mood }}
			</div>
		</div>
		<div class="trashother">
			<div id="player" class="video">
				<iframe
					width="100%"
					height="50"
					:src="this.videourl"
					frameborder="0"
					allowfullscreen
				></iframe>
				<div class="videodisc">{{ trash.musictitle }}</div>
			</div>

			<div class="trashbottom">
				<v-divider></v-divider>
				<div class="trashbottombottom">
					<div class="bottomleft">
						<div class="timewrap">
							<v-chip class="time" color="error">
								{{ lefttime }}
							</v-chip>

							<div class="timecontent">시간 뒤 펑~!</div>
						</div>
					</div>
					<div class="bottomright">
						<v-btn icon @click="likeup">
							<v-icon style="height:100%" color="pink lighten-4"
								>mdi-heart</v-icon
							>
							{{ trash.likes }}
						</v-btn>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	name: "TrashComponent",
	props: {
		trash: {
			type: Object,
			default: {},
		},
	},
	data() {
		return {
			lefttime: 0,
			videourl: "",
			heightclass: false,
		};
	},
	mounted() {
		let today = new Date();
		let getday = new Date(this.trash.date);
		this.lefttime = Math.abs(today.getHours() - getday.getHours());

		this.videourl = "https://www.youtube.com/embed/" + this.trash.videoid;
	},
	methods: {
		likeup() {
			this.trash.likes = this.trash.likes + 1;
		},
		youtube_parser(url) {
			var regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/;

			var match = url.match(regExp);

			return match && match[7].length == 11 ? match[7] : false;
		},
	},
};
</script>

<style>
.trashwrapper {
	display: inline-block;
	max-width: 300px;
	margin: 5px;
	border-radius: 5px;
	max-height: 400px;
	border-radius: 5px;

	border: 1px solid rgba(192, 192, 192, 0.363);
	/* box-shadow: 0.5px 0.5px 3px rgb(192, 192, 192); */
}

.trashdescription {
	height: auto;
	text-align: center;
	align-items: center;
	margin: 0 auto;
	display: flex;
	padding: 15px;
}
.trashother {
	height: 130px;
	position: relative;
}
.timewrap {
	height: 100%;
	align-items: center;
}
.video {
	height: 60%;
}
.videodisc {
	font-size: 10px;
	text-align: right;
	padding: 5px;
	margin-bottom: 5px;
}
.time {
	display: inline-block;
}
.timecontent {
	width: 70%;
	height: 100%;
	margin-left: 2px;
	display: inline-block;
	text-align: left;
}
.trashbottombottom {
	align-items: center;
	height: 100%;
}
.trashbottom {
	height: 35px;
	bottom: 0;
	width: 100%;
	position: absolute;
}
.bottomleft {
	height: 100%;
	width: 60%;
	display: inline-block;
}
.bottomright {
	height: 100%;
	width: 15%;
	align-items: center;
	text-align: right;
	display: inline-block;
}
</style>
