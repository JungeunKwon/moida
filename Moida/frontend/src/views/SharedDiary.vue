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
					name: "3학년 1반 모여라 ~",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
					count: 3,
				},
				{
					id: 0,
					name: "~ 영대 컴공 14학번 모임 ~",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
					count: 3,
				},
				{
					id: 0,
					name: "3학년 1반 모여라 ~",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
					count: 3,
				},
				{
					id: 0,
					name: "~ 영대 컴공 14학번 모임 ~",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
					count: 3,
				},
				{
					id: 0,
					name: "3학년 1반 모여라 ~",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
					count: 3,
				},
				{
					id: 0,
					name: "~ 영대 컴공 14학번 모임 ~",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
					count: 3,
				},
				{
					id: 0,
					name: "3학년 1반 모여라 ~",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
					count: 3,
				},
				{
					id: 0,
					name: "~ 영대 컴공 14학번 모임 ~",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
					count: 3,
				},
				{
					id: 0,
					name: "3학년 1반 모여라 ~",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
					count: 3,
				},
				{
					id: 0,
					name: "~ 영대 컴공 14학번 모임 ~",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
					count: 3,
				},
				{
					id: 0,
					name: "3학년 1반 모여라 ~",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
					count: 3,
				},
				{
					id: 0,
					name: "~ 영대 컴공 14학번 모임 ~",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
					count: 3,
				},
				{
					id: 0,
					name: "3학년 1반 모여라 ~",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
					count: 3,
				},
				{
					id: 0,
					name: "~ 영대 컴공 14학번 모임 ~",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
					count: 3,
				},
				{
					id: 0,
					name: "3학년 1반 모여라 ~",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
					count: 3,
				},
				{
					id: 0,
					name: "~ 영대 컴공 14학번 모임 ~",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
					count: 3,
				},
				{
					id: 0,
					name: "3학년 1반 모여라 ~",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
					count: 3,
				},
				{
					id: 0,
					name: "~ 영대 컴공 14학번 모임 ~",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
					count: 3,
				},
				{
					id: 0,
					name: "3학년 1반 모여라 ~",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
					count: 3,
				},
				{
					id: 0,
					name: "~ 영대 컴공 14학번 모임 ~",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
					count: 3,
				},
				{
					id: 0,
					name: "3학년 1반 모여라 ~",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
					count: 3,
				},
				{
					id: 0,
					name: "~ 영대 컴공 14학번 모임 ~",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
					count: 3,
				},
				{
					id: 0,
					name: "3학년 1반 모여라 ~",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
					count: 3,
				},
				{
					id: 0,
					name: "~ 영대 컴공 14학번 모임 ~",
					limit: 5,
					host_id: 0,
					host_nickname: "방장",
					password: null,
					count: 3,
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
	height: 100%;
	overflow: auto;
	margin: 1px;
}

.flip-list-move {
	transition: transform 1s;
}
::-webkit-scrollbar {
	width: 3px; /* 세로축 스크롤바 길이 */
}

::-webkit-scrollbar-track {
	background-color: lightblue;
}
::-webkit-scrollbar-track-piece {
	background-color: white;
}
::-webkit-scrollbar-thumb {
	border-radius: 8px;
	background-color: rgba(128, 128, 128, 0.295);
}
::-webkit-scrollbar-thumb:hover {
	background-color: rgba(128, 128, 128, 0.808);
	cursor: pointer;
}
</style>
