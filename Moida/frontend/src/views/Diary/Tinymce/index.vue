<template>
	<div
		:class="{ fullscreen: fullscreen }"
		class="tinymce-container"
		:style="{ width: containerWidth }"
	>
		<div>
			<div class="editor-custom-btn-container-submit">
				<div style="display:inline-block; height:30px">
					<!-- <v-time-picker v-model="picker" scrollable color="#fadf99"></v-time-picker> -->

					<v-radio-group v-model="isPrivate" row>
						<v-radio color="grey" label="전체공개" value="1"></v-radio>
						<v-radio color="grey" label="친구공개" value="2"></v-radio>
						<v-radio color="grey" label="비공개" value="3"></v-radio>
					</v-radio-group>
				</div>
				<el-button
					v-if="!isEdit"
					class="diaryWriteBtn"
					icon="el-icon-check"
					size="mini"
					@click="processpost"
				>submit</el-button>
				<el-button
					v-if="isEdit"
					class="diaryWriteBtn"
					icon="el-icon-check"
					size="mini"
					@click="processpost"
				>edit</el-button>
			</div>
		</div>

		<div class="tinymce-content">
			<textarea :id="tinymceId" class="tinymce-textarea" />
		</div>
		<div>
			<div class="editor-custom-btn-container-upload">
				<editorImage color="#f7ebc3" class="editor-upload-btn" @successCBK="imageSuccessCBK" />
			</div>
		</div>
		<mainimgselect :imgselectdialog="imgselectdialog" :items="imglist" @setMainImg="setMainImg" />
		<v-dialog v-model="dateselect" max-width="500px" background-color="white" background="white">
			<v-card style="padding:20px">
				<v-menu
					v-model="datemenu"
					:close-on-content-click="false"
					:nudge-right="40"
					transition="scale-transition"
					offset-y
					min-width="290px"
				>
					<template v-slot:activator="{ on }">
						<v-text-field
							v-model="inputdate"
							label="작성날짜"
							prepend-icon="mdi-calendar"
							readonly
							v-on="on"
							color="#fadf99"
						/>
					</template>
					<v-date-picker v-model="inputdate" @input="datemenu = false" color="#fadf99" />
				</v-menu>
				<v-menu
					v-model="timemenu"
					:close-on-content-click="false"
					:nudge-right="40"
					transition="scale-transition"
					offset-y
					min-width="290px"
				>
					<template v-slot:activator="{ on }">
						<v-text-field
							v-model="inputtime"
							label="작성시간"
							prepend-icon="mdi-calendar"
							readonly
							v-on="on"
							color="#fadf99"
						/>
					</template>
					<v-time-picker v-model="inputtime" @input="timemenu = false" scrollable color="#fadf99"></v-time-picker>
				</v-menu>
				<el-button
					v-if="!isEdit"
					class="diaryWriteBtn"
					icon="el-icon-check"
					size="mini"
					@click="processpost2"
				>submit</el-button>
				<el-button
					v-if="isEdit"
					class="diaryWriteBtn"
					icon="el-icon-check"
					size="mini"
					@click="processpost2"
				>edit</el-button>
			</v-card>
		</v-dialog>
	</div>
</template>


<script>
/**
 * docs:
 * https://panjiachen.github.io/vue-element-admin-site/feature/component/rich-editor.html#tinymce
 */

import editorImage from "./components/EditorImage";
import plugins from "./plugins";
import toolbar from "./toolbar";
import load from "./dynamicLoadScript";
import { mapActions } from "vuex";
import mainimgselect from "./components/mainimgselector";
import moment from "moment";

// why use this cdn, detail see https://github.com/PanJiaChen/tinymce-all-in-one
const tinymceCDN =
	"https://cdn.jsdelivr.net/npm/tinymce-all-in-one@4.9.3/tinymce.min.js";

