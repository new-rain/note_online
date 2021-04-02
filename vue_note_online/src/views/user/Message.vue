<template>
  <div class="message">
    <Menu></Menu>
    <div class="bk_skyblue"></div>
    <el-row>
      <el-col :span="4">
        <div class="blank_30"></div>
      </el-col>
      <el-col :span="16">
        <div class="bkg_g mbody">
          <el-tabs :tab-position="left" style="height: 660px;" type="border-card">
            <el-tab-pane>
              <span slot="label" @click="changeType(0)">
                <div class="wt_150 mgl_10">
                  留 言
                  <i v-if="havaNew[0]" class="el-icon-jxy-dian color_red fs_25"></i>
                  <i v-else class="el-icon-jxy-dian color_white fs_25"></i>
                </div>
              </span>
              <div class="ta_r mgr_50 mgt_10 mgb_10">
                <a class="mgr_50 color_blue" @click="readAll">全部已读</a>
                <a class="color_blue" @click="deleteAll">清空所有消息</a>
              </div>
              <div v-for="message in messages" class="mgb_20 msg wd_95" @click="goCheck(message)">
                <el-row>
                  <el-col :span="19">
                    <b v-if="!message.mstate">
                      <i class="el-icon-jxy-dian color_red fs_20"></i>
                    </b>
                    <b><a class="color_skyBlue" @click.prevent="goFriendHome(message.mwhoToNo)">{{ message.sender }}</a></b>
                  </el-col>
                  <el-col :span="4">
                    <div class="ta_r">{{ message.mtime }}</div>
                  </el-col>
                  <el-col :span="1">
                    <div class="ta_r" @click="deleteMsg(message.mno)">
                      <i class="el-icon-delete color_red fs_15"></i>
                    </div>
                  </el-col>
                </el-row>
                <div class="o_h hg_30">
                  <div v-if="!message.mstate" class="bkg_g2">
                    {{ message.mbody }}
                  </div>
                  <div v-else class="bkg_w">
                    {{ message.mbody }}
                  </div>
                </div>
              </div>
              <el-pagination class="ta_c" background layout="prev, pager, next, jumper" @current-change="CurrentChange"
                             :page-size="6" :total="msgNum" hide-on-single-page>
              </el-pagination>
            </el-tab-pane>
            <el-tab-pane>
              <span slot="label" @click="changeType(1)">
                <div class="wt_150 mgl_10">
                  评 论
                  <i v-if="havaNew[1]" class="el-icon-jxy-dian color_red fs_25"></i>
                  <i v-else class="el-icon-jxy-dian color_white fs_25"></i>
                </div>
              </span>
              <div class="ta_r mgr_50 mgt_10 mgb_10 color_blue">
                <a class="mgr_50 color_blue" @click="readAll">全部已读</a>
                <a class="color_blue" @click="deleteAll">清空所有消息</a>
              </div>
              <div v-for="message in messages" class="mgb_20 msg wd_95" @click="goCheck(message)">
                <el-row>
                  <el-col :span="19">
                    <b v-if="!message.mstate">
                      <i class="el-icon-jxy-dian color_red fs_20"></i>
                    </b>
                    <a class="color_skyBlue" @click.prevent="goFriendHome(message.mwhoToNo)">{{ message.sender }}</a>
                    评论了你的笔记
                    <b><a class="color_black" @click.prevent="goNote(message.noteNo)">{{ message.noteName }}</a></b>
                  </el-col>
                  <el-col :span="4">
                    <div class="ta_r">{{ message.mtime }}</div>
                  </el-col>
                  <el-col :span="1">
                    <div class="ta_r" @click="deleteMsg(message.mno)">
                      <i class="el-icon-delete color_red fs_15"></i>
                    </div>
                  </el-col>
                </el-row>
                <div class="o_h hg_30">
                  <div v-if="!message.mstate" class="bkg_g2">
                    {{ message.mbody }}
                  </div>
                  <div v-else class="bkg_w">
                    {{ message.mbody }}
                  </div>
                </div>
              </div>
              <el-pagination class="ta_c" background layout="prev, pager, next, jumper" @current-change="CurrentChange"
                             :page-size="6" :total="msgNum" hide-on-single-page>
              </el-pagination>
            </el-tab-pane>
            <el-tab-pane>
              <span slot="label" @click="changeType(2)">
                <div class="wt_150 mgl_10">
                  点 赞
                  <i v-if="havaNew[2]" class="el-icon-jxy-dian color_red fs_25"></i>
                  <i v-else class="el-icon-jxy-dian color_white fs_25"></i>
                </div>
              </span>
              <div class="ta_r mgr_50 mgt_10 mgb_10"><a class="color_blue" @click="deleteAll">清空所有消息</a></div>
              <div v-for="message in messages" class="mgb_20 ms wd_95">
                <el-row>
                  <el-col :span="19">
                    <a class="color_skyBlue" @click.prevent="goFriendHome(message.mwhoToNo)">{{ message.sender }}</a>
                    <span>点赞了你的笔记:</span>
                    <b><a class="color_blue" @click.prevent="goNote(message.noteNo)"> {{ message.noteName }}</a></b>
                  </el-col>
                  <el-col :span="4">
                    <div class="ta_r">{{ message.mtime }}</div>
                  </el-col>
                  <el-col :span="1">
                    <div class="ta_r" @click="deleteMsg(message.mno)">
                      <i class="el-icon-delete color_red fs_15"></i>
                    </div>
                  </el-col>
                </el-row>
              </div>
              <el-pagination class="ta_c" background layout="prev, pager, next, jumper" @current-change="CurrentChange"
                             :page-size="6" :total="msgNum" hide-on-single-page>
              </el-pagination>
            </el-tab-pane>
            <el-tab-pane>
              <span slot="label" @click="changeType(3)">
                <div class="wt_150 mgl_10">
                  关 注
                  <i v-if="havaNew[3]" class="el-icon-jxy-dian color_red fs_25"></i>
                  <i v-else class="el-icon-jxy-dian color_white fs_25"></i>
                </div>
              </span>
              <div class="ta_r mgr_50 mgt_10 mgb_10"><a class="color_blue" @click="deleteAll">清空所有消息</a></div>
              <div v-for="message in messages" class="mgb_20 wd_95 hg_30 bd_b">
                <el-row>
                  <el-col :span="19">
                    <a class="color_skyBlue" @click.prevent="goFriendHome(message.mwhoToNo)">{{ message.sender }}</a>关注了你
                  </el-col>
                  <el-col :span="4">
                    <div class="ta_r">{{ message.mtime }}</div>
                  </el-col>
                  <el-col :span="1">
                    <div class="ta_r" @click="deleteMsg(message.mno)">
                      <i class="el-icon-delete color_red fs_15"></i>
                    </div>
                  </el-col>
                </el-row>
              </div>
              <el-pagination class="ta_c" background layout="prev, pager, next, jumper" @current-change="CurrentChange"
                             :page-size="10" :total="msgNum" hide-on-single-page>
              </el-pagination>
            </el-tab-pane>
            <el-tab-pane>
              <span slot="label" @click="changeType(4)">
                <div class="wt_150 mgl_10">
                  系统通知
                  <i v-if="havaNew[4]" class="el-icon-jxy-dian color_red fs_25"></i>
                  <i v-else class="el-icon-jxy-dian color_white fs_25"></i>
                </div>
              </span>
              <div class="ta_r mgr_50 mgt_10 mgb_10">
                <a class="mgr_50 color_blue" @click="readAll">全部已读</a>
                <a class="color_blue" @click="deleteAll">清空所有消息</a>
              </div>
              <div v-for="message in messages" class="mgb_20 msg wd_95" @click="goCheck(message)">
                <el-row>
                  <el-col :span="19">
                    <b v-if="!message.mstate">
                      <i class="el-icon-jxy-dian color_red fs_20"></i>
                    </b>
                    <b>系统通知</b>
                  </el-col>
                  <el-col :span="4">
                    <div class="ta_r">{{ message.mtime }}</div>
                  </el-col>
                  <el-col :span="1">
                    <div class="ta_r" @click="deleteMsg(message.mno)">
                      <i class="el-icon-delete color_red fs_15"></i>
                    </div>
                  </el-col>
                </el-row>
                <div class="o_h hg_30">
                  <div v-if="!message.mstate" class="bkg_g2">
                    {{ message.mbody }}
                  </div>
                  <div v-else class="bkg_w">
                    <el-row>
                      <el-col :span="22">{{ message.mbody }}</el-col>
                      <el-col :span="2">
                        <i class="el-icon-chat-line-square color_skyBlue" v-if="message.mtype==5"
                           @click.stop="noteAppeal(message)">申诉</i></el-col>
                    </el-row>
                  </div>
                </div>
              </div>
              <el-pagination class="ta_c" background layout="prev, pager, next, jumper" @current-change="CurrentChange"
                             :page-size="6" :total="msgNum" hide-on-single-page>
              </el-pagination>
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-col>
      <el-col :span="4">
      </el-col>
    </el-row>
    <el-drawer
        title="系统通知"
        :visible.sync="check"
        :direction="direction"
        :with-header="false">
      <div v-if="type==0">
        <h2 class="mgt_20">{{ msg.sender }}的留言</h2>
      </div>
      <div v-else-if="type==1">
        <h2 class="mgt_20">{{ msg.sender }} 的评论</h2>
      </div>
      <div v-else-if="type==4">
        <h2 class="mgt_20">系统通知</h2>
      </div>
      <div class="ta_l pd_30">
        <p>{{ msg.mbody }}</p>
      </div>
    </el-drawer>
    <AboutMe></AboutMe>
    <ToTop></ToTop>
  </div>
