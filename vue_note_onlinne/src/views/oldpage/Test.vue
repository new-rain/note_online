<template>
  <div class="test">
    <h3>近7天新增笔记数量统计</h3>
    <hr/>
    <div class="about" id="main"></div>
  </div>
</template>

<script>


export default {
  name: "Test",
  data() {
    return {
      nums: [],
      date: []
    }
  },
  mounted() {
    var myChart = this.$echarts.init(document.getElementById("main"));
    myChart.setOption({
      title: {
        text: "",
      },
      tooltip: {},
      xAxis: {
        data: this.date,
      },
      yAxis: {},
      series: [
        {
          name: "销量",
          type: "bar",
          data: this.nums,
        },
      ],
    });
  },
  methods: {},
  created() {
    this.nums = [1, 2, 3, 9, 5, 6, 10];
    this.date = ["2.4", "2.5", "2.6", "2.7", "2.8", "2.9"]
  }
}
</script>

<style scoped>
h2 {
  text-align: center;
  padding: 30px;
  font-size: 18px;
}

#main {
  height: 400px;
  width: 60%;
}
</style>

<el-col :span="8">
<div class="pd_lr_20">
  <div class="bkg_w hg_330 pd_20">
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
              <i class="el-icon-jxy-reduhuo color_red"></i>{{ hotUser.hotNum }}
            </el-col>
            <el-col :span="2"></el-col>
          </el-row>
        </div>
      </div>
    </div>
  </div>
</div>
</el-col>
<el-col :span="16">
<div class="pd_lr_20">
  <div class="bkg_w hg_330 pd_20">
    <el-row>
      <el-col :span="8">
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
              <i class="el-icon-jxy-guanzhu1"></i> {{ user.watchNum }}
            </el-col>
            <el-col :span="1"></el-col>
          </el-row>
        </div>
      </el-col>
      <el-col :span="8">
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
              <i class="el-icon-jxy-good"></i> {{ user.getGoodNum }}
            </el-col>
            <el-col :span="1"></el-col>
          </el-row>
        </div>
      </el-col>
      <el-col :span="8">
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
              <i class="el-icon-jxy-guanzhu"></i> {{ user.attentionNum }}
            </el-col>
            <el-col :span="1"></el-col>
          </el-row>
        </div>
      </el-col>
    </el-row>
  </div>
</div>
</el-col>