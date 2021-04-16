<template>
  <div class="adminUpdate">
    <AdminHead></AdminHead>
    <el-row>
      <el-col :span="4">
        <AdminMenu></AdminMenu>
      </el-col>
      <el-col :span="20">
        <div class="adminMsg">
          <el-row>
            <el-col :span="6">
              <img :src="adminHead" @click="choose=true"/>
            </el-col>
            <el-col :span="18" v-if="!isUpdate">
              <div class="o_h mgb_10 mgt_10">
                <div class="msg"><b v-html="'id' + '\xa0\xa0\xa0\xa0' + '：'">id：</b>{{ admin.id }}</div>
                <div class="msg"><b>用户名：</b>{{ admin.name }}</div>
              </div>
              <div class="o_h mgb_10">
                <div class="msg"><b>电话：</b>{{ admin.mobile }}</div>
                <div class="msg"><b v-html="'邮' + '\xa0\xa0\xa0' + ' 箱：'">邮箱：</b>{{ admin.email }}</div>
              </div>
              <div class="o_h mgb_10">
                <div class="msg"><b>违规处理：</b>{{ admin.irNum }}</div>
                <div class="msg"><b>申诉处理：</b>{{ admin.apNum }}</div>
              </div>
              <div class="o_h mgb_10">
                <div class="msg"><b>业绩：</b>{{ admin.achievement }}</div>
                <div class="msg">
                  <el-button @click="isUpdate=!isUpdate">修改</el-button>
                </div>
              </div>
            </el-col>
            <el-col :span="18" v-else>
              <div class="o_h mgb_10 mgt_10">
                <div class="msg"><b v-html="'id' + '\xa0\xa0\xa0\xa0' + '：'">id：</b>
                  <el-input v-model="admin.id" disabled></el-input>
                </div>
                <div class="msg"><b>用户名：</b>
                  <el-input v-model="admin.name"></el-input>
                </div>
              </div>
              <div class="o_h mgb_10">
                <div class="msg"><b>电话：</b>
                  <el-input v-model="admin.mobile"></el-input>
                </div>
                <div class="msg"><b v-html="'邮' + '\xa0\xa0\xa0\xa0' + '箱：'"></b>
                  <el-input v-model="admin.email"></el-input>
                </div>
              </div>
              <div class="o_h mgb_10">
                <div class="msg"><b>违规处理：</b>{{ admin.irNum }}</div>
                <div class="msg"><b>申诉处理：</b>{{ admin.apNum }}</div>
              </div>
              <div class="o_h mgb_10">
                <div class="msg"><b>业绩：</b>{{ admin.achievement }}</div>
                <div class="msg">
                  <el-button @click="update">修改</el-button>
                  <el-button @click="isUpdate=!isUpdate">取消</el-button>
                </div>
              </div>
            </el-col>
          </el-row>
          <hr/>
          <el-tabs class="bkg_w pd_10 hg_400 ach" v-model="activeName" @tab-click="">
            <el-tab-pane label="违规处理记录" name="first">
              <el-table :data="appeals" stripe style="width: 100%">
                <el-table-column type="index"></el-table-column>
                <el-table-column prop="anName" label="笔记标题" width="180"></el-table-column>
                <el-table-column prop="areason" label="违规类型" width="180"></el-table-column>
                <el-table-column prop="auName" label="申诉人"></el-table-column>
                <el-table-column prop="ares" label="申诉结果"></el-table-column>
                <el-table-column prop="aaTime" label="处理时间"></el-table-column>
              </el-table>
              <el-pagination v-if="appealNums > 5" class="ta_c mgt_10" background layout="prev, pager, next, jumper"
                             :page-size="5" :total="appealNums" @current-change="updateAPage">
              </el-pagination>
            </el-tab-pane>
            <el-tab-pane label="申诉处理记录" name="second">
              <el-table :data="irrs" stripe style="width: 100%">
                <el-table-column type="index"></el-table-column>
                <el-table-column prop="inName" label="笔记标题" width="180"></el-table-column>
                <el-table-column prop="irType" label="违规类型" width="180"></el-table-column>
                <el-table-column prop="ibody" label="违规描述"></el-table-column>
                <el-table-column prop="res" label="处理结果"></el-table-column>
                <el-table-column prop="iaTime" label="处理时间"></el-table-column>
              </el-table>
              <el-pagination v-if="irrsNums > 5" class="ta_c mgt_10" background layout="prev, pager, next, jumper"
                             :page-size="5" :total="irrsNums" @current-change="updateIPage">
              </el-pagination>
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-col>
    </el-row>
    <div v-if="choose" class="chooseHeader ta_c">
      <h2 class="mgt_20 mgb_30">请选择头像</h2>
      <div class="hleft">
        <div class="chooseImg">
          <input type="file" accept="image/*" @change="getFile($event)"/>
          <span v-if="!showImg">
              <img src="" id="img"/>
              <p>{{ imgName }}</p>
             </span>
          <div v-else>
            <p class="plus">+</p>
          </div>
        </div>
      </div>
      <div class="hright">
        <img :src="adminHead"/>
      </div>
      <div class="mgt_30">
        <el-button class="hbt" @click="choose=false">取消</el-button>
        <el-button class="hbt" type="primary" @click="submit()">选择</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import AdminMenu from "@/components/AdminMenu";
