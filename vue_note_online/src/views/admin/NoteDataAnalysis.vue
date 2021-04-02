<template>
  <div class="noteDataAnalysis">
    <el-row>
      <el-col :span="4">
        <AdminMenu></AdminMenu>
      </el-col>
      <el-col :span="20">
        <el-row>
          <el-col :span="14">
            <div class="pd_20">
              <div class="bkg_w hg_330 pd_20" id="main"></div>
            </div>
          </el-col>
          <el-col :span="10">
            <div class="pd_20">
              <div class="bkg_w hg_330 pd_20">
                <h3 class="mgb_10">热度榜前10</h3>
                <div class="ta_l">
                  <div v-for="(hotNote,i) in hotNotes">
                    <div class="hg_30">
                      <el-row>
                        <el-col :span="4">
                          <div class="blank_10"></div>
                        </el-col>
                        <el-col :span="1"><b>{{ i + 1 }}.</b></el-col>
                        <el-col :span="12">
                          <span class="title mgl_10" v-if="i<3"><b>{{ hotNote.nname }}</b></span>
                          <span class="title mgl_10" v-else>{{ hotNote.nname }}</span>
                        </el-col>
                        <el-col :span="3">
                          <i class="el-icon-jxy-reduhuo color_red"></i>{{ hotNote.hotNum }}
                        </el-col>
                        <el-col :span="4"></el-col>
                      </el-row>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </el-col>
        </el-row>
        <div class="pd_lr_20">
          <div class="bkg_w hg_280 pd_20" id="visitNum"></div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import AdminMenu from "@/components/AdminMenu";

export default {
  name: "noteDataAnalysis",
  data() {
    return {
      noteNums: [],
      user: {},
      hotNotes: [],
      visitNum: []
    }
  },
  methods: {
    getDateAndData() {
      this.$axios.get("/getNoteNumMsg").then(res => {
        let myChart = this.$echarts.init(document.getElementById("main"));
        myChart.setOption({
          title: {
            text: "近七天笔记新增量",
          },
          tooltip: {},
          xAxis: {
            data: res.data[0],
          },
          yAxis: {},
          series: [
            {
              name: "数量",
              type: "bar",//line折现统计图 bar条形统计图 pie圆形统计图
              data: res.data[1],
            },
          ],
        });
      }).catch(error => {
        console.log(error);
      })
    },
    getVisitData() {
      this.$axios.get("/select7DayVNum").then(res => {
        let myChart = this.$echarts.init(document.getElementById("visitNum"));
        myChart.setOption({
          title: {
            text: "近七天浏览量",
          },
          tooltip: {},
          xAxis: {
            data: res.data[0],
          },
          yAxis: {},
          series: [
            {
              name: "数量",
              type: "line",//line折现统计图 bar条形统计图 pie圆形统计图
              data: res.data[1],
            },
          ],
        });
      }).catch(error => {
        console.log(error);
      })
    },
    getHot() {
      this.$axios.get("/queryHot").then(res => {
        this.hotNotes = res.data;
      }).catch(error => {
        this.$message.error("获取信息出错")
      })
    }
  },
  mounted() {
    this.getDateAndData();
    this.getVisitData();
    this.getHot();
  },
  components: {
    AdminMenu
  },
  created() {
    this.user = this.$store.getters.getUser;
    // this.getDateAndData();
  }
}
</script>

<style scoped>
.noteDataAnalysis {
  background-color: rgba(32, 140, 255, 0.1);
}

.title {
  font-weight: 500;
}
</style>