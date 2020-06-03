<template>
	<div class="full-calendar-body">
		<div class="weeks">
			<strong class="week" v-for="dayIndex in 7" :key="dayIndex">
				{{ (dayIndex - 1) | localeWeekDay(firstDay, locale) }}
			</strong>
		</div>
		<div class="dates" ref="dates">
			<div class="dates-bg">
				<div
					class="week-row"
					v-for="(week, idx) in currentDates"
					:key="idx"
				>
					<div
						class="day-cell"
						v-for="(day, idx) in week"
						:key="idx"
						:class="{
							today: day.isToday,
							'not-cur-month': !day.isCurMonth,
						}"
					>
						<p class="day-number">{{ day.monthDay }}</p>
						<div class="event-box">
							<event-card
								:event="event"
								:date="day.date"
								v-for="(event, idx) in day.events"
								:key="idx"
								v-show="idx < eventLimit"
								@click="eventClick"
							/>
							<p
								v-if="day.events.length > eventLimit"
								class="more-link"
							>
								{{ day.events.length - eventLimit }}
								개 more...
							</p>
						</div>
					</div>
				</div>
			</div>

			<!-- full events when click show more -->
			<!-- <div
				class="more-events"
				v-show="showMore"
				:style="{ left: morePos.left + 'px', top: morePos.top + 'px' }"
			>
				<div class="more-header">
					<span class="title">{{ moreTitle(selectDay.date) }}</span>
					<span class="close" @click.stop="showMore = false">x</span>
				</div>
				<div class="more-body">
					<ul class="body-list">
						<li
							v-for="event in selectDay.events"
							:key="event"
							v-show="event.isShow"
							class="body-item"
							@click="eventClick(event, $event)"
						>{{ event.title }}</li>
					</ul>
				</div>
			</div>
			<slot name="body-card"></slot>-->
		</div>
	</div>
</template>

<script>
import moment from "moment";
import { mapGetters } from "vuex";
import dateFunc from "../dateFunc";
export default {
	components: {
		eventCard: () => import("./eventCard.vue"),
	},
	data() {
		return {
			eventLimit: 3,
			events: [
				{
					content: "6월2일개인..",
					day: "2020-06-02",
					cssClass: "private",
				},
				{
					content: "6월2일개인..",
					day: "2020-06-02",
					cssClass: "private",
				},
				{
					content: "6월2일개인..",
					day: "2020-06-02",
					cssClass: "private",
				},
				{
					content: "6월2일개인..",
					day: "2020-06-02",
					cssClass: "private",
				},
				{
					content: "6월4일개인..",
					day: "2020-06-04",
					cssClass: "private",
				},
				{
					content: "6월4일개인..",
					day: "2020-06-04",
					cssClass: "private",
				},
				{
					content: "6월4일개인..",
					day: "2020-06-04",
					cssClass: "private",
				},
				{
					content: "6월6일개인..",
					day: "2020-06-06",
					cssClass: "private",
				},
				{
					content: "6월6일개인..",
					day: "2020-06-06",
					cssClass: "shared",
				},
				{
					content: "6월6일개인..",
					day: "2020-06-06",
					cssClass: "shared",
				},
				{
					content: "6월8일개인..",
					day: "2020-06-08",
					cssClass: "private",
				},
				{
					content: "6월2일공유..",
					day: "2020-06-02",
					cssClass: "shared",
				},
				{
					content: "6월2일공유..",
					day: "2020-06-02",
					cssClass: "shared",
				},
				{
					content: "6월4일공유..",
					day: "2020-06-04",
					cssClass: "shared",
				},
				{
					content: "6월29일공유..",
					day: "2020-06-29",
					cssClass: "shared",
				},
				{
					content: "6월16일공유..",
					day: "2020-06-16",
					cssClass: "shared",
				},
			],
		};
	},
	props: {
		nickname: "",
	},
	mounted() {
		console.log(this.nickname);
	},
	computed: {
		...mapGetters(["currentMonth", "firstDay", "locale"]),
		currentDates() {
			return this.getCalendar();
		},
	},
	methods: {
		eventClick(event, jsEvent) {
			if (!event.isShow) return;
			jsEvent.stopPropagation();
			let pos = this.computePos(jsEvent.target);
			this.$emit("eventClick", event, jsEvent, pos);
		},
		getCalendar() {
			let monthViewStartDate = dateFunc.getMonthViewStartDate(
				this.currentMonth,
				this.firstDay,
			);
			let calendar = [];
			for (let perWeek = 0; perWeek < 6; perWeek++) {
				let week = [];
				for (let perDay = 0; perDay < 7; perDay++) {
					week.push({
						monthDay: monthViewStartDate.date(),
						isToday: monthViewStartDate.isSame(moment(), "day"),
						isCurMonth: monthViewStartDate.isSame(
							this.currentMonth,
							"month",
						),
						weekDay: perDay,
						date: moment(monthViewStartDate),
						events: this.addEvents(monthViewStartDate),
					});
					monthViewStartDate.add(1, "day");
				}
				calendar.push(week);
			}
			return calendar;
		},
		addEvents(date) {
			// console.log(date.date());
			let thisDayEvents = this.events.filter(event => {
				let edate = moment(event.day);
				return date.isSame(edate, "day");
			});
			return thisDayEvents;
		},
	},
	filters: {
		localeWeekDay(weekday, firstDay, locale) {
			firstDay = parseInt(firstDay);
			const localMoment = moment().locale(locale);
			return localMoment.localeData().weekdaysShort()[
				(weekday + firstDay) % 7
			];
		},
	},
};
</script>

<style lang="scss">
.full-calendar-body {
	margin-top: 20px;
	.weeks {
		display: flex;
		border-top: 1px solid #e0e0e0;
		border-bottom: 1px solid #e0e0e0;
		border-left: 1px solid #e0e0e0;
		.week {
			flex: 1;
			text-align: center;
			border-right: 1px solid #e0e0e0;
		}
	}
	.dates {
		position: relative;
		.week-row {
			// width: 100%;
			// position:absolute;
			border-left: 1px solid #e0e0e0;
			display: flex;
			.day-cell {
				flex: 1;
				min-height: 112px;
				padding: 4px;
				border-right: 1px solid #e0e0e0;
				border-bottom: 1px solid #e0e0e0;
				.day-number {
					text-align: right;
				}
				&.today {
					background-color: #fcf8e3;
				}
				&.not-cur-month {
					.day-number {
						color: rgba(0, 0, 0, 0.24);
					}
				}
				.event-box {
					text-align: left;
					.more-link {
						cursor: pointer;
						// text-align: right;
						padding-left: 8px;
						padding-right: 2px;
						color: rgba(0, 0, 0, 0.38);
						font-size: 14px;
					}
					.event-item {
						cursor: pointer;
						font-size: 12px;
						margin-bottom: 2px;
						color: rgba(0, 0, 0, 0.87);
						padding: 0 0 0 4px;
						height: 18px;
						line-height: 18px;
						white-space: nowrap;
						overflow: hidden;
						text-overflow: ellipsis;

						&.private {
							background-color: #ff0000;
						}
						&.shared {
							background-color: #c7e6fd;
						}
					}
				}
			}
		}
	}
}
</style>
