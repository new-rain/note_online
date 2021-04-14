<template>
  <div class="notebook_online">
    <Menu></Menu>
    <div class="blank_66_700"></div>
    <el-row>
      <el-col :span="4">
        <div class="blank_700">&nbsp;</div>
      </el-col>
      <el-col :span="16" class="content mgt_50">
        <h1 class="mgt_20">我的笔记本</h1>
        <hr class="mgb_20">
        <div class="notebooks">
          <div></div>
          <el-button class="wd_90" @click="newNotebook()" type="primary" plain>新 建 笔 记 本</el-button>
          <div v-if="notebooks==''">
            <p>您还没有笔记，快去新建吧</p>
            <img :src="nullImg"/>
          </div>
          <div v-else v-for="notebook in notebooks">
            <div class="book">
              <div class="notebook" @click="see(notebook.bno)">
                <img :src="notebook.coverUrl"/>
              </div>
              <div class="msg">
                <div @click="see(notebook.bno)">
                  <div class="blank"></div>
                  <b>{{ notebook.bname }}</b>
                  <p class="describe">{{ notebook.bdescribe }}</p>
                </div>
                <i class="el-icon-setting" @click="management(notebook)"></i>
              </div>
            </div>
          </div>
        </div>
        <div v-if="mag">
          <div class="management">
            <h2>文章管理</h2>
            <div class="note">
              <el-row>
                <el-col :span="2">&nbsp;</el-col>
                <el-col :span="8">
                  <img :src="note.coverUrl">
                </el-col>
                <el-col :span="1">&nbsp;</el-col>
                <el-col :span="12" class="desc">
                  <p>编号：{{ note.bno }}</p>
                  <p>名称：{{ note.bname }}</p>
                  <p>描述：{{ note.bdescribe }}</p>
                </el-col>
              </el-row>
            </div>
            <el-button type="danger" @click="dele()" plain>删除</el-button>
            <el-button type="primary" plain @click="goManagement(note.bno)">编辑</el-button>
            <el-button plain @click="mag=false">取消</el-button>
          </div>
        </div>
      </el-col>
      <el-col :span="4">
        <ToTop></ToTop>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Menu from '../../components/Menu';
import ToTop from "../../components/ToTop";
import "../../assets/css/notebook_online.css";
import nullImg from "../../assets/images/null.jpg"

export default {
  name: "OldPageNotebook",
  data() {
    return {
      note: {},
      user: {},
      notebooks: {},
      mag: false,
      nullImg: nullImg
    }
  },
  methods: {
    getUser() {
      this.user = this.$store.getters.getUser;
      if (this.user == '') {
        alert("请先登录");
        window.location.href = "/login";
      }
    },
    getNoteBooks() {
      var no = this.user.no;
      var that = this;
      this.$axios.get("getNoteBooks?no=" + no).then(res => {
        that.notebooks = res.data;
      }).catch(error => {
        alert("获取笔记本出错")
      })
    },
    management(notebook) {
      this.note = notebook;
      this.mag = true;
    },
    newNotebook() {
      window.location.href = "/newNoteBook";
    },
    del() {
      this.$axios.delete("deleteNoteBook/" + this.note.bno).then(res => {
        window.location.href = "notebook";
      }), error => {
        alert("删除错误")
      }
    },
    dele() {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.del();
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
    see(no) {
      this.$store.commit("SET_NOTEBOOKNO", no);
      window.location.href = "/seeNotebook"
    },
    goManagement(no) {
      this.$store.commit("SET_NOTEBOOKNO", no);
      window.location.href = "/notebookManagement"
    }
  }
  ,
  created() {
    this.getUser();
    this.getNoteBooks();
  },
  components: {
    Menu,
    ToTop
  }
}
</script>
<style scoped>
.notebook_online {
  background-color: #e0f0ff;
}

.book {
  float: left;
  width: 30%;
  height: 170px;
  margin-top: 30px;
  margin-left: 25px;
  border-bottom: 1px solid #000;
}

.content {
  background: #fff;
}

.notebook {
  float: left;
  background-color: aliceblue;
  width: 120px;
  height: 150px;
}

.notebook img {
  width: 120px;
  height: 150px;
}

.notebook p {
  margin-top: 30px;
  overflow: hidden;
}


.msg {
  margin-left: 130px;
  margin-right: 20px;
  text-align: left;
  height: 160px;
}

.describe {
  height: 100px;
  overflow: hidden;
}

.msg i {
  margin-left: 130px;
}

.blank {
  height: 10px;
}

.management {
  width: 30%;
  height: 300px;
  background-color: #fff;
  position: fixed;
  top: 200px;
  left: 35%;
  border-radius: 20px;
  box-shadow: 0px 0px 10px #4facfe;
  padding-top: 20px;
}

.management h2 {
  float: left;
  width: 150px;
  margin-left: 165px;
  margin-bottom: 20px;
}

.note {
  width: 460px;
  height: 150px;
  overflow: hidden;
  margin-bottom: 30px;
}

.note img {
  width: 120px;
  height: 150px;
}

.note .desc {
  text-align: left;
  overflow: hidden;
  margin-bottom: 10px;
}

.desc p {
  margin-bottom: 10px;
}
</style>