<template>
	<div id="chatRoom">
		<div id="chatRoom_top">
			<img
				id="chat_back"
				src="../../../assets/icons/chat_back.png"
				@click="closeChat()"
			/>
			<div id="targetNickname">{{ targetNickname }}</div>
			<div id="chat_on">
				<img
					:src="isOn ? './icons/on.png' : './icons/off.png'"
					width="15px"
				/>
			</div>
		</div>
		<div id="chats_div">
			<div v-for="(msg, idx) in messages" id="chats" :key="idx">
				<div v-if="msg.writer == targetNickname" class="you">
					<img :src="targetImg" class="targetImg" />
					<div>
						<div id="chats_youdate">
							&nbsp;&nbsp;&nbsp;&nbsp;
							{{ getLastDate(msg.lastDate) }}
						</div>
						<div v-if="msg.type == 'SHARE'" class="you_store_div">
							<!-- <div class="you_store">
								<div class="store_title" @click="goStore(msg.store.storeId)">
									<img
										class="store_icon"
										src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/place_icon.png"
									/>
									<div class="store_name">{{ msg.store.storeName }}</div>
									<img
										class="store_arrow"
										src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png"
									/>
								</div>
								<div class="store_desc">
									<div class="store_img_div">
										<img class="store_img" :src="msg.store.storeImage" />
									</div>
									<div class="store_detail">
										<div>REVIEWS : {{ msg.store.reviewCnt }}</div>
										<div>TEL : {{ msg.store.storeTel }}</div>
									</div>
								</div>
							</div>-->
						</div>
						<div
							v-if="msg.type != 'SHARE' && msg.content != ''"
							class="you_msg"
						>
							{{ msg.content }}
						</div>
					</div>
				</div>

				<div v-if="msg.writer == userNickname" class="me">
					<div id="chats_medate">{{ getLastDate(msg.lastDate) }}</div>
					<div v-if="msg.type == 'SHARE'" class="me_store_div">
						<!-- <div class="me_store">
							<div class="store_title" @click="goStore(msg.store.storeId)">
								<img
									class="store_icon"
									src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/place_icon.png"
								/>
								<div class="store_name">{{ msg.store.storeName }}</div>
								<img
									class="store_arrow"
									src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png"
								/>
							</div>
							<div class="store_desc">
								<div class="store_img_div">
									<img class="store_img" :src="msg.store.storeImage" />
								</div>
								<div class="store_detail">
									<div>REVIEWS : {{ msg.store.reviewCnt }}</div>
									<div>TEL : {{ msg.store.storeTel }}</div>
								</div>
							</div>
						</div>-->
					</div>
					<div
						v-if="msg.type != 'SHARE' && msg.content != ''"
						class="me_msg"
					>
						{{ msg.content }}
					</div>
				</div>
			</div>
		</div>
		<div id="send_div">
			<input
				id="chatRoom_input"
				v-model="content"
				type="text"
				@keyup.enter="sendMessage()"
			/>
			<img
				id="send"
				src="../../../assets/icons/send.png"
				@click="sendMessage()"
			/>
		</div>
	</div>
</template>

<script>
import { mapActions, mapMutations, mapGetters } from "vuex";

