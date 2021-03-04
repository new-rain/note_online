<template>
  <div class="appeal">
    <Menu></Menu>
    <div class="appealMsg">
      <h2>违规申诉</h2>
      <p class="mgt_30"><b>申诉文章序号：</b>{{ noteNo }}</p>
      <p><b>申诉文章标题：</b>{{ noteName }}</p>
      <p><b>被举报类型：</b>{{ type }}</p>
      <b>申诉说明</b>
      <el-input type="textarea" v-model="abody" maxlength="200" show-word-limit :rows="5"></el-input>
      <el-button type="primary" plain class="wd_100 mgt_30" @click="NoteAppeal">提交</el-button>
    </div>
  </div>
</template>

<script>
import Menu from "@/components/Menu";

export default {
  name: "Appeal",
  data() {
    return {
      mno: '',
      message: {},
      noteNo: '',
      noteName: '',
      reason: '',
      type: '',
      uno: '',
      adType: '',
      abody:''
    }
  },
  methods: {
    selectMessageByNo() {
      this.$axios.get("/selectMessageByNo", {
        params: {
          no: this.mno
        }
      }).then(res => {
        this.message = res.data;
        this.getIrrMsg(res.data.mbody);
      }).catch(error => {
        this.$message.error("系统异常，请反馈")
      })
    },
    getIrrMsg(msg) {
      let m = msg.split('《');
      m = m[1].split('》');
      let n = m[0].split('->');
      this.noteNo = n[0];
      this.noteName = n[1];
      let a = m[1].split('由于');
      a = a[1].split('，');
      this.reason = a[0];
      if (a[1] == '请您限时修改') {
        this.adType = 1;
      } else {
        this.adType = 2;
      }
    },
    NoteAppeal() {
      const params = new URLSearchParams();
      params.append("uNo", this.uno);
      params.append("noteNo", this.noteNo);
      params.append("abody", this.abody);
      params.append("reason", this.reason);
      params.append("adType", this.adType);
      this.$axios.post("/insertAppeal", params).then(res => {
        if (res.data) {
          window.location.href = "/message"
        } else {
          this.$message.error("申诉失败，请重试")
        }
      }).catch(error => {
        this.$message.error("系统错误，请反馈")
      })
    }
  },
  components: {Menu},
  created() {
    this.uno = this.$store.getters.getUserNo;
    this.mno = this.$route.params.mno;
    this.selectMessageByNo();
  }
}
</script>

<style scoped>

.appeal {
  background-color: rgba(32, 140, 255, 0.1);
  position: absolute;
  height: 100%;
  width: 100%;
}

.appealMsg {
  width: 30%;
  height: 360px;
  margin-top: 100px;
  margin-left: 35%;
  padding: 20px;
  text-align: left;
  background-color: rgba(32, 140, 255, 0.15);
}
</style>