<template>
  <div class="caaAppeal">
    <el-row>
      <el-col :span="4">
        <AdminMenu></AdminMenu>
      </el-col>
      <el-col :span="14">
        <div class="blank_10"></div>
        <div class="note">
          <h2>{{ note.nname }}</h2>
          <div class="typo typo-selection page_content ta_l mgl_20 mgr_20">
            <div v-html="note.arthicle"></div>
          </div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="mgl_20 mgr_20 mgt_30 ta_l bkg_w pd_20">
          <h2 class="ta_c">申诉详情</h2>
          <ol>
            <li><b>笔记编号：</b>{{ note.nno }}</li>
            <li><b>申诉者：</b>{{ appeal.auName }}</li>
            <li><b>违规类型：</b>{{ appeal.areason }}</li>
            <li>
              <b>原处理：</b>
              <span v-if="appeal.adType==1">限时修改</span>
              <span v-if="appeal.adType==2">删除文章</span>
            </li>
            <li><b>申诉描述</b>
              <el-input type="textarea" :autosize="{ minRows: 5, maxRows: 5}" v-model="appeal.abody"></el-input>
            </li>
          </ol>
        </div>
        <div class="pd_20 mgt_50 ta_c">
          <el-button class="wd_80 mgt_10" type="success" @click="aduit(1)">取消惩罚</el-button>
          <br>
          <el-button class="wd_80 mgt_10" type="primary" @click="aduit(2)">维持不表</el-button>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import hljs from "highlight.js";
import AdminMenu from "@/components/AdminMenu";

export default {
  name: "CheckAndAduitAppeal",
  data() {
    return {
      note: {},
      appeal: {}
    }
  },
  methods: {
    getNote(no) {
      this.$axios.get("/seeNote", {
        params: {
          no: no
        }
      }).then(res => {
        this.note = res.data;
        this.$nextTick(() => {
          let blocks = document.querySelectorAll('pre code');
          blocks.forEach((block) => {
            hljs.highlightBlock(block);
          });
        })
      }).catch(error => {
        console.log(error);
      })
    },
    getAppeal(no) {
      this.$axios.get("/getAppeal", {
        params: {
          no: no
        }
      }).then(res => {
        this.appeal = res.data;
        this.getNote(this.appeal.anNo);
      }).catch(error => {
        this.$message.error("系统出错，请反馈")
      })
    },
    aduit(type) {
      if (type == 1) {
        const params = new URLSearchParams();
        params.append("type", this.appeal.adType);
        params.append("uno", this.appeal.auNo);
        params.append("ano", this.appeal.ano);
        params.append("reason", this.appeal.areason);
        params.append("noteName", this.note.nname);
        params.append("noteNo", this.appeal.anNo);
        this.$axios.put("/appealSuccess", params).then(res => {
          if (!res.data) {
            this.$message.error("处理失败，请重试")
          }else{
            window.location.href = "/admin/appealAudit"
          }
        }).catch(error => {
          this.$message.error("系统错误")
        })
      }
      else {
        const params = new URLSearchParams();
        params.append("no", this.appeal.ano);
        this.$axios.put("/auditaAppeal", params).then(res => {
          if (!res.data) {
            this.$message.error("处理失败，请重试")
          }else{
            window.location.href = "/admin/appealAudit"
          }
        }).catch(error => {
          this.$message({
            showClose: true,
            message: '系统错误',
            type: 'error'
          })
        })
      }
    }
  },
  components: {AdminMenu},
  created() {
    this.getAppeal(this.$route.params.ano);
  }
}
</script>

<style scoped>
.caaAppeal {
  background-color: rgba(32, 140, 255, 0.1);
}

.note {
  overflow-y: scroll;
  position: absolute;
  width: 58%;
  height: 98%;
}
</style>