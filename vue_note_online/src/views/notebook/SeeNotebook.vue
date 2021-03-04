<template>
  <div class="seeNotebook">
    <div class="bk"></div>
    <div class="blank_66_700"></div>
    <Menu></Menu>
    <el-row>
      <el-col :span="4">
        <div class="blank_100"></div>
      </el-col>
      <el-col :span="16">
        <div class="notebook_head">
          <img class="f_l" :src="notebook.coverUrl"/>
          <div class="notebookMsg">
            <h1>{{ notebook.bname }}</h1>
            <span class="color_skyBlue" @click="goAuthor(notebook.uno)">{{ notebook.uname }}</span>
            <p class=" o_h"><b>描述：</b>{{ notebook.bdescribe }}</p>
            <p class="f_l mgr_20"><b>点赞数：</b>{{ notebook.bgoodNum }}</p>
            <p><b>阅读数：</b>{{ notebook.breadNum }}</p>
            <p class="f_l"><b>创建时间：</b>
              <el-date-picker v-model="notebook.bcreateTime" class="date" type="date" readonly></el-date-picker>
            </p>
            <p><b>修改时间：</b>
              <el-date-picker v-model="notebook.bupdateTime" class="date" type="date" readonly></el-date-picker>
            </p>
            <div class="hg_30">
              <b>标签：</b>
              <el-tag class="mgr_20" v-for="(tag,index) in notebook.btypes" :key="index">
                {{ tag }}
              </el-tag>
            </div>
            <div v-if="notebook.uno==userNo" class="f_l mgt_20">
              <el-button type="primary" icon="el-icon-edit" plain @click="goManagement">编辑笔记本</el-button>
              <el-button type="success" icon="el-icon-plus" plain @click="newNote">新建笔记</el-button>
              <el-button type="info" icon="el-icon-plussetting" plain @click="editbtn">管理笔记</el-button>
            </div>
            <div v-else class="mgt_20">
              <el-button v-if="!notebook.isCollection" type="primary" icon="el-icon-plus" plain @click="goCollecte">收藏
              </el-button>
              <el-button v-else type="success" icon="el-icon-check" plain @click="cancelCollecte">已收藏</el-button>
            </div>
          </div>
          <br>
          <hr class="mgt_50">
        </div>
        <div class="titles">
          <h2 class="mgb_20">目录</h2>
          <div v-for="note in notes">
            <div class="title">
              <h3 @click="see(note.nno)">{{ note.nname }}</h3>
              <div>
                <el-button v-if="edit" type="primary" icon="el-icon-edit" class="seeNotebook_btn" size="mini"
                           @click="changeNote(note.nno)">
                </el-button>
                <el-button v-if="edit" type="danger" icon="el-icon-delete" class="seeNotebook_btn" size="mini"
                           @click="delNote(note.nno)"></el-button>
              </div>
            </div>
            <hr>
          </div>
        </div>
      </el-col>
      <el-col :span="4">
        <AboutMe></AboutMe>
        <FeedBackPlugin></FeedBackPlugin>
        <ToTop></ToTop>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Menu from '@/components/Menu';
import ToTop from "@/components/ToTop";
import AboutMe from "@/components/AboutMe.vue";
import '../../assets/css/notebook_online.css';
import noteOnline from "@/assets/js/noteOnline";
import FeedBackPlugin from "@/components/FeedBackPlugin";

export default {
  name: "SeeNotebook",
  data() {
    return {
      edit: false,
      notebookNo: '',
      notebook: {},
      userNo: "",
      notes: ''
    }
  },
  methods: {
    getNotebookMsg(no) {
      this.userNo = this.$store.getters.getUserNo;
      const uNo = this.userNo;
      const that = this;
      this.$axios.get("getBookMenu", {
        params: {
          no: no,
          uNo: uNo
        }
      }).then(res => {
        that.notebook = res.data;
        that.notes = that.notebook.notes;
      }), error => {
        this.$message({
          showClose: true,
          message: '系统出错，请反馈',
          type: 'warning'
        });
        window.location.href = "/notebook";
      }
    },
    goManagement() {
      const no = this.notebook.bno;
      window.location.href = "/notebookManagement/" + no;
    },
    editbtn() {
      this.edit = !this.edit;
    },
    see(no) {
      window.location.href = "/seeNote/" + no;
    },
    newNote() {
      window.location.href = '/writeNote/' + this.notebookNo;
    },
    delNote(no) {
      this.$axios.delete("/delNote?no=" + no).then(res => {
        if (res.data) {
          this.$message({
            showClose: true,
            message: '删除成功',
            type: 'success'
          });
        } else {
          this.$message({
            showClose: true,
            message: '删除失败，请稍后重试',
            type: 'warning'
          });
        }
        window.location.href = "/seeNoteBook/" + this.notebookNo;
      }).catch(error => {
        this.$message({
          showClose: true,
          message: '系统出错，请反馈',
          type: 'error'
        });
      })
    },
    goCollecte() {
      const uNo = this.userNo
      const noteNo = this.notebookNo;
      const that = this;
      var params = new URLSearchParams();
      params.append("uNo", uNo);
      params.append("nNo", noteNo);
      params.append("type", 1);
      this.$axios.put("/addCollection", params).then(res => {
        if (res.data) {
          that.notebook.isCollection = true;
        }
      }).catch(error => {
        console.log(error)
      })
    },
    cancelCollecte() {
      const uNo = this.userNo
      const noteNo = this.notebookNo;
      const that = this;
      var params = new URLSearchParams();
      params.append("uNo", uNo);
      params.append("nNo", noteNo);
      params.append("type", 1);
      this.$axios.put("/cancelCollection", params).then(res => {
        if (res.data) {
          that.notebook.isCollection = false;
        }
      }).catch(error => {
        console.log(error)
      })
    },
    goAuthor(no) {
      window.location.href = "/friend/FriendHome/" + no;
    },
    changeNote(no) {
      window.location.href = "/changeNote/" + no;
    }
  },
  created() {
    this.notebookNo = this.$route.params.notebookNo;
    this.getNotebookMsg(this.notebookNo);
  },
  components: {
    FeedBackPlugin,
    Menu,
    AboutMe,
    ToTop
  }
}
</script>

<style scoped>
.notebook_head {
  width: 100%;
  text-align: left;
  margin-top: 50px;
  background: none;
}

.notebook_head img {
  margin-left: 120px;
  width: 240px;
  height: 320px;
}

.notebookMsg {
  margin-left: 420px;
  padding-right: 50px;
}

.notebookMsg b {
  font-size: 20px;
  margin-bottom: 20px;
}

.notebookMsg p b {
  font-size: 15px;
}

.notebookMsg p {
  margin-top: 20px;
}

.titles {
  margin-left: 50px;
  margin-right: 50px;
}

.title {
  overflow: hidden;
  text-align: left;
  height: 30px;
}

.titles hr {
  margin-top: 3px;
  margin-bottom: 15px;
}

.title h3 {
  width: 85%;
  float: left;
}

.seeNotebook_btn {
  width: 50px;
  height: 30px;
}

.date /deep/ .el-input__inner {
  border: none !important;
  width: 150px;
}

.f_l /deep/ .el-date-editor.date.el-input.el-input--prefix.el-input--suffix.el-date-editor--date {
  width: 150px;
}


</style>