import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default {
	name: "ChatRoom",
	props: {
		chat: {
			type: Object,
			required: true,
		},
	},
	data() {
		return {
			test: "../../../assets/icons/default.png",
			userNickname: "",
			targetNickname: "",
			targetImg: "",
			content: "",
			messages: [{}],
			roomuuid: "",
			stompClient: null,
			socket: null,
			isOn: false,
		};
	},
	mounted() {},

	created() {
		this.targetNickname = this.chat.userNickname;
		this.targetImg = this.chat.userProfile;
		this.userNickname = this.$store.getters.nickname;
		this.roomuuid = this.chat.roomuuid;
		var socketUrl = "https://k02d1061.p.ssafy.io/ws-stomp";
		//var socketUrl = "http://192.168.43.253:8080/ws-stomp"; //"http://172.20.10.2:8080/ws-stomp";
		this.socket = new SockJS(socketUrl);
		var here = this;
		this.stompClient = Stomp.over(this.socket);
		this.stompClient.connect(
			{},
			function(frame) {
				console.log(frame);
				here.stompClient.subscribe(
					"/sub/chat/room/" + here.roomuuid,
					message => {
						console.log(message);
						var recv = JSON.parse(message.body);
						here.recvMessage(recv);
						here.chatOnScroll();
					},
				);
				here.stompClient.send(
					"/pub/chat/message",
					JSON.stringify({
						type: "JOIN",
						roomuuid: here.roomuuid,
						writer: here.userNickname,
						content: "",
					}),
					{},
				);
			},
			error => {
				alert("error create " + error);
			},
		);
	},
	updated() {
		this.chatOnScroll();
	},
	destroyed() {
		console.log("destroyed");
		if (this.stompClient != null) {
			this.stompClient.send(
				"/pub/chat/message",
				JSON.stringify(
					{
						type: "QUIT",
						roomuuid: this.chat.roomuuid,
						writer: this.userNickname,
						content: this.content,
					},
					{},
				),
			);
			this.stompClient.disconnect();
		}
	},
	methods: {
		getLastDate(date) {
			if (date == undefined) return;
			return date.replace("T", " ").substring(0, 16);
		},
		sendShare() {
			this.stompClient.send(
				"/pub/chat/message",
				JSON.stringify(
					{
						type: "SHARE",
						roomuuid: this.chat.roomuuid,
						writer: this.userNickname,
						content: "",
					},
					{},
				),
			);
		},
		closeChat() {
			console.log("closeChat()");
			if (this.stompClient != null) {
				this.stompClient.send(
					"/pub/chat/message",
					JSON.stringify(
						{
							type: "QUIT",
							roomuuid: this.chat.roomuuid,
							writer: this.userNickname,
							content: this.content,
						},
						{},
					),
				);
				this.stompClient.disconnect();
			}
			this.messages = [];
			this.$emit("closeChat");
		},
		sendMessage() {
			if (this.content == "") return;
			if (this.stompClient == null) return;
			this.stompClient.send(
				"/pub/chat/message",
				JSON.stringify(
					{
						type: "TALK",
						roomuuid: this.chat.roomuuid,
						writer: this.userNickname,
						content: this.content,
					},
					{},
				),
			);
			this.content = "";
		},

		recvMessage(recv) {
			console.log("recvMessage");
			console.log(recv);
			if (recv.type == "LOG") {
				if (this.userNickname != recv.loguser) return;
			}

			if (recv.type == "QUIT") {
				if (recv.writer != this.userNickname) {
					this.isOn = false;
					return;
				}
			}
			if (recv.type == "JOIN") {
				if (recv.writer != this.userNickname) {
					this.isOn = true;
				}
				return;
			}

			if (recv.type == "SHARE") {
				if (
					recv.loguser != null &&
					recv.loguser.length > 0 &&
					recv.loguser != this.userNickname
				)
					return;
				this.recvStore(recv);
			} else {
				this.messages.push({
					roomuuid: recv.roomuuid,
					writer: recv.writer,
					content: recv.content,
					type: recv.type,
					lastDate: recv.lastDate,
				});
			}
			this.messages.sort((a, b) => new Date(a.date) - new Date(b.date));
			console.log(this.messages);
		},
		connect() {
			this.stompClient.connect(
				{},
				function(frame) {
					console.log(frame);
					this.stompClient.subscribe(
						"/sub/chat/room/" + this.roomuuid,
						message => {
							var recv = JSON.parse(message.body);
							this.recvMessage(recv);
						},
					);
					this.stompClient.send(
						"/pub/chat/message",
						{},
						JSON.stringify({
							type: "JOIN",
							roomuuid: this.roomuuid,
							writer: this.userNickname,
							content: "",
						}),
					);
				},
				error => {
					alert("error " + error);
				},
			);
		},
		chatOnScroll() {
			var objDiv = document.getElementById("chats_div");
			if (objDiv.scrollHeight == null) return;
			objDiv.scrollTop = objDiv.scrollHeight;
		},
	},
};
</script>

<style>
#chatRoom {
	width: 100%;
	height: 100%;
	position: relative;
	overflow: hidden;
	margin-top: 10px;
}

