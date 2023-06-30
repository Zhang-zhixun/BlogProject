<template>

  <el-card class="box-card" style="padding: 20px">
    <template #header>
      <div class="card-header">
        <div>
          <el-button @click="router.push('/index/courseInsert')" type="primary" size="large">添加课程</el-button>
        </div>
        <div v-if="!(store.auth.user.adminUsername === 'zzx')">
          <label>课程名称：</label>
          <el-input v-model="form.courseName" type="text" style="width: 100px;" placeholder="输入名称"/>
          <el-button style="width: 50px;" @click="getCourseByCourseName">搜索</el-button>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <label>教师名称：</label>
          <el-input v-model="form.teacherName" type="text" style="width: 100px;" placeholder="输入名称"/>
          <el-button style="width: 50px;" @click="getCourseByTeacherName">搜索</el-button>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <label>是否上线：</label>
          <el-select v-model="form.selectByOnline" placeholder="选择" style="width: 100px;">
            <el-option :label="form.all" @click="getCourseByCourseName"></el-option>
            <el-option :label="form.goLive" :key="1" :value="1" @click="getCourseByIsOnline"></el-option>
            <el-option :label="form.noLive" :key="0" :value="0" @click="getCourseByIsOnline"></el-option>
          </el-select>
        </div>
        <div v-if="store.auth.user.adminUsername === 'zzx'">
          <label>课程名称：</label>
          <el-input v-model="form.courseName" type="text" style="width: 100px;" placeholder="输入名称"/>
          <el-button style="width: 50px;" @click="getCourseByUCourseName">搜索</el-button>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <label>是否上线：</label>
          <el-select v-model="form.selectByOnline" placeholder="选择" style="width: 100px;">
            <el-option :label="form.all" @click="getCourseByUCourseName"></el-option>
            <el-option :label="form.goLive" :key="1" :value="1" @click="getCourseByUIsOnline"></el-option>
            <el-option :label="form.noLive" :key="0" :value="0" @click="getCourseByUIsOnline"></el-option>
          </el-select>
        </div>
      </div>
    </template>

    <el-table :data="tableData" max-height="550" border style="width: 100%;text-align: center">
      <el-table-column prop="courseId" label="课程编号" align="center"/>
      <el-table-column prop="courseName" label="名称" align="center"/>
      <el-table-column prop="courseDescription" label="描述" align="center"/>
      <el-table-column prop="coursePrice" label="定价" align="center"/>
      <el-table-column prop="teacher" label="授课老师" align="center">
        <template #default="{ row }">
          {{ getTeacherNames(row.teachers) }}
        </template>
      </el-table-column>
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
        <el-button type="primary" @click="router.push({name:'main-chapter', params:{id : scope.row.courseId}})">
          查看章节
        </el-button>
        <el-button type="primary" @click="router.push({name:'main-courseEdit', params:{id : scope.row.courseId}})">
          编辑
        </el-button>
        <el-button type="danger" @click="handleDelete(scope.row.courseId)">删除</el-button>
      </el-table-column>

    </el-table>

  </el-card>

</template>

<script setup>
import {reactive, ref} from "vue";
import {get, post} from "@/net";
import router from "@/router";
import {useStore} from "@/stores";
import {ElMessage, ElMessageBox, ElInput, ElButton} from "element-plus";

const store = useStore()

const form = reactive({
  isOnline: false,
  selectByOnline: '',
  noLive: '未上线',
  goLive: '已上线',
  all: '全部',
  courseName: '',
  teacherName: '',
});

const tableData = ref([])

  const getCourseByUIsOnline = () => {
    post('/api/index/CourseByUStatus', {status: form.selectByOnline,uname: store.auth.user.adminUsername}, (message) => {
      console.log(message)
      tableData.value = message;
      tableData.value.forEach((item) => {
        item.onlineStatus = item.onlineStatus === 1; // 1表示上线，0表示下线
      });
    }, () => {
      store.auth.user = null
    })
  }

  const getCourseByUCourseName = () => {
    post('/api/index/getCourseByUName', {name: form.courseName,uname: store.auth.user.adminUsername}, (message) => {
      tableData.value = message;
      tableData.value.forEach((item) => {
        item.onlineStatus = item.onlineStatus === 1; // 1表示上线，0表示下线
      });
    }, () => {
      store.auth.user = null
    })
  }

  const getCourseByIsOnline = () => {
    get('/api/index/CourseByStatus/' + form.selectByOnline, (message) => {
      tableData.value = message;
      tableData.value.forEach((item) => {
        item.onlineStatus = item.onlineStatus === 1; // 1表示上线，0表示下线
      });
    }, () => {
      store.auth.user = null
    })
  }

  const getCourseByCourseName = () => {
    post('/api/index/getCourseByCourseName', {name: form.courseName}, (message) => {
      tableData.value = message;
      tableData.value.forEach((item) => {
        item.onlineStatus = item.onlineStatus === 1; // 1表示上线，0表示下线
      });
    }, () => {
      store.auth.user = null
    })
  }

  const getCourseByTeacherName = () => {
    post('/api/index/getCourseByTeacherName', {name: form.teacherName}, (message) => {
      tableData.value = message;
      tableData.value.forEach((item) => {
        item.onlineStatus = item.onlineStatus === 1; // 1表示上线，0表示下线
      });
    }, () => {
      store.auth.user = null
    })
  }
  if(store.auth.user.adminUsername === 'zzx'){
    getCourseByUCourseName();
  }else{
    getCourseByCourseName();
  }

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
    get('/api/index/deleteCourse/' + id, () => {
      getCourseByCourseName();
    }, () => {
      getCourseByCourseName();
    })
  }
}
const getTeacherNames = (teachers) => {
  let ts = '';
  for (let i = 0; i < teachers.length; i++) {
    if (i === teachers.length - 1) {
      ts = ts + teachers[i].name
    } else {
      ts = ts + teachers[i].name + ","
    }
  }
  return ts;
}
const updateOnlineStatus = (row) => {
  const newStatus = row.onlineStatus ? 1 : 0; // 将布尔值转换为对应的整数值

  // 发起请求，调用后端接口更新状态
  post(`/api/index/updateCourseStatus/${row.courseId}`, {onlineStatus: newStatus}, () => {

  }, () => {

  });
};

</script>

<style scoped>
.goods_container {
  background-color: white;
}

</style>