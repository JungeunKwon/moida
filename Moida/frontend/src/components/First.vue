<style>
@import "../utils/3DBookShowcase/css/component.css";
@import "../utils/3DBookShowcase/css/default.css";
</style>

<template>
	<div class="container" @click="opened = false">
		<div class="main">
			<ul id="bk-list" class="bk-list clearfix">
				<li>
					<div
						@click.stop="opened = true"
						:class="{
							'bk-book': true,
							'book-1': true,
							'bk-viewinside': opened,
							'bk-bookdefault': !opened,
						}"
					>
						<div class="bk-front">
							<div class="bk-cover-back"></div>
							<div class="bk-cover">
								<h2>
									<span>모 이 다</span>
									<span>- 모두를 이어주는 다이어리 -</span>
								</h2>
							</div>
						</div>
						<div class="bk-page">
							<div class="bk-content bk-content-current">
								<router-link to="/login" v-if="opened" class="bk-tag bt1">로그인</router-link>
								<router-link to="/signUp" v-if="opened" class="bk-tag bt2">회원가입</router-link>
								<slot />
							</div>
						</div>
						<div class="bk-back"></div>
						<div class="bk-right"></div>
						<div class="bk-left">
							<h2>
								<span>HOLYMOLY</span>
								<!-- <span>모두를 이어주는 다이어리</span> -->
							</h2>
						</div>
						<div class="bk-top"></div>
						<div class="bk-bottom"></div>
					</div>
				</li>
			</ul>
		</div>
	</div>
</template>

<script>
import { mapState, mapGetters, mapMutations } from "vuex";
export default {
	data() {
		return {
			others: {},
			opened: false,
		};
	},
	mounted() {},
	computed: {},
	methods: {
		openDiary() {
			this.opened = true;
		},
	},
};
</script>