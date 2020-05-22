<template>
	<v-btn
		v-show="fab"
		v-scroll:#test="onScroll"
		style="opacity: 0.9"
		fab
		dark
		fixed
		bottom
		right
		small
		color="#c7ceea"
		@click="toTop"
	>
		<v-icon>mdi-chevron-up</v-icon>
	</v-btn>
</template>

<script>
export default {
	name: "GoTop",
	data: () => ({
		fab: false,
	}),
	mounted() {
		console.log("GoTop mounted()");
	},
	methods: {
		onScroll(e) {
			if (typeof window === "undefined") return;
			const top = window.pageYOffset || e.target.scrollTop || 0;
			this.fab = top > 20;
		},
		toTop() {
			console.log("toTop()");
			document.getElementById("test").scrollTop = 0;
		},
	},
};
</script>
