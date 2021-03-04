<template>
  <div class="notenook_menu">
    <el-menu class="notebook_menu_menu" background-color="#4facfe" text-color="#fff" mode="horizontal">
      <el-menu-item index="0" class="icon">
        <span style="color: aqua">在线笔记</span>
      </el-menu-item>
      <el-menu-item index="1" class="menu"><a href="/">首页</a></el-menu-item>
      <el-menu-item index="2" class="menu"><a href="/notebook">笔记本</a></el-menu-item>
      <el-menu-item index="4" class="menu"><a href="/classifyByType">标签归档</a></el-menu-item>
<!--      <el-submenu index="4" class="menu">-->
<!--        <template slot="title">归档</template>-->
<!--        <el-menu-item index="4-1"><a href="/classifyByTime"><p class="wd_100">时间归档</p></a></el-menu-item>-->
<!--        <el-menu-item index="4-2"><a href="/classifyByType"><p class="wd_100">标签归档</p></a></el-menu-item>-->
<!--      </el-submenu>-->
      <el-submenu index="5" class="menu">
        <template slot="title">搜索</template>
        <el-menu-item index="5-1"><a href="/searchNotebook"><p class="wd_100">笔记本搜索</p></a></el-menu-item>
        <el-menu-item index="5-2"><a href="/searchNote"><p class="wd_100">笔记搜索</p></a></el-menu-item>
      </el-submenu>
      <el-menu-item index="5" class="menu"><a href="/friend/friendList/" target="_blank">好友</a></el-menu-item>
      <el-menu-item index="5" class="menu"><a href="/message" target="_blank">消息</a></el-menu-item>
      <a href="/user" target="_blank" class="menu_head_img"><img :src="user.headUrl" width="50px" height="50px"/></a>
    </el-menu>
  </div>
</template>

<script>
import '@/assets/css/notebook_online.css';

export default {
  name: 'Menu',
  data() {
    return {
      user: {}
    }
  },
  methods: {
    getUser() {
      this.user = this.$store.getters.getUser;
      if (this.user =="") {
        alert("请先登录")
        window.location.href = "/login";
      }
    },
  },
  created() {
    this.getUser()
  },
}
</script>

<style scoped>
.notenook_menu {
  height: 60px;
  width: 100%;
}

.notebook_menu_menu {
  width: 100%;
  position: fixed;
  z-index: 10;
}

a {
  color: #ffffff;
}
</style>
