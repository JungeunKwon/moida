<template>
	<div
		:class="{ fullscreen: fullscreen }"
		class="tinymce-container"
		:style="{ width: containerWidth }"
	>
		<div>
			<div class="editor-custom-btn-container-submit">
				<div style="display:inline-block; height:30px">
					<v-radio-group v-model="isPrivate" row>
						<v-radio label="전체공개" value="3"></v-radio>
						<v-radio label="친구공개" value="2"></v-radio>
						<v-radio label="비공개" value="1"></v-radio>
					</v-radio-group>
				</div>
				<el-button
					style="{ background: '#1890ff', borderColor: '#1890ff' }"
					icon="el-icon-check"
					size="mini"
					type="primary"
					@click="processpost"
				>submit</el-button>
			</div>
		</div>

		<div class="tinymce-content">
			<textarea :id="tinymceId" class="tinymce-textarea" />
		</div>
		<div>
			<div class="editor-custom-btn-container-upload">
				<editorImage color="#1890ff" class="editor-upload-btn" @successCBK="imageSuccessCBK" />
			</div>
		</div>
		<mainimgselect :imgselectdialog="imgselectdialog" :items="imglist" @setMainImg="setMainImg" />
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
import { postDiary } from "../../../api/diary";
import mainimgselect from "./components/mainimgselector";
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
	},
	data() {
		return {
			mainimg: "",
			hasChange: false,
			hasInit: false,
			tinymceId: this.id,
			fullscreen: false,
			imgselectdialog: false,
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
				imagetools_cors_hosts: ["www.tinymce.com", "codepen.io"],
				default_link_target: "_blank",
				link_title: false,
				resize: false,
				min_heght: 500,
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
				// 整合七牛上传
				// images_dataimg_filter(img) {
				//   setTimeout(() => {
				//     const $image = $(img);
				//     $image.removeAttr('width');
				//     $image.removeAttr('height');
				//     if ($image[0].height && $image[0].width) {
				//       $image.attr('data-wscntype', 'image');
				//       $image.attr('data-wscnh', $image[0].height);
				//       $image.attr('data-wscnw', $image[0].width);
				//       $image.addClass('wscnph');
				//     }
				//   }, 0);
				//   return img
				// },
				// images_upload_handler(blobInfo, success, failure, progress) {
				//   progress(0);
				//   const token = _this.$store.getters.token;
				//   getToken(token).then(response => {
				//     const url = response.data.qiniu_url;
				//     const formData = new FormData();
				//     formData.append('token', response.data.qiniu_token);
				//     formData.append('key', response.data.qiniu_key);
				//     formData.append('file', blobInfo.blob(), url);
				//     upload(formData).then(() => {
				//       success(url);
				//       progress(100);
				//     })
				//   }).catch(err => {
				//     failure('出现未知问题，刷新页面，或者联系程序员')
				//     console.log(err);
				//   });
				// },
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
			arr.forEach(v => {
				window.tinymce
					.get(_this.tinymceId)
					.insertContent(`<img class="wscnph"  src="${v.url}" >`);
			});
		},
		processpost() {
			this.getImgesrc();
			if (this.imglist != null) {
				this.imgselectdialog = true;
			} else {
				this.postcontent();
			}
		},
		postcontent() {
			var ed = this.getContent();

			postDiary({
				description: ed,
				isPrivate: parseInt(this.isPrivate),
				imgurl: this.mainimg,
			})
				.then(response => {
					console.log(response);
				})
				.catch(error => {
					console.log(error);
				});
		},
	},
};
</script>

<style scoped>
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
</style>
