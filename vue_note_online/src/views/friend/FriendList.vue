<template>
  <div class="friend">
    <Menu></Menu>
    <el-row>
      <el-col :span="2">
        <div class="blank_30"></div>
      </el-col>
      <el-col :span="20">
        <div class="header" :style="background">
          <el-row>
            <el-col :span="2">
              <div class="blank_50"></div>
            </el-col>
            <el-col :span="4">
              <img :src="user.headUrl"/>
            </el-col>
            <el-col :span="12" class="ta_l">
              <p class="mgt_50"><b>{{ user.nickname }}</b></p>
              <p class="mgt_20"><b>{{ user.brief }}</b></p>
            </el-col>
            <el-col :span="6"></el-col>
          </el-row>
        </div>
        <div class="friendList mgt_20">
          <el-row>
            <el-col :span="6" class="left">
              <h2><b><i class="el-icon-jxy-Friends">我的好友</i></b></h2>
              <hr>
              <el-button icon="el-icon-tickets" class="wd_90" type="primary" plain @click="chooseWhat(1)">全部关注
              </el-button>
              <br>
              <el-button icon="el-icon-jxy-iconhuxiangguanzhu" class="wd_90" type="primary" plain @click="chooseWhat(2)">互相关注
              </el-button>
              <br>
              <el-button icon="el-icon-jxy-guanzhu" class="wd_90" type="primary" plain @click="chooseWhat(3)">我的关注
              </el-button>
              <br>
              <el-button icon="el-icon-jxy-guanzhu1" class="wd_90" type="primary" plain @click="chooseWhat(4)">关注我的
              </el-button>
              <br>
              <el-button icon="el-icon-search" class="wd_90" type="primary" plain @click="searchUser">添加好友
              </el-button>
            </el-col>
            <el-col :span="18" class="right ta_l">
              <h1><b>{{title}}</b></h1>
              <hr>
              <div v-for="friend in show">
                <el-row class="fri mgb_20">
                  <el-col :span="3">
                    <img @click="see(friend.no)" :src="friend.headUrl" alt="好友头像"/>
                  </el-col>
                  <el-col :span="18">
                    <div @click="see(friend.no)">
                      <b>{{ friend.nickname }}</b>
                      <p class="blank_50 o_h">{{ friend.brief }}</p>
                    </div>
                  </el-col>
                  <el-col :span="3">
                    <el-button type="primary" plain v-if="delOrAdd==4" icon="el-icon-circle-plus"
                               @click="goEach(friend)"></el-button>
                    <el-button type="primary" plain v-else icon="el-icon-remove"
                               @click="remove(friend)"></el-button>
                  </el-col>
                </el-row>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-col>
      <el-col :span="2">
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Menu from '@/components/Menu';
import noteOnline from "@/assets/js/noteOnline";

