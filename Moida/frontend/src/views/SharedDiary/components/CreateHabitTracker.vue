<template>
	<v-dialog v-model="dialog" width="500">
		<template v-slot:activator="{ on }">
			<div v-on="on">
				<slot />
			</div>
		</template>
		<v-card id="createhabitTracker">
			<div class="inputDiv">
				<div>HabitTracker</div>
				<input
					id="subject"
					class="input"
					type="text"
					@focus="subjectMsg = ''"
					v-model="habitTracker.subject"
				/>
				<div v-if="subjectMsg != ''" class="errorMsg">
					{{ subjectMsg }}
				</div>
			</div>
			<div class="inputDiv">
				<div>상세 설명</div>
				<textarea
					class="input"
					id="description"
					@focus="descriptionMsg = ''"
					rows="5"
					v-model="habitTracker.description"
				/>
				<div v-if="descriptionMsg != ''" class="errorMsg">
					{{ descriptionMsg }}
				</div>
			</div>

			<div id="htDateDiv">
				<v-menu
					ref="menu1"
					v-model="menu1"
					:close-on-content-click="false"
					:return-value.sync="habitTracker.startDate"
					transition="scale-transition"
					offset-y
					min-width="290px"
				>
					<template v-slot:activator="{ on }">
						<v-text-field
							class="htMenu"
							v-model="habitTracker.startDate"
							label="시작 날짜"
							readonly
							v-on="on"
						></v-text-field>
					</template>
					<v-date-picker
						v-model="habitTracker.startDate"
						no-title
						scrollable
					>
						<v-spacer></v-spacer>
						<v-btn text color="gray" @click="menu1 = false"
							>Cancel</v-btn
						>
						<v-btn
							text
							color="gray"
							@click="$refs.menu1.save(habitTracker.startDate)"
							>OK</v-btn
						>
					</v-date-picker>
				</v-menu>

				<v-menu
					ref="menu2"
					v-model="menu2"
					:close-on-content-click="false"
					:return-value.sync="habitTracker.endDate"
					transition="scale-transition"
					offset-y
					min-width="290px"
				>
					<template v-slot:activator="{ on }">
						<v-text-field
							class="htMenu"
							v-model="habitTracker.endDate"
							label="종료 날짜"
							readonly
							v-on="on"
						></v-text-field>
					</template>
					<v-date-picker
						v-model="habitTracker.endDate"
						no-title
						scrollable
					>
						<v-spacer></v-spacer>
						<v-btn text color="gray" @click="menu2 = false"
							>Cancel</v-btn
						>
						<v-btn
							text
							color="gray"
							@click="$refs.menu2.save(habitTracker.endDate)"
							>OK</v-btn
						>
					</v-date-picker>
				</v-menu>
			</div>

			<div class="buttonDiv">
				<button @click="createHT">만들기</button>
				<button @click="dialog = false">취소</button>
			</div>
		</v-card>
	</v-dialog>
</template>

<script>
import { mapActions } from "vuex";
import moment from "moment";
export default {
	name: "CreatehabitTracker",
	props: { sharedDiaryId: {} },
	data() {
		return {
			dialog: false,
			habitTracker: {
				groupid: 0,
				subject: "",
				description: "",
				startDate: moment()
					.locale("ko")
					.format("YYYY-MM-DD"),
				endDate: moment()
					.add(1, "day")
					.locale("ko")
					.format("YYYY-MM-DD"),
			},
			date: new Date().toISOString().substr(0, 10),
			menu1: false,
			menu2: false,
			subjectMsg: "",
			descriptionMsg: "",
		};
	},
	updated() {
		if (!this.dialog) {
			this.clear();
		}
	},
	methods: {
		...mapActions("habitTracker", ["createHabitTracker"]),
		exceedHandler(file) {
			console.warn("File is too large!");
		},
		clear() {
			this.habitTracker.subject = "";
			this.habitTracker.description = "";
			this.subjectMsg = "";
			this.descriptionMsg = "";
		},
		createHT() {
			if (!this.validate()) return;
			this.habitTracker.groupid = this.sharedDiaryId;
			this.createHabitTracker(this.habitTracker)
				.then(response => {
					console.log(response.data);
					this.$emit("pushMyHT", {
						description: this.habitTracker.description,
						endDate: this.habitTracker.endDate,
						id: response.data,
						startDate: this.habitTracker.startDate,
						subject: this.habitTracker.subject,
						doHabitId: -1,
					});
					this.dialog = false;
				})
				.catch(error => {
					console.log(error);
				});
		},
		validate() {
			var result = true;

			if (this.habitTracker.endDate <= this.habitTracker.startDate) {
				alert("시작 날짜가 더 빨라야합니다 !");
				result = false;
			}

			if (this.habitTracker.subject.trim() == "") {
				this.subjectMsg = "다이어리 제목을 작성해주세요 >_<";
				result = false;
			}
			if (this.habitTracker.description.trim() == "") {
				this.descriptionMsg = "상세 설명을 작성해주세요 >_<";
				result = false;
			}
			return result;
		},
	},
};
</script>

<style>
#createhabitTracker {
	padding: 20px;
}

#createhabitTracker .input {
	border: 1.5px solid silver;
	height: 50px;
	width: 100%;
	outline: none;
	padding: 5px;
}
#createhabitTracker .input:focus {
	border: 1.5px solid rgb(250, 223, 153);
}

.inputDiv {
	margin: 15px 0 15px 0;
}

.inputDiv > div:first-child {
	font-family: "KyoboHand";
	font-size: 20px;
	margin: 0 0 5px 5px;
	text-align: left;
}

#createhabitTracker .buttonDiv {
	margin-top: 20px;
}

#createhabitTracker .buttonDiv button {
	font-family: "KyoboHand";
	font-size: 17px;
	background-color: rgb(250, 223, 153);
	margin: 0 10px 0 10px;
	padding: 5px 10px 5px 10px;
	border-radius: 10px;
	outline: none;
}

#createhabitTracker .buttonDiv button:hover {
	box-shadow: 1px 1px 3px rgba(128, 128, 128, 0.445);
}
</style>
