<template>
  <div class="checkAndAudit">
    <AdminHead></AdminHead>
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
        <div class="mgl_20 mgr_20 mgt_20 ta_l bkg_w pd_20">
          <h2 class="ta_c">违规情况</h2>
          <ol>
            <li><b>编号：</b>{{ irr.ino }}</li>
            <li><b>笔记编号：</b>{{ irr.inNo }}</li>
            <li><b>举报者：</b>{{ irr.irName }}</li>
            <li><b>被举报者：</b>{{ irr.isName }}</li>
            <li><b>违规类型：</b>{{ irr.irType }}</li>
            <li><b>违规描述</b>
              <el-input type="textarea" :autosize="{ minRows: 5, maxRows: 5}" v-model="irr.ibody"></el-input>
            </li>
          </ol>
        </div>
        <div class="pd_20 mgt_20 ta_c">
          <el-button class="wd_80 mgt_10" type="warning" @click="aduit(1)">责令修改</el-button>
          <br>
          <el-button class="wd_80 mgt_10" type="danger" plain @click="aduit(2)">删除文章</el-button>
          <br>
          <el-button class="wd_80 mgt_10" type="danger" @click="aduit(3)">封禁帐号</el-button>
          <br>
          <el-button class="wd_80 mgt_10" type="primary" @click="aduit(4)">无违规</el-button>
          <br>
          <el-checkbox v-model="oneKey" label="1" class="mgt_20"><b>一键处理相似问题</b></el-checkbox>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import AdminMenu from "@/components/AdminMenu";
import '@/assets/css/typo.css'
import '@/assets/css/notebook_online.css'
import 'highlight.js/styles/a11y-dark.css'
import hljs from "highlight.js";
import AdminHead from "@/components/AdminHead";

export default {
  name: "CheckAndAudit",
  data() {
    return {
      no: '',
      irr: {},
      note: {},
      oneKey: false
    }
  },
  methods: {
    getIrr() {
      this.$axios.get("/selectIrrByINo", {
        params: {
          no: this.no
        }
      }).then(res => {
        this.irr = res.data;
        this.getNote(res.data.inNo);
      }).catch(error => {
        console.log(error)
      })
    },
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
        console.log(error)
      })
    },
    aduit(aduitRes) {
      if (aduitRes == 1 || aduitRes == 4) {
        this.sendMsg(aduitRes, this.oneKey);
      } else if (aduitRes == 2) {
        this.delNote(this.irr.inNo);
        this.sendMsg(aduitRes);
      } else if (aduitRes == 3) {
        //封禁账号
        this.sendMsg(aduitRes);
        this.banUser(this.irr.isNo);
      }
      this.$message({
        showClose: true,
        message: '处理成功',
        type: 'success'
      })
      window.location.href = "/admin/auditSuccess/" + this.note.nname + "/" + this.note.nno + "/" + this.irr.irType + "/" + aduitRes;
    },
    sendMsg(aduitRes, onekey) {
      let msg = this.getIrrResult(this.irr.inName, aduitRes, this.irr.irType);
      let params = new URLSearchParams();
      params.append("inNo", this.irr.inNo);
      params.append("iType", this.irr.itype);
      params.append("iNo", this.no);
      params.append("res", aduitRes);
      params.append("irNo", this.irr.irNo);
      params.append("isNo", this.irr.isNo);
      params.append("msg", msg);
      params.append("onekey", onekey);
      this.$axios.post("aduitIrr", params).then(res => {
      }).catch(error => {
        console.log(error)
      })
    },
    getIrrResult(nname, audit, reason) {
      let msg = [];
      if (audit == 1) {
        msg[0] = "感谢您的举报，经审核，我们已对文章《" + nname + "》做出责令修改的决定";
        msg[1] = "经审核，您的文章《" + this.note.nno + "->" + nname + "》由于" + reason + "，请您限时修改";
      } else if (audit == 2) {
        msg[0] = "感谢您的举报，经审核，我们已对文章《" + nname + "》做出删除的决定";
        msg[1] = "经审核，您的文章《" + this.note.nno + "->" + nname + "》由于" + reason + "，已被删除";
      } else if (audit == 3) {
        msg[0] = "感谢您的举报，经审核，我们已对文章《" + nname + "》做出封禁帐号的决定";
      } else if (audit == 4) {
        msg[0] = "感谢您的举报，经审核，文章《" + nname + "》未发现" + reason + "的情况，如有问题，请您再次举报，进行详细说明";
      }
      return msg;
    },
    delNote(no) {
      this.$axios.delete("/delNote?no=" + no).then(res => {
        if (res.data) {
          this.$message({
            showClose: true,
            message: '删除成功',
            type: 'success'
          });
        } else {
          this.$message({
            showClose: true,
            message: '删除失败，请稍后重试',
            type: 'warning'
          });
        }
      }).catch(error => {
        this.$message({
          showClose: true,
          message: '系统出错，请反馈',
          type: 'error'
        });
      })
    },
    banUser(no) {
      this.$axios.delete("/banUser?no=" + no).then(res => {
        this.$message({
          showClose: true,
          message: "已成功封禁用户账号" + no,
          type: 'success'
        })
      }).catch(error => {
        console.log(error)
      })
    }
  },
  components: {
    AdminHead,
    AdminMenu
  },
  created() {
    this.no = this.$route.params.irrNo;
    this.getIrr();
  }
}
</script>

<style scoped>
.checkAndAudit {
  background-color: rgba(32, 140, 255, 0.1);
}

.note {
  overflow-y: scroll;
  position: absolute;
  width: 58%;
  height: 98%;
}
</style>