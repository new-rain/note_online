<template>
  <div class="releaseMessage">
    <AdminHead></AdminHead>
    <el-row>
      <el-col :span="4">
        <AdminMenu></AdminMenu>
      </el-col>
      <el-col :span="20">
        <div class="message">
          <h1 class="mgb_20">信息发布</h1>
          <el-input class="msg" type="textarea" :autosize="{ minRows: 24, maxRows: 24}"
                    placeholder="请输入内容" v-model="message"></el-input>
          <div class="mgt_30">
            <el-button class="wd_49" type="primary" @click="releaseMsg('message')">立即发布</el-button>
            <el-button class="wd_49" @click="cancel('message')">重新编辑</el-button>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import AdminMenu from "@/components/AdminMenu";
import AdminHead from "@/components/AdminHead";

export default {
  name: "ReleaseMessage",
  components: {AdminHead, AdminMenu},
  data() {
    return {
      message: '',
    }
  },
  methods: {
    releaseMsg() {
      if (this.message != '') {
        let params = new URLSearchParams();
        params.append("body", this.message);
        this.$axios.post("/releaseMsg", params).then(res => {
          if (res.data) {
            this.$message({
              showClose: true,
              message: '发布成功',
              type: 'success'
            })
          } else {
            this.$message({
              showClose: true,
              message: '发布失败',
              type: 'error'
            })
          }
        }).catch(error => {
          this.$message({
            showClose: true,
            message: '系统错误，请反馈',
            type: 'error'
          })
        })
      } else {
        this.$message({
          showClose: true,
          message: '请输入内容',
          type: 'warning'
        })
      }
    },
    cancel(message) {
      this.message = '';
    }
  }
}
</script>

<style scoped>
.releaseMessage {
  overflow: hidden;
  background-color: rgba(32, 140, 255, 0.1);
}

.message {
  padding-top: 20px;
  padding-left: 50px;
  padding-right: 50px;
}

h1{
  text-align: left;
  font-family: "SFMono-Regular", Consolas, "Liberation Mono", Menlo, Courier, monospace;
}
</style>