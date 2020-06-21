<template>
	<div id="chatting">
		<!-- <v-btn @click="increateRoom">템ㅅ프</v-btn> -->
		<div id="chat_left">
			<div
				v-for="(chat, idx) in chatList"
				id="chat"
				:key="idx"
				@click="inenterRoom(chat)"
			>
				<div class="chat_userImg_class">
					<img id="chat_userImg" :src="chat.userProfile" />
				</div>
				<div class="chat_content_class">
					<div id="chat_nickname">{{ chat.userNickname }}</div>

					<div id="chat_list_content">
						{{ getContent(chat.lastSentence) }}
					</div>

					<p id="chat_date">
						{{ getLastDate(chat.lastDate) }}
					</p>
				</div>
			</div>
		</div>
		<div id="chat_right">
			<ChatRoom
				v-if="isOpen"
				ref="chattingref"
				:key="chattingkey"
				:chat="chat"
				@closeChat="closeChat"
			/>
			<div v-else id="none">채팅을 시작해볼까요 ✿˘◡˘✿</div>
		</div>
	</div>
</template>

<script>
import { mapActions, mapMutations, mapGetters } from "vuex";
import ChatRoom from "./components/ChatRoom";

export default {
	name: "Chatting",
	components: {
		ChatRoom,
	},
	data() {
		return {
			isOpen: false,
			chatList: [],
			chattingkey: 0,
			chat: {
				user: "",
				userpro: "",
				roomuuid: "",
			},
			isChat: false,
		};
	},
	mounted() {
		// this.isChat = ture;
		// if (this.isChat == 1) {
		// 	this.chat = this.$store.getters.chat;
		// 	console.log(this.chat);
		// 	this.isOpen = true;
		// } else if (this.isChat == 2) {
		// 	this.createRoom();
		// }
		// store.state.isChat = 0;
		//this.createRoom();
	},
	created() {
		var targetNickname = this.$store.getters.targetNickname;
		var chat = {
			userProfile: "",
			userNickname: "",
			roomuuid: "",
		};
		if (targetNickname != "") {
			this.roomCheck({
				host: this.$store.getters.nickname,
				user: targetNickname,
			})
				.then(response => {
					if (response.data == "") {
						console.log("채팅방 생성하셈");
						this.increateRoom(targetNickname);
					} else {
						chat.roomuuid = response.data.roomuuid;
						if (
							response.data.hostNickname ==
							this.$store.getters.nickname
						) {
							chat.userProfile = response.data.userProfileImg;
							chat.userNickname = response.data.userNickname;
						} else {
							chat.userProfile = response.data.hostProfileImg;
							chat.userNickname = response.data.hostNickname;
						}
						this.inenterRoom(chat);
					}
				})
				.catch(error => {
					console.log(error);
				});
		}
		this.SET_TARGET_NICKNAME("");
		this.findAllRoom();
	},
	methods: {
		...mapActions("chat", [
			"getRoom",
			"enterRoom",
			"getRoomInfo",
			"createRoom",
			"getAllRooms",
			"roomCheck",
		]),
		...mapMutations("chat", ["SET_TARGET_NICKNAME"]),

		closeChat() {
			this.isOpen = false;
		},
		findAllRoom() {
			this.getAllRooms(this.$store.getters.nickname)
				.then(response => {
					this.chatList = response.data;
				})
				.catch(error => {
					console.log("findAllRoomError :::::", error);
				});
		},
		increateRoom(targetNickname) {
			console.log("createRoom");
			var chat = {
				userProfile: "",
				userNickname: "",
				roomuuid: "",
			};
			//this.chat.user = this.$store.getters.targetNickname;
			//this.chat.userpro = this.$store.getters.targetImgUrl;
			this.createRoom(targetNickname)
				.then(response => {
					this.findAllRoom();
					chat.roomuuid = response.data.roomuuid;
					if (
						response.data.hostNickname ==
						this.$store.getters.nickname
					) {
						chat.userProfile = response.data.userProfileImg;
						chat.userNickname = response.data.userNickname;
					} else {
						chat.userProfile = response.data.hostProfileImg;
						chat.userNickname = response.data.hostNickname;
					}
					this.inenterRoom(chat);
				})
				.catch(err => {
					console.log("CreateRoom err ", err);
				});
		},
		inenterRoom(current) {
			if (this.isOpen == true) {
				this.$refs.chattingref.closeChat();
				//	this.$refs.chattingref.destroy();
				this.closeChat();
				this.chattingkey = this.chattingkey + 1;
			}
			//	this.chattingkey = true;
			this.chat = current;

			this.isOpen = true;
		},
		getLastDate(date) {
			if (date == undefined) return;
			return date.replace("T", " ").substring(0, 16);
		},
		getContent(content) {
			if (content == undefined) return;
			if (content.length < 10) return content;
			return content.substring(0, 10) + "...";
		},
	},
};
</script>

