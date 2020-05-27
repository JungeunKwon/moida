<template>
	<div class="comp-full-calendar">
		<c-header>
			<div slot="header-left">
				<slot name="fc-header-left"> </slot>
			</div>

			<div slot="header-right">
				<slot name="fc-header-right"> </slot>
			</div>
		</c-header>
		<c-body> </c-body>
	</div>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";
import moment from "moment";
export default {
	components: {
		cHeader: () => import("./components/header.vue"),
		cBody: () => import("./components/body.vue"),
	},
	mounted() {
		this.SET_CURRENT_MONTH(moment().startOf("month"));
	},
	computed: {
		...mapGetters(["currentMonth", "firstDay"]),
	},
	methods: {
		...mapMutations("calendar", ["SET_CURRENT_MONTH", "SET_FIRST_DAY"]),
	},
};
</script>

<style></style>
