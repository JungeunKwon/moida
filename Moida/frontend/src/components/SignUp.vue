<template>
	<div id="signUp">
		<div v-if="here" id="signUp_title">회원가입</div>
		<v-form id="form" ref="form" v-model="valid">
			<img-inputer
				ref="profile"
				v-model="file"
				name="file"
				placeholder="Drop file here or click"
				bottom-text="Drop file here or click"
				exceed-size-text="사진의 크기가 초과하였습니다"
				:max-size="10240"
				@onExceed="exceedHandler"
				id="signUp_img"
			/>
			<div style="height: 50px" />
			<div class="signUp_input">
				<v-text-field
					ref="email"
					v-model="email"
					:rules="[rules.required, rules.email, duplicatedemail]"
					:error-messages="errorMessages"
					label="이메일"
					outlined
					required
					@change="changemail"
					color="#fadf99"
					style="float: left; width: calc(100% - 100px);"
				/>
				<button @click="emailcheck" class="signUp_btn dupli">
					중복체크
				</button>
			</div>
			<div class="signUp_input">
				<v-text-field
					ref="nickname"
					v-model="nickname"
					:rules="[rules.required, duplicatednickname]"
					label="닉네임"
					required
					outlined
					color="#fadf99"
					@change="changenick"
					style="float: left; width: calc(100% - 100px);"
				/>
				<button @click="nicknamecheck" class="signUp_btn dupli">
					중복체크
				</button>
			</div>
			<div class="signUp_input">
				<v-text-field
					ref="name"
					v-model="name"
					:rules="[() => !!name || 'This field is required']"
					label="이름"
					required
					color="#fadf99"
					outlined
				/>
			</div>
			<div class="signUp_input">
				<v-text-field
					ref="firstpassword"
					v-model="firstpassword"
					:append-icon="show3 ? 'mdi-eye' : 'mdi-eye-off'"
					:rules="[rules.required, rules.min]"
					:type="show3 ? 'text' : 'password'"
					name="input-10-2"
					label="비밀번호"
					color="#fadf99"
					hint="At least 8 characters"
					class="input-group--focused"
					@click:append="show3 = !show3"
					outlined
				/>
			</div>
			<div class="signUp_input">
				<v-text-field
					ref="secondpassword"
					v-model="secondpassword"
					:append-icon="show4 ? 'mdi-eye' : 'mdi-eye-off'"
					:rules="[
						() =>
							firstpassword === secondpassword ||
							'The password you entered dont match',
					]"
					:type="show4 ? 'text' : 'password'"
					name="input-10-2"
					label="비밀번호 확인"
					color="#fadf99"
					hint="At least 8 characters"
					@click:append="show4 = !show4"
					outlined
				/>
			</div>
			<div>
				<p>성별</p>
				<v-radio-group ref="gender" v-model="gender" required>
					<v-radio label="남" value="0" />
					<v-radio label="여" value="1" />
				</v-radio-group>
			</div>

			<v-menu
				v-model="menu2"
				:close-on-content-click="false"
				:nudge-right="40"
				transition="scale-transition"
				offset-y
				min-width="290px"
			>
				<template v-slot:activator="{ on }">
					<v-text-field
						ref="birth_year"
						v-model="birth_year"
						label="생년월일"
						prepend-icon="mdi-calendar"
						readonly
						v-on="on"
						color="#fadf99"
					/>
				</template>
				<v-date-picker
					v-model="birth_year"
					@input="menu2 = false"
					color="#fadf99"
				/>
			</v-menu>
			<div
				style="width: 100%; height: 20px; margin: 0 auto; text-align: center;"
			>
				<button class="signUp_btn submit" @click="resetForm">
					Cancel
				</button>
				<button class="signUp_btn submit" @click="submit">
					Submit
				</button>
			</div>
		</v-form>
	</div>
