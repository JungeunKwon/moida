<template>
	<div class="full-calendar-header">
		<div class="header-left">
			<slot name="header-left"></slot>
		</div>
		<div class="header-center">
			<span class="prev-month" @click.stop="goPrev">{{ leftArrow }}</span>
			<span class="title">{{ title }}</span>
			<span class="next-month" @click.stop="goNext">
				{{ rightArrow }}
			</span>
		</div>
		<div class="header-right">
			<slot name="header-right"></slot>
		</div>
	</div>
</template>
<script>
import moment from "moment";
import { mapGetters, mapMutations } from "vuex";
export default {
	data() {
		return {
			leftArrow: "<",
			rightArrow: ">",
		};
	},
	mounted() {},
	computed: {
		...mapGetters(["currentMonth", "locale"]),
		title() {
			if (!this.currentMonth) return;
			return this.currentMonth.locale(this.locale).format("YYYY년 MM월");
		},
	},
	methods: {
		...mapMutations("calendar", ["SET_CURRENT_MONTH", "SET_FIRST_DAY"]),
		goPrev() {
			var newMonth = moment(this.currentMonth)
				.subtract(1, "months")
				.startOf("month");
			this.SET_CURRENT_MONTH(newMonth);
			console.log(this.currentMonth);
		},
		goNext() {
			var newMonth = moment(this.currentMonth)
				.add(1, "months")
				.startOf("month");
			this.SET_CURRENT_MONTH(newMonth);
			console.log(this.currentMonth);
		},
	},
};
</script>
<style lang="scss">
.full-calendar-header {
	display: flex;
	align-items: center;
	.header-left,
	.header-right {
		flex: 1;
	}
	.header-center {
		flex: 3;
		text-align: center;
		.title {
			margin: 0 10px;
		}
		.prev-month,
		.next-month {
			cursor: pointer;
		}
	}
}
</style>
