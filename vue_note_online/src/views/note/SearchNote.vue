<template>
  <div class="searchNote">
    <Menu></Menu>
    <div class="bk"></div>
    <div class="blank_66_700_25"></div>
    <div class="bkg_g searchMsg pd_20 ta_l">
      <h1 class="ta_c mgb_30">推 荐</h1>
      <div class="wd_100 ta_c blank_80 bkg_w pdt_20">
        <b>最高阅读</b><br>
        <p class="c_b fw_b">{{ maxRead.nname }}</p>
      </div>
      <div class="wd_100 ta_c blank_80 bkg_w pdt_20 mgt_20" @click="see(maxGood.nno)">
        <b>最高点赞</b><br>
        <p class="c_b fw_b">{{ maxGood.nname }}</p>
      </div>
      <div class="wd_100 ta_c blank_80 bkg_w pdt_20 mgt_20" @click="see(maxComment.nno)">
        <b>最多评论</b><br>
        <p class="c_b fw_b">{{ maxComment.nname }}</p>
      </div>
      <img :src="teacher" class="teacherImg"/>
    </div>
    <el-row>
      <el-col :span="2">
        <div class="blank_50"></div>
      </el-col>
      <el-col :span="4">
        <div class="blank_30"></div>
      </el-col>
      <el-col :span="16" class="bkg_w pd_lr_30">
        <br>
        <h1>笔 记 查 找</h1>
        <div class="mgt_20">
          <el-input class="searchInput" v-model="keyWord" placeholder="请输入搜索内容"
                    @keyup.enter.native="getNotes(keyWord)"></el-input>
          <el-button class="el-icon-search" @click="getNotes(keyWord)"></el-button>
        </div>
        <hr class="mgt_20">
        <div v-if="!havaData">
          <p>请输入数据进行查找</p><br>
          <img :src="search"/>
        </div>
        <div v-else>
          <div v-if="noData" class="searchContent">
            <h2>未找到结果</h2>
          </div>
          <div v-else v-for="(note,index) in notes" class="ta_l pd_lr_30 mgt_20" @click="see(note.nno)">
            <el-row>
              <el-col :span="18 "><b>{{ note.nname }}</b></el-col>
              <el-col :span="6">
                <i class="el-icon-thumb">{{ note.ngoodNum }}</i>
                <i class="el-icon-reading mgl_50">{{ note.nreadNum }}</i>
                <i class="el-icon-s-comment mgl_50">{{ note.ncommentNum }}</i>
              </el-col>
            </el-row>
            <hr>
          </div>
        </div>
      </el-col>
      <el-col :span="2">
        <ToTop></ToTop>
        <AboutMe></AboutMe>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Menu from "@/components/Menu";
import ToTop from "@/components/ToTop";
import AboutMe from "@/components/AboutMe";
import search from '@/assets/images/search.png';
import teacher from '@/assets/images/teacher.png';
import noteOnline from "@/assets/js/noteOnline";

export default {
  name: "SearchNote",
  data() {
    return {
      keyWord: '',
      search: search,
      teacher: teacher,
      havaData: false,
      noData: true,
      notes: [{nname: ''}],
      maxRead: {nname: ''},
      maxComment: {nname: ''},
      maxGood: {nname: ''}
    }
  },
  methods: {
    getNotes(keyWord) {
      this.havaData = true;
      this.noData = true;
      const that = this;
      this.$axios.get("/searchNotes?keyWord=" + keyWord).then(res => {
        if (res.data.length > 0) {
          that.noData = false;
        }
        that.notes = res.data;
        this.getMaxRead(res.data);
        this.getMaxComment(res.data);
        this.getMaxGoodNum(res.data);
      }).catch(error => {
        this.$message({
          showClose: true,
          message: '系统出错，请反馈',
          type: 'warning'
        });
      })
    },
    getMaxRead(list) {
      let note = list[0];
      for (var i = 0; i < list.length; i++) {
        if (list[i].nreadNum > note.nreadNum) {
          note = list[i];
        }
      }
      this.maxRead = note;
    },
    getMaxComment(list) {
      let note = list[0];
      for (var i = 0; i < list.length; i++) {
        if (list[i].ncommentNum > note.ncommentNum) {
          note = list[i];
        }
      }
      this.maxComment = note;
    },
    getMaxGoodNum(list) {
      let note = list[0];
      for (var i = 0; i < list.length; i++) {
        if (list[i].ngoodNum > note.ngoodNum) {
          note = list[i];
        }
      }
      this.maxGood = note;
    },
    see(no) {
      window.location.href = '/seeNote/' + no;
    }
  },
  created() {
  },
  components: {
    Menu,
    ToTop,
    AboutMe,
  }
}
</script>

<style scoped>
.searchInput {
  width: 90%;
}

.searchContent {
  height: 588px;
}

.searchMsg {
  height: 693px;
  position: fixed;
  left: 8%;
  width: 14%;
}

.teacherImg {
  position: fixed;
  height: 200px;
  margin-left: 50px;
  bottom: 10px;
}
</style>