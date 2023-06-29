<template>
  <el-card class="box-card" style="padding: 20px">

    <template #header>
      <div class="card-header">
        <div>
          <br>
          搜索用户名:
          <el-input placeholder="请输入内容" v-model="pages" clearable style="width: 150px"></el-input>
          &nbsp;&nbsp;&nbsp;
          <el-button @click="getSelectNameLinkFindByUser" type="primary" size="large">搜索</el-button>
        </div>
      </div>
    </template>

    <el-table :data="tableData" max-height="550" border style="width: 100%;text-align: center">
      <el-table-column prop="userId" label="用户id"></el-table-column>
      <el-table-column prop="name" label="用户名"></el-table-column>
      <el-table-column prop="gender" label="性别"></el-table-column>
      <el-table-column prop="age" label="年龄"></el-table-column>
      <el-table-column prop="school" label="所在院校"></el-table-column>
      <el-table-column prop="email" label="电子邮箱"></el-table-column>
      <el-table-column prop="identity" label="用户角色"></el-table-column>
      <el-table-column prop="userAccount.userAccountUsername" label="用户账户名"></el-table-column>

      <el-table-column prop="name" label="操作" v-slot="scope" align="center">
        <el-button type="primary" @click="router.push({name:'main-userEditPage', params:{id : scope.row.userId}})">
          编辑
        </el-button>

        <el-button type="danger" @click="handleDelete(scope.row.userId)">删除</el-button>
      </el-table-column>

    </el-table>

    <div class="block">
      <br>  <br>
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage4"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

    <!--
    page-sizes:页数选择数组（当前页显示：10, 20, 30, 50/页）
    page-size：默认显示"10"/页
    total：总条数（可以绑定）
    page-size： 页面显示条数（可以绑定）
    current-page：当前页码
    handleCurrentChange:改变当前页数值触发
    -->

  </el-card>

</template>

<script setup>
import {reactive, ref} from "vue";
import {get, post} from "@/net";
import router from "@/router";
import {useStore} from "@/stores";
import {ElMessage, ElMessageBox} from "element-plus";

const pageSize = ref(5)       //每页显示多少条
const currentPage = ref(1)    //当前页码
const total = ref(0)          //总条数(可用，绑定查询后数据计算长度即可)

const getSelectNameLinkFindByUser = () => {  //获取全部信息（不是进来的时候获取）
  console.log("getSelectNameLinkFindByUser ==>")

  const InputSearch = reactive(pages.value)
  post(`/user/SelectNameLinkFindByUser`, {InputSearch: InputSearch}, () => {
    console.log("success   post(`/user/SelectNameLinkFindByUser`, {InputSearch: InputSearch}, () => { ==>")
  }, () => {
    console.log("failure   post(`/user/SelectNameLinkFindByUser`, {InputSearch: InputSearch}, () => { ==>")
    get('/user/SelectNameLinkFindByUser2', (message) => {
      console.log("get('/user/SelectNameLinkFindByUser2', (message) => { ==>")

      tableData.value = []
      console.log("message ====1212===> ")
      console.log(message)
      tableData.value = message;
      tableData.value;
      console.log("tableData.value = ")
      console.log(tableData.value)
      total.value = tableData.value.length
    }, () => {
      //router.push('/index/user')
      store.auth.user = null
    })
  });
}

const handleSizeChange = (pageSize1) => {    //改变每页显示条数函数
  //每页条数(每页显示多少条单选框)状态改变时触发 选择一页显示多少行
                                    console.log(" handleSizeChange ==>")
  currentPage4.value = 1         //当前页置1（回到首页）  (数据处理要放到前面)
  pageSize.value = pageSize1  //每页显示多少条更新成设置的
                                    console.log("pageSize ==>")
                                    console.log(pageSize1)

  console.log("currentPage4 ==>")
  console.log(currentPage4.value)    //当前页
  console.log("pageSize ==>")
  console.log(pageSize.value)       //每页显示多少条

  const currentPage4reactive = reactive(currentPage4.value)
  const pageSizereactive = reactive(pageSize.value)

  console.log("handleSizeChange ==>post ==>")
  post(`/user/handleCurrentChangePost`, {
    currentPage4reactive: currentPage4reactive,
    pageSizereactive: pageSizereactive
  }, () => {
    console.log("success   post(`/user/SelectNameLinkFindByUser`, {InputSearch: InputSearch}, () => { ==>")
  }, () => {
    console.log("failure   post(`/user/SelectNameLinkFindByUser`, {InputSearch: InputSearch}, () => { ==>")
    get('/user/handleCurrentChangePost2', (message) => {
      console.log("get('/user/SelectNameLinkFindByUser2', (message) => { ==>")

      tableData.value = []
      console.log("message = ")
      console.log(message)
      tableData.value = message.list;
      tableData.value;
      console.log("tableData.value = ")
      console.log(tableData.value)
      total.value =message.totalCount
    }, () => {
      //router.push('/index/user')
      store.auth.user = null
    })
  });

}

