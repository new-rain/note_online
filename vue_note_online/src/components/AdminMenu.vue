<template>
  <div class="adminMenu">
    <el-menu
        class="el-menu-vertical-demo"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b">
      <el-menu-item class="menu_item" index="0">
        <img :src="logo"/>
        <p>管理员</p>
      </el-menu-item>
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-s-marketing"></i>
          <span>数据分析</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="1-1" @click="jump(1)">笔记数据分析</el-menu-item>
          <el-menu-item index="1-2" @click="jump(2)">用户数据分析</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-jxy-jubao"></i>
          <span>违规处理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="2-1" @click="jump(3)">违规处理</el-menu-item>
          <el-menu-item index="2-3" @click="jump(4)">处理记录</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-menu-item index="3" @click="jump(5)">
        <i class="el-icon-bell"></i>
        <span slot="title">申诉处理</span>
      </el-menu-item>
      <el-menu-item index="4" @click="jump(6)">
        <i class="el-icon-document"></i>
        <span slot="title">信息发布</span>
      </el-menu-item>
      <el-menu-item index="5" @click="jump(7)">
        <i class="el-icon-chat-line-round"></i>
        <span slot="title">用户反馈</span>
      </el-menu-item>
      <el-menu-item index="5" @click="jump(8)">
        <i class="el-icon-upload2"></i>
        <span slot="title">退出管理员</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import logo from '@/assets/images/logo.png'

export default {
  name: "AdminMenu",
  data() {
    return {
      logo: logo
    }
  },
  methods: {
    jump(no) {
      if (no == 1) {
        window.location.href = "/admin"
      } else if (no == 2) {
        window.location.href = "/admin/userData"
      } else if (no == 3) {
        window.location.href = "/admin/noteAudit";
      } else if (no == 4) {
        window.location.href = "/admin/auditLog";
      } else if (no == 5) {
        window.location.href = "/admin/appealAudit";
      } else if (no == 6) {
        window.location.href = "/admin/releaseMessage";
      } else if (no == 7) {
        window.location.href = "/admin/checkFeedback";
      } else if (no == 8) {
        this.$store.commit("REMOVE_ADMIN");
        window.location.href = "/admin/login";
      }
    }
  },
  created() {
    const admin = this.$store.getters.getAdmin;
    if (admin != 'note_online_admin') {
      alert("请先登录管理员账号")
      window.location.href = '/admin/login'
    }
  }
}
</script>

<style scoped>
.adminMenu {
  height: 753px;
  text-align: left;
}

.el-menu-vertical-demo {
  height: 753px;
}

.menu_item {
  height: 160px;
  text-align: center;
}

.menu_item img {
  width: 90px;
  height: 90px;
  border-radius: 50%;
  margin-top: 25px;
}

.menu_item p {
  font-weight: bold;
  font-size: 15px;
}
</style>