</template>
<script>
import axios from "axios";
export default {
	name: "SignUp",
	data: () => ({
		here: false,
		name: "",
		valid: true,
		loading: true,
		show3: false,
		show4: false,
		password: "Password",
		firstpassword: "",
		secondpassword: "",
		file: "",
		birth_year: new Date().toISOString().substr(0, 10),
		email: "",
		gender: 0,
		menu2: false,
		nickname: "",
		errorMessages: "",
		isnicknameduplicated: true,
		isemailduplicated: true,
		rules: {
			required: value => !!value || "Required.",
			min: v => v.length >= 8 || "Min 8 characters",
			email: value => {
				const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
				return pattern.test(value) || "Invalid e-mail.";
			},
		},
	}),
	computed: {
		form() {
			return {
				email: this.email,
				name: this.name,
				firstpassword: this.firstpassword,
				secondpassword: this.secondpassword,
				gender: this.gender,
				birth_year: this.birth_year,
				nickname: this.nickname,
			};
		},
	},
	mounted() {
		if (this.$route.fullPath == "/signUp") {
			this.here = true;
		}
	},
	methods: {
		duplicatednickname() {
			if (this.isnicknameduplicated === false) {
				return true;
			} else {
				return "중복 검사 해주세요.";
			}
		},
		duplicatedemail() {
			if (this.isemailduplicated === false) {
				return true;
			} else {
				return "중복 검사 해주세요.";
			}
		},
		resetForm() {
			this.errorMessages = [];
			this.formHasErrors = false;

			Object.keys(this.form).forEach(f => {
				this.$refs[f].reset();
			});
		},
		submit() {
			this.formHasErrors = false;

			Object.keys(this.form).forEach(f => {
				if (this.$refs[f].validate(true) === false) {
					this.formHasErrors = true;
					this.$refs[f].validate(true);
					return;
				}
			});
			if (this.formHasErrors === false) {
				var formData = new FormData();
				formData.append("email", this.email);
				formData.append("password", this.firstpassword);
				formData.append("phone", "010-9749-9959");
				formData.append("gender", this.gender);
				formData.append("username", this.name);
				//formData.append("latitude", this.latitude);
				//formData.append("longitude", this.longitude);
				formData.append("nickname", this.nickname);
				if (
					this.$refs.profile.file == null ||
					this.$refs.profile.file === ""
				) {
					formData.append("uploadFile", null);
				} else {
					formData.append("uploadFile", this.$refs.profile.file);
				}

				for (var key of formData.entries()) {
					console.log(key[0] + ", " + key[1]);
				} // 폼데이터 로그 출력법
				// axios
				// 	.post("http://192.168.77.80:8080/v1/signup", formData)
				// 	.then(response => {
				// 		console.log(
				// 			"response : ",
				// 			JSON.stringify(response, null, 2),
				// 		);
				// 		if (response.status == 200) {
				// 			alert("회원 가입 성공");
				// 			this.$router.push("/");
				// 		} else {
				// 			alert("회원 가입 실패");
				// 			resetForm();
				// 		}
				// 	})
				// 	.catch(error => {
				// 		console.log("failed", error);
				// 	});
			} else {
				alert("제대로 입력하삼ㅡㅡ");
			}
		},
		changenick() {
			this.isnicknameduplicated = true;
			this.$refs["nickname"].validate(true);
		},
		changemail() {
			this.isemailduplicated = true;
			this.$refs["email"].validate(true);
		},
		nicknamecheck() {
			// axios
			// 	.get(
			// 		"http://192.168.77.80:8080/v1/check/nickname/" +
			// 			this.nickname,
			// 	)
			// 	.then(response => {
			// 		console.log(
			// 			"response : ",
			// 			JSON.stringify(response, null, 2),
			// 		);
			// 		if (response.data === false) {
			// 			alert("중복된 닉네임입니다");
			// 			this.isnicknameduplicated = true;
			// 		} else {
			// 			alert("사용가능한 닉네임입니다");
			// 			this.isnicknameduplicated = false;
			// 		}
			// 		this.$refs["nickname"].validate(true);
			// 	})
			// 	.catch(error => {
			// 		console.log("failed", error);
			// 		this.isnicknameduplicated = true;
			// 	});
		},
		emailcheck() {
			// axios
			// 	.get("http://192.168.77.80:8080/v1/check/email/" + this.email)
			// 	.then(response => {
			// 		console.log(
			// 			"response : ",
			// 			JSON.stringify(response, null, 2),
			// 		);
			// 		if (response.data === false) {
			// 			alert("중복된 이메일입니다");
			// 			this.isemailduplicated = true;
			// 		} else {
			// 			alert("사용가능한 이메일입니다");
			// 			this.isemailduplicated = false;
			// 		}
			// 		this.$refs["email"].validate(true);
			// 	})
			// 	.catch(error => {
			// 		console.log("failed", error);
			// 		this.isemailduplicated = true;
			// 	});
		},
		exceedHandler(file) {
			console.log(this.$refs.profile);
			console.warn("onExceed -> file", file);
			// this.$refs.profile.reset();
		},
	},
};
</script>

<style>
#signUp {
	height: 100%;
	width: 100%;
	overflow: auto;
}

#form > div.img-inputer.img-inputer-- > p {
	text-align: center;
}

.signUp_input {
	display: block;
	height: 90px;
	width: 100%;
}

.signUp_btn {
	cursor: pointer;
	padding: 10px 15px 10px 15px;
	margin: 6px;
	outline: gray;
}

.signUp_btn.dupli {
	float: left;
}

#signUp::-webkit-scrollbar {
	display: none;
}
</style>
