<template>
  <div class="searchNotebook">
    <Menu></Menu>
    <div class="bk"></div>
    <div class="blank_66_700_25"></div>
    <div class="bkg_g searchMsg pd_20 ta_l">
      <h1 class="ta_c mgb_30">推 荐</h1>
      <div class="wd_100 ta_c blank_80 bkg_w pdt_20">
        <b>最高阅读</b><br>
        <p class="c_b fw_b">{{ maxRead.bname }}</p>
      </div>
      <div class="wd_100 ta_c blank_80 bkg_w pdt_20 mgt_20">
        <b>最高点赞</b><br>
        <p class="c_b fw_b">{{ maxGood.bname }}</p>
      </div>
      <div class="wd_100 ta_c blank_80 bkg_w pdt_20 mgt_20">
        <b>最多评论</b><br>
        <p class="c_b fw_b">{{ maxComment.bname }}</p>
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
        <h1>笔 记 本 查 找</h1>
        <div class="mgt_20">
          <el-input class="searchInput" v-model="keyWord" placeholder="请输入搜索内容"
                    @keyup.enter.native="getNotebooks(keyWord)"></el-input>
          <el-button class="el-icon-search" @click="getNotebooks(keyWord)"></el-button>
        </div>
        <hr class="mgt_20">
        <div v-if="!havaData">
          <b>请输入数据进行查找</b><br>
          <img :src="search"/>
        </div>
        <div v-else>
          <div v-if="noData" class="searchContent">
            <p>未找到结果</p>
          </div>
          <div v-else v-for="(notebook,index) in notebooks" class="ta_l pd_lr_30 mgt_20" @click="see(notebook.bno)">
            <el-row class="notebookMsg">
              <el-col :span="4">
                <img :src="notebook.coverUrl"/>
              </el-col>
              <el-col :span="16">
                <b>{{ notebook.bname }}</b>
                <p>{{ notebook.bdescribe }}</p>
              </el-col>
              <el-col :span="4" class="ta_c"><br>
                <p><b>共{{ notebook.bnoteNum }}篇笔记</b></p>
                <p><i class="el-icon-thumb mgt_5">{{ notebook.bgoodNum }}</i></p>
                <p><i class="el-icon-reading">{{ notebook.breadNum }}</i></p>
                <p><i class="el-icon-s-comment">{{ notebook.bcommentNum }}</i></p>
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
  name: "FindNotebook",
  data() {
    return {
      search: search,
      teacher: teacher,
      keyWord: '',
      havaData: false,
      noData: true,
      notebooks: [],
      maxGood: {},
      maxRead: {},
      maxComment: {}
    }
  },
  methods: {
    getNotebooks(keyWord) {
      this.noData = true;
      const that = this;
      this.$axios.get("/searchNotebooks?keyWord=" + keyWord).then(res => {
        if (res.data.length > 0) {
          that.noData = false;
        }
        that.notebooks = res.data;
        this.getMaxGood(res.data);
        this.getMaxRead(res.data);
        this.getMaxComment(res.data);
        this.havaData = true;
      }).catch(error => {
        this.$message({
          showClose: true,
          message: '系统出错，请反馈',
          type: 'warning'
        });
      })
    },
    getMaxGood(lists) {
      let notebook = lists[0];
      for (let i = 0; i < lists.length; i++) {
        if (lists[i].bgoodNum > notebook.bgoodNum) {
          notebook = lists[i];
        }
      }
      this.maxGood = notebook;
    },
    getMaxRead(lists) {
      let notebook = lists[0];
      for (let i = 0; i < lists.length; i++) {
        if (lists[i].breadNum > notebook.breadNum) {
          notebook = lists[i];
        }
      }
      this.maxRead = notebook;
    },
    getMaxComment(lists) {
      let notebook = lists[0];
      for (let i = 0; i < lists.length; i++) {
        if (lists[i].bcommentNum > notebook.bcommentNum) {
          notebook = lists[i];
        }
      }
      this.maxComment = notebook;
    },
    see(no) {
      window.location.href = "/seeNotebook/" + no;
    }
  },
  created() {
  },
  components: {
    Menu,
    ToTop,
    AboutMe
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

.notebookMsg {
  height: 150px;
  overflow: hidden;
}

.notebookMsg img {
  width: 120px;
  height: 150px;
}
</style>