<template>
  <div class="writeNote">
    <Menu></Menu>
    <el-form :model="note" :rules="rules" ref="note" label-width="100px" class="writeNote_form">
      <el-form-item label="题目" class="nname" prop="nname">
        <el-input v-model="note.nname"></el-input>
      </el-form-item>
      <el-form-item prop="article">
        <mavon-editor class="article" v-model="note.article"></mavon-editor>
      </el-form-item>
      <el-form-item>
        <el-button class="wd_49" type="primary" plain @click="saveNote('note')">提交</el-button>
        <el-button class="wd_49" type="danger" plain @click="cancle()">取消</el-button>
      </el-form-item>
    </el-form>
    <AboutMe></AboutMe>
  </div>
</template>

<script>
import Menu from '../../components/Menu';
import AboutMe from '../../components/AboutMe';
import noteOnline from "@/assets/js/noteOnline";

export default {
  name: "WriteNote",
  data() {
    return {
      notebookNo: '',
      user: {},
      note: {
        nname: '',
        article: ''
      },
      rules: {
        nname: [
          {required: true, message: '请输入题目', trigger: 'blur'},
        ],
        article: [
          {required: true, message: '请输入内容', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    getMsg() {
      this.user = this.$store.getters.getUser;
      this.notebookNo = this.$route.params.notebookNo;
    },
    cancle(){
      window.location.href = "/seeNotebook/" + this.notebookNo;
    },
    saveNote(note) {
      this.$refs[note].validate((valid) => {
        if (valid) {
          let params = new URLSearchParams();
          params.append("nname", this.note.nname);
          params.append("article", this.note.article);
          params.append("uNo", this.user.no);
          params.append("nbNo", this.notebookNo);
          this.$axios.post('writeNote', params).then(res => {
            if (res.data > 0) {
              window.location.href = "/seeNote/" + res.data;
            } else {
              this.$message({
                showClose: true,
                message: '添加失败，请重新尝试',
                type: 'error'
              });
            }
          }).catch(error => {
            this.$message({
              showClose: true,
              message: '系统出错，请反馈',
              type: 'error'
            });
          });
        } else {
          return false;
        }
      });
    }
  },
  created() {
    this.getMsg();
  },
  components: {Menu, AboutMe}
}
</script>

<style scoped>
.writeNote {
  margin: 0;
  padding: 0;
  background-image: linear-gradient(to bottom, #c0dfff 0%, white 100%);
}

.writeNote_form {
  width: 92%;
  height: 682px;
}

.nname {
  margin-top: 10px;
}

.article {
  height: 530px;
}
</style>