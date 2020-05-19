<template>
	<v-btn
		v-show="fab"
		v-scroll="onScroll"
		style="opacity: 0.9"
		fab
		dark
		fixed
		bottom
		right
		color="#fa8072"
		@click="toTop"
	>
		<v-icon>mdi-chevron-up</v-icon>
	</v-btn>
</template>

<script>
export default {
	data: () => ({
		fab: false,
	}),

	methods: {
		onScroll(e) {
			if (typeof window === "undefined") return;
			const top = window.pageYOffset || e.target.scrollTop || 0;
			this.fab = top > 20;
		},
		toTop() {
			this.$vuetify.goTo(0);
		},
	},
};
</script>
