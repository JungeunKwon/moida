<template>
	<div>
		<editor :height="450"></editor>
	</div>
</template>
<script>
import Editor from "./Tinymce/index";

export default {
	name: "WriteDiary",
	components: {
		editor: Editor,
	},
};
</script>
