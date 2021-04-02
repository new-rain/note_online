<template>
  <div class="user">
    <Menu></Menu>
    <AboutMe></AboutMe>
    <FeedBackPlugin></FeedBackPlugin>
    <div class="bk_skyblue"></div>
    <el-row>
      <el-col :span="4">
        <div class="blank_50"></div>
      </el-col>
      <el-col :span="16" class="user_content">
        <div class="user_head o_h" :style="headUrl">
          <div class="user_header">
            <img :src="user.headUrl" @click="choose=true"/>
          </div>
          <div class="user_name">
            <h1>{{ user.nickname }}</h1>
            <p>{{ user.brief }}</p>
            <el-button type="danger" size="mini" plain @click="exit">退出账号</el-button>
          </div>
        </div>
        <el-tabs v-model="activeTab" type="border-card" stretch>
          <el-tab-pane label="个人信息" name="first">
            <div class="user_message">
              <el-divider class="accmsg" content-position="left">
                <h2>账号信息</h2>
              </el-divider>
              <div class="user_account_msg">
                <el-form :model="user" label-width="auto" class="user_form ">
                  <el-form-item class="user_option">
                    <label class="user_lable" slot="label"><b>账号：</b></label>
                    <el-input v-model="user.no" :disabled="true"></el-input>
                  </el-form-item>
                  <el-form-item class="user_option">
                    <label class="user_lable" slot="label"><b>昵称：</b></label>
                    <el-input v-model="user.nickname" :disabled="management"></el-input>
                  </el-form-item>
                  <el-form-item class="user_option">
                    <label class="user_lable" slot="label"><b>简介：</b></label>
                    <el-input type="textarea" v-model="user.brief" maxlength="300"
                              show-word-limit :disabled="management"></el-input>
                  </el-form-item>
                  <el-form-item class="user_option">
                    <label class="user_lable" slot="label"><b>注册时间：</b></label>
                    <el-date-picker v-model="user.createTime" type="date" :disabled="true">
                    </el-date-picker>
                  </el-form-item>
                </el-form>
              </div>
              <div class="blank_30"></div>
              <el-divider class="accmsg" content-position="left">
                <h2>个人信息</h2>
              </el-divider>
              <div class="user_msg">
                <el-form :model="user" label-width="auto" class="user_form">
                  <el-form-item class="user_option">
                    <label class="user_lable" slot="label"><b>姓名：</b></label>
                    <el-input v-model="user.name" :disabled="management"></el-input>
                  </el-form-item>
                  <el-form-item class="user_option">
                    <label class="user_lable" slot="label"><b>生日：</b></label>
                    <el-date-picker v-model="user.birthday" type="date" :disabled="management">
                    </el-date-picker>
                  </el-form-item>
                  <el-form-item class="user_option">
                    <label class="user_lable" slot="label"><b>性别：</b></label>
                    <el-radio-group v-model="user.gender" :disabled="management">
                      <el-radio :label="1" border>男</el-radio>
                      <el-radio :label="2" border>女</el-radio>
                    </el-radio-group>
                  </el-form-item>
                  <el-form-item class="user_option">
                    <label class="user_lable" slot="label"><b>职 业：</b></label>
                    <el-input v-model="user.job" :disabled="management"></el-input>
                  </el-form-item>
                  <el-form-item class="user_option">
                    <label class="user_lable" slot="label"><b>现住址：</b></label>
                    <el-input v-model="user.nowAddress" :disabled="management"></el-input>
                  </el-form-item>
                  <el-form-item class="user_option">
                    <label class="user_lable" slot="label"><b>家庭住址：</b></label>
                    <el-input v-model="user.address" :disabled="management"></el-input>
                  </el-form-item>
                  <div v-if="management">
                    <el-button type="primary" class="wd_49" @click="management=false">管理</el-button>
                    <el-button type="warning" class="wd_49" @click="updatepassword">修改密码</el-button>
                  </div>
                  <el-button v-if="!management" type="primary" class="wd_49" @click="modify">修改</el-button>
                  <el-button v-if="!management" type="info" class="wd_49" @click="management=true">取消</el-button>
                </el-form>
                <div class="blank_50"></div>
              </div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="个人收藏" name="second">
            <div class="blank_500 f_l"></div>
            <div class="user_message">
              <el-tabs :tab-position="tabWhere" @tab-click="getCollection(colType)" v-model="colType">
                <el-tab-pane label="笔  记" name="1">
                  <div v-for="collectionNote in collectionNotes" @click="seeNote(collectionNote.nno)">
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
                <el-tab-pane label="笔记本" name="2">
                  <div v-for="collectionNotebook in collectionNotebooks" @click="seeNotebook(collectionNotebook.bno)">
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
            </div>
          </el-tab-pane>
          <el-tab-pane label="点赞" name="third">
            <h3 class="ta_c mgb_20">我的点赞</h3>
            <el-row>
              <el-col :span="2">
                <div class="blank_500"></div>
              </el-col>
              <el-col :span="20">
                <div v-for="goodNote in goodNotes" @click="seeNote(goodNote.nno)">
                  <el-row>
                    <el-col :span="18">
                      <b>{{ goodNote.nname }}</b>
                    </el-col>
                    <el-col :span="2">
                      <i class="el-icon-reading"></i> {{ goodNote.nreadNum }}
                    </el-col>
                    <el-col :span="2">
                      <i class="el-icon-jxy-good"></i> {{ goodNote.ngoodNum }}
                    </el-col>
                    <el-col :span="2">
                      <i class="el-icon-jxy-shoucang"></i> {{ goodNote.ncollectionNum }}
                    </el-col>
                  </el-row>
                  <hr>
                </div>
              </el-col>
              <el-col :span="2"></el-col>
            </el-row>
          </el-tab-pane>
        </el-tabs>
      </el-col>
      <el-col :span="4">
        <AboutMe></AboutMe>
        <div v-if="choose" class="chooseHeader ta_c">
          <h2 class="mgt_20 mgb_30">请选择头像</h2>
          <el-row class="img">
            <el-col :span="2">
              <div class="blank_30"></div>
            </el-col>
            <el-col :span="20">
              <div class="cov">
                <img src="http://localhost:8888/img/user_head/10001.jpeg"/>
                <el-radio v-model="cover" label="http://localhost:8888/img/user_head/10001.jpeg">风筝小熊
                </el-radio>
              </div>
              <div class="cov">
                <img src="http://localhost:8888/img/user_head/default_head_img.jpg"/>
                <el-radio v-model="cover" label="http://localhost:8888/img/user_head/default_head_img.jpg">绅士
                </el-radio>
              </div>
              <div class="cov">
                <img src="http://localhost:8888/  img/user_head/10002.jpg"/>
                <el-radio v-model="cover" label="http://localhost:8888/img/user_head/10002.jpg">虎皮团子</el-radio>
              </div>
              <div class="cov">
                <img src="http://localhost:8888/img/user_head/10003.jpg"/>
                <el-radio v-model="cover" label="http://localhost:8888/img/user_head/10003.jpg">棕熊</el-radio>
              </div>
              <div class="cov">
                <img src="http://localhost:8888/img/user_head/10004.jpg"/>
                <el-radio v-model="cover" label="http://localhost:8888/img/user_head/10004.jpg">可爱</el-radio>
              </div>
              <div class="cov">
                <img src="http://localhost:8888/img/user_head/10005.jpg"/>
                <el-radio v-model="cover" label="http://localhost:8888/img/user_head/10005.jpg">囧熊</el-radio>
              </div>
              <div class="cov">
                <img src="http://localhost:8888/img/user_head/10006.jpg"/>
                <el-radio v-model="cover" label="http://localhost:8888/img/user_head/10006.jpg">qsc</el-radio>
              </div>
              <div class="cov">
                <img src="http://localhost:8888/img/user_head/10007.jpg"/>
                <el-radio v-model="cover" label="http://localhost:8888/img/user_head/10007.jpg">zse</el-radio>
              </div>
              <div class="cov">
                <img src="http://localhost:8888/img/user_head/10008.jpg"/>
                <el-radio v-model="cover" label="http://localhost:8888/img/user_head/10008.jpg">狗狗</el-radio>
              </div>
            </el-col>
            <el-col :span="2"></el-col>
          </el-row>
          <el-button class="wd_49" @click="choose=false">取消</el-button>
          <el-button class="wd_49" type="primary" @click="chooseHeader()">选择</el-button>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Menu from '@/components/Menu'
