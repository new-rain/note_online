<template>
  <div class="friendHome">
    <Menu></Menu>
    <el-row>
      <el-col :span="2">
        <div class="blank_30"></div>
        <div class="f_body"></div>
      </el-col>
      <el-col :span="20">
        <div class="msg ta_l headBack">
          <div class="blank_50 mgt_10"></div>
          <el-row>
            <el-col :span="4">
                <el-avatar class="headImg mgl_50" :size="150" :src="friendHead"></el-avatar>
            </el-col>
            <el-col :span="16">
              <div class="mgl_20 mgt_10">
                <h2 class="mgb_10"><b>{{ friend.nickname }}</b></h2>
                <p>{{ friend.brief }}</p>
                <hr class="mgb_10">
                <p><b>邮箱：</b>{{ friend.email }}</p>
                <b>笔龄：</b>{{ times }}
                <b class="mgl_50">笔记本：</b>{{ friend.notebookNum }}
                <b class="mgl_50">笔记：</b>{{ friend.noteNum }}
              </div>
            </el-col>
            <el-col :span="4">
              <div class="mgl_100">
                <div class="blank_130"></div>
                <el-button icon="el-icon-jxy-iconhuxiangguanzhu" circle plain v-if="relation==1"></el-button>
                <el-button icon="el-icon-jxy-guanzhu1" circle plain v-else-if="relation==2"></el-button>
                <el-button icon="el-icon-jxy-guanzhu" circle plain v-else></el-button>
              </div>
            </el-col>
          </el-row>
        </div>
        <div class="mgt_20 bkg_w pd_30">
          <el-tabs type="border-card" stretch>
            <el-tab-pane label="笔记本">
              <h2 class="mgt_20"><b>{{ friend.nickname }}</b>的笔记本</h2>
              <div class="mgt_50">
                <el-button type="primary" class="wd_19" plain @click="getFriendNotebook(1)">时间正序</el-button>
                <el-button type="primary" class="wd_19" plain @click="getFriendNotebook(2)">时间倒序</el-button>
                <el-button type="primary" class="wd_19" plain @click="getFriendNotebook(3)">点赞量优先</el-button>
                <el-button type="primary" class="wd_19" plain @click="getFriendNotebook(4)">阅读量优先</el-button>
                <el-button type="primary" class="wd_19" plain @click="getFriendNotebook(5)">综合热度</el-button>
                <hr>
              </div>
              <div v-for="notebook in notebooks" class="notebook_sort ta_l mgt_20 wd_49 f_l">
                <div class="o_h" @click="seeNotebook(notebook.bno)">
                  <el-row>
                    <el-col :span="5">
                      <img class="f_l" :src="notebook.coverUrl"/>
                    </el-col>
                    <el-col :span="19">
                      <div class="note_desc f_l mgl_20">
                        <p>{{ notebook.bname }}</p>
                        <hr>
                        <div class="desc">
                          <p>{{ notebook.bdescribe }}</p>
                        </div>
                        <el-row>
                          <el-col :span="5" class="mgt_5">
                            <i class="el-icon-reading">{{ notebook.breadNum }} </i>
                          </el-col>
                          <el-col :span="5" class="mgt_5">
                            <i class="el-icon-thumb mgl_20">{{ notebook.bgoodNum }} </i>
                          </el-col>
                          <el-col :span="5" class="mgt_5">
                            <i class="el-icon-s-comment mgl_20">{{ notebook.bcommentNum }} </i>
                          </el-col>
                          <el-col :span="8">
                            <el-date-picker v-model="notebook.bcreateTime" class="date o_h" type="date"
                                            readonly></el-date-picker>
                          </el-col>
                        </el-row>
                      </div>
                    </el-col>
                  </el-row>

                </div>
                <hr class="wd_90">
              </div>
            </el-tab-pane>
            <el-tab-pane label="关注">
              <h2 class="mgt_20"><b>{{ friend.nickname }}</b>的关注</h2>
              <div v-for="fri in attentions">
                <el-row>
                  <el-col :span="3">
                    <el-avatar class="mgl_50" :size="100" :src="fri.headImg" @click="seeUser(fri.no)"></el-avatar>
                  </el-col>
                  <el-col :span="1">
                    <div class="hg_20"></div>
                  </el-col>
                  <el-col :span="20">
                    <div @click="see(fri.no)" class="ta_l mgl_20 mgt_20">
                      <b>{{ fri.nickname }}</b>
                      <p class="blank_50 o_h">{{ fri.brief }}</p>
                    </div>
                  </el-col>
                </el-row>
                <hr class="wd_90 mgl_50">
              </div>
            </el-tab-pane>
            <el-tab-pane label="收藏">
              <el-tabs :tab-position="tabWhere">
                <el-tab-pane label="笔  记">
                  <div v-for="collectionNote in colNotes" class="ta_l" @click="seeNote(collectionNote.nno)">
                    <el-row>
                      <el-col :span="18">
                        <b>{{ collectionNote.nname }}</b>
                      </el-col>
                      <el-col :span="2">
                        <i class="el-icon-reading"></i> {{ collectionNote.nreadNum }}
                      </el-col>
                      <el-col :span="2">
                        <i class="el-icon-jxy-good"></i> {{ collectionNote.ngoodNum }}
                      </el-col>
                      <el-col :span="2">
                        <i class="el-icon-jxy-shoucang"></i> {{ collectionNote.ncollectionNum }}
                      </el-col>
                    </el-row>
                    <hr class="pd_lr_30">
                  </div>
                </el-tab-pane>
                <el-tab-pane label="笔记本">
                  <div v-for="collectionNotebook in colNotebooks" class="ta_l"
                       @click="seeNotebook(collectionNotebook.bno)">
                    <el-row>
                      <el-col :span="4">
                        <img class="notebookImg" :src="collectionNotebook.coverUrl"/>
                      </el-col>
                      <el-col :span="20">
                        <b>{{ collectionNotebook.bname }}</b><span
                          class="fs_10 color_skyBlue">--{{ collectionNotebook.uname }}</span>
                        <hr class="wd_30">
                        <div class="blank_60">
                          {{ collectionNotebook.bdescribe }}
                        </div>
                        <el-row>
                          <el-col :span="12">
                            <el-date-picker v-model="collectionNotebook.bcreateTime" class="date" type="date"
                                            readonly></el-date-picker>
                          </el-col>
                          <el-col :span="4"><i class="el-icon-reading"></i> {{ collectionNotebook.breadNum }}</el-col>
                          <el-col :span="4"><i class="el-icon-jxy-good"></i> {{ collectionNotebook.bgoodNum }}
                          </el-col>
                          <el-col :span="4"><i class="el-icon-jxy-shoucang"></i> {{ collectionNotebook.bcollectionNum }}
                          </el-col>
                        </el-row>
                      </el-col>
                    </el-row>
                    <hr>
                  </div>
                </el-tab-pane>
              </el-tabs>
            </el-tab-pane>
            <el-tab-pane label="留言">
              <h2 class="mgt_20"><b>给{{ friend.nickname }}留言</b></h2>
              <el-input type="textarea" :rows="8" class="mgt_20" v-model="mBody" maxlength="300"
                        show-word-limit>
              </el-input>
              <div class="blank_30"></div>
              <el-button type="primary" plain class="wd_100" @click="leaveMessage">提交</el-button>
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-col>
      <el-col :span="2"></el-col>
    </el-row>
  </div>
