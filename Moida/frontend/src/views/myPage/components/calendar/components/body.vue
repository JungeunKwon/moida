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
							/>
							<p
								v-if="day.events.length > eventLimit"
								class="more-link"
								@click.stop="selectThisDay(day, $event)"
							>
								{{ day.events.length - eventLimit }}
								개 더있떠염
							</p>
						</div>
					</div>
				</div>
			</div>

			<!-- full events when click show more -->
			<div
				class="more-events"
				id="more-events"
				v-show="showMore"
				:style="{ left: morePos.left + 'px', top: morePos.top + 'px' }"
			>
				<div class="more-header">
					<span class="title">{{ moreTitle(selectDay.date) }}</span>
					<span class="close" @click.stop="showMore = false">x</span>
				</div>
				<div class="more-body">
					<event-card
						:event="event"
						:date="selectDay.date"
						v-for="(event, idx) in selectDay.events"
						:key="idx"
					/>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import moment from "moment";
import { mapGetters, mapActions } from "vuex";
import dateFunc from "../dateFunc";
export default {
	components: {
		eventCard: () => import("./eventCard.vue"),
	},
	data() {
		return {
			eventLimit: 3,
			events: [],
			showMore: false,
			morePos: {
				top: 0,
				left: 0,
			},
			selectDay: {},
		};
	},
	props: {
		nickname: "",
	},
	created() {
		window.addEventListener("click", this.outOfMore);
	},
	beforeDestroy() {
		window.removeEventListener("click", this.outOfMore);
	},
	mounted() {
		this.getDiary(this.$route.params.nickname)
			.then(response => {
				this.events = response;
			})
			.catch(error => console.log(error.response));
	},
	computed: {
		...mapGetters(["currentMonth", "firstDay", "locale"]),
		currentDates() {
			return this.getCalendar();
		},
	},
	methods: {
		...mapActions("calendar", ["getDiary"]),
		outOfMore(e) {
			if (document.getElementById("more-events").contains(e.target)) {
			} else {
				this.showMore = false;
			}
		},
		moreTitle(date) {
			return moment(date).format("MM월 DD일");
		},
		selectThisDay(day, event) {
			this.selectDay = day;
			this.showMore = true;
			this.morePos = this.computePos(event.target);
			this.morePos.top -= 100;
			let events = day.events.filter(item => {
				return item.isShow == true;
			});
		},
		computePos(target) {
			let eventRect = target.getBoundingClientRect();
			let pageRect = this.$refs.dates.getBoundingClientRect();
			return {
				left: eventRect.left - pageRect.left,
				top: eventRect.top + eventRect.height - pageRect.top,
			};
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
						text-align: center;
						padding-left: 8px;
						padding-right: 2px;
						color: rgba(0, 0, 0, 0.38);
						font-size: 14px;
					}
					.event-item {
						cursor: pointer;
						font-size: 12px;
						margin-bottom: 2px;
						color: rgba(2, 2, 2, 0.87);
						padding: 0 0 0 4px;
						height: 18px;
						line-height: 18px;
						white-space: nowrap;
						overflow: hidden;
						text-overflow: ellipsis;

						&.private {
							background-color: #fbd6c6;
						}
						&.group {
							background-color: #f7ebc3;
						}
						&.public {
							background-color: #c7ceea;
						}
						&.friend {
							background-color: #cde4db;
						}
					}
				}
			}
		}
		.more-events {
			position: absolute;
			width: 150px;
			z-index: 2;
			border: 1px solid #eee;
			box-shadow: 0 2px 6px rgba(0, 0, 0, 0.15);
			.more-header {
				background-color: rgb(250, 223, 153);
				padding: 5px;
				display: flex;
				align-items: center;
				font-size: 14px;
				.title {
					flex: 1;
				}
				.close {
					margin-right: 2px;
					cursor: pointer;
					font-size: 20px;
				}
			}
			.more-body {
				min-height: 146px;
				background-color: white;
				.event-item {
					cursor: pointer;
					font-size: 18px;
					margin-bottom: 2px;
					color: rgba(2, 2, 2, 0.87);
					padding: 0 0 0 4px;
					height: 25px;
					line-height: 25px;
					white-space: nowrap;
					overflow: hidden;
					text-overflow: ellipsis;

					&.private {
						background-color: #fbd6c6;
					}
					&.group {
						background-color: #f7ebc3;
					}
					&.public {
						background-color: #c7ceea;
					}
					&.friend {
						background-color: #cde4db;
					}
				}
			}
		}
	}
}
</style>