import AboutMe from '@/components/AboutMe'
import '../../assets/css/notebook_online.css'
import FeedBackPlugin from "@/components/FeedBackPlugin";

export default {
  name: "User",
  data() {
    return {
      headUrl: '',
      user: [],
      management: true,
      activeTab: 'first',
      tabWhere: 'top',
      colType: '1',
      collectionNotebooks: [],
      collectionNotes: [],
      goodNotes: [],
      choose: false,
      cover: ''
    }
  },
  methods: {
    getMsg() {
      this.user = this.$store.getters.getUser;
      if (this.user == '') {
        this.$message({
          showClose: true,
          message: '请先登录',
          type: 'error'
        });
        window.location.href = "/login";
      }
      this.headUrl = 'background:url(' + this.user.headUrl + ')';
    },
    modify() {
      let user = this.user;
      let params = new URLSearchParams();
      params.append("no", user.no);
      params.append("nickname", user.nickname);
      params.append("brief", user.brief);
      params.append("name", user.name);
      params.append("birthday", user.birthday);
      params.append("gender", user.gender);
      params.append("job", user.job);
      params.append("address", user.address);
      params.append("nowAddress", user.nowAddress);
      this.$axios.put("updateUser", params).then(res => {
        if (res.data) {
          this.$message({
            showClose: true,
            message: '修改成功',
            type: 'success'
          });
          window.location.href = "/user"
        }
      }).catch(error => {
        this.$message({
          showClose: true,
          message: '系统出错，请反馈',
          type: 'error'
        });
        window.location.href = "/user"
      })
      this.$store.commit("SET_USER", user);
    },
    updatepassword() {
      window.location.href = "/forget";
    },
    getCollection(colType) {
      if (colType == 1) {
        this.getCollectionNotes();
      } else {
        this.getCollectionNotebooks();

      }
    },
    getCollectionNotes() {
      let no = this.user.no;
      this.$axios.get("getColNote?no=" + no).then(res => {
        this.collectionNotes = res.data;
      }).catch(error => {
        console.log(error)
      })
    },
    getCollectionNotebooks() {
      let no = this.user.no;
      this.$axios.get("getColNotebook?no=" + no).then(res => {
        this.collectionNotebooks = res.data;
      }).catch(error => {
        console.log(error)
      })
    },
    seeNote(nno) {
      window.location.href = "/seeNote/" + nno;
    },
    seeNotebook(no) {
      window.location.href = "/seeNotebook/" + no;
    },
    getGoodNotes() {
      let no = this.user.no;
      this.$axios.get("getGoodNotes?no=" + no).then(res => {
        this.goodNotes = res.data;
      }).catch(error => {
        console.log(error)
      })
    },
    chooseHeader() {
      let params = new URLSearchParams();
      params.append("headUrl", this.cover);
      params.append("no", this.user.no);
      this.$axios.put("/updateHeadUrl", params).then(res => {
        if (res.data) {
          this.choose = false;
          this.user.headUrl = this.cover;
          this.headUrl = 'background:url(' + this.cover + ')';
          this.$store.commit("SET_USER", this.user);
        }
      }).catch(error => {
        console.log(error)
      })
    },
    exit(){
      this.$store.commit("REMOVE_STATE");
      window.location.href="/login"
    }
  },
  created() {
    this.getMsg();
    this.getCollectionNotes();
    this.getGoodNotes();
  },
  components: {
    FeedBackPlugin,
    Menu,
    AboutMe
  }
}
</script>

