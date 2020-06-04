<template>
	<div class="comp-full-calendar">
		<c-header>
			<div slot="header-left">
				<slot name="fc-header-left">{{ nickname }}</slot>
			</div>

			<div slot="header-right">
				<slot name="fc-header-right"></slot>
			</div>
		</c-header>
		<c-body :nickname="nickname">
			<div slot="body-card">
				<slot name="fc-body-card"></slot>
			</div>
		</c-body>
	</div>
</template>

<script>
import { mapGetters, mapMutations, mapActions } from "vuex";
import moment from "moment";
export default {
	props: { nickname: "" },
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

<style lang="scss">
.comp-full-calendar {
	// font-family: "elvetica neue", tahoma, "hiragino sans gb";
	padding: 20px;
	background: #fff;
	margin: 0 auto;
	ul,
	p {
		margin: 0;
		padding: 0;
	}
}
</style>
