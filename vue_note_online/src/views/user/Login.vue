<template>
  <div class="login">
    <div class="login_back">
      <h1>登 录</h1>
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="login_form">
        <el-form-item prop="no" class="register_option">
          <label class="login_lable" slot="label"><b>账号:</b></label>
          <input v-model="ruleForm.no"/>
        </el-form-item>
        <el-form-item prop="password" class="register_option">
          <label class="login_lable" slot="label"><b>密码:</b></label>
          <input type="password" v-model="ruleForm.password"/>
        </el-form-item>
        <el-form-item>
          <button @click="submitForm('ruleForm')">登 录</button>
        </el-form-item>
      </el-form>
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
      ruleForm: {
        no: '',
        password: '',
      },
      rules: {
        no: [
          {required: true, message: '请输入账号', trigger: 'blur'},
          {min: 0, max: 8, message: '账号长度为8', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'change'}
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const that = this;
          var no = this.ruleForm.no;
          var password = this.ruleForm.password;
          var params = new URLSearchParams();
          params.append("no", no);
          params.append("password", password);
          this.$axios.post('/login', params).then(res => {
            if (res.data != null) {
              that.$store.commit("SET_USER", res.data);
              that.$store.commit("SET_USERNO", res.data.no);
              window.location.href = "/";
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
          })
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
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

.login_back h1 {
  width: 20%;
  margin-left: 40%;
  text-align: center;
  font-size: 40px;
  font-weight: bold;
}

.login_form {
  margin-top: 50px;
  width: 90%;
}

.register_option {
  width: 75%;
  margin-left: 12%;
}

.login_back input {
  width: 100%;
  border: none;
  border-bottom: 2px solid #000000;
  background: none;
  font-size: 20px;
}

.login_back button {
  border: none;
  margin-top: 20px;
  width: 50%;
  height: 40px;
  background-image: linear-gradient(to right, #00dbde 0%, #1994f8 100%);
  border-radius: 20px;
  color: #ffffff;
  font-size: 18px;
  margin-left: 80px;
}

.login_lable {
  font-size: 15px;
}

.link {
  margin-top: 60px;
}

.forget {
  margin-left: 280px;
}

</style>