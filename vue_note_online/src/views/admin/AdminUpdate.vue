<template>
  <div class="adminUpdate">
    <el-row>
      <el-col :span="4">
        <AdminMenu></AdminMenu>
      </el-col>
      <el-col :span="20">
      </el-col>
    </el-row>
  </div>
</template>

<script>
import AdminMenu from "@/components/AdminMenu";

export default {
  name: "AdminUpdate",
  data() {
    return {}
  },
  methods: {},
  created() {
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