</template>

<script>
import Menu from "@/components/Menu";

export default {
  name: "FriendHome",
  data() {
    return {
      friendNo: '',
      friendHead: '',
      friend: {},
      notebooks: [],
      relation: 2,
      times: '',
      attentions: [],
      mBody: '',
      mToWhoNo: '',
      mWhoToNo: '',
      colNotebooks: {},
      colNotes: {},
      tabWhere: 'top'
    }
  },
  methods: {
    getMsg() {
      let no = this.$route.params.friendNo;
      this.friendNo = no;
      this.getFriendMsg(no);
      this.getFriendAttention(no);
      this.getFriendColNotebook(no);
      this.getFriendColNote(no);
    },
    getFriendMsg(no) {
      this.$axios.get("/getFriendMsg", {
        params: {
          no: no
        }
      }).then(res => {
        this.friend = res.data;
        this.getUserHead(this.friend.headUrl);
        this.get_time(this.friend.createTime);
        this.getFriendNotebook(2);
      }).catch(error => {
        console.log(error);
        this.$message.error('获取好友信息出错');
      })
    },
    getUserHead(headUrl) {
      this.$axios({
        method: 'get',
        url: '/getUserHead?headUrl=' + headUrl,
        responseType: 'arraybuffer'
      }).then(response => {
        this.friendHead = 'data:image/jpeg;base64,' + this.arrayBufferToBase64(response.data)
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
    getFriendColNotebook(no) {
      this.$axios.get("/getColNotebook", {
        params: {
          no: no
        }
      }).then(res => {
        this.colNotebooks = res.data
      }).catch(error => {
        this.$message.error("系统错误，请反馈");
      })
    },
    getFriendColNote(no) {
      this.$axios.get("/getColNote", {
        params: {
          no: no
        }
      }).then(res => {
        this.colNotes = res.data
      }).catch(error => {
        this.$message.error("系统错误，请反馈");
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
        this.times = years.toFixed(1) + "年"
      } else {
        if ((nmonth - cmonth) > 0) {
          let months = (nmonth - cmonth) + (nday - cday) / 30;
          this.times = months.toFixed(1)+"月";
        } else {
          if ((nday - cday) > 0) {
            this.times = (nday - cday) + "天";
          } else {
            this.times = "刚刚";
          }
        }
      }
    },
    getFriendNotebook(type) {
      this.$axios.get("/orderQueryNoteBookByTime", {
        params: {
          no: this.friend.no,
          type: type
        }
      }).then(res => {
        this.notebooks = res.data;
      }).catch(error => {
        this.$message.error('获取信息失败');
      })
    },
    getFriendAttention(no) {
      this.$axios.get("/getFriendAttention", {
        params: {
          no: no
        }
      }).then(res => {
        this.attentions = res.data;
        for (let i = 0; i < this.attentions.length; i++) {
          let headUrl = this.attentions[i].headUrl;
          this.$axios({
            method: 'get',
            url: '/getUserHead?headUrl=' + headUrl,
            responseType: 'arraybuffer' // 最为关键
          }).then(response => {
            this.attentions[i].headImg = 'data:image/jpeg;base64,' + this.arrayBufferToBase64(response.data)
          })
        }
      }).catch(error => {
        console.log(error);
        this.$message.error('获取好友关注列表出错');
      })
    },
    seeNotebook(no) {
      window.location.href = "/seeNotebook/" + no;
    },
    seeUser(no) {
      window.location.href = "/friend/friendHome/" + no;
    },
    leaveMessage() {
      if (this.mBody != '') {
        let params = new URLSearchParams();
        params.append("mBody", this.mBody);
        params.append("mToWhoNo", this.friendNo);
        params.append("mWhoToNo", this.$store.getters.getUserNo);
        this.$axios.post("/leaveMessage", params).then(res => {
          if (res.data) {
            this.mBody = null;
            this.$message({
              showClose: true,
              message: '留言成功',
              type: 'success'
            });
          }
        }).catch(error => {
          this.$message.error('留言失败');
        })
      } else {
        this.$message({
          showClose: true,
          message: '请输入留言内容'
        });
      }
    },
    seeNote(no) {
      window.location.href = "/seeNote/" + no;
    }
  },
  created() {
    this.getMsg();
  },
  components: {
    Menu
  }
}
</script>

<style scoped>
.friendHome {
  background-color: rgba(32, 140, 255, 0.1);
}

.msg {
  height: 270px;
}

.headImg {
  border: 1px solid #1994f8;
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

.f_body {
  background-color: #ffffff;
  position: fixed;
  bottom: 0;
  left: 8.33%;
  width: 83.33%;
  height: 400px;
}

.headBack{
  background: white;
}

</style>