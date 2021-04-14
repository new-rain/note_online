<template>
  <div class="toTop">
<!--    <el-tooltip class="item" effect="light" content="返回顶部" placement="left">-->
<!--      <el-button class="toTop" icon="el-icon-caret-top" type="primary" plain circle @click="toTop()" circle></el-button>-->
<!--    </el-tooltip>-->


  </div>
</template>

<script>
// import 'jquery.scrollto'

export default {
  name: "ToTop",
  methods: {
    toTop() {
      document.body.scrollIntoView();
    },
  }
}
</script>

<style scoped>
/*.toTop {*/
/*  position: fixed;*/
/*  width: 50px;*/
/*  height: 50px;*/
/*  right: 20px;*/
/*  bottom: 20px;*/
/*  font-size: 20px;*/
/*}*/
</style>