<template>
  <div class="noteBookSort">
    <div class="bk"></div>
    <div class="blank_66_700_25"></div>
    <div class="acc_msg">
      <div class="mgt_80">
        <h2>笔记本信息</h2>
        <div class="msg">
          <p class="fw_b">笔记本数</p>
          <b>{{ res[0] }}</b>
        </div>
        <div class="msg">
          <p class="fw_b">点击数</p>
          <b>{{ res[1] }}</b>
        </div>
        <div class="msg">
          <p class="fw_b">获赞数</p>
          <b>{{ res[2] }}</b>
        </div>
        <div class="msg">
          <p class="fw_b">评论数</p>
          <b>{{ res[3] }}</b>
        </div>
        <div class="msg">
          <p class="fw_b">笔记数</p>
          <b>{{ res[4] }}</b>
        </div>
        <img :src="teacher"/>
      </div>
    </div>
    <Menu></Menu>
    <el-row>
      <el-col :span="2">
        <div class="blank_50"></div>
      </el-col>
      <el-col :span="4">
        <div class="blank_50"></div>
      </el-col>
      <el-col :span="16" class="pd_20">
        <el-button type="primary" class="wd_19" plain @click="getNoteBooks(1)">时间正序</el-button>
        <el-button type="primary" class="wd_19" plain @click="getNoteBooks(2)">时间倒序</el-button>
        <el-button type="primary" class="wd_19" plain @click="getNoteBooks(3)">点赞量优先</el-button>
        <el-button type="primary" class="wd_19" plain @click="getNoteBooks(4)">阅读量优先</el-button>
        <el-button type="primary" class="wd_19" plain @click="getNoteBooks(5)">综合热度</el-button>
        <hr>
        <div class="ta_c" v-if="notebooks==''">
          <p>您还没有笔记，快去新建吧</p>
          <img :src="nullImg"/>
        </div>
        <div v-for="notebook in notebooks" class="notebook_sort ta_l">
          <div class="o_h" @click="see(notebook.bno)">
            <img class="f_l" :src="notebook.coverUrl"/>
            <div class="note_desc f_l mgl_20">
              <p>{{ notebook.bname }}</p>
              <hr>
              <div class="desc">
                <p>{{ notebook.bdescribe }}</p>
              </div>
              <div class="f_l">
                创建时间：
                <el-date-picker v-model="notebook.bcreateTime" class="date" type="date" readonly></el-date-picker>
              </div>
              <div class="mgt_5">
                <i class="el-icon-reading">{{ notebook.breadNum }} </i>
                <i class="el-icon-thumb mgl_20">{{ notebook.bgoodNum }} </i>
                <i class="el-icon-s-comment mgl_20">{{ notebook.bcommentNum }} </i>
              </div>
            </div>
            <div class="ta_c f_l"><br><br><br>
              <el-button v-if="showManage" type="primary" icon="el-icon-edit" circle
                         @click.stop="goManagement(notebook.bno)"></el-button>
              <el-button v-if="showManage" type="danger" icon="el-icon-delete" circle
                         @click.stop="dele(notebook.bno)"></el-button>
            </div>
          </div>
          <hr>
        </div>
      </el-col>
      <el-col :span="2">
        <div class="note_manage">
          <el-tooltip class="item" effect="light" content="新建笔记本" placement="left">
            <el-button type="primary" class="plus btn" icon="el-icon-plus" plain circle
                       @click="newNotebook"></el-button>
          </el-tooltip>
          <el-tooltip class="item" effect="light" content="打开设置" placement="left">
            <el-button type="primary" class="manage btn" icon="el-icon-setting" plain circle
                       @click="showManage=!showManage"></el-button>
          </el-tooltip>
        </div>
        <ToTop></ToTop>
        <AboutMe></AboutMe>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Menu from "@/components/Menu";
import ToTop from "@/components/ToTop";
import AboutMe from "@/components/AboutMe.vue"
import nullImg from "@/assets/images/null.jpg";
import teacher from "@/assets/images/teacher.png";
import noteOnline from "@/assets/js/noteOnline";

export default {
  name: "NoteBook",
  data() {
    return {
      notebooks: [],
      userNo: '',
      showManage: false,
      teacher: teacher,
      res: [],
      nullImg: nullImg
    }
  },
  methods: {
    getMsg() {
      this.userNo = this.$store.getters.getUserNo;
      this.getNoteBooks(2);
      this.getNotebookMsg();
    },
    getNotebookMsg() {
      const that = this;
      this.$axios.get("/getNotebookMsg?no=" + this.userNo).then(res => {
        that.res = res.data;
      }).catch(error => {
        this.$message({
          showClose: true,
          message: '获取信息出错，请反馈',
          type: 'error'
        });
      })
    },
    getNoteBooks(type) {
      const that = this;
      this.$axios.get("/orderQueryNoteBookByTime", {
        params: {
          no: that.userNo,
          type: type
        }
      }).then(res => {
        that.notebooks = res.data;
      }).catch(error => {
        this.$message({
          showClose: true,
          message: '系统出错，请反馈',
          type: 'error'
        });
      })
    },
    see(no) {
      window.location.href = "/seeNotebook/" + no;
    },
    goManagement(no) {
      window.location.href = "/notebookManagement/" + no;
    },
    dele(no) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.del(no);
        this.mag = false;
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    del(no) {
      this.$axios.delete("deleteNoteBook/" + no).then(res => {
        window.location.href = "notebook";
      }).catch(error => {
        this.$message({
          showClose: true,
          message: '删除错误',
          type: 'error'
        });
      })
    },
    newNotebook() {
      window.location.href = "/newNoteBook";
    },
  },
  created() {
    this.getMsg();
  },
  components: {
    Menu,
    ToTop,
    AboutMe
  }
}
</script>

<style scoped>
.noteBookSort {
  height: 753px;
}

.acc_msg {
  width: 15%;
  height: 753px;
  padding-left: 20px;
  left: 7.8%;
  background: #f9f9f9;
  position: fixed;
  text-align: center;
}

.acc_msg h2 {
  margin-bottom: 30px;
}

.msg {
  float: left;
  width: 90px;
  height: 100px;
  margin-bottom: 20px;
  margin-right: 13px;
  background: #ffffff;
  border-radius: 10px;
}

.notebook_sort img {
  width: 120px;
  height: 150px;
}

.note_desc {
  height: 150px;
  width: 70%;
  overflow: hidden;
}

.note_desc hr {
  width: 20%;
}

.note_desc .desc {
  height: 60px;
  overflow: hidden;
}

.date /deep/ .el-input__inner {
  border: none !important;
}

.note_manage .btn {
  width: 50px;
  height: 50px;
}

.plus {
  position: fixed;
  right: 20px;
  bottom: 140px;
}

.manage {
  position: fixed;
  right: 20px;
  bottom: 80px;
}

.msg b {
  font-size: 30px;
  color: #4facfe;
}
</style>