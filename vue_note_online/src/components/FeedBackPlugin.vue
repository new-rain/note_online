<template>
  <div class="feedbackPlugin">
    <!--
      1.系统错误
      2.结果异常
      3.体验建议
      4.其他
    -->
    <el-tooltip class="item" effect="light" content="反馈" placement="left">
      <el-button type="primary" icon="el-icon-chat-line-square" plain circle @click="open=!open"></el-button>
    </el-tooltip>
    <div class="myMsg ta_c" v-if="open">
      <h2 class="ta_c mgb_20">问题反馈</h2>
      <b class="color_white">问题类型：</b>
      <el-select v-model="type" placeholder="请选择">
        <el-option v-for="t in types" :key="t.value"
                   :label="t.label" :value="t.value"></el-option>
      </el-select>
      <el-input type="textarea" :rows="15" class="mgt_20 mgb_30" maxlength="200" show-word-limit
                v-model="body"></el-input>
      <el-button type="primary" plain @click="feedback" class="wd_80">提交反馈</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "FeedBackPlugin",
  data() {
    return {
      open: false,
      no: '',
      body: '',
      types: [
        {
          label: '系统错误',
          value: 1
        },
        {
          label: '结果异常',
          value: 2
        },
        {
          label: '体验建议',
          value: 3
        },
        {
          label: '其他',
          value: 4
        }
      ],
      type: ''
    }
  },
  methods: {
    feedback() {
      const params = new URLSearchParams();
      params.append("no", this.no);
      params.append("type", this.type);
      params.append("body", this.body);
      this.$axios.post("/feedback", params).then(res => {
        if (res.data) {
          this.open = false;
          this.$message({
            showClose: true,
            message: '反馈成功，感谢您的反馈',
            type: 'success'
          })
        } else {
          this.$message.error("反馈失败，请稍后重试")
        }
      }).catch(error => {
        this.$message.error("系统错误，请反馈")
      })
    }
  },
  created() {
    this.no = this.$store.getters.getUserNo;
  }
}
</script>

<style scoped>
.feedbackPlugin {
  position: fixed;
  left: 20px;
  bottom: 80px;
  width: 50px;
  height: 50px;
}

.myMsg {
  position: fixed;
  left: 20px;
  bottom: 130px;
  width: 300px;
  height: 500px;
  color: #ffffff;
  padding: 20px;
  border-radius: 20px;
  background: rgba(25, 154, 248, 0.8);
}
</style>