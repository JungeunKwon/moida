<template>
	<div class="upload-container">
		<el-button
			class="diaryWriteBtn"
			icon="el-icon-upload"
			size="mini"
			@click="dialogVisible = true"
		>photo upload</el-button>
		<v-dialog v-model="dialogVisible" max-width="500px" background-color="white" background="white">
			<div class="tinymcemodal">
				<div class="tinymceimgbtn">
					<img-inputer
						ref="profile"
						size="large"
						v-model="file"
						name="file"
						placeholder="Drop file here or click"
						bottom-text="Drop file here or click"
						exceed-size-text="사진의 크기가 초과하였습니다"
						:max-size="1024*1024"
						width="400px"
						@onExceed="exceedHandler"
						id="signUp_img"
					/>
				</div>
				<div>
					<div style="display:inline-block; margin:10px">
						<el-button class="diaryWriteBtn" @click="handleSubmit">Confirm</el-button>
					</div>
					<div style="display:inline-block; margin:10px">
						<el-button class="diaryWriteBtn" @click="dialogVisible = false">Cancel</el-button>
					</div>
				</div>
			</div>
		</v-dialog>
	</div>
</template>

<script>
// import { getToken } from 'api/qiniu'
import { mapActions } from "vuex";

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
			file: "",
			uploadFile: "",
		};
	},
	methods: {
		...mapActions("diary", ["uploadimg"]),

		handleSubmit() {
			this.listObj = {};
			this.fileList = [];
			this.uploadimg({
				uploadFile: this.file,
			})
				.then(response => {
					console.log(response);
					this.$emit("successCBK", response.data);
					this.file = "";
					this.uploadFile = "";
					this.dialogVisible = false;
				})
				.catch(error => {
					console.log(error);
				});
		},

		exceedHandler(file) {
			console.warn("onExceed -> file", file);
			// this.$refs.profile.reset();
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
	padding: 20px;
}
.upload-container {
	background-color: white;
}
</style>
