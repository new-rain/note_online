<template>
  <div class="adminLogin">
    <div class="login">
      <h1>管理员登录</h1>
      <div class="mgt_50">
        <input class="loginInput" placeholder="请输入管理员账号" v-model="adminNo"></input>
        <input class="loginInput mgt_30" type="password" placeholder="请输入管理员密码" v-model="adminPassword"></input>
        <el-button class="mgt_30 wd_60" type="primary" plain @click="login">管理员登录</el-button>
      </div>
      <div class="goRegister">
        <a href="/admin/adminRegister">管理员注册</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "AdminLogin",
  data() {
    return {
      adminNo: '',
      adminPassword: ''
    }
  },
  methods: {
    login() {
      this.$axios.get("adminLogin", {
        params: {
          id: this.adminNo,
          password: this.adminPassword
        }
      }).then(res => {
        if (res.data != null && res.data != '') {
          this.$store.commit("SET_ADMIN", res.data);
          this.$store.commit("SET_ADMINNO", res.data.id);
          this.getAdminHead(res.data.headUrl);
          window.location.href = '/admin';
        } else {
          this.$message.error("账号或密码错误，请重新尝试")
        }

      }).catch(error => {
        console.log(error);
      })
    },
    getAdminHead(headUrl) {
      this.$axios({
        method: 'get',
        url: '/getAdminHead?headUrl=' + headUrl,
        responseType: 'arraybuffer'
      }).then(response => {
        this.head = 'data:image/jpeg;base64,' + this.arrayBufferToBase64(response.data)
        this.$store.commit("SET_ADMINHEAD", this.head);
      })
    },
    arrayBufferToBase64(buffer) {
      let binary = ''
      let bytes = new Uint8Array(buffer)
      let len = bytes.byteLength
      for (let i = 0; i < len; i++) {
        binary += String.fromCharCode(bytes[i])
      }
      return window.btoa(binary)
    },
  }
}
</script>

<style scoped>
.adminLogin {
  background-color: rgba(32, 140, 255, 0.1);
  position: absolute;
  width: 100%;
  height: 100%;
}

.login {
  position: absolute;
  width: 36%;
  height: 350px;
  left: 32%;
  top: 150px;
  padding: 20px;
  background-color: rgba(32, 140, 255, 0.15);
  border-radius: 20px;
}

.loginInput {
  background: none;
  border: none;
  border-bottom: 1px solid #000000;
  width: 60%;
}

.goRegister {
  margin-top: 80px;
  text-align: right;
}

.goRegister a {

  color: blue;
}
</style>