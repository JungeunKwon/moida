<template>
	<div style="width:50%; margin: 0 auto;">
		<TrashCom />
	</div>
</template>

<script>
// @ is an alias to /src
import TrashCom from "@/components/TrashComponent.vue";

export default {
	name: "Trash",
	components: {
		TrashCom,
	},
};
</script>
