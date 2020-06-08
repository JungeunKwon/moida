<template>
	<div class="sdl-root">
		<div class="sharedMenu mini">
			<img class="tape" src="@/assets/images/tape.png" />
			<div class="sharedPaper mini">내가 참가한 다이어리</div>
		</div>
		<div class="itemList">
			<item />
		</div>
	</div>
</template>

<script>
export default {
	components: {
		item: () => import("./components/SharedDiaryListItem"),
	},
};
</script>

<style lang="scss">
.sdl-root {
	// background-color: white;
	width: 100%;
	height: 100%;
	display: flex;
	.sharedMenu {
		flex: 3;
		margin: 0;
		&.mini {
			&:hover {
				cursor: unset;
				transform: none;
			}
		}
	}
	.sharedPaper {
		padding: 0 10px 0 10px;
		margin: -22px auto 0 auto;
		&.mini {
			width: fit-content !important;
			height: fit-content !important;
			&:hover {
				cursor: unset;
				transform: none;
			}
		}
	}

	.itemList {
		flex: 7;
	}
}
</style>