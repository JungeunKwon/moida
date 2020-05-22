<template>
	<div id="SharedDiary">
		<SharedDiaryItem
			v-for="(item, idx) in diaries"
			:key="idx"
			:item="item"
		/>
	</div>
</template>
<script>
import SharedDiaryItem from "./components/item";
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
					name:
						"3학년 1반 모여라 ~ 긴글 테스트 용임 %^^% 불만 있으신지?",
					host_id: 0,
					host_nickname: "방장",
				},
				{
					id: 0,
					name: "~ 영대 컴공 14학번 모임 ~",
					host_id: 0,
					host_nickname: "방장",
				},
				{
					id: 0,
					name: "3학년 1반 모여라 ~",
					host_id: 0,
					host_nickname: "방장",
				},
				{
					id: 0,
					name: "~ 영대 컴공 14학번 모임 ~",
					host_id: 0,
					host_nickname: "방장",
				},
				{
					id: 0,
					name: "3학년 1반 모여라 ~",
					host_id: 0,
					host_nickname: "방장",
				},
				{
					id: 0,
					name: "~ 영대 컴공 14학번 모임 ~",
					host_id: 0,
					host_nickname: "방장",
				},
				{
					id: 0,
					name: "3학년 1반 모여라 ~",
					host_id: 0,
					host_nickname: "방장",
				},
				{
					id: 0,
					name: "~ 영대 컴공 14학번 모임 ~",
					host_id: 0,
					host_nickname: "방장",
				},
				{
					id: 0,
					name: "3학년 1반 모여라 ~",
					host_id: 0,
					host_nickname: "방장",
				},
				{
					id: 0,
					name: "~ 영대 컴공 14학번 모임 ~",
					host_id: 0,
					host_nickname: "방장",
				},
				{
					id: 0,
					name: "3학년 1반 모여라 ~",
					host_id: 0,
					host_nickname: "방장",
				},
				{
					id: 0,
					name: "~ 영대 컴공 14학번 모임 ~",
					host_id: 0,
					host_nickname: "방장",
				},
				{
					id: 0,
					name: "3학년 1반 모여라 ~",
					host_id: 0,
					host_nickname: "방장",
				},
				{
					id: 0,
					name: "~ 영대 컴공 14학번 모임 ~",
					host_id: 0,
					host_nickname: "방장",
				},
				{
					id: 0,
					name: "3학년 1반 모여라 ~",
					host_id: 0,
					host_nickname: "방장",
				},
				{
					id: 0,
					name: "~ 영대 컴공 14학번 모임 ~",
					host_id: 0,
					host_nickname: "방장",
				},
				{
					id: 0,
					name: "3학년 1반 모여라 ~",
					host_id: 0,
					host_nickname: "방장",
				},
				{
					id: 0,
					name: "~ 영대 컴공 14학번 모임 ~",
					host_id: 0,
					host_nickname: "방장",
				},
				{
					id: 0,
					name: "3학년 1반 모여라 ~",
					host_id: 0,
					host_nickname: "방장",
				},
				{
					id: 0,
					name: "~ 영대 컴공 14학번 모임 ~",
					host_id: 0,
					host_nickname: "방장",
				},
				{
					id: 0,
					name: "3학년 1반 모여라 ~",
					host_id: 0,
					host_nickname: "방장",
				},
				{
					id: 0,
					name: "~ 영대 컴공 14학번 모임 ~",
					host_id: 0,
					host_nickname: "방장",
				},
				{
					id: 0,
					name: "3학년 1반 모여라 ~",
					host_id: 0,
					host_nickname: "방장",
				},
				{
					id: 0,
					name: "~ 영대 컴공 14학번 모임 ~",
					host_id: 0,
					host_nickname: "방장",
				},
			],
		};
	},
	mounted() {},
	computed: {},
	methods: {},
};
</script>

<style>
#SharedDiary {
	/* height: 100%;
	overflow-y: auto;
	overflow-x: hidden; */
	margin: 1px;
}
</style>
