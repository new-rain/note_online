<template>
  <div class="changeNote">
    <Menu></Menu>
    <el-form :model="note" :rules="rules" ref="note" label-width="100px" class="writeNote_form">
      <el-form-item label="题目" class="nname" prop="nname">
        <el-input v-model="note.nname"></el-input>
      </el-form-item>
      <el-form-item prop="article">
        <mavon-editor class="article" v-model="note.arthicle"></mavon-editor>
      </el-form-item>
      <el-form-item>
        <el-button class="wd_49" type="primary" plain @click="changeNote()">提交</el-button>
        <el-button class="wd_49" type="danger" plain @click="cancel()">取消</el-button>
      </el-form-item>
    </el-form>
    <AboutMe></AboutMe>
  </div>
</template>

<script>
import Menu from '@/components/Menu';
import AboutMe from '@/components/AboutMe';
import noteOnline from "@/assets/js/noteOnline";

export default {
  name: "ChangeNote",
  data() {
    return {
      noteNo: '',
      userNo: '',
      note: {},
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
    getNote(no) {
      const that = this;
      this.$axios.get("/getNote", {
        params: {
          no: no
        }
      }).then(res => {
        that.note = res.data;
        that.check(that.userNo, that.note.uno);
      }).catch(error => {
        console.log(error)
      })
    },
    changeNote() {
      const note = this.note;
      const that = this;
      const params = new URLSearchParams();
      params.append("nno", note.nno);
      params.append("nname", note.nname);
      params.append("arthicle", note.arthicle);
      params.append("url", note.nbodyUrl);
      this.$axios.put("/changeNote", params).then(res => {
        if (res.data) {
          this.$message({
            showClose: true,
            message: '修改成功',
            type: 'success'
          })
          window.location.href = "/seeNote/" + that.noteNo;
        } else {
          this.$message({
            showClose: true,
            message: '修改失败，请重新尝试',
            type: 'error'
          })
        }
      }).catch(error => {
        console.log(error)
      })
    },
    check(no, uno) {
      if (no != uno) {
        alert("没有修改权限" + no + "-" + uno);
        window.location.href = "/seeNote/" + this.noteNo;
      }
    },
    cancel() {
      window.location.href = "/seeNote/" + this.noteNo;
    }
  },
  created() {
    const no = this.$route.params.noteNo;
    this.userNo = this.$store.getters.getUserNo;
    this.noteNo = no;
    this.getNote(no);
  },
  components: {
    Menu,
    AboutMe
  }
}
</script>

<style scoped>
.changeNote {
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