<template>
	<div class="upload-container">
		<el-button
			:style="{ background: color, borderColor: color }"
			icon="el-icon-upload"
			size="mini"
			type="primary"
			@click="dialogVisible = true"
		>photo upload</el-button>
		<v-dialog v-model="dialogVisible" max-width="500px" background-color="white" background="white">
			<div class="tinymcemodal">
				<div class="tinymceimgbtn">
					<el-upload
						:multiple="true"
						:file-list="fileList"
						:show-file-list="true"
						:on-remove="handleRemove"
						:on-success="handleSuccess"
						:before-upload="beforeUpload"
						class="editor-slide-upload"
						action="https://httpbin.org/post"
						list-type="picture-card"
					>
						<el-button size="small" type="primary">Click upload</el-button>
					</el-upload>
				</div>
				<div>
					<div style="display:inline-block;">
						<el-button @click="dialogVisible = false">Cancel</el-button>
					</div>
					<div style="display:inline-block;">
						<el-button type="primary" @click="handleSubmit">Confirm</el-button>
					</div>
				</div>
			</div>
		</v-dialog>
	</div>
</template>

<script>
// import { getToken } from 'api/qiniu'

export default {
	name: "EditorSlideUpload",
	props: {
		color: {
			type: String,
			default: "#1890ff",
		},
	},
	data() {
		return {
			dialogVisible: false,
			listObj: {},
			fileList: [],
		};
	},
	methods: {
		checkAllSuccess() {
			return Object.keys(this.listObj).every(
				item => this.listObj[item].hasSuccess,
			);
		},
		handleSubmit() {
			const arr = Object.keys(this.listObj).map(v => this.listObj[v]);
			if (!this.checkAllSuccess()) {
				this.$message(
					"Please wait for all images to be uploaded successfully. If there is a network problem, please refresh the page and upload again!",
				);
				return;
			}
			this.$emit("successCBK", arr);
			this.listObj = {};
			this.fileList = [];
			this.dialogVisible = false;
		},
		handleSuccess(response, file) {
			const uid = file.uid;
			const objKeyArr = Object.keys(this.listObj);
			for (let i = 0, len = objKeyArr.length; i < len; i++) {
				if (this.listObj[objKeyArr[i]].uid === uid) {
					this.listObj[objKeyArr[i]].url = response.files.file;
					this.listObj[objKeyArr[i]].hasSuccess = true;
					return;
				}
			}
		},
		handleRemove(file) {
			const uid = file.uid;
			const objKeyArr = Object.keys(this.listObj);
			for (let i = 0, len = objKeyArr.length; i < len; i++) {
				if (this.listObj[objKeyArr[i]].uid === uid) {
					delete this.listObj[objKeyArr[i]];
					return;
				}
			}
		},
		beforeUpload(file) {
			const _self = this;
			const _URL = window.URL || window.webkitURL;
			const fileName = file.uid;
			this.listObj[fileName] = {};
			return new Promise((resolve, reject) => {
				const img = new Image();
				img.src = _URL.createObjectURL(file);
				img.onload = function() {
					_self.listObj[fileName] = {
						hasSuccess: false,
						uid: file.uid,
						width: this.width,
						height: this.height,
					};
				};
				resolve(true);
			});
		},
	},
};
</script>

<style>
.tinymceimgbtn {
	width: 100%;
	margin: 0 auto;
}
.editor-slide-upload {
	margin-bottom: 20px;
	margin: 0 auto;
	width: 60%;
}
.editor-slide-upload > .el-upload--picture-card {
	width: 100%;
	margin: 0 auto;
}
.tinymcemodal {
	background-color: white;
	margin: 0 auto;
	width: 100%;
}
.upload-container {
	background-color: white;
}
</style>
