<template>
  <div class="adminMenu">
    <el-menu
        class="el-menu-vertical-demo"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b">
      <el-menu-item class="menu_item" index="0">
        <img :src="logo" alt="logo"/><br/>
        <p>{{ admin.name }}</p>
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
      <el-submenu index="2" v-if="admin.id!==1">
        <template slot="title">
          <i class="el-icon-jxy-jubao"></i>
          <span>违规处理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="2-1" @click="jump(3)">违规处理</el-menu-item>
          <el-menu-item index="2-3" @click="jump(4)">处理记录</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-menu-item index="3" @click="jump(5)" v-if="admin.id!==1">
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
      <el-menu-item index="6" @click="jump(8)">
        <i class="el-icon-document-delete"></i>
        <span slot="title">敏感词汇管理</span>
      </el-menu-item>
      <el-submenu index="7">
        <template slot="title">
          <i class="el-icon-chat-line-round"></i>
          <span>个人信息</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="7-1" @click="jump(9)">查看修改</el-menu-item>
          <el-menu-item index="7-2" @click="jump(10)">修改密码</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="8" v-if="admin.id===1">
        <template slot="title">
          <i class="el-icon-s-check"></i>
          <span>管理员管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="7-1" @click="jump(11)">管理员管理</el-menu-item>
          <el-menu-item index="7-2" @click="jump(12)">生成注册码</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-menu-item index="8" @click="jump(13)">
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
      logo: logo,
      admin: ''
    }
  },
  methods: {
    jump(no) {
      if (no === 1) {
        window.location.href = "/admin"
      } else if (no === 2) {
        window.location.href = "/admin/userData"
      } else if (no === 3) {
        window.location.href = "/admin/noteAudit";
      } else if (no === 4) {
        window.location.href = "/admin/auditLog";
      } else if (no === 5) {
        window.location.href = "/admin/appealAudit";
      } else if (no === 6) {
        window.location.href = "/admin/releaseMessage";
      } else if (no === 7) {
        window.location.href = "/admin/checkFeedback";
      } else if (no === 8) {
        window.location.href = "/admin/shieldManage";
      } else if (no === 9) {
        window.location.href = "/admin/adminUpdate";
      } else if (no === 10) {
        window.location.href = "/admin/adminPwd";
      } else if (no === 11) {
        window.location.href = "/admin/adminManage";
      } else if (no === 12) {
        window.location.href = "/admin/addRNum";
      } else if (no === 13) {
        this.$store.commit("REMOVE_ADMIN");
        window.location.href = "/admin/login";
      }
    }
  },
  created() {
    let admin = this.$store.getters.getAdmin;
    this.admin = admin;
    if (admin == null || admin === "") {
      alert("请先登录管理员账号")
      window.location.href = '/admin/login'
    }
  }
}
</script>

<style scoped>
.adminMenu {
  height: 703px;
  text-align: left;
}

.el-menu-vertical-demo {
  height: 703px;
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