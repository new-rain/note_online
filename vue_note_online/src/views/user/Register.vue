<template>
  <div class="register">
    <div class="blank_50"></div>
    <el-row>
      <el-col :span="2">
        <div class="blank_100">&nbsp;</div>
      </el-col>
      <el-col :span="10" class="left">
        <h2>在线笔记系统</h2>
        <p>一个在线笔记平台</p>
        <p>想记就记，想取就取，抓住灵感</p>
      </el-col>
      <el-col :span="10" class="right">
        <div></div>
        <div class="reg">
          <h1 class="mgb_20"><b>欢迎注册</b></h1>
          <el-input class="mgt_20" v-model="username" placeholder="请填写用户名"></el-input>
          <el-input class="mgt_20" v-model="email" id="email" placeholder="请输入邮箱" @blur="checkmail()"></el-input>
          <div class="mgt_20">
            <el-input class="checkcode" v-model="idecode" placeholder="请输入验证码"></el-input>
            <el-button class="getcode" type="primary" @click="getcode()" :disabled="canGetCode">获取验证码</el-button>
          </div>
          <el-input class="mgt_20" type="password" v-model="password" placeholder="请输入密码"></el-input>
          <el-input class="mgt_20" type="password" v-model="pwd" placeholder="请确认密码"></el-input>
          <div class="ta_l">
            <el-button class="mgt_20 wd_30" type="primary" plain @click="choose=true">选择头像</el-button>
          </div>
          <div class="ta_r">
            <a href="gologin" class="wd_50">已有账号？点击登录</a><br><br>
          </div>
          <el-checkbox v-model="agree"></el-checkbox>
          我已阅读并同意<span @click="use=true" style="color: #1994f8">使用条款</span>
          <br><br>
          <el-button type="primary" class="submit" @click="submit()">注册</el-button>
        </div>
      </el-col>
      <el-col :span="2">
        <div class="grid-content bg-purple">&nbsp;</div>
      </el-col>
    </el-row>
    <el-dialog
        title="使用条款"
        :visible.sync="use"
        width="60%"
        :before-close="handleClose">
      <span>这是一段信息，这是使用条款，这是一段信息，这是使用条款，这是一段信息，这是使用条款这是一段信息，这是使用条款，这是一段信息，这是使用条款，这是一段信息，这是使用条款</span>
      <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="agreeClause">阅读并同意</el-button>
        </span>
    </el-dialog>
    <div v-if="choose" class="chooseHeader">
      <h2 class="mgt_20 mgb_30">请选择头像</h2>
      <el-row class="img">
        <el-col :span="2">
          <div class="blank_30"></div>
        </el-col>
        <el-col :span="20">
          <div class="cov">
            <img src="http://localhost:8888/img/user_head/10001.jpeg"/>
            <el-radio v-model="cover" label="http://localhost:8888/img/user_head/10001.jpeg">
            </el-radio>
          </div>
          <div class="cov">
            <img src="http://localhost:8888/img/user_head/default_head_img.jpg"/>
            <el-radio v-model="cover" label="http://localhost:8888/img/user_head/default_head_img.jpg"></el-radio>
          </div>
          <div class="cov">
            <img src="http://localhost:8888/  img/user_head/10002.jpg"/>
            <el-radio v-model="cover" label="http://localhost:8888/img/user_head/10002.jpg"></el-radio>
          </div>
          <div class="cov">
            <img src="http://localhost:8888/img/user_head/10003.jpg"/>
            <el-radio v-model="cover" label="http://localhost:8888/img/user_head/10003.jpg"></el-radio>
          </div>
          <div class="cov">
            <img src="http://localhost:8888/img/user_head/10004.jpg"/>
            <el-radio v-model="cover" label="http://localhost:8888/img/user_head/10004.jpg"></el-radio>
          </div>
          <div class="cov">
            <img src="http://localhost:8888/img/user_head/10005.jpg"/>
            <el-radio v-model="cover" label="http://localhost:8888/img/user_head/10005.jpg"></el-radio>
          </div>
          <div class="cov">
            <img src="http://localhost:8888/img/user_head/10006.jpg"/>
            <el-radio v-model="cover" label="http://localhost:8888/img/user_head/10006.jpg"></el-radio>
          </div>
          <div class="cov">
            <img src="http://localhost:8888/img/user_head/10007.jpg"/>
            <el-radio v-model="cover" label="http://localhost:8888/img/user_head/10007.jpg"></el-radio>
          </div>
          <div class="cov">
            <img src="http://localhost:8888/img/user_head/10008.jpg"/>
            <el-radio v-model="cover" label="http://localhost:8888/img/user_head/10008.jpg"></el-radio>
          </div>
        </el-col>
        <el-col :span="2"></el-col>
      </el-row>
      <el-button class="wd_49" @click="choose=false">取消</el-button>
      <el-button class="wd_49" type="primary" @click="chooseHeader()">选择</el-button>
    </div>
  </div>
