<template>
	<p
		class="event-item"
		:class="event.cssClass"
		@click="$emit('click', event, $event)"
	>
		{{ event.content }}
	</p>
</template>

<script>
export default {
	props: ["event", "date"],
	computed: {},
};
</script>
