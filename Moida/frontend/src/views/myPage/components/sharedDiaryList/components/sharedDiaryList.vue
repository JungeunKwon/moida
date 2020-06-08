<template>
	<transition name="modal">
		<div class="modal-mask">
			<div class="modal-wrapper">
				<div class="modal-container">
					<div class="modal-header">
						<button
							class="modal-default-button"
							@click="$emit('close')"
						>
							다끼
						</button>
					</div>
					<div class="itemList">
						<item
							v-for="(item, idx) in diaries"
							:key="idx"
							:item="item"
						/>
					</div>
				</div>
			</div>
		</div>
	</transition>
</template>

<script>
export default {
	name: "sharedDiaryList",
	props: {
		diaries: { type: Array, default: [] },
	},
	components: {
		item: () => import("./SharedDiaryListItem"),
	},
	mounted() {
		window.addEventListener("click", this.outOfMore);
	},
	beforeDestroy() {
		window.removeEventListener("click", this.outOfMore);
	},
	methods: {
		outOfMore(e) {
			if (document.getElementById("boun").contains(e.target)) {
			} else {
				this.$emit("close");
			}
		},
	},
};
</script>

<style>
.modal-header h3 {
	margin-top: 0;
	color: #42b983;
}
.modal-default-button {
	float: right;
	margin-bottom: 20px;
	font-size: 2rem;
	color: red;
}
.modal-mask {
	position: fixed;
	z-index: 9998;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
	display: table;
	transition: opacity 0.3s ease;
}

.modal-wrapper {
	display: table-cell;
	vertical-align: middle;
}

.modal-container {
	width: 700px;
	max-height: 700px;
	overflow: auto;
	margin: 0px auto;
	padding: 20px 30px;
	background-color: #fff;
	border-radius: 2px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
	transition: all 0.3s ease;
	font-family: Helvetica, Arial, sans-serif;
}
.modal-enter {
	opacity: 0;
}

.modal-leave-active {
	opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
	-webkit-transform: scale(1.1);
	transform: scale(1.1);
}
</style>
