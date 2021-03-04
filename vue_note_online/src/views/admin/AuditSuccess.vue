<template>
  <div class="aduitSuccess ta_c">
    <el-row>
      <el-col :span="4">
        <AdminMenu></AdminMenu>
      </el-col>
      <el-col :span="20">
        <div class="mgt_100 asbody">
          <b>处理成功,处理结果如下</b>
          <div class="ta_l mgl_100 mgt_20">
            <p><b>笔记名：</b>{{ noteName }}</p>
            <p><b>编号：</b>{{ noteNo }}</p>
            <p><b>违规类型：</b>{{ iType }}</p>
            <p><b>处理结果：</b>{{ res }}</p>
          </div>
        </div>
        <div class="back mgt_50 mgl_50">
          <b>{{ time }}s</b>后自动返回<br>
          <el-button type="text" @click="back()">立即返回</el-button>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import AdminMenu from "@/components/AdminMenu";

export default {
  name: "AduitSuccess",
  components: {AdminMenu},
  data() {
    return {
      noteName: '',
      noteNo: '',
      iType: '',
      res: '',
      time: 10,
    }
  },
  methods: {
    timeMinus() {
      const that = this;
      this.timer = setInterval(() => {
        if (that.time > 0) {
          that.time -= 1;
        } else if (that.time == 0) {
          that.back();
        }
      }, 1000)
    },
    back() {
      window.location.href="/admin/noteAudit"
    }
  },
  created() {
    this.noteName = this.$route.params.noteName;
    this.noteNo = this.$route.params.noteNo;
    this.iType = this.$route.params.iType;
    this.res = this.$route.params.res;
    this.timeMinus();
  },
  beforeDestroy() {
    if (this.timer) {
      clearInterval(this.timer);
    }
  }
}
</script>

<style scoped>
.aduitSuccess {
  background-color: rgba(32, 140, 255, 0.1);
  text-align: center;
}

.asbody {
  width: 30%;
  margin-left: 35%;
  background-color: rgba(32, 140, 255, 0.15);
  padding: 30px;
}
</style>