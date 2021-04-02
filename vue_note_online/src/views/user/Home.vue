<template>
  <div class="home">
    <AboutMe></AboutMe>
    <FeedBackPlugin></FeedBackPlugin>
    <div class="blank_100"></div>
    <div class="home_head">
      <el-popover placement="right" width="200" trigger="hover" content="点击头像进入个人中心">
        <img slot="reference" :src="user.headUrl" @click="userCenter"/>
      </el-popover>
      <h1><b>{{ user.nickname }}</b></h1>
      <p class="fs_20">{{ user.brief }}</p>
    </div>

    <div class="home_menu">
      <div class="home_block" @click="notebook">
        <i class="el-icon-reading"></i>
        <p>笔记本</p>
      </div>
      <div class="home_block" @click="classifyType">
        <i class="el-icon-folder"></i>
        <p>归档</p>
      </div>
<!--      <div class="home_block h_hover">-->
<!--        <el-tooltip class="item" effect="light" content="时间归档" placement="top">-->
<!--          <div class="top_i" @click="classifyTime">-->
<!--            <i class="el-icon-folder"></i>-->
<!--          </div>-->
<!--        </el-tooltip>-->
<!--        <el-tooltip class="item" effect="light" content="标签归档" placement="bottom">-->
<!--          <div class="bottom_p" @click="classifyType">-->
<!--            <p>归档</p>-->
<!--          </div>-->
<!--        </el-tooltip>-->
<!--      </div>-->
      <div class="home_block h_hover">
        <el-tooltip class="item" effect="light" content="笔记本查找" placement="top">
          <div class="top_i" @click="searchNotebook">
            <i class="el-icon-search"></i>
          </div>
        </el-tooltip>
        <el-tooltip class="item" effect="light" content="笔记查找" placement="bottom">
          <div class="bottom_p" @click="searchNote">
            <p>查找</p>
          </div>
        </el-tooltip>
      </div>
      <div class="home_block home_block_friend" @click="friends">
        <i class="el-icon-user"></i>
        <p>好友</p>
      </div>
      <div class="home_block home_block_message" @click="message">
        <i class="el-icon-chat-square"></i>
        <p>消息</p>
      </div>
    </div>
  </div>
</template>

<script>
import '@/assets/css/notebook_online.css';
import noteOnline from "@/assets/js/noteOnline";
import AboutMe from "@/components/AboutMe";
import FeedBackPlugin from "@/components/FeedBackPlugin";

export default {
  name: "Home",
  components: {FeedBackPlugin, AboutMe},
  data() {
    return {
      user: {}
    }
  },
  methods: {
    getUser() {
      this.user = this.$store.getters.getUser;
      if(this.user==""){
        window.location.href="/login"
      }
    },
    notebook() {
      window.location.href = "/notebook";
    },
    classifyTime() {
      window.location.href = "/classifyByTime";
    },
    classifyType() {
      window.location.href = "/classifyByType";
    },
    searchNotebook() {
      window.location.href = "/searchNotebook";
    },
    searchNote() {
      window.location.href = "/searchNote";
    },
    friends() {
      window.location.href = "/friend/friendList/";
    },
    userCenter() {
      window.location.href = "/user";
    },
    message() {
      window.location.href = "/message";
    }
  },
  created() {
    this.getUser();
  }
}
</script>

<style scoped>
.home {
  background-color: rgba(32, 140, 255, 0.1);
  height: 753px;
}

.home_head {
  margin-bottom: 20px;
  text-align: center;
}

.home_head img {
  width: 150px;
  height: 150px;
  border: 3px solid #ffffff;
  border-radius: 50%;
  margin-bottom: 20px;
}

.home_head h1 {
  margin-bottom: 10px;
}

.home_head hr {
  width: 20%;
  height: 2px;
  margin-left: 40%;
  background-color: #ffffff;
}

.home_menu {
  margin-top: 50px;
  margin-left: 10%;
  width: 80%;
  height: 160px;
}

.home_block {
  float: left;
  width: 15%;
  height: 160px;
  margin-right: 6.25%;
  font-size: 25px;
  background-color: #eff;
  border-radius: 10%;
}

.home_block_message {
  margin-right: 0;
}

.home_block i {
  margin-top: 25px;
  font-size: 50px;
}

.top_i {
  height: 80px;
  width: 100%;
  border-top-left-radius: 16px;
  border-top-right-radius: 16px;
}

.bottom_p {
  height: 80px;
  width: 100%;
  border-bottom-left-radius: 16px;
  border-bottom-right-radius: 16px;
}

.h_hover :hover {
  background: #e0f0f0;
}
</style>