</template>

<script>
import '@/assets/css/notebook_online.css'

export default {
  name: "Register",
  data() {
    return {
      username: "",
      password: "",
      pwd: "",
      email: "",
      idecode: "",
      agree: false,
      flag: false,
      use: false,
      canGetCode: false,
      cover: '',
      choose: false,
      headUrl: ''
    }
  },
  methods: {
    checkmail() {
      const myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
      if (!myreg.test(this.email)) {
        this.$message({
          showClose: true,
          message: '请输入正确的邮箱格式',
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
        }, 60000)
        const email = this.email;
        if (email !== "") {
          this.$axios.get("getcode?email=" + email).then(res => {
            if (res.data) {
              this.$message({
                showClose: true,
                message: '验证码已发送至您的邮箱，请查收',
                type: 'success'
              });
            } else {
              this.$message({
                showClose: true,
                message: '获取失败，请重新尝试',
                type: 'warning'
              });
            }
          }).catch(error => {
            this.$message({
              showClose: true,
              message: '系统出错，请重新尝试',
              type: 'error'
            });
          })
        } else {
          this.$message({
            showClose: true,
            message: '系请输入邮箱',
          });
        }
      } else {
        this.$message({
          showClose: true,
          message: '系应稍后再次获取'
        });
      }
    },
    submit() {
      if (!this.agree) {
        this.$message({
          showClose: true,
          message: '请先勾选同意使用条款进行注册',
        });
      } else {
        const password = this.password;
        const pwd = this.pwd;
        const username = this.username;
        const email = this.email;
        if (username != null && password != null && pwd != null && email != null) {
          if (password != pwd) {
            this.$message({
              showClose: true,
              message: '两次密码不一致',
              type: 'warning'
            });
          } else {
            const code = this.idecode;
            const params = new URLSearchParams();
            params.append("email", email);
            params.append("code", code);
            const that = this;
            this.$axios.post("/checkcode", params).then(res => {
              const result = res.data;
              if (result) {
                that.flag = true;
                that.register(username, password, email);
              }
            }).catch(error => {
              this.$message({
                showClose: true,
                message: '系统出错，请重新尝试!!!',
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
      }
    },
    register(username, password, email) {
      const flag = this.flag;
      if (flag) {
        const params = new URLSearchParams();
        params.append("username", username);
        params.append("password", password);
        params.append("email", email);
        params.append("headerUrl", this.headUrl);
        const that = this;
        this.$axios.post("register", params).then(res => {
          const result = res.data;
          if (result[0] === 0) {
            const no = result[1];
            alert('注册成功，您的账号是：' + no)
            window.location.href = "/login"
          } else if (result[0] === 1) {
            const no = result[1];
            this.$message({
              showClose: true,
              message: '该邮箱已注册',
              type: 'warning'
            });
            window.location.href = 'login'
          }
        }).catch(error => {
          this.$message({
            showClose: true,
            message: '系统出错，请反馈',
            type: 'error'
          });
        })
      } else {
        this.canGetCode = true;
        this.$message({
          showClose: true,
          message: '验证码错误',
          type: 'warning'
        });
      }
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
    },
    agreeClause() {
      this.use = false;
      this.agree = true;
    },
    chooseHeader() {
      this.headUrl = this.cover;
      this.choose = false;
    }
  },
}
</script>

<style scoped>
.register {
  background-image: linear-gradient(to top, #accbee 0%, #e7f0fd 100%);
  height: 753px;
}


.left {
  background-color: cornflowerblue;
  height: 650px;
  box-shadow: 10px 8px 10px #aaa;
}

.left h2 {
  font-size: 40px;
  margin-top: 20px;
  font-family: 'Times New Roman', Times, serif;
}

.right {
  background-color: #fff;
  height: 650px;
  box-shadow: 10px 8px 10px #aaa;
}

.reg {
  margin-top: 50px;
  margin-left: 80px;
  margin-right: 80px;
}

.reg a {
  color: blue;
}

.register .checkcode {
  float: left;
  width: 75%;
}

.getcode {
  width: 24%;
}

.submit {
  width: 60%;
}

.chooseHeader {
  width: 60%;
  height: 600px;
  border-radius: 20px;
  position: fixed;
  top: 100px;
  left: 20%;
  background-color: #fff;
  box-shadow: 0 0 10px #4facfe;
}

.cov {
  width: 120px;
  height: 180px;
  float: left;
  margin-right: 25px;
  margin-bottom: 20px;
}

.chooseHeader img {
  width: 120px;
  height: 120px;
  border-radius: 50%;
}

</style>