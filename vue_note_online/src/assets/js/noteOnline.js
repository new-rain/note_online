import store from '../../store'

export default {
    checkUser() {
        const user = store.getters.getUser;
        if (user == null) {
            alert("状态错误，请登录");
            window.location.href = '/login'
        }
    },
    mailFormat() {
        return /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    },
}