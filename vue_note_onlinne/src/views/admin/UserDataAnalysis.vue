<template>
  <div class="userDataAnalysis">
    <admin-head></admin-head>
    <el-row>
      <el-col :span="4">
        <AdminMenu></AdminMenu>
      </el-col>
      <el-col :span="20">
        <el-row>
          <el-col :span="8">
            <div class="pd_10">
              <div class="bkg_w hg_300 pd_10" id="main"></div>
            </div>
          </el-col>
          <el-col :span="16">
            <el-row>
              <el-col :span="14">
                <div class="pd_tb_10">
                  <div class="bkg_w hg_300 pd_10" id="userAddress"></div>
                </div>
              </el-col>
              <el-col :span="10">
                <div class="pd_tb_10 pd_r_10">
                  <div class="bkg_w hg_300 pd_10">
                    <h3 class="mgb_20">用户分布详情</h3>
                    <el-row>
                      <el-col :span="3">序号</el-col>
                      <el-col :span="8">省份</el-col>
                      <el-col :span="8">用户数</el-col>
                      <el-col :span="5">占比</el-col>
                    </el-row>
                    <div v-for="(a,i) in addressUserNum">
                      <div v-if="i < 6">
                        <el-row>
                          <el-col :span="3">{{ i + 1 }}</el-col>
                          <el-col :span="8">{{ a.name }}</el-col>
                          <el-col :span="8">{{ a.value }}</el-col>
                          <el-col :span="5">{{ a.proportation }}</el-col>
                        </el-row>
                      </div>
                    </div>
                    <div class="ta_r mgt_20">
                      <!--                      <el-button size="small" type="primary" plain>更多</el-button>-->
                    </div>
                  </div>
                </div>
              </el-col>
            </el-row>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <div class="pd_lr_10">
              <div class="bkg_w hg_300 pd_20">
                <h3 class="mgb_10">人气榜</h3>
                <div class="ta_l">
                  <div v-for="(hotUser,i) in hotUsers">
                    <div class="hg_30">
                      <el-row>
                        <el-col :span="2">
                          <div class="blank_10"></div>
                        </el-col>
                        <el-col :span="1"><b>{{ i + 1 }}.</b></el-col>
                        <el-col :span="16">
                          <span class="title mgl_10" v-if="i<3"><b>{{ hotUser.nickname }}</b></span>
                          <span class="title mgl_10" v-else>{{ hotUser.nickname }}</span>
                        </el-col>
                        <el-col :span="3">
                          <i class="el-icon-jxy-reduhuo color_orange"></i>{{ hotUser.hotNum }}
                        </el-col>
                        <el-col :span="2"></el-col>
                      </el-row>
                    </div>
                  </div>
                  <div class="ta_r mgt_10">
                    <!--                    <el-button size="mini" type="primary" plain>更多</el-button>-->
                  </div>
                </div>
              </div>
            </div>
          </el-col>
          <el-col :span="5">
            <div class="pd_r_10">
              <div class="bkg_w hg_300 pd_20">
                <h3 class="mgb_10">获赞榜</h3>
                <div v-for="(user,i) in goodUsers" class="ta_l o_h">
                  <el-row>
                    <el-col :span="3">
                      <div class="hg_30"></div>
                    </el-col>
                    <el-col :span="13">
                      <div v-if="i<3"><b>{{ user.nickname }}</b></div>
                      <div v-else>{{ user.nickname }}</div>
                    </el-col>
                    <el-col :span="6">
                      <i class="el-icon-jxy-good color_red"></i> {{ user.getGoodNum }}
                    </el-col>
                    <el-col :span="1"></el-col>
                  </el-row>
                </div>
                <div class="ta_r mgt_10">
                  <!--                  <el-button size="mini" type="primary" plain>更多</el-button>-->
                </div>
              </div>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="pd_lr_10">
              <div class="bkg_w hg_300 pd_20">
                <h3 class="mgb_10">点击榜</h3>
                <div v-for="(user,i) in readUsers" class="ta_l o_h">
                  <el-row>
                    <el-col :span="3">
                      <div class="hg_30"></div>
                    </el-col>
                    <el-col :span="13">
                      <div v-if="i<3"><b>{{ user.nickname }}</b></div>
                      <div v-else>{{ user.nickname }}</div>
                    </el-col>
                    <el-col :span="6">
                      <i class="el-icon-jxy-guanzhu1 color_green"></i> {{ user.watchNum }}
                    </el-col>
                    <el-col :span="1"></el-col>
                  </el-row>
                </div>
                <div class="ta_r mgt_10">
                  <!--                  <el-button size="mini" type="primary" plain>更多</el-button>-->
                </div>
              </div>
            </div>
          </el-col>
          <el-col :span="5">
            <div class="pd_lr_10">
              <div class="bkg_w hg_300 pd_20">
                <h3 class="mgb_10">关注榜</h3>
                <div v-for="(user,i) in attentionUsers" class="ta_l o_h">
                  <el-row>
                    <el-col :span="3">
                      <div class="hg_30"></div>
                    </el-col>
                    <el-col :span="13">
                      <div v-if="i<3"><b>{{ user.nickname }}</b></div>
                      <div v-else>{{ user.nickname }}</div>
                    </el-col>
                    <el-col :span="6">
                      <i class="el-icon-jxy-guanzhu color_blue"></i> {{ user.attentionNum }}
                    </el-col>
                    <el-col :span="1"></el-col>
                  </el-row>
                </div>
                <div class="ta_r mgt_10">
                  <!--                  <el-button size="mini" type="primary" plain>更多</el-button>-->
                </div>
              </div>
            </div>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import AdminMenu from "@/components/AdminMenu";
import AdminHead from "@/components/AdminHead";

export default {
  name: "userDataAnalysis",
  data() {
    return {
      hotUsers: [],
      goodUsers: [],
      readUsers: [],
      attentionUsers: [],
      addressUserNum: []
    }
  },
  methods: {
    getRegisterNum() {
      this.$axios.get("/selectRegisterNum").then(res => {
        let myChart = this.$echarts.init(document.getElementById("main"));
        myChart.setOption({
          title: {
            text: "近七天注册量",
          },
          tooltip: {},
          xAxis: {
            data: res.data[1],
          },
          yAxis: {},
          series: [
            {
              name: "数量",
              type: "line",//line折现统计图 bar条形统计图 pie圆形统计图
              data: res.data[0],
            },
          ],
        });
      }).catch(error => {
        console.log(error);
      })
    },
    getEachTop() {
      this.$axios.get("/eachTop").then(res => {
        this.readUsers = res.data[0];
        this.goodUsers = res.data[1];
        this.attentionUsers = res.data[2];
        this.hotUsers = res.data[3];
      }).catch(error => {
        console.log(error);
      })
    },
    getUserAddress() {
      this.$axios.get("/addressUserNum").then(res => {
        this.addressUserNum = res.data;
        let myChart = this.$echarts.init(document.getElementById("userAddress"));
        myChart.setOption({
          title: {
            text: "用户分布情况",
          },
          tooltip: {},
          series: [
            {
              name: "数量",
              type: "pie",//line折现统计图 bar条形统计图 pie圆形统计图
              data: res.data,
            },
          ],
        });
      }).catch(error => {
        console.log(error);
      })
    }
  },
  components: {
    AdminHead,
    AdminMenu
  },
  created() {
    this.getEachTop();
    this.getRegisterNum();
    this.getUserAddress();
  }
}
</script>

<style scoped>
.userDataAnalysis {
  overflow: hidden;
  background-color: rgba(0, 0, 0, 0.05);
}

#userAddress {
}
</style>