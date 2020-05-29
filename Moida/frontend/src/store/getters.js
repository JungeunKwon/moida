const getters = {
	token: state => state.user.token,
	email: state => state.user.email,
	username: state => state.user.username,
	gender: state => state.user.gender,
	nickname: state => state.user.nickname,
	phone: state => state.user.phone,
	profile_img: state => state.user.profile_img,
	//calendar
	locale: state => state.calendar.locale,
	currentMonth: state => state.calendar.currentMonth,
	firstDay: state => state.calendar.firstDay,
};
export default getters;