</template>

<script>
import Menu from "@/components/Menu";
import ToTop from "@/components/ToTop";
import AboutMe from "@/components/AboutMe";
import noteOnline from "@/assets/js/noteOnline";

export default {
  name: "Message",
  data() {
    return {
      left: 'left',
      no: '',
      pageSize: 6,
      type: 0,
      messages: [],
      msg: '',
      msgNum: 0,
      check: false,
      direction: 'rtl',
      page: 1,
      havaNew: []
    }
  },
  methods: {
    getMessages(no, type, page) {
      let that = this;
      this.$axios.get("/getMessages", {
        params: {
          no: no,
          type: type,
          page: page
        }
      }).then(res => {
        that.messages = res.data.list;
        that.msgNum = res.data.total;
      }).catch(error => {
        this.$message.error('获取信息失败');
      })
    },
    changeType(typeNum) {
      this.messages = [];
      this.type = typeNum;
      if (typeNum === 2 || typeNum === 3) {
        this.readAll();
      }
      let no = this.no;
      this.getMessages(no, typeNum, 1)
    },
    CurrentChange(page) {
      this.page = page;
      this.getMessages(this.no, this.type, page);
    },
    readAll() {
      var that = this;
      let no = this.no;
      let type = this.type;
      var params = new URLSearchParams();
      params.append("no", no);
      params.append("type", type);
      this.$axios.put("/readAll", params).then(res => {
        if (res.data) {
          that.havaNew[that.type] = false;
          that.getMessages(no, type, 1);
        }
      }).catch(error => {
        console.log(error)
      })
    },
    deleteAll() {
      var that = this;
      let no = this.no;
      let type = this.type;
      var params = new URLSearchParams();
      params.append("no", no);
      params.append("type", type);
      this.$axios.delete("/deleteAll", {
        params: {
          no: no,
          type: type
        }
      }).then(res => {
        if (res.data) {
          that.messages = [];
          that.msgNum = 0;
        }
      }).catch(error => {
        console.log(error)
      })
    },
    goCheck(msg) {
      this.msg = msg;
      let that = this;
      this.$axios.put("/checkMsg?no=" + msg.mno).then(res => {
        if (res.data) {
          this.check = true;
          that.getMessages(that.no, that.type, that.page);
          that.checkHaveNew(that.no);
        }
      }).catch(error => {
        this.$message.error('查看信息失败');
      })
    },
    deleteMsg(no) {
      let that = this;
      this.$axios.delete("/deleteMsg?no=" + no).then(res => {
        if (res.data) {
          this.$message({
            showClose: true,
            message: '删除成功',
            type: 'success'
          });
          that.getMessages(that.no, that.type, that.page);
          if (that.messages.length >= 0) {
            if (that.page - 1 > 0) {
              that.getMessages(that.no, that.type, that.page - 1);
            }
          }
        } else {
          this.$message.error('删除失败');
        }
      }).catch(error => {
        this.$message.error('删除出错');
      })
    },
    goFriendHome(no) {
      window.location.href = "/friend/FriendHome/" + no;
    },
    goNote(no) {
      window.location.href = "/seeNote/" + no;
    },
    checkHaveNew(no) {
      this.$axios.get("/checkHaveNew?no=" + no).then(res => {
        that.havaNew = res.data;
      }).catch(error => {
        this.$message({
          showClose: true,
          message: '系统出错，请反馈',
          type: 'error'
        });
      })
    },
    noteAppeal(message) {
      window.location.href = "/appeal/" + message.mno;
    }
  },
  created() {
    noteOnline.checkUser();
    let uno = this.$store.getters.getUserNo;
    this.no = uno;
    this.getMessages(uno, 0, 1);
    this.checkHaveNew(uno);
  },
  components: {
    Menu,
    ToTop,
    AboutMe
  }
}
</script>

<style scoped>
.mbody {
  margin-top: 10px;
  text-align: left;
}

.mbody /deep/ .el-tabs__item {
  text-align: center !important;
  width: 150px;
  height: 40px;
  padding: 0;
  margin: 10px;
}

.msg {
  height: 65px;
  border-bottom: 1px solid #dddddd;
}

.ms {
  height: 30px;
  border-bottom: 1px solid #dddddd;
}

</style>
