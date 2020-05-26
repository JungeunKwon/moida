<template>
	<v-dialog v-model="trashdialog" max-width="50%" style="padding:20px">
		<template v-slot:activator="{ on }">
			<div v-on="on">
				<slot />
			</div>
		</template>
		<v-card v-if="!innerdialog">
			<img src="https://media.giphy.com/media/1Ye9oNy0TAC0G7bN4t/giphy.gif" />
		</v-card>
		<v-card v-if="innerdialog && !isMusic">
			<v-card-text>
				<p class="font-weight-bold">해당 감정이 맞나요? 아니면 다시 선택해주세요.</p>
				<v-chip>
					<v-avatar left>
						<v-img :src="moodsrc"></v-img>
					</v-avatar>
					{{mood}}
				</v-chip>
				<v-chip-group v-model="selection" active-class="deep-purple accent-4 white--text" column>
					<div style="margin: 0 auto; width:80%">
						<v-row align="center" justify="start">
							<v-col v-for="(item, i) in items" :key="item.text" class="shrink">
								<v-chip>
									<v-avatar left>
										<v-img :src="item.src"></v-img>
									</v-avatar>
									{{item.text}}
								</v-chip>
							</v-col>
						</v-row>
					</div>
				</v-chip-group>
				<div style="margin: 0 auto; width:100%">
					<v-btn text style="display: inline-block; width: 20%;" @click="inserttrash">노래선택</v-btn>
				</div>
			</v-card-text>
		</v-card>
		<v-card v-if="innerdialog && isMusic">
			<v-card-text>
				<p class="font-weight-bold">이 노래 한번 들어보세요.</p>
				<v-sheet class="mx-auto" elevation="8" max-width="800">
					<v-slide-group
						v-model="model"
						class="pa-4"
						prev-icon="mdi-minus"
						next-icon="mdi-plus"
						show-arrows
					>
						<v-slide-item v-for="music in musiclist" :key="music.id" v-slot:default="{ active, toggle }">
							<v-card
								:color="active ? 'primary' : 'grey lighten-1'"
								class="ma-4"
								height="200"
								width="100"
								@click="toggle"
							>
								<v-row class="fill-height" align="center" justify="center">
									<v-avatar left>
										<v-img :src="music.thumbnails"></v-img>
									</v-avatar>
									{{music.musicname}}
								</v-row>
							</v-card>
						</v-slide-item>
					</v-slide-group>
				</v-sheet>
				<div style="margin: 0 auto; width:100%">
					<v-btn text style="display: inline-block; width: 20%;" @click="inserttrash">노래선택</v-btn>
				</div>
			</v-card-text>
		</v-card>
	</v-dialog>
</template>
<script>
import { findByMood } from "../../../../src/api/music";

export default {
	name: "TrashDialog",
	props: {
		items: {},
		mood: "",
		moodsrc: "",
		content: "",
	},
	data() {
		return {
			trashdialog: false,
			innerdialog: true,
			isMusic: false,
			url: "",
			selection: {},
			musiclist: [],
		};
	},
	methods: {
		inserttrash() {
			var item = this.items[this.selection];
			findByMood("슬픔")
				.then(response => {
					console.log(response);
					this.musiclist = response.data.content;
					this.isMusic = true;
				})
				.catch(error => {});
		},
	},
};
</script>