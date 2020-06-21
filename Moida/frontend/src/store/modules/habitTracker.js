import {
    createHabitTracker,
    getHabitTracker,
    getTodayHT,
    getMyHabitTracker,
    getMyHabitTrackerRecord,
    joinHabitTracker,
    leaveHabitTracker,
    deleteHabitTracker,
    doHabitTracker,
    deleteDoHabitTracker
} from "@/api/habitTracker";

const state = {
    sharedDiaryId: 0,
};
const mutations = {
    SET_SD_ID: function (state, id) {
        state.sharedDiaryId = id;
    }
};

const actions = {
    createHabitTracker({
        commit
    }, data) {
        console.log(data);
        return new Promise((resolve, reject) => {
            createHabitTracker(data)
                .then(response => {
                    resolve(response);
                })
                .catch(error => {
                    reject(error);
                });
        });
    },
    getHabitTracker({
        commit
    }, id) {
        return new Promise((resolve, reject) => {
            getHabitTracker(id)
                .then(response => {
                    resolve(response);
                })
                .catch(error => {
                    reject(error);
                });
        });
    },
    getMyHabitTracker({
        commit
    }, id) {
        return new Promise((resolve, reject) => {
            getMyHabitTracker(id)
                .then(response => {
                    resolve(response);
                })
                .catch(error => {
                    reject(error);
                });
        });
    },
    getTodayHT({
        commit
    }, id) {
        return new Promise((resolve, reject) => {
            getTodayHT(id)
                .then(response => {
                    resolve(response);
                })
                .catch(error => {
                    reject(error);
                });
        });
    },
    getMyHabitTrackerRecord({
        commit
    }, id) {
        return new Promise((resolve, reject) => {
            getMyHabitTrackerRecord(id)
                .then(response => {
                    resolve(response);
                })
                .catch(error => {
                    reject(error);
                });
        });
    },
    joinHabitTracker({
        commit
    }, id) {
        return new Promise((resolve, reject) => {
            joinHabitTracker(id)
                .then(response => {
                    resolve(response);
                })
                .catch(error => {
                    reject(error);
                });
        });
    },
    deleteHabitTracker({
        commit
    }, id) {
        return new Promise((resolve, reject) => {
            deleteHabitTracker(id)
                .then(response => {
                    resolve(response);
                })
                .catch(error => {
                    reject(error);
                });
        });
    },
    leaveHabitTracker({
        commit
    }, id) {
        return new Promise((resolve, reject) => {
            leaveHabitTracker(id)
                .then(response => {
                    resolve(response);
                })
                .catch(error => {
                    reject(error);
                });
        });
    },
    doHabitTracker({
        commit
    }, data) {
        return new Promise((resolve, reject) => {
            doHabitTracker(data)
                .then(response => {
                    resolve(response);
                })
                .catch(error => {
                    reject(error);
                });
        });
    },
    deleteDoHabitTracker({
        commit
    }, data) {
        return new Promise((resolve, reject) => {
            deleteDoHabitTracker(data)
                .then(response => {
                    resolve(response);
                })
                .catch(error => {
                    reject(error);
                });
        });
    },
};

export default {
    namespaced: true,
    state,
    mutations,
    actions,
};