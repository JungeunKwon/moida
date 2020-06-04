<template>
	<div>
		<editor :height="450" :isEdit="false"></editor>
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
