<template>
	<div>
		<editor :height="450"></editor>
	</div>
</template>
<script>
import Editor from "./Tinymce/index";

export default {
	name: "Diary",
	components: {
		editor: Editor,
	},
};
</script>
