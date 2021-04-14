<template>
  <div class="aboutMe">
    <el-tooltip class="item" effect="light" content="关于我" placement="left">
      <el-button type="primary" icon="el-icon-phone" plain circle @click="open=!open"></el-button>
    </el-tooltip>
    <div class="myMsg ta_l" v-if="open">
      <h2 class="ta_c mgb_20">关于我</h2>
      <p>
        本项目是我的大学毕业设计,是一个基于SpringBoot+Vue+MySql的在线笔记本；
        毕业设计选择这个题目的原因是：自己在学习时有做笔查找笔记的情况。
        查找了目前主流的笔记应用，发现大部分都是本地应用，且流行的全为本地应用。
        虽然能实现远程同步，但需要在本地设备上安装相应的App。有极大的局限性。
        所以，在毕业设计选题时选择了这个题目。
      </p><br>
      <h3 class="ta_c mgt_30 mgb_20">本人联系方式</h3>
      <p>QQ:1551903505<i class="el-icon-phone mgl_20"></i>:183****9891</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "AboutMe",
  data() {
    return {
      open: false,
    }
  },
}
</script>

<style scoped>
.aboutMe {
  position: fixed;
  left: 20px;
  bottom: 20px;
  width: 50px;
  height: 50px;
}

.myMsg {
  position: fixed;
  left: 20px;
  bottom: 80px;
  width: 300px;
  height: 500px;
  color: #ffffff;
  padding: 20px;
  border-radius: 20px;
  background: rgba(25, 154, 248, 0.8);
}

.myMsg h2,h3 {
  color: #ffffff;
}
</style>