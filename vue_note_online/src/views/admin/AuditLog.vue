<template>
  <div class="aduitLog">
    <el-row>
      <el-col :span="4">
        <AdminMenu></AdminMenu>
      </el-col>
      <el-col :span="20">
        <el-row>
          <el-col :span="2">
            <div class="blank_30"></div>
          </el-col>
          <el-col :span="20">
            <h1 class="mgt_20 mgb_30">笔记违规处理记录</h1>
            <div class="hg_600">
              <el-table :data="irrs" stripe style="width: 100%">
                <el-table-column type="index"></el-table-column>
                <el-table-column prop="inName" label="笔记标题" width="180"></el-table-column>
                <el-table-column prop="irType" label="违规类型" width="180"></el-table-column>
                <el-table-column prop="ibody" label="违规描述"></el-table-column>
                <el-table-column prop="res" label="处理结果"></el-table-column>
                <el-table-column prop="iaTime" label="处理时间"></el-table-column>
              </el-table>
            </div>
            <el-pagination background layout="prev, pager, next, jumper"
                           :total="nums" @current-change="updatePage">
            </el-pagination>
          </el-col>
          <el-col :span="2"></el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import AdminMenu from "@/components/AdminMenu";

export default {
  name: "AduitLog",
  data() {
    return {
      left: 'top',
      irrs: [],
      nums: 0
    }
  },
  methods: {
    getIrrLog(page) {
      this.$axios.get("/selectIrrLogs", {
        params: {
          page: page
        }
      }).then(res => {
        this.nums = res.data[0];
        this.irrs = res.data[1]
      }).catch(error => {
        console.log(error)
      })
    },
    updatePage(page) {
      this.getIrrLog(page);
    }
  },
  components: {AdminMenu},
  created() {
    this.getIrrLog(1);
  }
}
</script>

<style scoped>
.aduitLog{
  overflow: hidden;
  background-color: rgba(200, 200, 200, 0.1);
}
</style>