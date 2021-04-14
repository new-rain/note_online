<template>
  <div class="notebook_management">
    <Menu></Menu>
    <el-row>
      <el-col :span="4">
        <div class="blank_100"></div>
      </el-col>
      <el-col :span="16">
        <div class="blank_50"></div>
        <el-row>
          <div class="blank_30"></div>
          <el-col :span="8">
            <img class="notebook_img" :src="notebook.coverUrl" alt="笔记本封面"/>
          </el-col>
          <el-col :span="16">
            <el-form class="msg_from" label-width="100px">
              <h1>笔记信息修改</h1>
              <el-form-item label="名称" class="msg_option">
                <el-input v-model="notebook.bname"></el-input>
              </el-form-item>
              <el-form-item label="描述" class="msg_option">
                <el-input maxlength="300" :rows="10" show-word-limit type="textarea"
                          v-model="notebook.bdescribe"></el-input>
              </el-form-item>
              <el-form-item label="标签" class="msg_option">
                <el-tag class="mgr_20" v-for="(tag,index) in notebook.btypes" :key="index" closable @close="delTag(tag)"
                        :disable-transitions="false">
                  {{ tag }}
                </el-tag>
                <el-input v-if="addTag" v-model="newTag" class="addTagInput" ref="saveTagInput"
                          @keyup.enter.native="pushTag()" @blur="addTag = false">
                </el-input>
                <el-button v-else @click="addNew()">+</el-button>
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>
        <div class="action">
          <el-button class="btn" type="success" @click="choose=true">修改封面</el-button>
          <el-button class="btn" @click="cancel()">取消</el-button>
          <el-button class="btn" type="primary" @click="update()">修改</el-button>
        </div>
      </el-col>
      <el-col :span="4">
        <AboutMe></AboutMe>
      </el-col>
    </el-row>
    <div v-if="choose" class="cover">
      <h2>请选择笔记本封面</h2>
      <el-row class="img">
        <el-col :span="2">&nbsp;</el-col>
        <el-col :span="20">
          <div class="cov">
            <img src="http://localhost:8888/img/notebook-img/default-notebook-img.png" alt="牛皮纸"/>
            <el-radio v-model="cover" label="http://localhost:8888/img/notebook-img/default-notebook-img.png">牛皮纸
            </el-radio>
          </div>
          <div class="cov">
            <img src="http://localhost:8888/img/notebook-img/notebook-img-1.png" alt="牛皮纸"/>
            <el-radio v-model="cover" label="http://localhost:8888/img/notebook-img/notebook-img-1.png">中式意境</el-radio>
          </div>
          <div class="cov">
            <img src="http://localhost:8888/img/notebook-img/notebook-img-2.png" alt="苍松翠柏"/>
            <el-radio v-model="cover" label="http://localhost:8888/img/notebook-img/notebook-img-2.png">苍松翠柏</el-radio>
          </div>
          <div class="cov">
            <img src="http://localhost:8888/img/notebook-img/notebook-img-3.png" alt="桃腮粉脸"/>
            <el-radio v-model="cover" label="http://localhost:8888/img/notebook-img/notebook-img-3.png">桃腮粉脸</el-radio>
          </div>
          <div class="cov">
            <img src="http://localhost:8888/img/notebook-img/notebook-img-4.png" alt="掇青拾紫"/>
            <el-radio v-model="cover" label="http://localhost:8888/img/notebook-img/notebook-img-4.png">掇青拾紫</el-radio>
          </div>
          <div class="cov">
            <img src="http://localhost:8888/img/notebook-img/notebook-img-5.png" alt="蝶粉蜂黄"/>
            <el-radio v-model="cover" label="http://localhost:8888/img/notebook-img/notebook-img-5.png">蝶粉蜂黄</el-radio>
          </div>
        </el-col>
        <el-col :span="2"></el-col>
      </el-row>
      <el-button class="btn" @click="choose=false">取消</el-button>
      <el-button class="btn" type="primary" @click="updateImg()">选择</el-button>
    </div>
  </div>
