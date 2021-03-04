<template>
  <div class="appealAudit">
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
            <h1 class="mgt_20 mgb_30">笔记违规处理</h1>
            <div class="hg_600">
              <el-table :data="pageInfo.list" stripe style="width: 100%">
                <el-table-column type="index"></el-table-column>
                <el-table-column prop="anNo" label="笔记编号" width="180"></el-table-column>
                <el-table-column prop="anName" label="笔记标题" width="180"></el-table-column>
                <el-table-column prop="areason" label="违规类型" width="180"></el-table-column>
                <el-table-column prop="auName" label="申诉人"></el-table-column>
                <el-table-column prop="abody" label="申诉描述"></el-table-column>
                <el-table-column align="center">
                  <template slot="header">
                    <p>处理</p>
                  </template>
                  <template slot-scope="scope">
                    <el-button size="mini" type="text" @click="check(scope.row)">查看处理</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </div>
            <el-pagination v-if="pageInfo.size>10" background layout="prev, pager, next, jumper"
                           :total="pageInfo.size" @current-change="updatePage">
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
  name: "AppealAudit",
  data() {
    return {
      pageInfo: {},
      appeals: []
    }
  },
  methods: {
    getAppeals(page) {
      this.$axios.get("/selectAppeals", {
        params: {
          page: page
        }
      }).then(res => {
        this.pageInfo = res.data;
      }).catch(error => {
        this.$message.error("系统错误，请反馈");
      })
    },
    updatePage(page) {
      this.getAppeals(page);
    },
    check(row) {
      window.location.href = "/admin/caaAppeal/" + row.ano;
    },
  },
  created() {
    this.getAppeals(1);
  },
  components: {AdminMenu}
}
</script>

<style scoped>

</style>