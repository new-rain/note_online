<template>
  <div class="typeByTime">
    <div class="blank_66_700"></div>
    <Menu></Menu>
    <el-row>
      <el-col :span="4">
        <div class="blank_100"></div>
      </el-col>
      <el-col :span="16">
        <div class="type_time_content page_content">
          <h1 class="ta_c mgb_20 wd_95 f_l">时间归档</h1>
          <el-button type="primary" circle plain icon="el-icon-sort" @click="getData"></el-button>
          {{ res[0] }}
          <div v-for="(value, key) in res" :key="key" class="mgt_30">
            <h2 :id="key">{{ key }}</h2>
            <hr>
            <div v-for="(result, k) in value" :key="k">
              <h3 :id="key">{{ k }}</h3>
              <hr>
              <div v-for="v in result" class="type_note">
                <div class="o_h mgr_20" @click="see(v.bno)">
                  <img :src="v.coverUrl"/>
                  <div class="type_note_desc">
                    <p class="mgl_10 bname">{{ v.bname }}<br></p>
                    <p>{{ v.bdescribe }}</p>
                  </div>
                </div>
              </div>
              <hr>

            </div>
          </div>
          <!--          <div v-for="results in res">-->
          <!--            <div v-for="(value, key) in results" :key="key">-->
          <!--              <h2 :id="key">{{ key }}</h2>-->
          <!--              <hr>-->
          <!--              <div v-for="val in value" class="type_notebook">-->
          <!--                <div class="bd_l_5 mgb_20 mgt_20">-->
          <!--                  <el-date-picker v-model="val[0].bcreateTime" class="date" type="date" readonly></el-date-picker>-->
          <!--                </div>-->
          <!--                <div v-for="v in val" class="type_note">-->
          <!--                  <div class="o_h mgr_20" @click="see(v.bno)">-->
          <!--                    <img :src="v.coverUrl"/>-->
          <!--                    <div class="type_note_desc">-->
          <!--                      <p class="mgl_10 bname">{{ v.bname }}<br></p>-->
          <!--                      <p>{{ v.bdescribe }}</p>-->
          <!--                    </div>-->
          <!--                  </div>-->
          <!--                </div>-->
          <!--              </div>-->
          <!--            </div>-->
          <!--          </div>-->
        </div>
      </el-col>
      <el-col :span="4">
        <ToTop></ToTop>
        <AboutMe></AboutMe>
        <ContentMenu></ContentMenu>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Menu from '@/components/Menu';
import ToTop from '@/components/ToTop';
import AboutMe from "@/components/AboutMe";
import ContentMenu from '@/components/ContentMenu'
import noteOnline from "@/assets/js/noteOnline";

export default {
  name: "ClassifyByTime",
  data() {
    return {
      userNo: '',
      notebooks: [],
      res: [],
      menu_open: true,
      timeOrder: true
    }
  },
  methods: {
    getData() {
      this.userNo = this.$store.getters.getUserNo;
      this.timeOrder = !this.timeOrder;
      this.$axios.get('queryGroupByTime', {
        params: {
          no: this.userNo,
          timeOrder: this.timeOrder
        }
      }).then(res => {
        this.res = res.data;
      }).catch(error => {
        console.log(error)
        this.$message({
          showClose: true,
          message: '获取数据出错',
          type: 'error'
        });
      });
    },
    see(no) {
      window.location.href = "/seeNotebook/" + no;
    }
  },
  created() {
    this.getData();
  },
  components: {
    Menu,
    ToTop,
    AboutMe,
    ContentMenu
  }
}
</script>
<style scoped>
.typeByTime {
  position: absolute;
  width: 100%;
  height: 100%;
  text-align: left;
  background-image: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%);
}

.typeByTime h2 {
  margin-top: 30px;
  margin-bottom: 30px;
}

.type_time_content {
  padding: 20px 50px;
  background: #ffffff;
}

.type_time_content h2 {
  margin-bottom: 0;
}

.type_time_content h3 {
  width: 250px;
  overflow: hidden;
}

.date /deep/ .el-input__inner {
  border: none !important;
  font-size: 20px;
  width: 200px;
}

.type_time_menu a {
  font-size: 20px;
}

.type_notebook {
  overflow: hidden;
}

.type_note {
  float: left;
  width: 32%;
}

.type_note img {
  width: 120px;
  height: 150px;
  float: left;
}

.type_note_desc {
  height: 180px;
}

.type_note_desc p {
  margin-left: 130px;
  height: 120px;
  overflow: hidden;
}

.bname {
  height: 25px !important;
  width: 150px;
  font-weight: bold;
  overflow: hidden;
}
</style>