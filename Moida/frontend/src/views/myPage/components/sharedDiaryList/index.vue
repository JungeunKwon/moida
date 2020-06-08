<template>
	<div class="sdl-root">
		<div class="sharedMenu mini">
			<img class="tape" src="@/assets/images/tape.png" />
			<div class="sharedPaper mini" @click="showModal=true">참가한 다이어리</div>
			<img
				v-if="user.nickname && $store.getters.nickname !== user.nickname"
				class="tape"
				src="@/assets/images/tape.png"
			/>
			<div
				v-if="user.nickname && $store.getters.nickname !== user.nickname"
				class="sharedPaper mini"
			>채팅걸기</div>
		</div>
		<diary-list v-if="showModal" :diaries="diaries" @close="showModal=false" />
	</div>
</template>

<script>
export default {
	props: {
		diaries: { type: Array, default: [] },
		user: { typs: Object, default: {} },
	},
	data() {
		return {
			showModal: false,
		};
	},
	components: {
		diaryList: () => import("./components/sharedDiaryList"),
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
		flex: 1;
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
				cursor: pointer;
				transform: none;
			}
		}
	}
}
</style>