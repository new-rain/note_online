<template>
  <div class="addRNum">
    <el-row>
      <el-col :span="4">
        <AdminMenu></AdminMenu>
      </el-col>
      <el-col :span="14">
        <div class="rnums">
          <div class="blank_10"></div>
          <h2 class="mgb_20 mgt_20">可用注册码</h2>
          <div v-for="rnum in rnums" class="hg_50">
            <el-tag type="info">{{ rnum }}</el-tag>
          </div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="mgl_20 mgr_20 mgt_30 ta_l pd_20">
          <h2 class="ta_c">添加注册码</h2>
        </div>
        <div class="pd_20 mgt_30 ta_c">
          <el-button class="wd_80 mgt_10" type="success" plain @click="addRNums(1)">添加 1 条注册码</el-button>
          <br/>
          <el-button class="wd_80 mgt_10" type="success" plain @click="addRNums(5)">添加 5 条注册码</el-button>
          <br/>
          <el-button class="wd_80 mgt_10" type="success" plain @click="addRNums(10)">添加 10 条注册码</el-button>
          <br/>
          <el-button class="wd_80 mgt_10" type="success" plain @click="addRNums(20)">添加 20 条注册码</el-button>
          <br/>
          <el-button class="wd_80 mgt_10" type="success" plain @click="addRNums(50)">添加 50 条注册码</el-button>
          <br/>
          <el-button class="wd_80 mgt_10" type="success" plain @click="addRNums(100)">添加 100 条注册码</el-button>
          <br/>
          <div class="mgt_50">
            <b>添加 </b>
            <input class="nums" v-model="num"></input>
            <b> 条注册码 </b><br/>
          </div>
          <el-button class="wd_80 mgt_10" type="primary" @click="addRNums(num)">添加</el-button>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import AdminMenu from "@/components/AdminMenu";

export default {
  name: "AddRNum",
  data() {
    return {
      rnums: [],
      num: ''
    }
  },
  methods: {
    getRNums() {
      this.$axios.get("/getRNum").then(res => {
        this.rnums = res.data;
      }).catch(error => {
        this.$message.error("系统错误")
      })
    },
    addRNums(num) {
      alert(num)
      let params = new URLSearchParams();
      params.append("num", num)
      this.$axios.post("/addRNum", params).then(res => {
        this.rnums = res.data;
      }).catch(error => {
        this.$message.error("系统错误")
      })
    }
  },
  created() {
    this.getRNums();
  },
  components: {AdminMenu}
}
</script>

<style scoped>
.addRNum {
  background-color: rgba(32, 140, 255, 0.15);
  position: absolute;
  width: 100%;
  height: 100%;
}

.rnums {
  overflow-y: scroll;
  height: 750px;
}

.nums {
  width: 10%;
  height: 25px;
}

</style>