export default {
	name: "Tinymce",
	components: { editorImage, mainimgselect },
	props: {
		id: {
			type: String,
			default: function() {
				return (
					"vue-tinymce-" +
					+new Date() +
					((Math.random() * 1000).toFixed(0) + "")
				);
			},
		},
		value: {
			type: String,
			default: "",
		},
		toolbar: {
			type: Array,
			required: false,
			default() {
				return [];
			},
		},
		menubar: {
			type: String,
			default: "file edit",
		},
		height: {
			type: [Number, String],
			required: false,
			default: 300,
		},
		width: {
			type: [Number, String],
			required: false,
			default: "auto",
		},
		diaryid: {
			type: String,
			default: "",
		},
		isEdit: {
			type: Boolean,
			default: false,
		},
	},
	data() {
		return {
			mainimg: "",
			dateselect: false,
			hasChange: false,
			hasInit: false,
			tinymceId: this.id,
			inputdate: new Date().toISOString().substring(0, 10),
			inputtime: moment()
				.local("ko")
				.format("HH:mm"),
			datemenu: null,
			timemenu: null,

			fullscreen: false,
			imgselectdialog: false,
			menu: null,
			membernickname: "",
			editcontext: "",
			languageTypeList: {
				en: "en",
				zh: "zh_CN",
				es: "es_MX",
				ja: "ja",
			},
			isPrivate: "3",
			imglist: [],
		};
	},
	computed: {
		containerWidth() {
			const width = this.width;
			if (/^[\d]+(\.[\d]+)?$/.test(width)) {
				// matches `100`, `'100'`
				return `${width}px`;
			}
			return width;
		},
	},
	watch: {
		value(val) {
			if (!this.hasChange && this.hasInit) {
				this.$nextTick(() =>
					window.tinymce.get(this.tinymceId).setContent(val || ""),
				);
			}
		},
	},
	mounted() {
		this.init();
		if (this.diaryid != undefined && this.diaryid != "") {
			this.getsearchById(this.diaryid);
		}
	},
	activated() {
		if (window.tinymce) {
			this.initTinymce();
		}
	},
	deactivated() {
		this.destroyTinymce();
	},
	destroyed() {
		this.destroyTinymce();
	},
	methods: {
		...mapActions("diary", ["postDiary", "searchById", "putDiary"]),
		getsearchById(id) {
			var here = this;
			this.searchById(id)
				.then(response => {
					here.context = response.data.description;
					here.membernickname = response.data.nickname;
					if (
						this.$store.getters.nickname != response.data.nickname
					) {
						here.$router.push("/diary");
					}
					here.setContent(response.data.description);
					here.isPrivate = response.data.isPrivate.toString();
				})
				.catch(error => {
					console.log(error);
				});
		},
		init() {
			// dynamic load tinymce from cdn
			load(tinymceCDN, err => {
				if (err) {
					this.$message.error(err.message);
					return;
				}
				this.initTinymce();
			});
		},
		initTinymce() {
			const _this = this;
			window.tinymce.init({
				selector: `#${this.tinymceId}`,
				language: this.languageTypeList["en"],
				height: this.height,
				body_class: "panel-body ",
				object_resizing: false,
				toolbar: this.toolbar.length > 0 ? this.toolbar : toolbar,
				menubar: this.menubar,
				plugins: plugins,
				end_container_on_empty_block: true,
				powerpaste_word_import: "clean",
				code_dialog_height: 300,
				code_dialog_width: 1000,
				advlist_bullet_styles: "square",
				advlist_number_styles: "default",
				default_link_target: "_blank",
				link_title: false,
				paste_data_images: true,
				auto_convert_smileys: true,
				resize: false,
				content_css:
					"//www.tiny.cloud/css/codepen.min.css,/./font/font.css",
				imagetools_toolbar:
					"rotateleft rotateright | flipv fliph | editimage imageoptions",
				min_heght: 500,
				font_formats:
					"Arial=arial,helvetica,sans-serif;" +
					"Arial Black=arial black,avant garde;" +
					"교보 손글씨=KyoboHand;" +
					"Recipekorea=Recipekorea;" +
					"Noto Sans KR=Noto Sans KR;" +
					"마포 한아름=MapoPeacefull;" +
					"마포꽃섬=MapoFlowerIsland;" +
					"어비 슬비로운생활체=UhBeeSeulvely;" +
					"어비 미미체=UhBeeMiMi;" +
					"조선 궁서체=ChosunGs;" +
					"조선일보 명조체=Chosunilbo_myungjo;" +
					"Gmarket Sans B=GmarketSansBold;" +
					"Gmarket Sans M=GmarketSansMedium;" +
					"Neo둥근모=NeoDunggeunmo;" +
					"KCC은영체=KCC-eunyoung;" +
					"타닥타닥체=TDTDTadakTadak;" +
					"봉숭아틴트=777Balsamtint",
				nonbreaking_force_tab: true, // inserting nonbreaking space &nbsp; need Nonbreaking Space Plugin
				init_instance_callback: editor => {
					if (_this.value) {
						editor.setContent(_this.value);
					}
					_this.hasInit = true;
					editor.on("NodeChange Change KeyUp SetContent", () => {
						this.hasChange = true;
						this.$emit("input", editor.getContent());
					});
				},
				setup(editor) {
					editor.on("FullscreenStateChanged", e => {
						_this.fullscreen = e.state;
					});
				},
			});
		},
		destroyTinymce() {
			const tinymce = window.tinymce.get(this.tinymceId);
			if (this.fullscreen) {
				tinymce.execCommand("mceFullScreen");
			}

			if (tinymce) {
				tinymce.destroy();
			}
		},
		setContent(value) {
			console.log("text", value);
			window.tinymce.get(this.tinymceId).setContent(value);
		},
		getContent() {
			return window.tinymce.get(this.tinymceId).getContent();
		},
		getrealContent() {
			return window.tinymce
				.get(this.tinymceId)
				.getContent()
				.replace(/(<([^>]+)>)/gi, "");
		},
		setMainImg(index) {
			this.mainimg = this.imglist[index];
			this.imgselectdialog = false;
			this.postcontent();
		},
		getImgesrc() {
			var text = this.getContent();
			this.imglist = [];
			while (true) {
				let startIdx = text.indexOf('src="');
				if (startIdx === -1) break;
				let endIdx = text.indexOf('"', startIdx + 5);
				let src = text.substring(startIdx + 5, endIdx);
				text = text.substring(endIdx + 1, text.length);
				this.imglist.push(src);
			}
			/* console.log(this.imglist);
			console.log(this.imglist[0]); */
			this.imglist.forEach(v => {
				console.log(v);
			});
		},
		imageSuccessCBK(arr) {
			const _this = this;

			window.tinymce
				.get(_this.tinymceId)
				.insertContent(`<img src="${arr}" >`);
		},
		processpost() {
			this.dateselect = true;
		},
		processpost2() {
			if (this.inputdate == null) {
				alert("날짜를 입력해주세요.");
				return;
			}
			if (this.inputtime == null) {
				alert("시간을 선택해주세요.");
				return;
			}
			this.getImgesrc();
			if (this.imglist != null && this.imglist.length > 0) {
				this.imgselectdialog = true;
			} else {
				this.postcontent();
			}
		},
		postcontent() {
			var ed = this.getContent();
			if (ed == null || ed == "") {
				alert("글 입력해주세요.");
				return;
			}
			var date = this.inputdate + " " + this.inputtime;
			console.log(date);
			if (this.isEdit) {
				this.putDiary({
					id: this.diaryid,
					description: ed,
					isPrivate: parseInt(this.isPrivate),
					imgurl: this.mainimg,
					inputDate: date,
				})
					.then(response => {
						console.log(response);
						this.$router.push("/detaildiary/" + response.data.id);
					})
					.catch(error => {
						console.log(error);
					});
			} else {
				this.postDiary({
					description: ed,
					isPrivate: parseInt(this.isPrivate),
					imgurl: this.mainimg,
					inputDate: date,
				})
					.then(response => {
						console.log(response);
						this.$router.push("/detaildiary/" + response.data);
					})
					.catch(error => {
						console.log(error);
					});
			}
			this.dateselect = false;
		},
	},
};
</script>

<style>
.diaryWriteBtn:hover {
	color: black !important;
	background-color: #f7ebc3 !important;
	border-color: white !important;
}
.tinymce-container {
	position: relative;
	line-height: normal;
}
.tinymce-container >>> .mce-fullscreen {
	z-index: 10000;
}
.tinymce-textarea {
	visibility: hidden;
	z-index: -1;
}
.editor-custom-btn-container-submit {
	position: fixed;
	right: 104px;
	bottom: 10px;
	z-index: 2005;
}

.editor-custom-btn-container-upload {
	position: absolute;
	right: 4px;
	top: 4px;
	/*z-index: 2005;*/
}
.fullscreen .editor-custom-btn-container-upload {
	z-index: 10000;
	position: fixed;
}
.fullscreen .editor-custom-btn-container-submit {
	z-index: 10000;
	position: fixed;
}
.editor-upload-btn {
	display: inline-block;
}
#tinymce p {
	margin: 5px !important;
}

.mce-content-body p {
	margin: 15px 0 !important;
}
</style>
