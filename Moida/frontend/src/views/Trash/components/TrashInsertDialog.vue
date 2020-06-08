<style>
.helper {
	display: inline-block;
	vertical-align: middle;
}
.musicCard {
	display: inline-block;
	vertical-align: middle;
	margin: 10px;
	width: 100%;
	margin-left: 0;
	height: 200px;
}
#musicList {
	width: 90%;
	height: 100%;
	margin: 0 auto;
}
.innermusiclist {
	border: 1px solid rgba(192, 192, 192, 0.363);
	box-shadow: 1px 1px 7px rgba(192, 192, 192, 0.534);
}
.music {
	z-index: 1;
	display: inline-block;
	white-space: nowrap;
	width: 100%;
	overflow-x: auto;
	overflow-y: hidden;
}
.music::-webkit-scrollbar {
	/* display: block; */
	height: 3px;
}
.music::-webkit-scrollbar-track {
	background-color: #5d5d5d0a;
	border-radius: 50%;
} /* 스크롤 바 밑의 배경 */
.music::-webkit-scrollbar-thumb {
	background: #9e9c9c9f;
} /* 실질적 스크롤 바 */
.music::-webkit-scrollbar-thumb:hover {
	background: #808080;
} /* 실질적 스크롤 바 위에 마우스를 올려다 둘 때 */
.music::-webkit-scrollbar-thumb:active {
	background: #808080;
} /* 실질적 스크롤 바를 클릭할 때 */
.music::-webkit-scrollbar-button {
	display: none;
} /* 스크롤 바 상 하단 버튼 */
</style>

<template>
	<v-dialog v-model="trashdialog" width="600px" style="padding:20px">
		<template v-slot:activator="{ on }">
			<div v-on="on">
				<slot />
			</div>
		</template>
		<v-card v-if="!innerdialog">
			<img src="https://media.giphy.com/media/oupKcowRzsad2/giphy.gif" />
		</v-card>
		<v-card v-if="innerdialog && !isMusic && !afteranaly">
			<v-card-text>
				<div v-if="!analyzing">
					<div class="trashTextDiv" style="padding:10px">
						<v-textarea
							v-model="trashcontent"
							label="감정을 여기다 써주세요."
							no-resize
							counter
							maxlength="500"
							muilt-line
							color="gray"
						></v-textarea>
					</div>
					<v-btn @click="getmood" color="white">버리기</v-btn>
				</div>
				<div v-if="analyzing">
					<img width="500" src="../../../assets/icons/analyzing.gif" />
				</div>
			</v-card-text>
		</v-card>
		<v-card v-if="innerdialog && !isMusic && afteranaly">
			<div id="trashinserttext">
				<div class="moodResultDiv" v-if="!selectanswer">
					<div class="moodResult">
						<v-progress-circular :rotate="180" :size="200" :width="15" :value="score" :color="color">
							<div id="moodImg">
								<img :src="moodsrc" width="30px" />
								<div>"{{ mood }}"</div>
							</div>
							<div id="moodText">
								{{ score }}
								<span>%</span>
							</div>
						</v-progress-circular>
					</div>
					<v-btn color="white" @click="(afteranswer = true), (selectanswer = true)">맞아요!</v-btn>
					<v-btn color="white" @click="(afteranswer = false), (selectanswer = true)">아닌 것 같은데...</v-btn>
				</div>
				<div v-if="selectanswer && afteranswer" style="padding: 20px;">
					<div class="deleteDiv">
						<v-subheader class="pl-0">Time</v-subheader>
						<v-slider
							color="black"
							track-color="gray"
							v-model="time"
							thumb-label="always"
							thumb-color="red"
							:max="24"
						></v-slider>
						<div id="deleteText">
							<b>{{time}}</b> 시간 뒤에 감정 쓰레기가 사라집니다
						</div>
					</div>

					<div style="margin: 0 auto; width:100%;">
						<v-btn text style="display: inline-block; width: 20%;" @click="getmusic">다음</v-btn>
					</div>
				</div>
				<div class="reselectDiv" v-if="selectanswer && !afteranswer">
					<div>
						<span class="tmTitle">감정을 다시 선택해주세요.</span>
					</div>
					<v-chip-group v-model="selection" active-class="amber lighten-4 accent-4 white--text" column>
						<div>
							<v-row align="center" justify="start">
								<v-col v-for="item in items" :key="item.text" class="shrink">
									<v-chip color="white" large>
										<img :src="item.src" width="30px" />
									</v-chip>
								</v-col>
							</v-row>
						</div>
					</v-chip-group>
					<div class="selText" v-if="selection != undefined">
						<span>선택된 감정은&nbsp;</span>
						<span>"{{items[selection].text}}"</span>
						<span>&nbsp;입니다</span>
					</div>
					<div class="deleteDiv">
						<v-subheader class="pl-0">Time</v-subheader>
						<v-slider
							color="black"
							track-color="gray"
							v-model="time"
							thumb-label="always"
							thumb-color="red"
							:max="24"
						></v-slider>
						<div id="deleteText">
							<b>{{time}}</b> 시간 뒤에 감정 쓰레기가 사라집니다
						</div>
					</div>
					<div style="margin: 0 auto; width:100%">
						<v-btn text style="display: inline-block; width: 20%;" @click="getmusic">다음</v-btn>
					</div>
				</div>
			</div>
		</v-card>
		<v-card v-if="innerdialog && isMusic" style="padding: 20px;">
			<div
				class="font-weight-bold"
				style="font-family: 'kyoboHand'; font-size: 20px;"
			>~ 영상을 클릭해서 노래를 선택해주세요 ~</div>
			<img id="back_arrow" src="../../../assets/icons/back.png" @click="moveLeft" />
			<div id="musicList">
				<div id="category" class="music">
					<v-item-group>
						<span class="helper"></span>
						<v-item
							v-for="music in musiclist"
							:key="music.id"
							class="musicCard"
							v-slot:default="{ active, toggle }"
						>
							<v-card
								:color="active ? getcolor() : 'grey lighten-5'"
								style="innermusiclist"
								@click="tempclick(toggle, active, music.id)"
							>
								<div style="width:100%; height:150px">
									<iframe
										width="100%"
										height="150"
										:src="geturl(music.videoid)"
										frameborder="0"
										allowfullscreen
										loading="lazy"
									></iframe>
								</div>
								<div style="width:100%; height:50px; text-align:center">{{ music.musicname }}</div>
							</v-card>
						</v-item>
					</v-item-group>
				</div>
			</div>
			<img id="next_arrow" src="../../../assets/icons/next.png" @click="moveRight" />

			<div style="margin: 0 auto; width:100%">
				<v-btn text style="display: inline-block; width: 20%;" @click="inserttrash">노래선택</v-btn>
			</div>
		</v-card>
	</v-dialog>
