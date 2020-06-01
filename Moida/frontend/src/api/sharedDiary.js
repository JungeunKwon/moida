import request from "@/utils/request";

export function createSharedDiary(data) {
    return request({
        url: "/v1/group",
        method: "post",
        data,
    });
}

export function getSharedDiary() {
    return request({
        url: "/v1/group/All",
        method: "get",
    });
}

// export function getSharedDiaryDetail(data) {
//     return request({
//         url: "/v1/group"
//     })
// }