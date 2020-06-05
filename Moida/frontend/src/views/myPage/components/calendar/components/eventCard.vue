<template>
	<p class="event-item" :class="event.cssClass" @click="card_click">{{ getDate }}</p>
</template>

<script>
import moment from "moment";
export default {
	props: ["event", "date"],
	computed: {
		getDate() {
			return this.event.day.format("HH시 mm분");
		},
	},
	methods: {
		card_click() {
			this.$router.push(`/detailDiary/${this.event.id}`);
		},
	},
};
</script>