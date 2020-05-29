<template>
	<div class="full-calendar-body">
		<div class="weeks">
			<strong class="week" v-for="dayIndex in 7" :key="dayIndex">
				{{
				(dayIndex - 1) | localeWeekDay(firstDay, locale)
				}}
			</strong>
		</div>
		<div class="dates" ref="dates">
			<div class="dates-bg">
				<div class="week-row" v-for="week in currentDates" :key="week">
					<div
						class="day-cell"
						v-for="day in week"
						:key="day"
						:class="{
							today: day.isToday,
							'not-cur-month': !day.isCurMonth,
						}"
					>
						<p class="day-number">{{ day.monthDay }}</p>
					</div>
				</div>
			</div>

			<!-- absolute so we can make dynamic td -->
			<!-- <div class="dates-events">
				<div class="events-week" v-for="week in currentDates" :key="week">
					<div
						class="events-day"
						v-for="day in week"
						:key="day"
						track-by="$index"
						:class="{
							today: day.isToday,
							'not-cur-month': !day.isCurMonth,
						}"
						@click.stop="dayClick(day.date, $event)"
					>
						<p class="day-number">{{ day.monthDay }}</p>
						<div class="event-box">
							<event-card
								:event="event"
								:date="day.date"
								:firstDay="firstDay"
								v-for="event in day.events"
								:key="event"
								v-show="event.cellIndex <= eventLimit"
								@click="eventClick"
							>
								<template slot-scope="p">
									<slot name="event-card" :event="p.event"></slot>
								</template>
							</event-card>
							<p
								v-if="day.events.length > eventLimit"
								class="more-link"
								@click.stop="selectThisDay(day, $event)"
							>
								+
								{{
								day.events[day.events.length - 1]
								.cellIndex - eventLimit
								}}
								more
							</p>
						</div>
					</div>
				</div>
			</div>-->

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
	data() {
		return {};
	},
	mounted() {
		this.getCalendar();
	},
	computed: {
		...mapGetters(["currentMonth", "firstDay", "locale"]),
		currentDates() {
			return this.getCalendar();
		},
	},
	methods: {
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
						//events: this.slotEvents(monthViewStartDate),
					});
					monthViewStartDate.add(1, "day");
				}
				calendar.push(week);
			}
			console.log(calendar);
			return calendar;
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
			}
		}
	}
}
</style>
