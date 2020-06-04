<template>
	<div>
		<editor :diaryid="diaryid" :isEdit="true" :height="450"></editor>
	</div>
</template>
<script>
import Editor from "./Tinymce/index";

export default {
	name: "editDiary",
	data() {
		return { diaryid: "" };
	},
	components: {
		editor: Editor,
	},
	created() {
		this.diaryid = this.$route.params.id;
		console.log(this.diaryid);
	},
	mounted() {},
};
</script>
