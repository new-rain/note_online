<template>
  <div class="searchUser">
    <Menu></Menu>
    <div class="bk_skyblue"></div>
    <div class="blank_66_700"></div>
    <el-row>
      <el-col :span="4">
        <div class="blank_30"></div>
      </el-col>
      <el-col :span="16">
        <h1 class="ta_c mgt_30">查找好友</h1>
        <div class="mgt_20">
          <el-input class="wd_90" v-model="keyWord" placeholder="请输入搜索内容"
                    @keyup.enter.native="searchUser(keyWord,1)"></el-input>
          <el-button class="el-icon-search" @click="searchUser(keyWord,1)"></el-button>
        </div>
        <hr class="mgt_20">
        <div v-if="!doSearch">
          <p>请输入数据进行查找</p><br>
          <img :src="search"/>
        </div>
        <div v-else-if="users.length==0">
          <div class="searchContent">
            <h2>未找到结果</h2>
            <img :src="search"/>
          </div>
        </div>
        <div v-else>
          <div v-for="user in users">
            <div @click="see(user.no)">
              <el-row>
                <el-col :span="2">
                  <div class="blank_30"></div>
                </el-col>
                <el-col :span="4">
                  <img class="userHeader" @click="see(user.no)" :src="user.headImg" alt="用户头像"/>
                </el-col>
                <el-col :span="12">
                  <div class="ta_l mgl_20 mgt_10">
                    <p>{{ user.nickname }}</p>
                    <p>{{ user.no }}</p>
                    <p>{{ user.brief }}</p>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div v-if="user.friendRe==0">
                    <el-button type="primary" plain class="mgt_30 wd_60" @click.stop="attention(user)"><i
                        class="el-icon-plus"></i> 关注
                    </el-button>
                  </div>
                  <div v-else-if="user.friendRe==2">
                    <el-button type="success" plain class="mgt_30 wd_60" @click.stop="cancelAttention(user)"><i
                        class="el-icon-check"></i> 已关注
                    </el-button>
                  </div>
                  <div v-else-if="user.friendRe==1">
                    <el-button type="success" plain class="mgt_30 wd_60" @click.stop="cancelEach(user)"><i
                        class="el-icon-jxy-iconhuxiangguanzhu"></i> 互相关注
                    </el-button>
                  </div>
                </el-col>
              </el-row>
            </div>
            <hr>
          </div>
          <el-pagination class="mgb_30 mgt_30" v-if="pageInfo[1]>10" background layout="prev, pager, next, jumper"
                         :total="pageInfo[1]" @current-change="updatePage">
          </el-pagination>
        </div>
      </el-col>
      <el-col :span="4">
        <AboutMe></AboutMe>
        <ToTop></ToTop>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Menu from "@/components/Menu";
import search from '@/assets/images/search.png';
import ToTop from "@/components/ToTop";
import AboutMe from "@/components/AboutMe";
import noteOnline from "@/assets/js/noteOnline";

export default {
  name: "SearchUser",
  data() {
    return {
      no: '',
      search: search,
      keyWord: '',
      doSearch: false,
      users: [],
      pageInfo: ''
    }
  },
  methods: {
    updatePage(page) {
      this.searchUser(this.keyWord, page)
    },
    searchUser(keyWord, page) {
      this.keyWord = keyWord;
      if (keyWord != '') {
        this.doSearch = true;
        this.$axios.get("/searchUser", {
          params: {
            keyWord: keyWord,
            no: this.no,
            page: page
          }
        }).then(res => {
          this.pageInfo = res.data;
          this.users = this.pageInfo[0];
          for (let i = 0; i < this.users.length; i++) {
            let h = this.users[i].headUrl;
            this.$axios({
              method: 'get',
              url: '/getUserHead?headUrl=' + h,
              responseType: 'arraybuffer' // 最为关键
            }).then(response => {
              this.users[i].headImg = 'data:image/jpeg;base64,' + this.arrayBufferToBase64(response.data)
            })
          }
        }).catch(error => {
          console.log(error)
        })
      } else {
        this.$message({
          showClose: true,
          message: '请输入关键字或账号',
          type: 'warning'
        });
      }
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
    see(no) {
      window.location.href = "/friend/friendHome/" + no;
    },
    attention(user) {
      let params = new URLSearchParams();
      params.append("aNo", this.no);
      params.append("bNo", user.no);
      this.$axios.post("/attention", params).then(res => {
        if (res.data) {
          let index = this.users.indexOf(user);
          this.users[index].friendRe = 2;
          this.$message({
            showClose: true,
            message: '关注成功',
            type: 'success'
          });
        }
      }).catch(error => {
        console.log(error);
        this.$message({
          showClose: true,
          message: '关注出错',
          type: 'error'
        });
      })
    },
    cancelAttention(user) {
      this.$axios.delete("/delRelationship", {
        params: {
          aNo: this.no,
          bNo: user.no
        }
      }).then(res => {
        if (res.data) {
          let index = this.users.indexOf(user);
          this.users[index].friendRe = 0;
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
    cancelEach(user) {
      let params = new URLSearchParams();
      params.append("aNo", user.no);
      params.append("bNo", this.no);
      this.$axios.post("/cancelEach", params).then(res => {
        if (res.data) {
          let index = this.users.indexOf(user);
          this.users[index].friendRe = 0;
        } else {
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
  },
  created() {
    this.no = this.$store.getters.getUserNo;
  },
  components: {
    Menu,
    ToTop,
    AboutMe,
  }
}
</script>

<style scoped>
.userHeader {
  width: 100px;
  height: 100px;
  border-radius: 50%;
}
</style>