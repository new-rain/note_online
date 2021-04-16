<template>
  <div class="login">
    <div class="login_back">
      <h1 class="ta_c">登 录</h1>
      <div class="mgt_50">
        <input class="loginInput" placeholder="请输入账号" v-model="no"></input>
        <input class="loginInput mgt_30 mgb_30" type="password" placeholder="请输入密码" v-model="password"></input>
        <button @click="login" class="mgt_30">登 录</button>
      </div>
      <div class="link">
        <a href="/register">没有账号?那就注册一个吧</a>
        <a href="/forget" class="forget">忘记密码</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      no: '',
      password: '',
      times: ''
    };
  },
  methods: {
    login() {
      let no = this.no;
      let password = this.password;
      if (no == '' || password == '') {
        this.$message.warning("请输入完整登录信息")
      } else {
        let params = new URLSearchParams();
        params.append("no", no);
        params.append("password", password);
        this.$axios.post('/login', params).then(res => {
          if (res.data != null) {
            this.checkState();
            if (this.times > 0) {
              this.$store.commit("SET_USER", res.data);
              this.$store.commit("SET_USERNO", res.data.no);
              this.getUserHead(res.data.headUrl);
              window.location.href = "/";
            } else {
              window.location.href = "/userAppeal/" + this.no;
            }
          } else {
            this.$message({
              showClose: true,
              message: '账号或密码错误，请重新输入',
              type: 'error'
            });
          }
        }).catch(error => {
          this.$message({
            showClose: true,
            message: '系统出错，请反馈',
            type: 'error'
          });
          console.log(error)
        })
      }
    },
    getUserHead(headUrl) {
      this.$axios({
        method: 'get',
        url: '/getUserHead?headUrl=' + headUrl,
        responseType: 'arraybuffer' // 最为关键
      }).then(response => {
        this.head = 'data:image/jpeg;base64,' + this.arrayBufferToBase64(response.data)
        this.$store.commit("SET_USERHEAD", this.head);
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
    checkState() {
      this.$axios.get("/checkState", {
        params: {
          no: this.no
        }
      }).then(res => {
        this.times = res.data;
      }).catch(error => [
        console.log(error)
      ])
    },
  },
}
</script>

<style scoped>
.login {
  height: 753px;
  background-image: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%);
}

.login_back {
  position: absolute;
  width: 36%;
  height: 360px;
  background: rgba(255, 255, 255, 0.6);
  left: 30%;
  top: 20%;
  padding: 20px;
  text-align: left;
  border-radius: 20px;
}

.login_back button {
  border: none;
  margin-top: 20px;
  width: 50%;
  height: 40px;
  margin-left: 25%;
  background-image: linear-gradient(to right, #00dbde 0%, #1994f8 100%);
  border-radius: 20px;
  color: #ffffff;
  font-size: 18px;
}


.link {
  margin-top: 60px;
}

.forget {
  margin-left: 280px;
}

.loginInput {
  background: none;
  border: none;
  border-bottom: 1px solid #000000;
  width: 60%;
  margin-left: 20%;
}

.login button {
  outline: none;
}
</style>