<style>
.user {
  text-align: left;
  font-family: Georgia, 'Times New Roman', Times, serif;
}

.user_content {
  background: #ffffff;
}

.user_head {
  width: 100%;
  height: 200px;
}

.user_header {
  float: left;
  width: 150px;
  margin-left: 10%;
}

.user_header img {
  width: 150px;
  height: 150px;
  margin-top: 25px;
  border-radius: 50%;
  border: 1px solid #45D4FB;
}

.user_name {
  float: left;
  width: 60%;
  height: 100%;
  margin-top: 30px;
  margin-left: 30px;
}

.user_message {
  width: 80%;
  margin-left: 10%;
}

.user_lable {
  font-size: 18px;
}

.notebookImg {
  width: 120px;
  height: 150px;
}

.date /deep/ .el-input__inner {
  border: none !important;
  width: 150px;
}


.chooseHeader {
  width: 60%;
  height: 600px;
  border-radius: 20px;
  position: fixed;
  top: 100px;
  left: 20%;
  background-color: #fff;
  box-shadow: 0 0 10px #4facfe;
  z-index: 10;
}

.cov {
  width: 120px;
  height: 180px;
  float: left;
  margin-right: 25px;
  margin-bottom: 20px;
}

.chooseHeader img {
  width: 120px;
  height: 120px;
  border-radius: 50%;
}
</style>