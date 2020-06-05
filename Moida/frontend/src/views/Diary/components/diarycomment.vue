<template>
	<div></div>
</template>
<script>
import { mapActions } from "vuex";

export default {
	name: "diarycomment",
	props: {
		comment: {
			type: Object,
			default: {},
		},
	},
	data() {
		return { diaryid: "" };
	},
	created() {},
	mounted() {},
	methods: {
		...mapActions("diary", ["diaryLike", "diaryDisLike"]),
	},
};
</script>