#chatRoom_top {
	height: 40px;
	line-height: 40px;
	box-shadow: 2px 2px 5px 0.1px rgba(153, 153, 153, 0.425);
}

#chat_back {
	width: 25px;
	margin: 6px;
	float: left;
	cursor: pointer;
}

#chat_back:hover {
	opacity: 0.7;
}

#targetNickname {
	font-weight: 400;
	float: left;
	margin: -2px 0 0 5px;
}

#chat_on {
	float: right;
	width: 15px;
	margin-right: 15px;
}

#chats_div {
	position: absolute;
	height: calc(100% - 90px);
	width: 100%;
	padding: 10px;
	overflow-y: auto;
}

#chats {
	width: 100%;
	padding: 4px 5px 4px 5px;
	text-align: left;
}

.me {
	width: 100%;
	text-align: right;
}

.me_msg {
	word-break: break-all;
	display: inline-block;
	background-color: salmon;
	color: white;
	padding: 3px 8px 3px 8px;
	border-radius: 10px;
	max-width: 250px;
}

#chats_medate {
	margin-right: 7px;
	font-size: 9px;
}

.you_msg {
	word-break: break-all;
	display: inline-block;
	background-color: rgba(192, 192, 192, 0.603);
	padding: 3px 8px 3px 8px;
	border-radius: 10px;
	margin: 2px 0 0 5px;
	max-width: 250px;
}

#chats_youdate {
	font-size: 9px;
	width: 200px;
}

.targetImg {
	width: 40px;
	height: 40px;
	float: left;
	border-radius: 50%;
}

.you_store {
	background-color: silver;
	width: 250px;
	height: 100px;
	float: right;
	border-radius: 10px;
}

.me_store_div,
.you_store_div {
	width: 100%;
	height: 130px;
}

.me_store,
.you_store {
	background-color: white;
	width: 280px;
	height: 130px;
	border-radius: 10px;
	text-align: left;
	overflow: hidden;
}

.you_store {
	float: left;
	margin-left: 5px;
}

.me_store {
	float: right;
}

.store_title {
	background-color: salmon;
	padding: 10px;
	color: white;
	height: 40px;
	cursor: pointer;
}

.store_icon {
	margin: 3px 5px 0 0;
	float: left;
}

.store_name {
	float: left;
	font-weight: 400;
}

.store_arrow {
	float: right;
	margin: 3px;
}

.store_desc {
	padding: 10px;
	height: 100px;
}

.store_img_div {
	float: left;
	width: 100px;
	height: 70px;
}

.store_img {
	width: 100%;
	height: 100%;
	overflow: hidden;
	object-fit: contain;
	display: flex;
	justify-content: center;
	align-items: cneter;
}

.store_detail {
	float: left;
	padding: 15px 0 0 15px;
	font-size: 13px;
}

#send_div {
	display: inline-block;
	width: 100%;
	position: absolute;
	bottom: 0;
	left: 0;
	background-color: white;
	height: 50px;
	padding: 0 5px 0 5px;
}

#chatRoom_input {
	float: left;
	outline: none;
	height: 45px;
	width: calc(100% - 45px);
	padding-bottom: 5px;
}

#send {
	cursor: pointer;
	float: left;
	width: 25px;
	margin: 6px 0 0 12px;
}

#chats_div::-webkit-scrollbar {
	display: block;
	width: 5px;
}
#chats_div::-webkit-scrollbar-track {
	background-color: #5d5d5d0a;
	border-radius: 50%;
} /* 스크롤 바 밑의 배경 */
#chats_div::-webkit-scrollbar-thumb {
	background: #80808028;
} /* 실질적 스크롤 바 */
#chats_div::-webkit-scrollbar-thumb:hover {
	background: #80808028;
} /* 실질적 스크롤 바 위에 마우스를 올려다 둘 때 */
#chats_div::-webkit-scrollbar-thumb:active {
	background: #80808028;
} /* 실질적 스크롤 바를 클릭할 때 */
#chats_div::-webkit-scrollbar-button {
	display: none;
} /* 스크롤 바 상 하단 버튼 */
</style>
