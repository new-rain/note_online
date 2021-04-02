<template>
  <div class="forget">
    <Menu></Menu>
    <el-row>
      <el-col :span="6">
        <div class="blank_100">&nbsp;</div>
      </el-col>
      <el-col :span="12">
        <div class="forget_content mgt_20">
          <div class="blank_100"></div>
          <div class="forget_update">
            <h1>找回密码</h1>
            <el-input v-model="no" placeholder="请输入账号"></el-input>
            <br><br>
            <el-input v-model="email" placeholder="请输入邮箱" @blur="checkmail()"></el-input>
            <br><br>
            <el-input v-model="code" class="forget_code" placeholder="请输入验证码"></el-input>
            <el-button class="forget_getcode" type="primary" @click="getcode()" :disabled="canGetCode">获取验证码</el-button>
            <br><br>
            <el-input v-model="password" type="password" placeholder="请输入密码"></el-input>
            <br><br>
            <el-input v-model="pwd" type="password" placeholder="请确认密码"></el-input>
            <br><br>
            <el-button type="primary" class="forget_update_password" @click="check()">修改密码</el-button>
            <div class="blank_80"></div>
            <a href="/updatePwdByPwd">通过旧密码修改</a>
          </div>
        </div>
      </el-col>
      <el-col :span="6">
        <AboutMe></AboutMe>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Menu from "@/components/Menu";
import AboutMe from "@/components/AboutMe";
import '@/assets/css/notebook_online.css'
import noteOnline from "@/assets/js/noteOnline";

export default {
  name: "Forget",
  data() {
    return {
      no: '',
      email: '',
      code: '',
      password: '',
      pwd: '',
      flag: false,
      canGetCode: false,
    }
  },
  methods: {
    checkmail() {
      let myreg = noteOnline.mailFormat();
      if (!myreg.test(this.email)) {
        this.$message({
          showClose: true,
          message: '请输入正确的邮箱',
          type: 'warning'
        });
        document.getElementById("email").focus();
      }
    },
    getcode() {
      if (!this.canGetCode) {
        this.canGetCode = true;
        setTimeout(() => {
          this.canGetCode = false
        }, 5000)
        let email = this.email;
        if (email != "") {
          this.$axios.get("/getcode?email=" + email).then(res => {
            if (res.data) {
              this.$message({
                showClose: true,
                message: '验证码以发到您的用邮箱，请前往查看'
              });
            } else {
              this.$message({
                showClose: true,
                message: '获取失败，请重新尝试',
                type: 'error'
              });
            }
          }), error => {
            this.$message({
              showClose: true,
              message: '获取出错，请反馈错误信息',
              type: 'error'
            });
          }
        } else {
          this.$message({
            showClose: true,
            message: '请输入邮箱',
          });
          this.canGetCode = true;
        }
      } else {
        this.$message({
          showClose: true,
          message: '请稍后再次获取',
        });
      }
    },
    check() {
      let password = this.password;
      let pwd = this.pwd;
      let no = this.no;
      let email = this.email;
      if (no != "", password != "" && pwd != "" && email != "") {
        if (password != pwd) {
          this.$message({
            showClose: true,
            message: '两次密码不一致',
            type: 'warning'
          });
        } else {
          let code = this.code;
          let params = new URLSearchParams();
          params.append("email", email);
          params.append("code", code);
          this.$axios.post("/checkcode", params).then(res => {
            let result = res.data;
            if (result) {
              this.flag = true;
              this.update(no, password);
            } else {
              this.$message({
                showClose: true,
                message: '验证码错误',
                type: 'warning'
              });
            }
          }).catch(error => {
            this.$message({
              showClose: true,
              message: '系统出错，请反馈',
              type: 'error'
            });
          })
        }
      } else {
        this.$message({
          showClose: true,
          message: '请填写完整信息',
          type: 'warning'
        });
      }

    },
    update(no, password) {
      let flag = this.flag;
      if (flag) {
        let params = new URLSearchParams();
        params.append("no", no);
        params.append("password", password);
        this.$axios.put("updatepassword", params).then(res => {
          if (res.data) {
            this.$message({
              showClose: true,
              message: '修改成功，即将返回登录',
              type: 'success'
            });
            window.location = "/login"
          } else {
            this.$message({
              showClose: true,
              message: '修改失败，请重新尝试',
              type: 'error'
            });
          }
        }).catch(error => {
          this.$message({
            showClose: true,
            message: '系统出错，请反馈',
            type: 'error'
          });
        })
      } else {
        this.$message({
          showClose: true,
          message: '验证码错误',
          type: 'warning'
        });
      }
    }
  },
  components: {
    AboutMe,
    Menu
  }
}
</script>

<style scoped>
.forget {
  height: 753px;
  background-image: linear-gradient(-225deg, #5D9FFF 0%, #B8DCFF 48%, #6BBBFF 100%);
}

.forget_update {
  width: 60%;
  margin-left: 20%;
}

.forget_code {
  float: left;
  width: 75%;
}

.forget_getcode {
  width: 25%;
}

.forget_update_password {
  width: 100%;
  margin-top: 20px;
}

.forget_content {
  background-color: #fff;
  height: 660px;
  border-radius: 10px;
  box-shadow: 10px 10px 5px #888888;
}

.forget_update {
  width: 60%;
  margin-left: 20%;
}

.forget_code {
  float: left;
  width: 75%;
}

.forget_getcode {
  width: 25%;
}

.forget_update_password {
  width: 100%;
  margin-top: 20px;
}

.forget a{
  color: blue;
}
</style>