import request from "@/utils/request";

export function createHabitTracker(data) {
    return request({
        url: "/v1/habit",
        method: "post",
        data,
    });
}
export function getHabitTracker(data) {
    return request({
        url: `/v1/habit/${data}`,
        method: "get",
    });
}
export function getTodayHT(data) {
    return request({
        url: `/v1/habit/dohabit/${data}`,
        method: "get",
    });
}
export function getMyHabitTracker(data) {
    return request({
        url: `/v1/habit/in/${data}`,
        method: "get",
    });
}
export function getMyHabitTrackerRecord(data) {
    return request({
        url: `/v1/habit/dohabit/${data}`,
        method: "get",
    });
}
export function joinHabitTracker(data) {
    return request({
        url: `/v1/habit/join/${data}`,
        method: "post",
    });
}
export function leaveHabitTracker(data) {
    return request({
        url: `/v1/habit/leave/${data}`,
        method: "delete",
    });
}
export function deleteHabitTracker(data) {
    return request({
        url: `/v1/habit/${data}`,
        method: "delete",
    });
}
export function doHabitTracker(data) {
    return request({
        url: "/v1/habit/dohabit/",
        method: "post",
        data
    });
}
export function deleteDoHabitTracker(data) {
    return request({
        url: `/v1/habit/dohabit/${data}`,
        method: "delete",
    });
}