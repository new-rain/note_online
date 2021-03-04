<template>
  <div class="user">
    <Menu></Menu>
    <div class="bk_skyblue"></div>
    <el-row>
      <el-col :span="4">
        <div class="blank_50"></div>
        {{collectionNotes}}
      </el-col>
      <el-col :span="16" class="user_content">
        <div class="user_head o_h" :style="headUrl">
          <div class="user_header">
            <img :src="user.headUrl"/>
          </div>
          <div class="user_name">
            <h1>{{ user.nickname }}</h1>
            <p>{{ user.brief }}</p>
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
            <div v-for="collectionNote in collectionNotes" @click="seeNote(collectionNote.nno)">
              {{collectionNote}}
              <hr class="pd_lr_30">
            </div>
<!--            <el-tabs :tab-position="left" @tab-click="getCollection(colType)" v-model="colType">-->
<!--              <el-tab-pane label="笔记本" name="1">笔记本</el-tab-pane>-->
<!--              <el-tab-pane label="笔  记" name="2">-->
<!--               -->
<!--              </el-tab-pane>-->
<!--            </el-tabs>-->
          </el-tab-pane>
          <el-tab-pane label="点赞" name="third">点赞</el-tab-pane>
        </el-tabs>
      </el-col>
      <el-col :span="4">
        <AboutMe></AboutMe>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Menu from '@/components/Menu'
import AboutMe from '@/components/AboutMe'
import '../../assets/css/notebook_online.css'

export default {
  name: "User",
  data() {
    return {
      headUrl: '',
      user: [],
      management: true,
      activeTab: 'first',
      left: 'left',
      colType: '1',
      collectionNotebooks: [],
      collectionNotes: [],
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
      var user = this.user;
      var that = this;
      var params = new URLSearchParams();
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
      } else {
        this.getCollectionNotes();
      }
    },
    getCollectionNotes() {
      const no = this.user.no;
      const that = this;
      this.$axios.get("getColNote?no=" + no).then(res => {
        that.collectionNotes = res.data;
      }).catch(error => {

      })
    },
    seeNote(nno){
      window.location.href = "/seeNote/" + nno;
    }
  },
  created() {
    this.getMsg();
    this.getCollectionNotes();
  },
  components: {
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
</style>