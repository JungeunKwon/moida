<template>
	<p class="event-item" :class="event.cssClass" @click="card_click">
		{{ event.id }}
	</p>
</template>

<script>
export default {
	props: ["event", "date"],
	computed: {},
	methods: {
		card_click() {
			this.$router.push(`/detaildiary/${this.event.id}`);
		},
	},
};
</script>
