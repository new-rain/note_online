import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        user: JSON.parse(localStorage.getItem('user')),
        userNo: localStorage.getItem('userNo'),
        userHead: localStorage.getItem('userHead'),
        adminHead: localStorage.getItem('adminHead'),
        admin: JSON.parse(localStorage.getItem('admin')),
        adminNo: localStorage.getItem('adminNo'),
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
        SET_USERHEAD: (state, userHead) => {
            state.userHead = userHead;
            localStorage.setItem("userHead", userHead);
        },
        SET_ADMINHEAD: (state, adminHead) => {
            state.adminHead = adminHead;
            localStorage.setItem("adminHead", adminHead);
        },
        SET_ADMIN: (state, admin) => {
            state.admin = admin;
            localStorage.setItem("admin", JSON.stringify(admin));
        },
        SET_ADMINNO: (state, adminNo) => {
            state.adminNo = adminNo;
            localStorage.setItem("adminNo", adminNo);
        },
        REMOVE_STATE: (state) => {
            state.user = {};
            state.userNo = '';
            state.userHead = '';
            localStorage.setItem("userHead", '');
            localStorage.setItem("userNo", '');
            localStorage.setItem("user", JSON.stringify(''));
        },
        REMOVE_ADMIN: (state) => {
            state.admin = '';
            localStorage.setItem("admin", JSON.stringify(''));
        },
        REMOVE_USERHEAD: (state) => {
            state.userHead = '';
            localStorage.setItem("userHead", '');
        },
        REMOVE_ADMINHEAD: (state) => {
            state.adminHead = '';
            localStorage.setItem("adminHead", '');
        },
    },
    getters: {
        getUser: state => {
            return state.user;
        },
        getUserNo: state => {
            return state.userNo;
        },
        getUserHead: state => {
            return state.userHead;
        },
        getAdminHead: state => {
            return state.adminHead;
        },
        getAdmin: state => {
            return state.admin;
        },
        getAdminNo: state => {
            return state.adminNo;
        },
        exit: state => {
            return state;
        }
    },
    actions: {},
    modules: {}
})
