<template>
	<v-dialog v-model="dialog" width="500px">
		<template v-slot:activator="{ on }">
			<div v-on="on"><slot></slot></div>
		</template>
		<div class="followModal">
			<div
				style="padding: 20px; font-size: 20px;"
				v-if="data.length == 0"
			>
				해당 정보가 없어요 ㅠ
			</div>
			<v-list-item
				v-for="(d, idx) in data"
				:key="idx"
				@click="$router.push(`/myPage/${d.nickname}`)"
			>
				<v-list-item-avatar color="grey">
					<img :src="d.profileimg" alt="프사" />
				</v-list-item-avatar>
				<v-list-item-content>
					<v-list-item-subtitle style="text-align:left">{{
						d.nickname
					}}</v-list-item-subtitle>
				</v-list-item-content>
			</v-list-item>
		</div>
	</v-dialog>
</template>

<script>
export default {
	props: {
		data: { type: Array, default: [] },
	},
	data() {
		return { dialog: false };
	},
	methods: {},
};
</script>

<style>
.followModal {
	background-color: white;
	max-height: 700px;
	overflow: auto;
	font-size: 15px;
}
</style>
