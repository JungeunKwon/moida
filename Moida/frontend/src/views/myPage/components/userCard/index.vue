<template>
	<div class="us-root">
		<img :src="user.profileImg" class="profile-img" />
		{{user.nickname}}
	</div>
</template>
<script>
export default {
	props: { user: { type: Object, default: {} } },
	data() {
		return {};
	},
};
</script>

<style lang="scss">
.us-root {
	width: 100%;
	height: 100%;
	.profile-img {
		width: 100%;
		height: 100%;
	}
}
</style>