<template>
  <div class="adminUpdate">
    <el-row>
      <el-col :span="4">
        <AdminMenu></AdminMenu>
      </el-col>
      <el-col :span="20">
        <form>
          <input type="file" @change="getFile($event)">
          <button class="button button-primary button-pill button-small" @click="submit($event)">提交</button>
        </form>
        <el-button @click="del">删除</el-button>
        <el-button @click="get">获取</el-button>
        <img :src="src" width="200px" alt="验证码">
        <img :src="head" width="200px" alt="验证码">
      </el-col>
    </el-row>
  </div>
</template>

<script>
import AdminMenu from "@/components/AdminMenu";
import noteOnline from "@/assets/js/noteOnline";

export default {
  name: "AdminUpdate",
  data() {
    return {
      file: '',
      src: '',
      head: ''
    }
  },
  methods: {
    getFile: function (event) {
      this.file = event.target.files[0];
      console.log(this.file);
    },
    submit: function (event) {
      //阻止元素发生默认的行为
      event.preventDefault();
      let formData = new FormData();
      formData.append("file", this.file);
      this.$axios.post('/upload', formData)
          .then(function (response) {
            console.log(response);
            window.location.reload();
          })
          .catch(function (error) {
            console.log(error);
            window.location.reload();
          });
    },
    get() {
      // let headUrl = this.$store.getters.getUser.headUrl;
      // this.$axios({
      //   method: 'get',
      //   url: '/getUserHead?headUrl=' + headUrl,
      //   responseType: 'arraybuffer'
      // }).then(response => {
      //   this.src = 'data:image/jpeg;base64,' + this.arrayBufferToBase64(response.data)
      //   this.head=this.src;u
      //   this.$store.commit("SET_USERHEAD", this.src);
      // })
    },
    // arrayBufferToBase64(buffer) {
    //   let binary = ''
    //   let bytes = new Uint8Array(buffer)
    //   let len = bytes.byteLength
    //   for (let i = 0; i < len; i++) {
    //     binary += String.fromCharCode(bytes[i])
    //   }
    //   return window.btoa(binary)
    // },
    del() {
      this.$store.commit("REMOVE_STATE");
    }
  },
  created() {
    this.head = this.$store.getters.getUserHead;
  },
  components: {
    AdminMenu
  }
}
</script>

<style scoped>
.adminUpdate {
  background-color: rgba(32, 140, 255, 0.1);
  position: absolute;
  width: 100%;
  height: 100%;
}
</style>