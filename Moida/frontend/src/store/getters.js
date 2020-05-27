const getters = {
	token: state => state.user.token,
	email: state => state.user.email,
	username: state => state.user.username,
	gender: state => state.user.gender,
	nickname: state => state.user.nickname,
	phone: state => state.user.phone,
	profile_img: state => state.user.profile_img,
	isFirst: state => state.auth.isFirst,
};

export default getters;