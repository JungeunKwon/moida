import moment from "moment";

let dateFunc = {
	getMonthViewStartDate(date, firstDay) {
		firstDay = parseInt(firstDay);
		let start = moment(date);
		let startOfMonth = moment(start.startOf("month"));
		start.subtract(startOfMonth.day(), "days");
		if (startOfMonth.day() < firstDay) {
			start.subtract(7, "days");
		}
		return start;
	},
};

export default dateFunc;
