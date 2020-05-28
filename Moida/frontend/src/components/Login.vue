<template>
	<div class="loginFormDiv">
		<span class="helper"></span>
		<div id="loginForm">
			<input
				v-model="email"
				class="login_input"
				type="text"
				placeholder="이메일"
				@focus="emailCheck = false"
			/>
			<span v-if="emailCheck" class="errorMsg">{{ emailMsg }}</span>
			<input
				v-model="password"
				class="login_input"
				type="password"
				placeholder="비밀번호"
				@focus="pwCheck = false"
			/>
			<span v-if="pwCheck" class="errorMsg">{{ pwMsg }}</span>
			<button id="login_btn" @click="login()" @keyup.enter="login()">로그인</button>
			<div id="login_bottom">
				<hr id="div_line" />
				<span class="login_bottom_text" @click="findPW">비밀번호 찾기</span>
				<!-- <img id="text_div" src="../assets/icons/text_div.png" />
				<span class="login_bottom_text" @click="signUp">회원가입</span>-->
			</div>
		</div>
	</div>
</template>

<script>
import { mapMutations } from "vuex";
export default {
	name: "Login",
	data() {
		return {
			email: "",
			password: "",
			show: false,
			emailMsg: "이메일을 입력해주세요!",
			pwMsg: "비밀번호를 입력해주세요!",
			emailCheck: false,
			pwCheck: false,
		};
	},
	methods: {
		...mapMutations("user", ["TOGGLE_ISFIRST"]),
		emailValidation(email) {
			let regExp = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
			if (regExp.test(email)) return true;
			else return false;
		},
		login() {
			if (!this.emailValidation(this.email)) {
				this.emailCheck = true;
				return;
			}
			if (this.password == "") {
				this.pwCheck = true;
				return;
			}
			this.$store
				.dispatch("user/login", {
					email: this.email,
					password: this.password,
				})
				.then(response => {
					// console.log(response);
					// console.log(response.data.code);
					if (response.data.code == undefined) {
						this.TOGGLE_ISFIRST(false);
						this.$router.push("/");
					} else {
						alert("아이디와 비밀번호를 확인해주세요.");
					}
				})
				.catch(error => {
					console.log(error);
				});
		},
		signUp() {
			this.$router.push("/signUp");
		},
		findPW() {},
	},
};
</script>

<style>
.loginFormDiv {
	height: 100%;
	width: 100%;
}

.helper {
	display: inline-block;
	height: 100%;
	vertical-align: middle;
}

#loginForm {
	display: inline-block;
	vertical-align: middle;
	margin: 0 auto;
	width: 400px;
}

.login_input {
	width: 100%;
	height: 50px;
	border: 1px solid silver;
	padding: 10px;
	margin-top: 20px;
}

.login_input:focus {
	outline: 2px solid rgb(250, 223, 153);
}

#login_btn {
	margin-top: 20px;
	width: 100%;
	height: 65px;
	background-color: rgb(250, 223, 153);
	color: white;
	border-radius: 0px;
	font-size: 20px;
	outline: none;
}

#login_btn:hover {
	opacity: 0.8;
}

#div_line {
	border: solid 0.5px rgb(230, 229, 229);
	margin-bottom: 5px;
}

#login_bottom {
	width: 100%;
	margin-top: 20px;
	text-align: center;
}

#text_div {
	height: 13px;
	width: 1px;
	margin: 0 5px 0 5px;
}

.login_bottom_text {
	font-size: 13px;
	color: rgb(66, 66, 66);
	font-weight: 300;
	cursor: pointer;
}

.login_bottom_text:hover {
	color: rgb(0, 0, 0);
}

.errorMsg {
	color: rgb(250, 223, 153);
	font-size: 12px;
	margin-left: 5px;
}

@media screen and (max-width: 500px) {
	#loginForm {
		width: 250px;
	}
}
</style>