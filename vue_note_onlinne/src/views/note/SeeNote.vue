<template>
  <div class="seeNote">
    <el-backtop target=".seeNote" :right="20" :bottom="30">
      <i class="el-icon-caret-top"></i>
    </el-backtop>
    <div class="bk_gray"></div>
    <Menu></Menu>
    <div class="author">
      <div class="bk_10"></div>
      <div class="author_head ta_c">
        <img :src="head"><br>
        <b>{{ author.nickname }}</b>
      </div>
      <hr>
      <div class="note_msg">
        <p><b>笔龄：</b>{{ times }}</p>
        <p><b>笔记本数：</b>{{ author.notebookNum }}</p>
        <p><b>访问数：</b>{{ author.watchNum }}</p>
        <p><b>获赞数：</b>{{ author.getGoodNum }}</p>
      </div>
    </div>
    <el-row>
      <el-col :span="4">
        <div class="blank_50"></div>
      </el-col>
      <el-col :span="16" class="content">
        <div class="blank_30"></div>
        <h1 class="ta_c">{{ note.nname }}</h1>
        <div class="typo typo-selection page_content">
          <div v-html="note.arthicle"></div>
        </div>
        <div class="interaction">
          <b @click="goAndCancelGood(note.isGood)">
            <div v-if="note.isGood">
              <i class="el-icon-jxy-good color_blue"> 点赞{{ note.ngoodNum }}</i>
            </div>
            <div v-else>
              <i class="el-icon-jxy-good"> 点赞{{ note.ngoodNum }}</i>
            </div>
          </b>
          <b><i class="el-icon-jxy-pinglun"> 评论{{ note.ncommentNum }}</i></b>
          <b><i class="el-icon-reading"> 观看数{{ note.nreadNum }}</i></b>
          <b @click="goAndCancelCollecte(note.isCollection)">
            <div v-if="note.isCollection">
              <i class="el-icon-jxy-shoucang color_blue"> 收藏{{ note.ncollectionNum }}</i>
            </div>
            <div v-else>
              <i class="el-icon-jxy-shoucang"> 收藏{{ note.ncollectionNum }}</i>
            </div>
          </b>
          <b><i class="el-icon-jxy-jubao" @click="open_irr=true">举报</i></b>
        </div>
        <div class="input_comment">
          <el-input v-model="comment" placeholder="请输入评论" class="inp" maxlength="200" show-word-limit></el-input>
          <el-button class="com" @click="goComment(comment,noteNo,author.no,author.nickname,1)">评论</el-button>
        </div>
        <div class="comment">
          <div v-for="comment in comments" class="firstComment">
            <div>
              <b>{{ comment.uname }}:</b>
              <span class="mgl_10 mgr_30"> {{ comment.ccontent }}</span>
              <span class="fs_10 color_grey mgr_50">{{ comment.showTime }}</span>
              <a @click="reply(comment,2)">回复</a>
            </div>
            <div v-for="comm in comment.comments" class="secondComment">
              <div v-if="comm.ctype==2">
                <b>{{ comm.uname }}:</b>
                <span class="mgl_10 mgr_30">{{ comm.ccontent }}</span>
                <span class="fs_10 color_grey mgr_50">{{ comment.showTime }}</span>
                <a @click="reply(comm,3)">回复</a>
              </div>
              <div v-if="comm.ctype==3">
                <b>{{ comm.uname }}</b> 回复 <b>{{ comm.toName }}:</b>
                <span class="mgl_10 mgr_30">{{ comm.ccontent }}</span>
                <span class="fs_10 color_grey mgr_50">{{ comment.showTime }}</span>
                <a @click="reply(comm,3)">回复</a>
              </div>
            </div>
            <hr>
          </div>
          <div v-if="note.ncommentNum>10">
            <el-pagination class="pageHelp ta_c" background @current-change="CurrentChange" layout="prev, pager, next"
                           :total="note.ncommentNum">
            </el-pagination>
          </div>
        </div>
        <div class="blank_50"></div>
      </el-col>
      <el-col :span="4">
        <el-tooltip class="item" effect="light" content="返回笔记本" placement="left">
          <el-button class="backNotebook" icon="el-icon-back" type="primary" plain circle
                     @click="backNotebook(note.nbNo)"></el-button>
        </el-tooltip>
        <div v-if="uNo==author.no" class="ta_c mgt_20">
          <el-button class="wd_80" icon="el-icon-edit" type="primary" plain @click="goChange(noteNo)">编辑笔记
          </el-button>
        </div>
      </el-col>
    </el-row>
    <ToTop></ToTop>
    <AboutMe></AboutMe>
    <ContentMenu></ContentMenu>
    <div class="reply_div ta_c" v-if="open_menu">
      <h3 class="mgt_20 mgb_10">评论</h3>
      <el-input class="wd_90" type="textarea" :autosize="{ minRows: 4, maxRows: 4}" placeholder="请输入内容"
                v-model="replyContent" maxlength="200" show-word-limit>
      </el-input>
      <div class="mgt_20">
        <el-button class="wd_40" @click="open_menu=false">取消</el-button>
        <el-button class="wd_40" type="primary" @click="goReply">评论
        </el-button>
      </div>
    </div>
    <div v-if="open_irr" class="irr_audit">
      <h1 class="ta_c mgb_30">举报</h1>
      <b>违规类型：</b>
      <el-select v-model="value" placeholder="请选择">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>
      <br>
      <b>详细描述：</b>
      <el-input type="textarea" :rows="5" class="mgt_10" v-model="irrReason"></el-input>
      <div class="mgt_30">
        <el-button class="wd_48" @click="open_irr=false">取消</el-button>
        <el-button type="primary" plain class="wd_49" @click="irr">举报</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import Menu from '@/components/Menu';
