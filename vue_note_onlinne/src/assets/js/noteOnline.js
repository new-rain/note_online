import store from '../../store'
import Axios from "axios";
import ElementUI from 'element-ui';

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
    checkState(no) {
        Axios.get("/getUserState", {
            params: {
                no: no
            }
        }).then(res => {
            if (!res.data) {
                alert("账号状态异常，即将进入账号状态查看页面")
                window.location.href = "/checkState/" + no;
            }
        }).catch(error => {
            console.log(error)
        })
    },
}