<template>
	<v-dialog v-model="dialog" width="700px" class="mof">
		<template v-slot:activator="{ on }">
			<div class="sharedPaper mini" v-on="on"><slot></slot></div>
		</template>
		<div class="Modal">
			<div class="itemList">
				<item v-for="(item, idx) in diaries" :key="idx" :item="item" />
			</div>
		</div>
	</v-dialog>
</template>

<script>
export default {
	props: {
		diaries: { type: Array, default: () => [] },
	},
	components: {
		item: () => import("./SharedDiaryListItem"),
	},
	data() {
		return { dialog: false };
	},
	methods: {},
};
</script>

<style lang="scss">
.mof {
	.Modal {
		width: 700px;
		background-color: white;
		max-height: 700px;
		overflow: auto;
		font-size: 15px;
		padding: 20px 30px;
		background-color: #fff;
		border-radius: 2px;
		box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
		transition: all 0.3s ease;
	}
	.sharedPaper {
		padding: 0 10px 0 10px;
		margin: -22px auto 0 auto;
		&.mini {
			width: fit-content !important;
			height: fit-content !important;
			&:hover {
				cursor: pointer;
				transform: none;
			}
		}
	}
}
</style>
