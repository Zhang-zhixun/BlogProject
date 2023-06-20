<template>

  <el-card class="box-card" style="padding: 20px">
    <template #header>
      <div class="card-header">
        <div>
          <el-button  @click="router.push('/index/courseInsert')" type="primary" size="large">添加课程</el-button>
        </div>
      </div>
    </template>

    <el-table :data="tableData" max-height="550" border style="width: 100%;text-align: center">
      <el-table-column prop="courseId" label="课程编号" align="center"/>
      <el-table-column prop="courseName" label="名称" align="center"/>
      <el-table-column prop="courseDescription" label="描述" align="center"/>
      <el-table-column prop="coursePrice" label="定价" align="center"/>
      <el-table-column prop="teacher.name" label="授课老师" align="center"/>
      <el-table-column prop="onlineStatus" label="上线状态" align="center">
        <template #default="{ row }">
            <el-switch
                style="display: block"
                v-model="row.onlineStatus"
                active-color="#13ce66"
                inactive-color="#ff4949"
                active-text="上线"
                inactive-text="下线"
                @change="updateOnlineStatus(row)">
            </el-switch>
        </template>
      </el-table-column>

      <el-table-column prop="name" label="操作" v-slot="scope" align="center">
        <el-button type="primary" @click="router.push({name:'main-courseEdit', params:{id : scope.row.courseId}})">查看</el-button>
        <el-button type="danger"  @click="handleDelete(scope.row.courseId)">删除</el-button>
      </el-table-column>

    </el-table>

  </el-card>

</template>

<script setup>
import {reactive, ref} from "vue";
import {get, post} from "@/net";
import router from "@/router";
import {useStore} from "@/stores";
import {ElMessage, ElMessageBox} from "element-plus";
const store = useStore()

const form = reactive({
  isOnline: false,
});

const tableData = ref([])
const getAllCourse = () =>
{
  get('/Courses', (message) => {
    console.log(message)
    tableData.value = message;
    tableData.value.forEach((item) => {
      item.onlineStatus = item.onlineStatus === 1; // 1表示上线，0表示下线
    });
  }, () => {
    store.auth.user = null
  })
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
    get ('/deleteCourse/' + id, ()=>{
      getAllCourse();
    }, () => {
      getAllCourse();
    })
  }
}
const updateOnlineStatus = (row) => {
  const newStatus = row.onlineStatus ? 1 : 0; // 将布尔值转换为对应的整数值

  // 发起请求，调用后端接口更新状态
  post(`/updateCourseStatus/${row.courseId}`, { onlineStatus: newStatus }, () => {

  }, () => {

  });
};

const getTeacher = (row) => {

  // 发起请求，调用后端接口更新状态
  post(`/updateCourseStatus/${row.courseId}`, { onlineStatus: newStatus }, () => {

  }, () => {

  });
};


</script>

<style scoped>
.goods_container {
  background-color: white;
}

</style>