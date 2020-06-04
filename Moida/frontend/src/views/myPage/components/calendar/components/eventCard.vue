<template>
	<p class="event-item" :class="event.cssClass" @click="card_click">
		{{ event.content }}
	</p>
</template>

<script>
export default {
	props: ["event", "date"],
	computed: {},
	methods: {
		card_click() {
			alert(this.event.id + "가 눌렸다!!");
		},
	},
};
</script>