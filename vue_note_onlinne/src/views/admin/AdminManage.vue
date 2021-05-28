<template>
  <div class="adminManage">
    <AdminHead></AdminHead>
    <el-row>
      <el-col :span="4">
        <AdminMenu></AdminMenu>
      </el-col>
      <el-col :span="20">
        <h1 class="mgt_20 mgb_20">管理员管理</h1>
        <el-row>
          <el-col :span="2">
            <div class="blank_10"></div>
          </el-col>
          <el-col :span="20">
            <div class="order">
              <el-button class="" type="primary" plain @click="orderAdmin(7)" icon="el-icon-top">时间升序</el-button>
              <el-button class="" type="primary" plain @click="orderAdmin(8)" icon="el-icon-bottom">时间降序</el-button>
              <el-button class="" type="primary" plain @click="orderAdmin(1)" icon="el-icon-top">违规处理数升序</el-button>
              <el-button class="" type="primary" plain @click="orderAdmin(2)" icon="el-icon-bottom">违规处理数降序</el-button>
              <el-button class="" type="primary" plain @click="orderAdmin(3)" icon="el-icon-top">申诉处理数升序</el-button>
              <el-button class="" type="primary" plain @click="orderAdmin(4)" icon="el-icon-bottom">申诉处理数降序</el-button>
              <el-button class="" type="primary" plain @click="orderAdmin(5)" icon="el-icon-top">业绩升序</el-button>
              <el-button class="" type="primary" plain @click="orderAdmin(6)" icon="el-icon-bottom">业绩降序</el-button>
            </div>
            <hr/>
            <el-table :data="admins" border :stripe="true">
              <el-table-column prop="id" label="账号" width="120" align="center"></el-table-column>
              <el-table-column prop="name" label="姓名" width="100" align="center"></el-table-column>
              <el-table-column prop="mobile" label="电话" width="150" align="center"></el-table-column>
              <el-table-column prop="email" label="邮箱" width="200" align="center"></el-table-column>
              <el-table-column prop="irNum" label="处理违规数" width="100" align="center"></el-table-column>
              <el-table-column prop="apNum" label="处理申诉数" width="100" align="center"></el-table-column>
              <el-table-column prop="achievement" label="业绩" width="100" align="center"></el-table-column>
              <el-table-column align="center">
                <template slot="header">
                  <p>管理</p>
                </template>
                <template slot-scope="scope">
                  <el-button size="mini" type="primary" plain @click="addAch(scope.row)">加业绩</el-button>
                  <el-button size="mini" type="danger" plain @click="del(scope.row)">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
            <el-pagination
                class="page"
                background
                @current-change="pageChange"
                :current-page="nowPage"
                :pager-count="5"
                layout="prev, pager, next, jumper"
                :total="total">
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
  name: "AdminManage",
  data() {
    return {
      admins: [],
      nowType: 0,
      total: 0,
      nowPage: 1
    }
  },
  methods: {
    getAdmin(type, page) {
      this.nowType = type;
      this.$axios.get("/selectAdmins", {
        params: {
          type: type,
          page: page
        }
      }).then(res => {
        this.admins = res.data.list;
        this.total = res.data.total;
      }).catch(error => {
        console.log(error)
      })
    },
    del(admin) {
      this.$axios.delete("/delAdmin", {
        params: {
          id: admin.id
        }
      }).then(res => {
        if (res.data) {
          this.$message({
            showClose: true,
            message: '删除成功',
            type: 'success'
          });
          this.getAdmin(this.nowType);
        } else {
          this.$message.error("删除出错，请重试");
        }
      }).catch(error => {
        console.log(error);
        this.$message.error("系统错误");
      })
    },
    addAch(admin) {
      let ach = prompt("请输入添加业绩数量");
      let params = new URLSearchParams();
      params.append("id", admin.id);
      params.append("ach", ach);
      this.$axios.put("/addAch", params).then(res => {
        if (res.data) {
          this.$message({
            showClose: true,
            message: '添加成功',
            type: 'success'
          });
          this.getAdmin(this.nowType,1);
        } else {
          this.$message.error("添加出错，请重试");
        }
      }).catch(error => {
        console.log(error);
        this.$message.error("系统错误");
      })
    },
    pageChange(page) {
      this.nowPage = page;
      this.getAdmin(this.nowType, page);
    },
    orderAdmin(type) {
      this.nowPage = 1;
      this.getAdmin(type, this.nowPage);
    }
  },
  created() {
    this.getAdmin(7, 1);
  },
  components: {AdminHead, AdminMenu},
}
</script>

<style scoped>
.adminManage {
  background-color: rgba(0, 0, 0, 0.03);
  position: absolute;
  width: 100%;
  height: 100%;
}

.order button {
  font-size: 12px;
  width: 11.5%;
  margin-bottom: 10px;
  padding-left: 8px;
}

.page {
  position: fixed;
  left: 45%;
  bottom: 30px;
}
</style>