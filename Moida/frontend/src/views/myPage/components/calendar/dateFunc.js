import moment from "moment";

let dateFunc = {
	getMonthViewStartDate(date, firstDay) {
		firstDay = parseInt(firstDay);
		let start = moment(date);
		let startOfMonth = moment(start.startOf("month"));
		// console.log(start.format("YYYY MM DD dd"));
		start.subtract(startOfMonth.day(), "days");
		// console.log(start.format("YYYY MM DD dd"));
		if (startOfMonth.day() < firstDay) {
			start.subtract(7, "days");
		}
		// start.add(firstDay, "days");
		// console.log(start.format("YYYY MM DD dd"));
		return start;
	},
};

export default dateFunc;
