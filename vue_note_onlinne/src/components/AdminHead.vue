<template>
  <div class="adminHead">
    <h1>在线笔记本管理系统</h1>
    <el-icon class="el-icon-reading"></el-icon>
  </div>
</template>

<script>
export default {
  name: "AdminHead",
  created() {
  }
}
</script>

<style scoped>
.adminHead {
  background: #545c64;
  height: 50px;
  border-bottom: 1px solid white;
  text-align: right;
}

h1 {
  color: white;
  text-align: left;
  margin-left: 20px;
  float: left;
  font-size: 25px;
  line-height: 50px;
  font-family: zqz;
}

@font-face {
  font-family: 'zqz';
  src: url('/src/assets/font/zqz.ttf');
}

.el-icon-reading {
  color: white;
  font-weight: bold;
  font-size: 45px;
  line-height: 50px;
  text-align: right;
  margin-right: 50px;
}
</style>