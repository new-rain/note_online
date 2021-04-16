<template>
  <div class="shieldManage">
    <AdminHead></AdminHead>
    <el-row>
      <el-col :span="4">
        <AdminMenu></AdminMenu>
      </el-col>
      <el-col :span="20">
        <h1 class="pd_30">敏感词管理</h1>
        <el-row class="mgb_30">
          <el-col :span="3">
            <div class="hg_20"></div>
          </el-col>
          <el-col :span="15">
            <el-input placeholder="请输入新增关键词" v-model="shield" @keyup.enter.native="addShield"></el-input>
          </el-col>
          <el-col :span="1">
            <div class="hg_20"></div>
          </el-col>
          <el-col :span="2">
            <el-button class="wd_100" type="warning" @click="addShield">添加</el-button>
          </el-col>
          <el-col :span="3"></el-col>
        </el-row>
        <el-row>
          <el-col :span="4">
            <div class="hg_20"></div>
          </el-col>
          <el-col :span="16">
            <el-tag class="mgr_20 mgt_20" type="danger" effect="dark" v-for="(tag,index) in shields" :key="index">
                {{ tag }}
            </el-tag>
          </el-col>
          <el-col :span="4"></el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import AdminMenu from "@/components/AdminMenu";
import AdminHead from "@/components/AdminHead";

export default {
  name: "ShieldManage",
  components: {AdminHead, AdminMenu},
  data() {
    return {
      shields: {},
      shield: ""
    }
  },
  methods: {
    getShields() {
      this.$axios.get("/getShields").then(res => {
        this.shields = res.data;
      }).catch(error => {
        console.log(error);
        this.$message.error("系统错误，请反馈");
      })
    },
    addShield() {
      if (this.shields.indexOf(this.shield) >= 0) {
        this.$message({
          type: 'warning',
          message: '敏感词[ ' + this.shield + ' ]已存在',
          showClose: true
        })
        this.shield = '';
      } else {
        let params = new URLSearchParams();
        params.append("shield", this.shield);
        this.$axios.post("/addShield", params).then(res => {
          if (res.data) {
            this.$message({
              type: "success",
              message: "添加成功",
              showClose: true
            })
            window.location.href = "/admin/shieldManage"
          } else {
            this.$message.error("添加失败，请重试");
          }
        }).catch(error => {
          console.log(error);
          this.$message.error("系统错误，请反馈");
        })
      }
    }
  },
  created() {
    this.getShields();
  }
}
</script>

<style scoped>
.shieldManage {
  background: rgba(32, 140, 255, 0.05);
}
</style>