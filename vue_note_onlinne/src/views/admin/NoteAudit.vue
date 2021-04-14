<template>
  <div class="noteAudit">
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
            <h1 class="mgt_10 mgb_10">笔记违规处理</h1>
            <div class="hg_600">
              <el-table :data="irrs" stripe style="width: 100%">
                <el-table-column type="index"></el-table-column>
                <el-table-column prop="inName" label="笔记标题" width="180"></el-table-column>
                <el-table-column prop="irType" label="违规类型" width="180"></el-table-column>
                <el-table-column prop="ibody" label="违规描述"></el-table-column>
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
import AdminHead from "@/components/AdminHead";

export default {
  name: "NoteAudit",
  data() {
    return {
      left: 'top',
      irrs: [],
      nums: 0
    }
  },
  methods: {
    getIrr(page) {
      this.$axios.get("/selectIrrs", {
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
    check(row) {
      window.location.href="/admin/checkAndAudit/"+row.ino;
    },
    updatePage(page) {
      this.getIrr(page);
    }
  },
  components: {
    AdminHead,
    AdminMenu
  },
  created() {
    this.getIrr(1);
  }
}
</script>

<style scoped>
.noteAudit {
  overflow: hidden;
  background-color: rgba(200, 200, 200, 0.1);
}
</style>