import ToTop from '@/components/ToTop';
import AboutMe from "@/components/AboutMe";
import ContentMenu from '../../components/ContentMenu';
import '@/assets/css/typo.css'
import '@/assets/css/notebook_online.css'
import 'highlight.js/styles/a11y-dark.css'
import hljs from "highlight.js"
import noteOnline from "@/assets/js/noteOnline";

export default {
  name: "seeNote",
  data() {
    return {
      noteNo: '',
      uNo: '',
      author: {},
      note: {},
      user: {},
      nowPage: 1,
      times: '',
      comment: '',
      replyContent: '',
      open_menu: false,
      comments: [],
      replyWho: {content: {}, type: ''},
      message: '',
      open_lm: false,
      open_irr: false,
      head: '',
      options: [{
        value: '1',
        label: '含有敏感词'
      }, {
        value: '2',
        label: '内容涉黄'
      }, {
        value: '3',
        label: '政治相关'
      }, {
        value: '4',
        label: '涉嫌广告'
      }, {
        value: '5',
        label: '内容侵权'
      }, {
        value: '6',
        label: '侮辱谩骂'
      }, {
        value: '7',
        label: '其他'
      }],
      value: '',
      irrReason: ''
    }
  },
  methods: {
    get_menu() {
      tocbot.init({
        tocSelector: '.note_menu',
        contentSelector: '.typo-selection',
        headingSelector: 'h1, h2, h3',
        hasInnerContainers: true,
      });
    },
    close_menu() {
      tocbot.destroy();
      this.menu_open = true
    },
    getNoteMsg(noteNo) {
      let uNo = this.uNo;
      this.$axios.get("/seeNote", {
        params: {
          no: noteNo,
          uNo: uNo
        }
      }).then(res => {
        this.note = res.data;
        this.arthicle = res.data.arthicle;
        this.$nextTick(() => {
          let blocks = document.querySelectorAll('pre code');
          blocks.forEach((block) => {
            hljs.highlightBlock(block);
          });
        })
        this.getAuthor(res.data.uno);
        this.getComment(this.note.nno, this.nowPage);
      }).catch(error => {
        this.$message({
          showClose: true,
          message: '系统出错，请反馈',
          type: 'error'
        });
        window.location.href = "/";
      })
    },
    getAuthor(no) {
      this.$axios.get("getAuthor?no=" + no).then(res => {
        this.author = res.data;
        this.getAuthorHead(res.data.headUrl);
        this.get_time(res.data.createTime);
      }).catch(error => {
        this.$message({
          showClose: true,
          message: '系统出错，请反馈',
          type: 'error'
        });
        window.location.href = "/";
      })
    },
    get_time(createTime) {
      let cTime = new Date(createTime);
      let nTime = new Date();
      let cyear = cTime.getFullYear();
      let nyear = nTime.getFullYear();
      let cmonth = cTime.getMonth();
      let nmonth = nTime.getMonth();
      let cday = cTime.getDate();
      let nday = nTime.getDate();
      if ((nyear - cyear) > 0) {
        let years = (nyear - cyear) + (nmonth - cmonth) / 12;
        this.times = years.toFixed(1) + " 年"
      } else {
        if ((nmonth - cmonth) > 0) {
          let months = (nmonth - cmonth) + (nday - cday) / 30;
          this.times = months.toFixed(1) + " 月";
        } else {
          if ((nday - cday) > 0) {
            this.times = (nday - cday) + " 天";
          } else {
            this.times = "刚刚";
          }
        }
      }
    },
    getComment(no, page) {
      this.$axios.get("queryComment?nNo=" + no + "&page=" + page).then(res => {
        this.comments = res.data;
      }).catch(error => {
        this.$message({
          showClose: true,
          message: '系统出错，请反馈',
          type: 'error'
        });
        window.location.href = "/";
      })
    },
    reply(comment, type) {
      this.open_menu = true;
      this.replyWho.content = comment;
      this.replyWho.type = type;
    },
    goComment(comment, nNo, toNo, toName, type) {
      if (comment != '') {
        let params = new URLSearchParams();
        params.append("content", comment);
        params.append("nNo", nNo);
        params.append("uNo", this.uNo);
        params.append("uName", this.user.nickname);
        params.append("toNo", toNo);
        params.append("toName", toName);
        params.append("cType", type);
        this.$axios.post("addComment", params).then(res => {
          if (res.data) {
            this.$message({
              showClose: true,
              message: '评论成功',
              type: 'success'
            });
            this.getComment(this.noteNo, 1);
            this.open_menu = false;
            this.replyContent = "";
            this.comment="";
          }
        }).catch(error => {
          this.$message({
            showClose: true,
            message: '系统出错，请反馈',
            type: 'error'
          });
        })
      }
    },
    goReply() {
      if (this.replyWho.type == 2) {
        this.goComment(this.replyContent, this.replyWho.content.cno, this.replyWho.content.uno, this.replyWho.content.uname, this.replyWho.type);
      } else {
        this.goComment(this.replyContent, this.replyWho.content.ctoNno, this.replyWho.content.uno, this.replyWho.content.uname, this.replyWho.type);
      }
    },
    CurrentChange(p) {
      this.getComment(this.note.nno, p);
    },
    backNotebook(no) {
      window.location.href = "/seeNotebook/" + no;
    },
    goGood() {
      let uNo = this.uNo
      let noteNo = this.noteNo;
      let params = new URLSearchParams();
      params.append("uNo", uNo);
      params.append("nNo", noteNo);
      params.append("type", 0);
      this.$axios.put("/addGood", params).then(res => {
        if (res.data) {
          this.note.isGood = true;
          this.note.ngoodNum += 1;
        }
      }).catch(error => {
        console.log(error)
      })
    },
    cancelGood() {
      let uNo = this.uNo
      let noteNo = this.noteNo;
      let params = new URLSearchParams();
      params.append("uNo", uNo);
      params.append("nNo", noteNo);
      this.$axios.put("/cancelGood", params).then(res => {
        if (res.data) {
          this.note.isGood = false;
          this.note.ngoodNum -= 1;
        }
      }).catch(error => {
        console.log(error)
      })
    },
    goAndCancelGood(isGood) {
      if (isGood) {
        this.cancelGood();
      } else {
        this.goGood();
      }
    },
    cancelCollecte() {
      let uNo = this.uNo
      let noteNo = this.noteNo;
      let params = new URLSearchParams();
      params.append("uNo", uNo);
      params.append("nNo", noteNo);
      params.append("type", 0);
      this.$axios.put("/cancelCollection", params).then(res => {
        if (res.data) {
          this.note.isCollection = false;
          this.note.ncollectionNum -= 1;
        }
      }).catch(error => {
        console.log(error)
      })
    },
    goCollecte() {
      let uNo = this.uNo
      let noteNo = this.noteNo;
      let params = new URLSearchParams();
      params.append("uNo", uNo);
      params.append("nNo", noteNo);
      params.append("type", 0);
      this.$axios.put("/addCollection", params).then(res => {
        if (res.data) {
          this.note.isCollection = true;
          this.note.ncollectionNum += 1;
        }
      }).catch(error => {
        console.log(error)
      })
    },
    goAndCancelCollecte(isCollection) {
      if (isCollection) {
        this.cancelCollecte();
      } else {
        this.goCollecte();
      }
    },
    goChange(no) {
      window.location.href = "/changeNote/" + no;
    },
    irr() {
      let params = new URLSearchParams();
      params.append("iType", this.value);
      params.append("iBody", this.irrReason);
      params.append("irNo", this.uNo);
      params.append("isNo", this.author.no);
      params.append("inNo", this.noteNo);
      params.append("inName", this.note.nname);
      this.$axios.post("/irr", params).then(res => {
        if (res.data) {
          this.open_irr = false;
          this.$message({
            showClose: true,
            message: '举报成功',
            type: 'success'
          })
        } else {
          this.$message.error("举报失败，请重试")
        }
      }).catch(error => {
        this.$message.error("系统错误，请反馈")
      })
    },
    getAuthorHead(headUrl) {
      this.$axios({
        method: 'get',
        url: '/getUserHead?headUrl=' + headUrl,
        responseType: 'arraybuffer' // 最为关键
      }).then(response => {
        this.head = 'data:image/jpeg;base64,' + this.arrayBufferToBase64(response.data)
      })
    },
    arrayBufferToBase64(buffer) {
      let binary = ''
      let bytes = new Uint8Array(buffer)
      let len = bytes.byteLength
      for (let i = 0; i < len; i++) {
        binary += String.fromCharCode(bytes[i])
      }
      return window.btoa(binary)
    },
  },
  created() {
    this.noteNo = this.$route.params.noteNo;
    this.uNo = this.$store.getters.getUserNo;
    this.user = this.$store.getters.getUser;
    this.getNoteMsg(this.noteNo);
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
.seeNote {
  height: 750px;
  overflow-x: hidden;
  text-align: left;
  background-image: linear-gradient(120deg, #eeeeee 0%, #fdfbfb 100%);
}

.content {
  background-color: #fff;
}

.typo {
  text-align: left;
  width: 95%;
  margin-left: 2.5%;
}

.interaction {
  overflow: hidden;
  background-color: #eee;
  width: 96%;
  margin-left: 2%;
  margin-bottom: 10px;
  padding: 10px;
}

.interaction b {
  float: left;
  font-size: 15px;
  margin-right: 30px;
}

.comment {
  text-align: left;
  margin-top: 80px;
}

.input_comment .inp {
  float: left;
  width: 88%;
  margin-left: 2%;
}

.input_comment .com {
  float: left;
  width: 8%;
  margin-left: 1%;
}

.firstComment {
  margin-left: 20px;
}

.firstComment a {
  color: #4facfe;
}

.secondComment {
  margin-left: 50px;
  border-left: 1px solid #999;
  padding-left: 5px;
}

.author {
  position: fixed;
  top: 60px;
  width: 200px;
  padding: 20px;
  left: 5px;
  background: #fff;
  text-align: left;
}

.author_head {
  overflow: hidden;
}

.author_head img {
  width: 80px;
  height: 80px;
  margin-left: 20px;
  margin-right: 20px;
  border-radius: 50%;
}

.note_msg {
  margin-left: 30px;
  margin-top: 10px;
}

.backNotebook {
  position: fixed;
  bottom: 140px;
  right: 20px;
  height: 50px;
  width: 50px;
}

.typo h1, h2 {
  padding-left: 5px;
  border-left: 5px solid #aaa;
}

.typo h3 {
  padding-left: 5px;
  border-left: 3px solid #aaa;
}

.reply_div {
  width: 30%;
  height: 240px;
  border-radius: 20px;
  position: fixed;
  top: 240px;
  left: 35%;
  background-color: #fff;
  box-shadow: 0 0 10px #4facfe;
}

.irr_audit {
  position: fixed;
  width: 30%;
  height: 350px;
  left: 35%;
  top: 150px;
  background-color: #ffffff;
  border-radius: 20px;
  border: 1px solid #1994f8;
  padding: 20px;
}

</style>