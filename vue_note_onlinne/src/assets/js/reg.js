const mail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
const mobile = /^[1][3-9][\d]{9}$/;

export default {
    regMail(str) {
        mail.test(str);
    },

    regMobile(str) {
        mobile.test(str);
    },

}