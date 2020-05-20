<template>
	<div>
		<button v-on:click="setTest">click</button>
		{{ u }}
		{{ e }}
	</div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
export default {
	methods: {
		setTest() {
			this.$store.dispatch("user/setTest", "test");
		},
	},
	computed: {
		...mapGetters({ u: "username", e: "phone" }),
	},
};
</script>

<style></style>
