<template>
	<v-dialog v-model="dialog" width="500">
		<template v-slot:activator="{ on }">
			<div v-on="on">
				<slot />
			</div>
		</template>
		<v-card id="createSharedDiary">
			<div class="inputDiv">
				<div>공다 표지</div>
				<img-inputer
					ref="diaryImg"
					v-model="sharedDiary.uploadFile"
					name="file"
					placeholder="Drop file here or click"
					bottom-text="Drop file here or click"
					exceed-size-text="사진의 크기가 초과하였습니다"
					:max-size="10240"
					@onExceed="exceedHandler"
				/>
			</div>
			<div class="inputDiv">
				<div>공다 제목</div>
				<input
					id="subject"
					class="input"
					type="text"
					@focus="subjectMsg = ''"
					v-model="sharedDiary.subject"
				/>
				<div v-if="subjectMsg != ''" class="errorMsg">{{subjectMsg}}</div>
			</div>
			<div class="inputDiv">
				<div>제한 인원</div>
				<input
					id="limitUser"
					class="input"
					@focus="limitUserMsg = ''"
					type="number"
					v-model="sharedDiary.limitUser"
				/>
				<div v-if="limitUserMsg != ''" class="errorMsg">{{limitUserMsg}}</div>
			</div>
			<div class="inputDiv">
				<div>공개 여부</div>
				<select class="input" name="isPrivate">
					<!-- select로 바꿔 -->
					<option value="false">공개</option>
					<option selected value="true">비공개</option>
				</select>
			</div>
			<div class="inputDiv">
				<div>상세 설명</div>
				<textarea class="input" id="description" @focus="descriptionMsg = ''" />
				<div v-if="descriptionMsg != ''" class="errorMsg">{{descriptionMsg}}</div>
			</div>
			<div class="buttonDiv">
				<button @click="createSharedDiary">만들기</button>
				<button @click="dialog=false">취소</button>
			</div>
		</v-card>
	</v-dialog>
</template>

<script>
export default {
	name: "CreateSharedDiary",
	data() {
		return {
			dialog: false,
			sharedDiary: {
				uploadFile: "",
				subject: "",
				limitUser: 0,
				isPrivate: false,
				description: "",
			},
			limitUserMsg: "",
			subjectMsg: "",
			descriptionMsg: "",
		};
	},
	updated() {
		if (!this.dialog) {
			this.sharedDiary.subject = "";
			this.sharedDiary.limitUser = "";
			this.sharedDiary.description = "";
			this.limitUserMsg = "";
			this.subjectMsg = "";
			this.descriptionMsg = "";
		}
	},
	methods: {
		exceedHandler(file) {
			console.warn("File is too large!");
		},
		createSharedDiary() {
			if (!this.validate()) return;
		},
		validate() {
			var result = true;
			if (this.sharedDiary.subject.trim() == "") {
				this.subjectMsg = "다이어리 제목을 작성해주세요 >_<";
				result = false;
			}
			if (this.sharedDiary.limitUser <= 1) {
				this.limitUserMsg =
					"인원은 나를 포함한 2명 이상으로 해주세요 >_<";
				result = false;
			}
			if (this.sharedDiary.description.trim() == "") {
				this.descriptionMsg = "상세 설명을 작성해주세요 >_<";
				result = false;
			}
			return result;
		},
	},
};
</script>

<style>
#createSharedDiary {
	padding: 20px;
}

#createSharedDiary .input {
	border: 1.5px solid silver;
	height: 50px;
	width: 100%;
	outline: none;
	padding: 5px;
}
#createSharedDiary .input:focus {
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

#createSharedDiary .buttonDiv {
	margin-top: 20px;
}

#createSharedDiary .buttonDiv button {
	font-family: "KyoboHand";
	font-size: 17px;
	background-color: rgb(250, 223, 153);
	margin: 0 10px 0 10px;
	padding: 5px 10px 5px 10px;
	border-radius: 10px;
	outline: none;
}

#createSharedDiary .buttonDiv button:hover {
	box-shadow: 1px 1px 3px rgba(128, 128, 128, 0.445);
}
</style>