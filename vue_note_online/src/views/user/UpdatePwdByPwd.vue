<template>
  <div class="updatePwdByPwd">
    <Menu></Menu>
    <div class="blank_100"></div>
    <h1>修改密码</h1>
    <el-row>
      <el-col :span="4">
        <div class="blank_50"></div>
      </el-col>
      <el-col :span="16">
        <div class="updateContent">
          <el-steps class="ta_l" :active="active" finish-status="success">
            <el-step title="确认旧密码"></el-step>
            <el-step title="输入新密码"></el-step>
            <el-step title="修改完成"></el-step>
          </el-steps>
          <div v-if="active==0">
            <div class="blank_100"></div>
            <el-input v-model="oldPassword" placeholder="请输入旧密码"></el-input>
            <div class="blank_100"></div>
            <el-button type="primary" class="wd_100" @click="checkOld">验证</el-button>
          </div>
          <div v-else-if="active==1">
            <div class="blank_100"></div>
            <el-input v-model="newPassword" placeholder="请输入新密码"></el-input>
            <div class="blank_100"></div>
            <el-button type="primary" class="wd_100" @click="next">确认修改</el-button>
          </div>
          <div v-else-if="active==2">
            <div class="blank_100"></div>
            <el-input v-model="password" placeholder="请确认新密码"></el-input>
            <div class="blank_100"></div>
            <el-button class="wd_100 mgt_20" @click="update">完成</el-button>
          </div>
          <div v-else-if="active==3">
            <img class="ok mgt_50" :src="ok"/>
            <p>修改成功，请重新<a href="/login">登录</a></p>
          </div>
        </div>
        <div class="blank_100"></div>
        <a href="/forget">通过邮箱验证</a>
      </el-col>
      <el-col :span="4">
        <AboutMe></AboutMe>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Menu from "@/components/Menu";
import AboutMe from "@/components/AboutMe";
import ok from "@/assets/images/ok.png";
import noteOnline from "@/assets/js/noteOnline";

export default {
  name: "UpdatePwdByPwd",
  data() {
    return {
      oldPassword: '',
      newPassword: '',
      password: '',
      user: '',
      active: 0,
      ok: ok
    };
  },
  methods: {
    getMsg() {
      this.user = this.$store.getters.getUser;
      if (this.user == '') {
        this.$message({
          showClose: true,
          message: '请先登录',
          type: 'error'
        });
        window.location.href = "/login";
      }
    },
    checkOld() {
      const that = this;
      this.$axios.get("/checkOldPassword", {
        params: {
          no: that.user.no,
          password: that.oldPassword
        }
      }).then(res => {
        if (res.data) {
          that.active++;
        } else {
          this.$message({
            showClose: true,
            message: '密码错误',
            type: 'error'
          });
          that.oldPassword = '';
        }
      }).catch(error => {
        this.$message({
          showClose: true,
          message: '系统出错，请反馈',
          type: 'error'
        });
      })
    },
    next() {
      this.active++;
    },
    update() {
      const password = this.password;
      const pwd = this.newPassword;
      if (password == pwd) {
        const no = this.user.no;
        const that = this;
        const params = new URLSearchParams();
        params.append("no", no);
        params.append("password", password);
        this.$axios.put("/updatepassword", params).then(res => {
          if (res.data) {
            that.active++;
            this.$store.commit("REMOVE_STATE");
          } else {
            this.$message({
              showClose: true,
              message: '密码错误',
              type: 'error'
            });
            that.oldPassword = '';
          }
        }).catch(error => {
          this.$message({
            showClose: true,
            message: '系统出错，请反馈',
            type: 'error'
          });
        })
      }
    }
  },
  created() {
    this.getMsg();
  },
  components: {
    Menu,
    AboutMe
  }
}
</script>

<style scoped>
.updatePwdByPwd {
  background-image: linear-gradient(to top, #e6e9f0 0%, #eef1f5 100%);
  height: 753px;
}

.ok {
  width: 200px;
  height: 200px;
}

.updatePwdByPwd a {
  color: blue;
}

</style>