export default {
  name: "Friend",
  data() {
    return {
      background: '',
      user: {},
      friends: [],
      each: [],
      careMe: [],
      Mycare: [],
      show: [],
      delOrAdd: 1,
      title:''
    }
  },
  methods: {
    getMsg() {
      this.user = this.$store.getters.getUser;
      this.background = 'background:url(' + this.user.headUrl + ')';
      this.selectEachOther(this.user.no);
    },
    selectEachOther(no) {
      this.$axios.get("/selectEachOther", {
        params: {
          no: no
        }
      }).then(res => {
        this.each = res.data;
        this.friends = this.friends.concat(this.each);
        this.selectCareMe(this.user.no);
      }).catch(error => {
        console.log(error);
        this.$message({
          showClose: true,
          message: '获取信息失败，请重试',
          type: 'error'
        });
        window.location.href = "/";
      })
    },
    selectCareMe(no) {
      this.$axios.get("/selectCareMe", {
        params: {
          no: no
        }
      }).then(res => {
        this.careMe = res.data;
        this.selectMyCare(this.user.no);
      }).catch(error => {
        console.log(error);
        this.$message({
          showClose: true,
          message: '获取信息失败，请重试',
          type: 'error'
        });
        window.location.href = "/";
      })
    },
    selectMyCare(no) {
      this.$axios.get("/selectMyCare", {
        params: {
          no: no
        }
      }).then(res => {
        this.Mycare = res.data;
        this.friends = this.friends.concat(this.Mycare);
        this.chooseWhat(1);
      }).catch(error => {
        console.log(error);
        this.$message({
          showClose: true,
          message: '获取信息失败，请重试',
          type: 'error'
        });
        window.location.href = "/";
      })
    },
    searchUser(){
      window.location.href="/friend/searchUser"
    },
    chooseWhat(no) {
      if (no === 1) {
        this.delOrAdd = 1;
        this.title="全部关注";
        this.show = this.friends;
      } else if (no === 2) {
        this.delOrAdd = 2;
        this.title="互相关注";
        this.show = this.each;
      } else if (no === 3) {
        this.delOrAdd = 3;
        this.title="我的关注";
        this.show = this.Mycare;
      } else if (no === 4) {
        this.delOrAdd = 4;
        this.title="关注我的";
        this.show = this.careMe;
      }
    },
    remove(friend) {
      let index = this.Mycare.indexOf(friend);
      if (index >= 0) {
        this.delRelationship(friend);
      } else {
        this.cancelEach(friend);
      }
    },
    goEach(friend) {
      let params = new URLSearchParams();
      params.append("aNo", friend.no);
      params.append("bNo", this.user.no);
      this.$axios.put("/goEach", params).then(res => {
        if (res.data) {
          let index = this.careMe.indexOf(friend);
          this.careMe.splice(index, 1);
          this.each.push(friend);
          this.friends.push(friend);
        }
      }).catch(error => {
        console.log(error);
        this.$message({
          showClose: true,
          message: '关注失败，请稍后重试',
          type: 'error'
        });
      })
    },
    cancelEach(friend) {
      let params = new URLSearchParams();
      params.append("aNo", friend.no);
      params.append("bNo", this.user.no);
      this.$axios.post("/cancelEach", params).then(res => {
        if (res.data) {
          let index = this.each.indexOf(friend);
          if (index >= 0) {
            this.each.splice(index, 1);
            let i = this.friends.indexOf(friend);
            this.friends.splice(i, 1);
            this.careMe.push(friend);
          }
        }else{
          this.$message({
            showClose: true,
            message: '取消关注失败，请重新尝试',
            type: 'error'
          });
        }
      }).catch(error => {
        console.log(error);
        this.$message({
          showClose: true,
          message: '取消关注出错',
          type: 'error'
        });
      })
    },
    delRelationship(friend) {
      this.$axios.delete("/delRelationship", {
        params: {
          aNo: this.user.no,
          bNo: friend.no
        }
      }).then(res => {
        if (res.data) {
          let index = this.Mycare.indexOf(friend);
          this.Mycare.splice(index, 1);
          let index1 = this.friends.indexOf(friend);
          this.friends.splice(index1, 1);
        }
      }).catch(error => {
        console.log(error);
        this.$message({
          showClose: true,
          message: '取消关注出错',
          type: 'error'
        });
      })
    },
    see(no) {
      window.location.href="/friend/friendHome/"+no;
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
.friend {
  margin: 0;
  background-image: linear-gradient(120deg, #fdfbfb 0%, #ebedee 100%);
}

.header {
  height: 200px;
}

.header img {
  width: 120px;
  height: 120px;
  border-radius: 50%;
  margin-top: 50px;
  border: 1px solid #1994f8;
}

.friendList {
  border: 1px solid #dddddd;
  background: #ffffff;
}

.left {
  padding: 30px;
  height: 442px;
}

.left i{
  font-weight: bold;
  font-size: 18px;
}

.left button {
  border: none;
  background: none;
  border-radius: 1px;
  font-size: 18px;
  text-align: left;
  color: #000000;
}

.right {
  padding: 20px;
  border-left: 1px solid #dddddd;
}

.fri {
  height: 100px;
  border-bottom: 1px solid #aaaaaa;
}

.fri img {
  width: 80px;
  height: 80px;
  border-radius: 50%;
}
</style>