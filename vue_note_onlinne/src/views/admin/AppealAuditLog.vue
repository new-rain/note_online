<template>
  <div class="appealAuditLog">
    <AdminHead></AdminHead>
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
            <h1 class="mgt_20 mgb_30">申诉处理记录</h1>
            <div class="hg_600">
              <el-table :data="appeals" stripe style="width: 100%">
                <el-table-column type="index"></el-table-column>
                <el-table-column prop="anName" label="笔记标题" width="180"></el-table-column>
                <el-table-column prop="areason" label="违规类型" width="180"></el-table-column>
                <el-table-column prop="auName" label="申诉人"></el-table-column>
                <el-table-column prop="admin" label="处理人"></el-table-column>
                <el-table-column prop="ares" label="申诉结果"></el-table-column>
                <el-table-column prop="aaTime" label="处理时间"></el-table-column>
              </el-table>
            </div>
            <el-pagination v-if="nums > 10" background layout="prev, pager, next, jumper"
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
import AdminHead from "@/components/AdminHead";

export default {
  name: "appealAuditLog",
  data() {
    return {
      left: 'top',
      appeals: [],
      nums: 0
    }
  },
  methods: {
    getAppealLog(page) {
      this.$axios.get("/selectAppealLog", {
        params: {
          page: page
        }
      }).then(res => {
        this.nums = res.data.total;
        this.appeals = res.data.list;
      }).catch(error => {
        console.log(error)
      })
    },
    updatePage(page) {
      this.getAppealLog(page);
    }
  },
  components: {AdminHead, AdminMenu},
  created() {
    this.getAppealLog(1);
  }
}
</script>

<style scoped>
.appealAuditLog {
  overflow: hidden;
  background-color: rgba(200, 200, 200, 0.1);
}
</style>
