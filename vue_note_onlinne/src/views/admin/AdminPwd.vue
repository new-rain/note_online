<template>
  <div class="adminPwd">
    <AdminHead></AdminHead>
    <el-row>
      <el-col :span="4">
        <AdminMenu></AdminMenu>
      </el-col>
      <el-col :span="20">
        <div class="blank_100"></div>
        <h1>密码修改</h1>
        <input v-model="oldPassword" type="password" class="pwd" placeholder="请输入旧密码"></input>
        <input v-model="password" type="password" class="pwd" placeholder="请输入新密码"></input>
        <input v-model="pwd" type="password" class="pwd" placeholder="请确认新密码"></input>
        <el-button class="wd_40 mgt_20" type="primary" @click="update">修改</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import AdminMenu from "@/components/AdminMenu";
import AdminHead from "@/components/AdminHead";

export default {
  name: "adminPwd",
  data() {
    return {
      oldPassword: '',
      password: '',
      pwd: ''
    }
  },
  methods: {
    update() {
      if (this.password == this.pwd) {
        let params = new URLSearchParams();
        params.append("id", this.$store.getters.getAdminNo);
        params.append("old", this.oldPassword);
        params.append("password", this.password);
        this.$axios.put("/updatePassword", params).then(res => {
          if(res.data){
            this.oldPassword="";
            this.password="";
            this.pwd="";
            this.$message({
              showClose:true,
              type:'success',
              message:'修改成功'
            })
          }else{
            this.$message.error("账号或密码错误")
          }
        }).catch(error => {
          console.log(error)
        })
      } else {
        this.$message.error("两次密码不一致")
      }
    }
  },
  created() {
  },
  components: {
    AdminHead,
    AdminMenu
  }
}
</script>

<style scoped>
.adminPwd {
  background-color: rgba(32, 140, 255, 0.1);
  position: absolute;
  width: 100%;
  height: 100%;
}

.adminPwd h1 {
  margin-bottom: 50px;
}

.pwd {
  width: 40%;
  background: none;
  display: block;
  margin: 30px auto;
  height: 40px;
  border-radius: 5px;
  border: 2px solid #4facfe;
  padding-left: 20px;
}
</style>