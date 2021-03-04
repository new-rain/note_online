<template>
  <div class="classifyByType">
    <div class="blank_66_700"></div>
    <Menu></Menu>
    <el-row>
      <el-col :span="4">
        <div class="blank_100"></div>
      </el-col>
      <el-col :span="16">
        <div class="classifyByType_content page_content ta_l o_h">
          <h1 class="ta_c">标签归档</h1>
          <div v-for="(value, key) in res" :key="key">
            <h2 :id="key">{{ key }}</h2>
            <hr>
            <div class="o_h">
              <div v-for="notebook in value" class="note_content">
                <div class="o_h mgr_20" @click="see(notebook.bno)">
                  <img :src="notebook.coverUrl"/>
                  <div class="type_note_desc">
                    <p class="fw_b">{{ notebook.bname }}</p>
                    <p>{{ notebook.bdescribe }}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
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
import ContentMenu from '@/components/ContentMenu';
import noteOnline from "@/assets/js/noteOnline";

export default {
  name: "ClassifyByType",
  data() {
    return {
      res: [],
      userNo: ''
    }
  },
  methods: {
    getData() {
      this.userNo = this.$store.getters.getUserNo;
      const that = this;
      this.$axios.get('queryGroupByType?no=' + this.userNo).then(res => {
        that.res = res.data;
      }).catch(error => {
        this.$message({
          showClose: true,
          message: '获取信息失败',
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
.classifyByType {
  position: absolute;
  width: 100%;
  background-image: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%);
}

.classifyByType_content {
  padding: 30px;
}

.classifyByType_content h2 {
  border-left: 5px solid #aaaaaa;
  padding-left: 5px;
  margin-top: 30px;
}

.note_content {
  float: left;
  width: 32%;
}

.note_content img {
  width: 120px;
  height: 150px;
  float: left;
}

.type_note_desc {
  margin-left: 130px;
  height: 150px;
  overflow: hidden;
}

</style>