<template>
	<div class="trashwrapper" max-width="300" outlined :id="trash.id">
		<div id="player" class="video">
			<div class="videovideo">
				<iframe
					width="100%"
					height="170"
					:src="this.videourl"
					frameborder="0"
					allowfullscreen
					loading="lazy"
				></iframe>
			</div>
			<div class="videodisc">{{ trash.music.musicname }}</div>
		</div>
		<v-divider></v-divider>

		<div class="trashother">
			<div class="trashdescription">
				<div style="margin:0 auto">
					{{ trash.description }}
				</div>
			</div>
		</div>
		<div class="trashtest">
			<div class="trashbottom">
				<v-divider></v-divider>
				<div class="trashbottombottom">
					<div class="bottomleft">
						<div class="timewrap">
							<v-chip class="time" color="red accent-1">{{
								lefttime
							}}</v-chip>

							<div class="timecontent">시간 뒤 펑~!</div>
						</div>
					</div>
					<div class="bottomright">
						<v-btn icon @click="likeup">
							<v-icon style="height:100%" color="pink lighten-4"
								>mdi-heart</v-icon
							>
							{{ trash.likecount }}
						</v-btn>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import $ from "jquery";

export default {
	name: "TrashComponent",
	props: {
		trash: {
			type: Object,
			default: {},
		},
		items: {
			type: Array,
			default: [],
		},
	},
	data() {
		return {
			lefttime: 0,
			videourl: "",
			heightclass: false,
		};
	},
	created() {
		this.videourl =
			"https://www.youtube-nocookie.com/embed/" +
			this.trash.music.videoid;
	},
	mounted() {
		let today = new Date();
		let getday = new Date(this.trash.date);
		this.lefttime = Math.abs(today.getHours() - getday.getHours());
		var colorcode = "";

		for (var i = 0; i < this.items.length; i++) {
			if (this.items[i].text == this.trash.mood) {
				colorcode = this.items[i].colorcode;
				break;
			}
		}
		console.log(colorcode);
		$("#" + this.trash.id).css({
			"background-color": colorcode,
		});
	},
	methods: {
		likeup() {
			this.trash.likecount = this.trash.likecount + 1;
		},
	},
};
</script>

<style>
.trashwrapper {
	display: inline-block;
	max-width: 300px;
	margin: 5px;
	border-radius: 10px;
	border: 1px solid rgba(192, 192, 192, 0.363);
	/* box-shadow: 0.5px 0.5px 3px rgb(192, 192, 192); */
}

.trashdescription {
	height: auto;
	text-align: center;
	align-items: center;
	margin: 0 auto;
	padding: 15px;
}
.trashother {
	height: 90%;
}
.timewrap {
	height: 100%;
	align-items: center;
}
.video {
	height: 200px;
}
.videovideo {
	width: 100%;

	border: 4px solid #000;
	-moz-border-radius: 10px 10px 0 0;
	border-radius: 10px 10px 0 0;
	overflow: hidden;
	height: 170px;
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
	width: 60%;
	margin-left: 2px;
	display: inline-block;
	text-align: left;
}
.trashbottombottom {
	align-items: center;
	height: 100%;
	width: 100%;
}
.trashbottom {
	height: 100%;
	bottom: 0;
	align-items: center;
	width: 100%;
	position: absolute;
}
.trashtest {
	height: 40px;
	position: relative;
}
.bottomleft {
	height: 100%;
	width: 60%;
	align-items: center;
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
