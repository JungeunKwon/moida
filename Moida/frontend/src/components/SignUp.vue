<template>
	<div id="signUp">
		<v-form id="form" ref="form" v-model="valid" onsubmit="return false;">
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
					v-model="loginForm.email"
					:rules="[rules.required, rules.email, duplicatedemail]"
					:error-messages="errorMessages"
					label="이메일"
					outlined
					required
					@change="changemail"
					color="#fadf99"
					style="float: left; width: calc(100% - 100px);"
				/>
				<button @click="emailcheck" class="signUp_btn dupli">중복체크</button>
			</div>
			<div class="signUp_input">
				<v-text-field
					ref="nickname"
					v-model="loginForm.nickname"
					:rules="[rules.required, duplicatednickname]"
					label="닉네임"
					required
					outlined
					color="#fadf99"
					@change="changenick"
					style="float: left; width: calc(100% - 100px);"
				/>
				<button @click="nicknamecheck" class="signUp_btn dupli">중복체크</button>
			</div>
			<div class="signUp_input">
				<v-text-field
					ref="username"
					v-model="loginForm.username"
					:rules="[() => !!loginForm.username || 'This field is required']"
					label="이름"
					required
					color="#fadf99"
					outlined
				/>
			</div>
			<div class="signUp_input">
				<v-text-field
					ref="phone"
					v-model="loginForm.phone"
					:rules="[rules.required, rules.phone]"
					label="전화번호"
					required
					color="#fadf99"
					outlined
				/>
			</div>
			<div class="signUp_input">
				<v-text-field
					ref="password"
					v-model="loginForm.password"
					:append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
					:rules="[rules.required, rules.min]"
					:type="show1 ? 'text' : 'password'"
					name="input-10-2"
					label="비밀번호"
					color="#fadf99"
					hint="At least 8 characters"
					class="input-group--focused"
					@click:append="show1 = !show1"
					outlined
				/>
			</div>

			<div class="signUp_input">
				<v-text-field
					ref="re_password"
					v-model="loginForm.re_password"
					:append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
					:rules="[
						() =>
							loginForm.password === loginForm.re_password ||
							'The password you entered dont match',
					]"
					:type="show2 ? 'text' : 'password'"
					name="input-10-2"
					label="비밀번호 확인"
					color="#fadf99"
					hint="At least 8 characters"
					@click:append="show2 = !show2"
					outlined
				/>
			</div>
			<div>
				<p>성별</p>
				<v-radio-group ref="gender" v-model="loginForm.gender" required>
					<v-radio label="남" value="0" />
					<v-radio label="여" value="1" />
				</v-radio-group>
			</div>

			<v-menu
				v-model="menu"
				:close-on-content-click="false"
				:nudge-right="40"
				transition="scale-transition"
				offset-y
				min-width="290px"
			>
				<template v-slot:activator="{ on }">
					<v-text-field
						ref="birth_date"
						v-model="loginForm.birth_date"
						label="생년월일"
						prepend-icon="mdi-calendar"
						readonly
						v-on="on"
						color="#fadf99"
					/>
				</template>
				<v-date-picker v-model="loginForm.birth_date" @input="menu = false" color="#fadf99" />
			</v-menu>
			<div style="width: 100%; height: 20px; margin: 0 auto; text-align: center;">
				<button class="signUp_btn submit" @click="resetForm">취소</button>
				<button class="signUp_btn submit" @click="submit">확인</button>
			</div>
		</v-form>
	</div>
</template>
<script>
export default {
	name: "SignUp",
	data: () => ({
		loginForm: {
			email: "",
			username: "",
			password: "",
			re_password: "",
			gender: 0,
			birth_date: new Date().toISOString().substring(0, 10),
			nickname: "",
			phone: "",
			uploadFile: "",
		},
		file: "",
		valid: true,
		loading: true,
		show1: false,
		show2: false,
		menu: false,
		errorMessages: "",
		isnicknameduplicated: true,
		isemailduplicated: true,
		rules: {
			required: value => !!value || "Required.",
			min: v => (v && v.length >= 8) || "Min 8 characters",
			email: value => {
				const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
				return pattern.test(value) || "Invalid e-mail.";
			},
			phone: value => {
				const pattern = /^\d{2,3}-\d{3,4}-\d{4}$/;
				return pattern.test(value) || "Invalid phoneNumber";
			},
		},
	}),
	computed: {
		form() {
			return {
				email: this.loginForm.email,
				username: this.loginForm.username,
				password: this.loginForm.password,
				re_password: this.loginForm.re_password,
				gender: this.loginForm.gender,
				birth_date: this.loginForm.birth_date,
				nickname: this.loginForm.nickname,
				phone: this.loginForm.phone,
			};
		},
	},
	mounted() {},
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
				if (
					this.$refs.profile.file == null ||
					this.$refs.profile.file === ""
				) {
					this.loginForm.uploadFile = null;
				} else {
					this.loginForm.uploadFile = this.$refs.profile.file;
				}

				this.$store
					.dispatch("user/signUp", this.loginForm)
					.then(response => {
						console.log(response);
						console.log("회원가입이 완료되었습니다.");
						this.$router.push("/login");
					})
					.catch(error => {
						console.log(error);
					});
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
			this.$store
				.dispatch("user/checkNickname", this.loginForm.nickname)
				.then(response => {
					console.log(response);
					if (response.data === false) {
						alert("중복된 닉네임입니다");
						this.isnicknameduplicated = true;
					} else {
						alert("사용가능한 닉네임입니다");
						this.isnicknameduplicated = false;
					}
					this.$refs["nickname"].validate(true);
				})
				.catch(error => {
					console.log(error);
					this.isnicknameduplicated = true;
				});
		},
		emailcheck() {
			if (this.rules.email(this.loginForm.email) === true) {
				this.$store
					.dispatch("user/checkEmail", this.loginForm.email)
					.then(response => {
						console.log(response);
						if (response.data === false) {
							alert("중복된 이메일입니다");
							this.isemailduplicated = true;
						} else {
							alert("사용가능한 이메일입니다");
							this.isemailduplicated = false;
						}
						this.$refs["email"].validate(true);
					})
					.catch(error => {
						console.log(error);
						this.isemailduplicated = true;
					});
			}
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
