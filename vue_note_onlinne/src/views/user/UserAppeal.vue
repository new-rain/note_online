<template>
  <div class="userAppeal">
    <div class="timeBody">
      <h2 class="mgb_30 mgt_30">您的账号已被封禁</h2>
      <div class="ta_l mgl_100">
        <p><b>封禁原因：</b>笔记<b>《{{ irr.inName }}》</b>涉及{{ irr.irType }}</p>
        <p class="wd_60"><b>具体说明：</b>{{ irr.ibody }}</p>
        <p>
          <b>剩余时长：</b>
          <b v-if="years>0" class=" t"> {{ years }} 年 </b>
          <b v-if="months>0" class="t"> {{ months }} 月 </b>
          <b v-if="days>0" class="t"> {{ days }} 日 </b>
          <b v-if="hours>0" class="t"> {{ hours }} 时 </b>
          <b v-if="minutes>0" class="t"> {{ minutes }} 分 </b>
          <b class="t"> {{ seconds }} 秒 </b>
        </p>
      </div>
      <el-button class="mgt_50 wd_30" type="primary" @click="goAppeal">申诉</el-button>
    </div>
    <div v-if="goExplain" class="goExplain">
      <h3 class="mgt_20">输入申诉说明</h3>
      <el-input type="textarea" class="wd_60 mgt_20 mgb_20" v-model="abody"></el-input>
      <br/>
      <el-button class="wd_30" type="info" @click="goExplain=false">取消</el-button>
      <el-button class="wd_30" type="primary" @click="appeal">申诉</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserAppeal",
  data() {
    return {
      times: 888888,
      years: 0,
      months: 0,
      days: 0,
      hours: 0,
      minutes: 0,
      seconds: 0,
      irr: [],
      abody: '',
      goExplain: false
    }
  },
  methods: {
    checkState(no) {
      this.$axios.get("/checkState", {
        params: {
          no: no
        }
      }).then(res => {
        this.times = res.data;
        this.getBanReason();
      }).catch(error => [
        console.log(error)
      ])
    },
    backTime() {
      window.setInterval(() => {
        setTimeout(this.getTime(), 0)
      }, 1000)
    },
    getTime() {
      let times = this.times;
      times = times - 1;
      this.times = times;
      let years = parseInt(times / 31536000);
      let x = times - (years * 31536000);
      let months = parseInt(x / 2592000);
      let y = x - (months * 2592000);
      let days = parseInt(y / (24 * 60 * 60));
      let h = y - (days * 24 * 60 * 60);
      let hours = parseInt(h / (60 * 60));
      let m = h - (hours * 60 * 60);
      let minutes = parseInt(m / (60));
      let s = m - (minutes * 60);
      this.years = years;
      this.months = months;
      this.days = days;
      this.hours = hours;
      this.minutes = minutes;
      this.seconds = s;
    },
    goAppeal() {
      this.goExplain = true;
    },
    appeal() {
      let params = new URLSearchParams();
      alert(this.irr.irType)
      params.append("uNo", this.no);
      params.append("noteNo", this.irr.inNo);
      params.append("abody", this.abody);
      params.append("reason", this.irr.irType);
      params.append("adType", 3);
      this.$axios.post("/insertAppeal", params).then(res => {
        alert(res.data)
      }).catch(error => {
        console.log(error)
      })
    },
    getBanReason() {
      this.$axios.get("/getBanReason", {
        params: {
          no: this.no
        }
      }).then(res => {
        this.irr = res.data;
      }).catch(error => {
        console.log(error)
      })
    }
  },
  created() {
    this.no = this.$route.params.no;
    this.checkState(this.no);
    this.backTime();
  }
}
</script>

<style scoped>
.userAppeal {
  height: 753px;
  background-image: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%);
}

.timeBody {
  position: fixed;
  width: 30%;
  background: rgba(255, 255, 255, 0.3);
  height: 300px;
  left: 35%;
  top: 200px;
  border-radius: 10px;
}

.t {
  text-align: right;
  width: 40px;
}

.goExplain {
  position: fixed;
  left: 38%;
  top: 240px;
  width: 24%;
  height: 200px;
  background: white;
  border-radius: 10px;
  border: 1px solid #4facfe;
}
</style>