const handleCurrentChange = (currentPage41) => {   //改变当前页数值
  //当前页改变时(1,2,3输入框改变)触发 跳转其他页
  currentPage4.value = currentPage41   //当前页码更新
  console.log(" handleCurrentChange ==>")

  console.log("currentPage4 ==>")
  console.log(currentPage4)

  const currentPage4reactive = reactive(currentPage4.value)
  const pageSizereactive = reactive(pageSize.value)

  console.log("currentPage4reactive.value ==>")
  console.log(currentPage4reactive.value)
  console.log("pageSizereactive ==>")
  console.log(pageSizereactive)

  console.log("handleSizeChange ==>post ==>")
  post(`/user/handleCurrentChangePost`, {
    currentPage4reactive: currentPage4reactive,
    pageSizereactive: pageSizereactive
  }, () => {
    console.log("success   post(`/user/SelectNameLinkFindByUser`, {InputSearch: InputSearch}, () => { ==>")
  }, () => {
    console.log("failure   post(`/user/SelectNameLinkFindByUser`, {InputSearch: InputSearch}, () => { ==>")
      get('/user/handleCurrentChangePost2', (message) => {
        console.log("get('/user/SelectNameLinkFindByUser2', (message) => { ==>")

        tableData.value = []
        console.log("message = ")
        console.log(message)
        tableData.value = message.list;
        tableData.value;
        console.log("tableData.value = ")
        console.log(tableData.value)
        total.value =message.totalCount

      }, () => {
        //router.push('/index/user')
        store.auth.user = null
      })
  });


}
















const currentPage4 = ref(1) //当前页面

const page = ref(0)
const xupdateOnlineStatus1 = () => {
  page.value = page.value + 1
}

const pages = ref("")

const store = useStore()

const form = reactive({
  isOnline: false,
});


const tableData = ref([])

const getAllCourse = () => {  //获取全部信息
  console.log("getAllCourse======================================= ==>")

  //每页条数(每页显示多少条单选框)状态改变时触发 选择一页显示多少行
  currentPage4.value = 1         //当前页置1（回到首页）  (数据处理要放到前面)
  pageSize.value =   10  //每页显示多少条更新成设置的

  const currentPage4reactive = reactive(currentPage4.value)
  const pageSizereactive = reactive(pageSize.value)

  console.log("currentPage4 ==>")
  console.log(currentPage4)
  console.log("pageSize ==>")
  console.log(pageSize)
  console.log("currentPage4reactive ==>")
  console.log(currentPage4reactive)
  console.log("pageSizereactive ==>")
  console.log(pageSizereactive)

  console.log("currentPage4 ==>")
  console.log(currentPage4.value)

  console.log("handleSizeChange ==>post ==>")
  post(`/user/handleCurrentChangePost`, {
    currentPage4reactive: currentPage4reactive,
    pageSizereactive: pageSizereactive
  }, () => {
    console.log("success   post(`/user/SelectNameLinkFindByUser`, {InputSearch: InputSearch}, () => { ==>")
  }, () => {
    console.log("failure   post(`/user/SelectNameLinkFindByUser`, {InputSearch: InputSearch}, () => { ==>")
    get('/user/handleCurrentChangePost2', (message) => {
      console.log("get('/user/SelectNameLinkFindByUser2', (message) => { ==>")

      tableData.value = []
      console.log("message = ")
      console.log(message)
      tableData.value = message.list;
      tableData.value;
      console.log("tableData.value = ")
      console.log(tableData.value)
      total.value =message.totalCount

    }, () => {
      //router.push('/index/user')
      store.auth.user = null
    })
  });
}
getAllCourse()

let s = false

const handleDelete = async (id) => {
  await ElMessageBox.confirm(
      '是否要删除该课程吗?',
      '警告',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
  ).then(() => {
    s = true
    ElMessage({
      type: 'success',
      message: '删除成功',
    })
  }).catch(() => {
    s = false
    ElMessage({
      type: 'info',
      message: '取消删除',
    })
  })
  if (s === true) {
    get('/user/deleteUserId/' + id, () => {
      getAllCourse();
    }, () => {
      getAllCourse();
    })
  }
}




/*
下面两个函数没用

const updateOnlineStatus = (row) => {
  const newStatus = row.onlineStatus ? 1 : 0; // 将布尔值转换为对应的整数值
  // 发起请求，调用后端接口更新状态
  post(`/updateCourseStatus/${row.courseId}`, {onlineStatus: newStatus}, () => {
  }, () => {
  });
};

const getTeacher = (row) => {
  // 发起请求，调用后端接口更新状态
  post(`/updateCourseStatus/${row.courseId}`, {onlineStatus: newStatus}, () => {
  }, () => {
  });
};
*/

</script>

<style scoped>
.goods_container {
  background-color: white;
}

</style>










