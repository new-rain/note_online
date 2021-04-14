<template>
  <div class="adminRegister">
    <div class="register">
      <h2>管 理 员 注 册</h2>
      <input v-model="name" placeholder="请输入名称"/>
      <input v-model="password" type="password" placeholder="请输入密码"/>
      <input v-model="pwd" type="password" placeholder="请确认密码"/>
      <input v-model="mobile" placeholder="请输入手机号"/>
      <input v-model="email" type="email" placeholder="请输入邮箱"/>
      <input v-model="rnum" placeholder="请输入注册码"/>
      <el-button type="primary" plain class="mgt_20 wd_60" @click="check">注 册</el-button><br/>
      <b><a href="/admin/login" class="mgt_10">登 录</a></b>
    </div>
  </div>
</template>

<script>
export default {
  name: "AdminRegister",
  data() {
    return {
      name: '',
      password: '',
      pwd: '',
      mobile: '',
      email: '',
      rnum: ''
    }
  },
  methods: {
    check() {
      if (this.name != "" && this.password != "" && this.mobile != "" && this.email != "" && this.rnum != "" && this.pwd != "") {
        this.register();
      } else {
        this.$message.error("请完善信息")
      }
    },
    register() {
      if (this.password == this.pwd) {
        let params = new URLSearchParams();
        params.append("name", this.name);
        params.append("password", this.password);
        params.append("mobile", this.mobile);
        params.append("email", this.email);
        params.append("rnum", this.rnum);
        this.$axios.post("/adminRegister", params).then(res => {
          let result = res.data;
          if (result == 0) {
            this.$message.error("注册失败，注册码不存在，请检查是否正确");
          } else if (result == 1) {
            this.$message.error("注册失败，注册码已被使用");
          } else if (result == 2) {
            this.$message.error("注册失败，请重试");
          } else {
            this.name="";
            this.password="";
            this.mobile="";
            this.email="";
            this.rnum="";
            this.pwd="";
            this.$message({
              type: 'success',
              showClose: true,
              message: '注册成功，账号为' + result
            })
          }
        }).catch(error => {

        })
      } else {
        this.$message.error("两次密码不一致")
      }
    }
  },
}
</script>

<style scoped>
.adminRegister {
  background-color: rgba(32, 140, 255, 0.15);
  position: absolute;
  width: 100%;
  height: 100%;
}

.adminRegister .register {
  padding: 50px;
  width: 50%;
  height: 450px;
  background: rgba(32, 140, 255, 0.2);
  margin: 100px auto;
  border-radius: 20px;
}

.adminRegister .register h2 {
  margin-bottom: 30px;
}

.adminRegister .register a {
  color: blue;
  margin-left: 95%;
}

.adminRegister input {
  display: block;
  width: 60%;
  height: 40px;
  background: none;
  border: none;
  outline: none;
  margin: 15px auto;
  border: 1px solid #4facfe;
  border-radius: 10px;
  padding-left: 30px;
  color: black;
  font-weight: bold;
}
</style>