</template>

<script>
import Menu from '@/components/Menu';
import AboutMe from '@/components/AboutMe';
import '@/assets/css/notebook_online.css'
import noteOnline from "@/assets/js/noteOnline";

export default {
  name: "NotebookManagement",
  data() {
    return {
      newTag: '',
      addTag: false,
      userNo: "",
      nno: '',
      notebook: {
        bname: '',
        bdescribe: '',
        btypes: [],
        bpower: []
      },
      cover: '',
      choose: false,
    }
  },
  methods: {
    getMsg() {
      this.userNo = this.$store.getters.getUserNo;
      this.nno = this.$route.params.notebookNo;
      this.getNotebook();
    },
    getNotebook() {
      let no = this.nno;
      this.$axios.get("getNoteBook?no=" + no).then(res => {
        this.notebook = res.data;
        if (this.userNo != this.notebook.uno) {
          this.$message({
            showClose: true,
            message: '没有修改权限',
            type: 'warning'
          });
          window.location.href = "/seeNotebook/" + this.nno;
        }
      }).catch(error => {
        this.$message({
          showClose: true,
          message: '获取失败，请重新尝试',
          type: 'error'
        });
      })
    },
    cancel() {
      window.location.href = "/seeNotebook/" + this.nno;
    },
    delTag(tag) {
      this.notebook.btypes.splice(this.notebook.btypes.indexOf(tag), 1);
    },
    pushTag() {
      let newTag = this.newTag;
      let index = this.notebook.btypes.indexOf(newTag);
      if (index === -1) {
        if (newTag !== '') {
          this.notebook.btypes.push(newTag);
        }
      } else {
        this.$message({
          showClose: true,
          message: '标签已存在',
          type: 'warning'
        });
      }
      this.newTag = '';
      this.addTag = false;
    },
    addNew() {
      this.addTag = true;
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },
    update() {
      let no = this.notebook.bno;
      let name = this.notebook.bname;
      let describe = this.notebook.bdescribe;
      let type = this.notebook.btypes;
      let coverUrl = this.notebook.coverUrl;
      let power = this.notebook.bpower;
      let params = new URLSearchParams();
      params.append("no", no);
      params.append("name", name);
      params.append("describe", describe);
      params.append("type", type);
      params.append("power", 1);
      params.append("coverUrl", coverUrl);
      this.$axios.put("updateNoteBook", params).then(res => {
        if (res.data) {
          this.$message({
            showClose: true,
            message: '修改成功，即将返回笔记本页面',
            type: 'success'
          });
          window.location.href = "/seeNotebook/" + no;
        } else {
          this.$message({
            showClose: true,
            message: '修改失败',
            type: 'warning'
          });
        }
      }).catch(error => {
        this.$message({
          showClose: true,
          message: '系统出错，请反馈',
          type: 'error'
        });
        window.location.href = "/notebook"
      })
    },
    updateImg() {
      this.notebook.coverUrl = this.cover;
      this.choose = false;
    }
  },
  created() {
    this.getMsg();
  },
  components: {AboutMe, Menu}
}
</script>

<style scoped>
.notebook_management {
  background-image: linear-gradient(120deg, #c2e9fb 0%, #a1c4fd 100%);
  height: 753px;
}

.notebook_img {
  width: 90%;
  height: 450px;
}

.msg_from {
  text-align: left;
}

.msg_from h1 {
  margin-left: 60px;
}

.msg_option .addTagInput {
  width: 100px;
}

.action {
  margin-top: 30px;
}

.btn {
  width: 32%;
}

.cover {
  width: 80%;
  height: 600px;
  border-radius: 20px;
  position: absolute;
  top: 100px;
  left: 10%;
  background-color: #fff;
  box-shadow: 0 0 10px #000;
}

.img {
  height: 75%;
}

.cov {
  width: 120px;
  height: 180px;
  float: left;
  margin-right: 25px;
  margin-bottom: 20px;
}

.cov img {
  width: 120px;
  height: 150px;
}
</style>