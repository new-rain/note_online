<template>
  <div class="newNotebook">
    <Menu></Menu>
    <el-row>
      <el-col :span="4">
        <div class="blank_50">&nbsp;</div>
      </el-col>
      <el-col :span="16">
        <h1 class="mgt_50">新建笔记本</h1>
        <el-row>
          <el-col :span="8">
            <img class="cover_img" :src=notebook.cover_img @click="choose=true"/>
          </el-col>
          <el-col :span="16">
            <el-form :model="notebook" :rules="rules" ref="notebook" label-width="100px" class="demo-ruleForm">
              <el-form-item label="名称" prop="bname">
                <el-input v-model="notebook.bname"></el-input>
              </el-form-item>
              <el-form-item label="描述">
                <el-input type="textarea" :rows="5" maxlength="300" show-word-limit v-model="notebook.bdescribe"></el-input>
              </el-form-item>
              <el-form-item label="标签" class="btype">
                <el-tag class="ntag" v-for="tag in notebook.tags" :key="tag" closable @close="delTag(tag)"
                        :disable-transitions="false">
                  {{ tag }}
                </el-tag>
                <el-input v-if="addTag" v-model="newTag" class="addTag" ref="saveTagInput"
                          @keyup.enter.native="pushTag()" @blur="addTag = false">
                </el-input>
                <el-button v-else @click="addNew()">+</el-button>
              </el-form-item>

              <el-form-item>
                <el-button class="btn" type="primary" @click="newNoteBook('notebook')">新建</el-button>
                <el-button class="btn" @click="resetForm('notebook')">重置</el-button>
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>
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
            <img src="http://localhost:8888/img/notebook-img/default-notebook-img.png"/>
            <el-radio v-model="cover" label="http://localhost:8888/img/notebook-img/default-notebook-img.png">棕色
            </el-radio>
          </div>
          <div class="cov">
            <img src="http://localhost:8888/img/notebook-img/notebook-img-1.png"/>
            <el-radio v-model="cover" label="http://localhost:8888/img/notebook-img/notebook-img-1.png">中式意境</el-radio>
          </div>
          <div class="cov">
            <img src="http://localhost:8888/img/notebook-img/notebook-img-2.png"/>
            <el-radio v-model="cover" label="http://localhost:8888/img/notebook-img/notebook-img-2.png">苍松翠柏</el-radio>
          </div>
          <div class="cov">
            <img src="http://localhost:8888/img/notebook-img/notebook-img-3.png"/>
            <el-radio v-model="cover" label="http://localhost:8888/img/notebook-img/notebook-img-3.png">桃腮粉脸</el-radio>
          </div>
          <div class="cov">
            <img src="http://localhost:8888/img/notebook-img/notebook-img-4.png"/>
            <el-radio v-model="cover" label="http://localhost:8888/img/notebook-img/notebook-img-4.png">掇青拾紫</el-radio>
          </div>
          <div class="cov">
            <img src="http://localhost:8888/img/notebook-img/notebook-img-5.png"/>
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
import Menu from "@/components/Menu"
import AboutMe from "@/components/AboutMe"
import "../../assets/css/notebook_online.css"
import noteOnline from "@/assets/js/noteOnline";

export default {
  name: "NewNoteBook",
  data() {
    return {
      choose: false,
      addTag: false,
      newTag: '',
      cover: '',
      userNo:'',
      notebook: {
        bname: '',
        bdescribe: '',
        bpower: '',
        cover_img: 'http://localhost:8888/img/notebook-img/default-notebook-img.png',
        tags: []
      },
      rules: {
        bname: [
          {required: true, message: '请输入笔记名', trigger: 'blur'},
        ],
      }
    }
  },
  methods: {
    pushTag() {
      const newTag = this.newTag;
      const tags = this.notebook.tags;
      const index = tags.indexOf(newTag);
      if (index === -1) {
        if (newTag !== '') {
          this.notebook.tags.push(newTag);
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
    delTag(tag) {
      const tags = this.tags;
      this.notebook.tags.splice(this.notebook.tags.indexOf(tag), 1);
    },
    newNoteBook(notebook) {
      this.$refs[notebook].validate((valid) => {
        if (valid) {
          const uno = this.userNo;
          const name = this.notebook.bname;
          const describe = this.notebook.bdescribe;
          const power = this.notebook.bpower;
          const type = this.notebook.tags;
          const coverImg = this.notebook.cover_img;
          const that = this;
          const params = new URLSearchParams();
          params.append("uno", uno);
          params.append("name", name);
          params.append("describe", describe);
          params.append("power", 1);
          params.append("type", type);
          params.append("coverImg", coverImg);
          this.$axios.post('/newNoteBook', params).then(res => {
            if (res.data > 0) {
              window.location.href = "/seeNotebook/" + res.data;
            } else {
              this.$message({
                showClose: true,
                message: '新建失败',
                type: 'error'
              });
            }
          }).catch(error => {
            this.$message({
              showClose: true,
              message: '新建笔记本错误',
              type: 'error'
            });
            window.location.href = "goNewNoteBook"
          })
        } else {
          return false;
        }
      });
    },
    resetForm(notebook) {
      this.$refs[notebook].resetFields();
    },
    updateImg() {
      this.notebook.cover_img = this.cover;
      this.choose = false;
    }
  },
  components: {
    Menu,
    AboutMe
  },
  created() {
    this.userNo=this.$store.getters.getUserNo;
  }
}
</script>

<style scoped>
.newNotebook {
  background-image: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%);
  text-align: left;
  height: 753px;
}

h1 {
  margin-left: 42%;
  margin-bottom: 50px;
}

.bpower {
  text-align: left;
}

.cover_img {
  width: 240px;
  height: 300px;
  margin-left: 100px;
}

.btype .addTag {
  width: 100px;
  margin-left: 3%;
}


.cover {
  position: fixed;
  width: 80%;
  height: 600px;
  border-radius: 20px;
  position: absolute;
  top: 100px;
  left: 10%;
  background-color: #fff;
  box-shadow: 0px 0px 10px #000;
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


.img {
  height: 75%;
}


.cover .btn {
  margin-top: 20px;
  width: 32%;
}

.newNotebook .btn {
  width: 48%;
}

</style>