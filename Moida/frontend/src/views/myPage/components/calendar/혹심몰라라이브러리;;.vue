<template>
	<div class="pa-5">
		<v-row style="width:100%; height:100%; margin:0;">
			<v-col cols="12" md="6" class="mb-4">
				<v-sheet class="calendar-sheet">
					<v-calendar></v-calendar>
				</v-sheet>
			</v-col>
		</v-row>
	</div>
</template>

<script>
export default {
	data() {
		return {};
	},
};
</script>

<style>
@media screen and (min-width: 960px) {
	.col-md-6 {
		flex: none;
		max-width: 100%;
	}
}
.mb-4 {
	padding: 0px;
}
.v-application .pa-5 {
	padding: 0 !important;
}
.v-application .mb-4 {
	margin-bottom: 0 !important;
}
.pa-5 {
	height: 100%;
	width: 100%;
}
.calendar-sheet {
	min-height: 500px;
	height: 100%;
	width: 100%;
}
</style>