<style>
#chatting {
	height: 100%;
	overflow: hidden;
	position: relative;
}

#chat_left {
	float: left;
	position: absolute;
	width: 50%;
	height: 100%;
	overflow-y: auto;
}

#chat_left::-webkit-scrollbar {
	display: block;
	width: 5px;
}
#chat_left::-webkit-scrollbar-track {
	background-color: #5d5d5d0a;
	border-radius: 50%;
} /* 스크롤 바 밑의 배경 */
#chat_left::-webkit-scrollbar-thumb {
	background: #80808028;
} /* 실질적 스크롤 바 */
#chat_left::-webkit-scrollbar-thumb:hover {
	background: #80808028;
} /* 실질적 스크롤 바 위에 마우스를 올려다 둘 때 */
#chat_left::-webkit-scrollbar-thumb:active {
	background: #80808028;
} /* 실질적 스크롤 바를 클릭할 때 */
#chat_left::-webkit-scrollbar-button {
	display: none;
} /* 스크롤 바 상 하단 버튼 */

#chat {
	height: 80px;
	padding: 5px;
	cursor: pointer;
	width: 100%;
	display: flex;
	align-items: center;
}

#chat:hover {
	box-shadow: 2px 2px 7px 2px #999;
}

#chat_userImg {
	width: 50px;
	height: 50px;
	float: left;
	border-radius: 50%;
}

#chat_nickname {
	width: 100%;
	margin: 0;
	margin-left: 10px;
	font-weight: 400;
	text-align: left;
	height: 30px;
}

#chat_date {
	width: 100%;
	font-size: 12px;
	text-align: right;
	height: 20px;
}

#chat_right {
	float: right;
	width: 50%;
	height: 100%;
	background-color: rgba(248, 217, 213, 0.151);
}

#none {
	margin-top: 50px;
	text-align: center;
}

#createRoom {
	cursor: pointer;
}

#chat_list_content {
	word-break: break-all;
	height: 30px;
	width: 100%;
	display: inline-block;
}
.chat_userImg_class {
	display: inline-block;
	width: 60px;
}
.chat_content_class {
	display: inline-block;
	height: 70px;
	width: calc(100% - 65px);
}
@media screen and (max-width: 774px) {
	#chatting {
		height: 100%;
		width: 100%;
		display: block;
		position: unset;
	}
	#chat_left {
		position: unset;
		display: block;
		width: 100%;
		height: 50%;
		overflow-y: auto;
	}

	#chat_right {
		position: unset;
		display: block;
		width: 100%;
		height: 50%;
		background-color: rgba(248, 217, 213, 0.151);
	}
}

@media screen and (max-width: 330px) {
	#chatting {
		height: 100%;
		width: 100%;
		display: block;
		position: unset;
	}

	#chat_left {
		position: unset;
		display: block;
		width: 100%;
		height: 50%;
		overflow-y: auto;
	}

	#chat_right {
		position: unset;
		display: block;
		width: 100%;
		height: 50%;
		background-color: rgba(248, 217, 213, 0.151);
	}
}
</style>
