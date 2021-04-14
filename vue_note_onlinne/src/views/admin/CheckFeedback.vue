<template>
  <div class="checkFeedback">
    <AdminHead></AdminHead>
    <el-row>
      <el-col :span="4">
        <AdminMenu></AdminMenu>
      </el-col>
      <el-col :span="20">
        <h1 class="mgt_30 mgb_30">用户反馈</h1>
        <div class="hg_600">
          <el-row>
            <el-col :span="2">
              <div class="hg_30"></div>
            </el-col>
            <el-col :span="4"><b>编号</b></el-col>
            <el-col :span="3"><b>反馈类型</b></el-col>
            <el-col :span="6"><b>反馈说明</b></el-col>
            <el-col :span="3"><b>反馈者</b></el-col>
            <el-col :span="4"><b>反馈时间</b></el-col>
            <el-col :span="2"></el-col>
            <br>
            <hr class="mgt_1 wd_80 mgl_10bf">
          </el-row>
          <div v-for="feedback in resData[1]" class="ta_c" @click="check(feedback)">
            <el-row>
              <el-col :span="2">
                <div class="hg_30"></div>
              </el-col>
              <el-col :span="4">
                <div v-if="!feedback.fstate">
                  <b>
                    <i class="el-icon-jxy-dian color_red"></i>
                    {{ feedback.fno }}
                  </b>
                </div>
                <div v-else><b>{{ feedback.fno }}</b></div>
              </el-col>
              <el-col :span="3"><b>{{ feedback.feType }}</b></el-col>
              <el-col :span="6">{{ feedback.fbody }}</el-col>
              <el-col :span="3"><b>{{ feedback.fuName }}</b></el-col>
              <el-col :span="4">{{ feedback.ftime }}</el-col>
              <el-col :span="2">
                <div class="hg_30"></div>
              </el-col>
              <br>
              <hr class="mgt_1 wd_80 mgl_10bf">
            </el-row>
          </div>
        </div>
        <el-dialog title="反馈详情" :visible.sync="open" width="30%">
          <div class="ta_l pd_lr_30">
            <p><b>编号：</b>{{ openFeedback.fno }}</p>
            <p><b>类型：</b>{{ openFeedback.feType }}</p>
            <p><b>用户：</b>{{ openFeedback.fuName }}</p>
            <p><b>时间：</b>{{ openFeedback.ftime }}</p>
            <p><b>详情：</b>{{ openFeedback.fbody }}</p>

          </div>
          <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="open = false">确 定</el-button>
          </span>
        </el-dialog>
        <div v-if="resData[0]>10">
          <el-pagination background layout="prev, pager, next, jumper"
                         :total="resData[0]" @current-change="updatePage">
          </el-pagination>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import AdminMenu from "@/components/AdminMenu";
import AdminHead from "@/components/AdminHead";

export default {
  name: "CheckFeedBack",
  data() {
    return {
      resData: '',
      nowPage: 1,
      open: false,
      openFeedback: {}
    }
  },
  components: {AdminHead, AdminMenu},
  methods: {
    getFeedbacks(page) {
      this.$axios.get("/getFeedbacks", {
        params: {
          page: page
        }
      }).then(res => {
        this.resData = res.data;
      }).catch(error => {
        this.$message.error("系统错误，请反馈")
      })
    },
    updatePage(page) {
      this.getFeedbacks(page);
    },
    check(feedback) {
      this.open = true;
      this.openFeedback = feedback;
      this.$axios.get("/updateState", {
        params: {
          no: feedback.fno,
          page: this.nowPage
        }
      }).then(res => {
        this.resData = res.data;
      }).catch(error => {
        this.$message.error("系统错误，请反馈")
      })
    }
  },
  created() {
    this.getFeedbacks(1);
  }
}
</script>

<style scoped>
.checkFeedback {
  overflow: hidden;
  background-color: rgba(200, 200, 200, 0.1);
}
</style>