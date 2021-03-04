import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        user: JSON.parse(localStorage.getItem('user')),
        userNo: localStorage.getItem('userNo'),
        admin: localStorage.getItem('admin'),
    },
    mutations: {
        SET_USER: (state, user) => {
            state.user = user;
            localStorage.setItem("user", JSON.stringify(user));
        },
        SET_USERNO: (state, userNo) => {
            state.userNo = userNo;
            localStorage.setItem("userNo", userNo);
        },
        SET_ADMIN: (state, admin) => {
            state.admin = admin;
            localStorage.setItem("admin", admin);
        },
        REMOVE_STATE: (state) => {
            state.user = {};
            state.userNo = '';
            localStorage.setItem("userNo", '');
            localStorage.setItem("user", JSON.stringify(''));
        },
        REMOVE_ADMIN: (state) => {
            state.admin = '';
            localStorage.setItem("admin", '');
        },
    },
    getters: {
        getUser: state => {
            return state.user;
        },
        getUserNo: state => {
            return state.userNo;
        },
        getAdmin: state => {
            return state.admin;
        },
        exit: state => {
            return state;
        }
    },
    actions: {},
    modules: {}
})