import AdminHead from "@/components/AdminHead";

export default {
  name: "AdminUpdate",
  data() {
    return {
      admin: {},
      adminHead: '',
      isUpdate: false,
      activeName: 'first',
      choose: false,
      showImg: true,
      appeals: [],
      appealNums: '',
      irrs: [],
      irrsNums: ''
    }
  },
  methods: {
    update() {
      let params = new URLSearchParams();
      params.append("id", this.admin.id)
      params.append("name", this.admin.name)
      params.append("mobile", this.admin.mobile)
      params.append("email", this.admin.email)
      this.$axios.put("/updateAdmin", params).then(res => {
        this.isUpdate = false;
        this.$store.commit("SET_ADMIN", this.admin);
      }).catch(error => {
        this.$message.error("系统错误，修改失败")
      })
    },
    updateImg() {
      let formData = new FormData();
      formData.append("file", this.file);
      formData.append("no", this.user.no);
      this.$axios.post('/uploadHead ', formData)
          .then(response => {
            this.getAdminHead(response.data)
            window.location.reload();
          }).catch(error => {
        console.log(error);
        window.location.reload();
      });
    },
    getAdminHead(headUrl) {
      this.$axios({
        method: 'get',
        url: '/getAdminHead?headUrl=' + headUrl,
        responseType: 'arraybuffer'
      }).then(response => {
        this.head = 'data:image/jpeg;base64,' + this.arrayBufferToBase64(response.data)
        this.$store.commit("SET_ADMINHEAD", this.head);
      })
    },
    arrayBufferToBase64(buffer) {
      let binary = ''
      let bytes = new Uint8Array(buffer)
      let len = bytes.byteLength
      for (let i = 0; i < len; i++) {
        binary += String.fromCharCode(bytes[i])
      }
      return window.btoa(binary)
    },
    getFile(evn) {
      this.showImg = false;
      this.file = event.target.files[0];
      this.imgName = this.file.name;
      if (window.FileReader) {
        let reader = new FileReader();
        reader.readAsDataURL(this.file);
        reader.onloadend = function (e) {
          document.getElementById("img").setAttribute("src", e.target.result);
        };

      }
    },
    submit() {
      let formData = new FormData();
      formData.append("file", this.file);
      formData.append("id", this.admin.id);
      this.$axios.post('/uploadAdminHead ', formData)
          .then(res => {
            this.$store.commit("REMOVE_ADMINHEAD");
            this.getAdminHead(res.data)
            window.location.reload();
          }).catch(error => {
        console.log(error);
        window.location.reload();
      });
    },
    getAppealLog(page) {
      this.$axios.get("/getAppealLog", {
        params: {
          id: this.admin.id,
          page: page
        }
      }).then(res => {
        this.appeals = res.data.list;
        this.appealNums = res.data.total;
      }).catch(error => {
        console.log(error);
        this.$message.error("查询出错")
      })
    },
    getIrrLog(page) {
      this.$axios.get("/getIrrLog", {
        params: {
          id: this.admin.id,
          page: page
        }
      }).then(res => {
        this.irrs = res.data.list;
        this.irrsNums = res.data.total;
      }).catch(error => {
        console.log(error);
        this.$message.error("查询出错")
      })
    },
    updateAPage(page) {
      this.getAppealLog(page);
    },
    updateIPage(page) {
      this.getIrrLog(page);
    }
  },
  created() {
    this.admin = this.$store.getters.getAdmin;
    this.adminHead = this.$store.getters.getAdminHead;
    this.getAppealLog(1);
    this.getIrrLog(1);
  },
  components: {
    AdminHead,
    AdminMenu
  }
}
</script>

<style scoped>
.adminUpdate {
  background-color: rgba(0, 0, 0, 0.05);
  position: absolute;
  width: 100%;
  height: 100%;
}

.adminMsg {
  padding-top: 30px;
  padding-left: 100px;
  padding-right: 100px;
  text-align: left;
  font-size: 20px;
}

.adminMsg img {
  width: 200px;
  height: 200px;
  border: 1px solid #bbbbbb;
}

.adminMsg div {
  line-height: 40px;
}

.msg {
  width: 300px;
  float: left;
}

.el-input {
  width: 200px;
}

.el-button {
  width: 130px;
}

.ach {
  width: 70%;
  position: absolute;
  border: 1px solid #bbbbbb;
  border-radius: 5px;
}

.chooseHeader {
  width: 40%;
  height: 300px;
  border-radius: 20px;
  position: fixed;
  top: 200px;
  left: 35%;
  background-color: #fff;
  box-shadow: 0 0 10px #999999;
  z-index: 10;
}

.chooseHeader img {
  width: 120px;
  height: 120px;
}


.chooseImg {
  width: 120px;
  height: 120px;
  border: 1px solid #4facfe;
  margin-left: 120px;
}

#img {
  border: 1px solid #4facfe;
}

.plus {
  font-size: 60px;
}

.hleft {
  width: 50%;
  float: left;
  border-right: 3px solid #bbbbbb;
}

.hbt {
  width: 200px;
}

.chooseImg input {
  width: 120px;
  height: 120px;
  position: absolute;
  left: 100px;
  opacity: 0;
}
</style>