</template>
<script>
import $ from "jquery";

import { findByMood } from "../../../../src/api/music";
import { postEtrash, sentimentanalysis } from "../../../../src/api/etrash";
import { mapActions } from "vuex";

export default {
	name: "TrashDialog",
	props: {
		items: {},
	},
	data() {
		return {
			trashdialog: false,
			innerdialog: true,
			isMusic: false,
			time: 0,
			url: "",
			score: 0,
			color: "",
			selection: null,
			musiclist: [],
			model: null,
			width: null,
			selectid: 0,
			trash: [],
			trashcontent: "",
			mood: "",
			afteranaly: false,
			analyzing: false,
			moodtrue: true,
			moodsrc: "",
			selectanswer: false,
		};
	},
	watch: {
		trashdialog: function(newVal, oldVal) {
			if (!this.trashdialog) {
				this.selection = 0;
				this.time = 0;
				this.innerdialog = true;
				this.isMusic = false;
				this.trashcontent = "";
				this.mood = "";
				this.moodsrc = "";
				this.afteranaly = false;
				this.analyzing = false;
				this.score = 0;
				this.selectanswer = false;
				this.afteranswer = false;
				$("#trashinserttext").css({
					"background-color": "#ffffff",
				});
			}
		},
		selection: function(newVal, oldVal) {
			if (this.selection == undefined) {
				$("#trashinserttext").css({
					"background-color": "#ffffff",
				});
				return;
			}
			var item = this.items[this.selection];
			if (!this.trashdialog) return;
			for (var i = 0; i < this.items.length; i++) {
				if (this.items[i].text == item.text) {
					$("#trashinserttext").css({
						"background-color": this.items[i].colorcode,
					});
					break;
				}
			}
		},
	},
	methods: {
		...mapActions("etrash", ["sentimentanalysis"]),

		getcolor() {
			console.log(this.items[this.selection].colorcode);
			return this.items[this.selection].colorcode;
		},
		trashinsert() {
			if (this.trashcontent == null || this.trashcontent == "") {
				alert("글을 써주세요.");
				return;
			}
			this.getmood();
		},
		getmood() {
			if (this.trashcontent == "") {
				alert("내용을 적어주세요!!!");
				return;
			}
			this.analyzing = true;
			setTimeout(() => {
				this.afteranaly = true;
				this.analyzing = false;
				// this.mood = this.items[0].text;
				// this.moodsrc = this.items[0].src;
				// this.selection = this.items[0].id;
				// this.color = this.items[0].colorcode;
				// this.score = 80;
			}, 2000);
			this.sentimentanalysis({
				description: this.trashcontent,
			})
				.then(response => {
					this.score = response.data.score2;
					for (var i = 0; i < this.items.length; i++) {
						if (this.items[i].text == response.data.sentimental) {
							this.mood = this.items[i].text;
							this.moodsrc = this.items[i].src;
							this.selection = this.items[i].id;
							this.color = this.items[i].colorcode;
							$("#trashinserttext").css({
								"background-color": this.items[i].colorcode,
							});
							break;
						}

						setTimeout(() => {
							this.afteranaly = true;
							this.analyzing = false;
						}, 2000);
					}
				})
				.catch(error => {
					this.afteranaly = false;
					this.analyzing = false;
				});
		},
		getmusic() {
			if (this.time == 0) {
				alert("시간을 선택해주세요.");
				return;
			}
			if (this.selection == undefined || this.selection == null) {
				alert("감정을 선택해주세요.");
				return;
			}
			var item = this.items[this.selection];
			findByMood(item.text)
				.then(response => {
					this.musiclist = response.data.content;
					this.isMusic = true;
				})
				.catch(error => {});
		},
		inserttrash() {
			if (this.trashcontent == "") {
				alert("내용을 적어주세요!!!");
				return;
			}
			var item = this.items[this.selection];
			this.innerdialog = false;
			postEtrash({
				likecount: 0,
				mood: item.text,
				description: this.trashcontent,
				deleteTime: this.time,
				music: {
					id: this.selectid,
				},
			})
				.then(response => {
					setTimeout(() => {
						this.$emit("getEtrashMain");
						this.trashdialog = false;
						this.innerdialog = true;
						this.isMusic = false;
					}, 2000);
				})
				.catch(error => {
					alert("오류입니다!");
					console.log("err : " + error);
					this.innerdialog = true;
					this.isMusic = false;
				});
		},
		moveLeft() {
			var getwidth = document.getElementById("category").offsetWidth + 10;
			this.width = getwidth;
			$("#category").animate({ scrollLeft: "-=" + this.width });
		},
		moveRight() {
			var getwidth = document.getElementById("category").offsetWidth + 10;
			this.width = getwidth;

			$("#category").animate({ scrollLeft: "+=" + this.width });
		},
		geturl(url) {
			return "https://www.youtube-nocookie.com/embed/" + url;
		},
		tempclick(toggle, active, id) {
			//true 면 빈값

			toggle();
			this.selectid = id;
		},
	},
};
</script>
<style>
#back_arrow,
#next_arrow {
	top: calc(50% - 30px);
	display: inline-block;
	z-index: 10;
	position: absolute;
	width: 30px;
	cursor: pointer;
	opacity: 50%;
}
#back_arrow:hover,
#next_arrow:hover {
	opacity: 70%;
}

#back_arrow {
	left: 0;
}

#next_arrow {
	right: 0;
}
.anaylzedmood {
	width: 200px;
	height: 200px;
	font-size: 15px;
	margin: 0 auto;
}

.moodResultDiv {
	padding: 20px;
}

.moodResult {
	width: 100%;
	height: 200px;
	margin: 0 auto;
	font-family: "kyoboHand";
	margin-bottom: 10px;
}

#moodImg {
	font-size: 25px;
}

#moodImg div {
	margin-top: -10px;
}

#moodText {
	margin-left: 10px;
	font-size: 30px;
	color: black;
	font-weight: bold;
}

#moodText span {
	color: black;
}

.moodResultDiv > .v-btn {
	margin: 10px;
}

.reselectDiv {
	margin: 0 auto;
	width: 100%;
	padding: 20px;
}

.tmTitle {
	padding: 5px;
	background-color: white;
	font-family: "kyoboHand";
	font-size: 18px;
	box-shadow: 1px 1px 3px silver;
	border-radius: 5px;
	width: 200px;
}

.reselectDiv .v-chip-group {
	display: inline-block;
	width: 350px;
	margin-left: 30px;
}

#deleteText {
	margin-top: -20px;
	margin-bottom: 20px;
}
</style>
