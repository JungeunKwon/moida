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
	<v-dialog v-model="imgselectdialog" width="500px" style="padding:20px">
		<v-card>
			<p class="font-weight-bold">대표 이미지를 선택해주세요.</p>
			<img id="back_arrow" src="../../../../assets/icons/back.png" @click="moveLeft" />
			<div id="musicList">
				<div id="mainimglist" class="music">
					<v-item-group>
						<v-item
							v-for="(img, index) in items"
							:key="index"
							class="musicCard"
							v-slot:default="{ active, toggle }"
						>
							<v-card
								:color="active ? 'primary' : 'grey lighten-5'"
								style="innermusiclist"
								@click="tempclick(toggle, active, index)"
							>
								<img width="100%" height="150" :src="img" />
							</v-card>
						</v-item>
					</v-item-group>
				</div>
			</div>
			<img id="next_arrow" src="../../../../assets/icons/next.png" @click="moveRight" />

			<div style="margin: 0 auto; width:100%">
				<v-btn text style="display: inline-block; width: 20%;" @click="selectmainimg">대표 이미지 선택</v-btn>
			</div>
		</v-card>
	</v-dialog>
</template>
<script>
import $ from "jquery";

export default {
	name: "mainimgdialog",
	props: {
		items: {
			type: Array,
			default: null,
		},
		imgselectdialog: {
			type: Boolean,
			default: false,
		},
	},
	data() {
		return {
			selectindex: null,
		};
	},
	methods: {
		selectmainimg() {
			if (this.selectindex != null) {
				this.$emit("setMainImg", this.selectindex);
			}
		},
		moveLeft() {
			var getwidth =
				document.getElementById("mainimglist").offsetWidth + 10;
			this.width = getwidth;
			$("#mainimglist").animate({ scrollLeft: "-=" + this.width });
		},
		moveRight() {
			var getwidth =
				document.getElementById("mainimglist").offsetWidth + 10;
			this.width = getwidth;

			$("#mainimglist").animate({ scrollLeft: "+=" + this.width });
		},
		tempclick(toggle, active, index) {
			//true 면 빈값
			toggle();
			this.selectindex = index;
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
</style>
