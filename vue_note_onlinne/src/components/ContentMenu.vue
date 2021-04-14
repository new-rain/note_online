<template>
  <div class="contentMenu">
    <el-tooltip class="item" effect="light" content="查看目录" placement="left">
      <el-button v-if="menu_open" class="getMenu" type="primary" icon="el-icon-tickets" @click="get_menu()" plain circle></el-button>
      <el-button v-else class="getMenu" icon="el-icon-tickets" @click="close_menu()" circle></el-button>
    </el-tooltip>
    <div class="note_menu"></div>
  </div>
</template>

  <script>
  export default {
    name: "ContentMenu",
    data() {
      return {
        menu_open: true
      }
    },
    methods: {
      get_menu() {
        this.menu_open = false;
        tocbot.init({
          tocSelector: '.note_menu',
          contentSelector: '.page_content',
          headingSelector: 'h1,h2,h3',
          hasInnerContainers: true,
          collapseDepth: 0,
        });
      },
      close_menu() {
        tocbot.destroy();
        this.menu_open = true
      }
    }
  }
  </script>

<style scoped>
.getMenu {
  position: fixed;
  width: 50px;
  height: 50px;
  right: 20px;
  bottom: 80px;
  font-size: 20px;
}

.note_menu{
  background: rgba(255,255,255,0.8);
  margin-left: 10px;
  width: 15%;
  position: fixed;
  right: 10px;
  bottom: 150px;
}


.note_menu /deep/ a{
  color: #1994f8;
}

.note_menu /deep/ ol li a {
  font-size: 16px;
  margin-left: 20px;
  font-weight: bold;
}

.note_menu /deep/ li ol li a{
  font-size: 16px;
  margin-left: 40px;
  font-weight: lighter;
}

.note_menu /deep/ li ol li ol li a{
  font-size: 14px;
  margin-left: 60px;
  font-weight: lighter;
}

</style>