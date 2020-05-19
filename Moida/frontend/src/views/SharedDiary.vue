<template>
	<div id="SharedDiary">
		<SharedDiaryItem v-for="(item, idx) in diaries" :key="idx" :item="item" />
	</div>
</template>

<script>
import SharedDiaryItem from "@/components/SharedDiaryItem";
export default {
	name: "SharedDiary",
	components: {
		SharedDiaryItem,
	},
	data() {
		return {
			diaries: [
				{
					id: 0,
					name: "방제",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
				},
				{
					id: 0,
					name: "방제",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
				},
				{
					id: 0,
					name: "방제",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
				},
				{
					id: 0,
					name: "방제",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
				},
				{
					id: 0,
					name: "방제",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
				},
				{
					id: 0,
					name: "방제",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
				},
				{
					id: 0,
					name: "방제",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
				},
				{
					id: 0,
					name: "방제",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
				},
				{
					id: 0,
					name: "방제",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
				},
				{
					id: 0,
					name: "방제",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
				},
				{
					id: 0,
					name: "방제",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
				},
				{
					id: 0,
					name: "방제",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
				},
				{
					id: 0,
					name: "방제",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
				},
			],
		};
	},
	mounted() {},
	computed: {},
	methods: {},
};
</script>

<style></style>
