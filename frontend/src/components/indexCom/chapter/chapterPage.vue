<template>

  <el-card class="box-card" style="padding: 20px">
    <template #header>
      <div class="card-header">
        <div>
          <el-button @click="router.push('/index/chapterInsert/' + route.params.id)" type="primary" size="large">添加章节</el-button>
        </div>
<!--        <div>-->
<!--          <label>课程名称：</label>-->
<!--          <el-input v-model="form.courseName" type="text" style="width: 100px;" placeholder="输入名称"/>-->
<!--          <el-button style="width: 50px;" @click="getCourseByCourseName">搜索</el-button>-->
<!--          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
<!--          <label>教师名称：</label>-->
<!--          <el-input v-model="form.teacherName" type="text" style="width: 100px;" placeholder="输入名称"/>-->
<!--          <el-button style="width: 50px;" @click="getCourseByTeacherName">搜索</el-button>-->
<!--          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
<!--          <label>是否上线：</label>-->
<!--          <el-select v-model="form.selectByOnline" placeholder="" style="width: 100px;">-->
<!--            <el-option :label="form.all" @click="getCourseByCourseName"></el-option>-->
<!--            <el-option :label="form.goLive" :key="1" :value="1" @click="getCourseByIsOnline"></el-option>-->
<!--            <el-option :label="form.noLive" :key="0" :value="0" @click="getCourseByIsOnline"></el-option>-->
<!--          </el-select>-->
<!--        </div>-->
        <div>
          <h1>{{form.courseName}}</h1>
        </div>
      </div>
    </template>

    <el-table :data="tableData" max-height="550" border style="width: 100%;text-align: center">
      <el-table-column prop="chapterId" label="章节编号" align="center"/>
      <el-table-column prop="chapterTitle" label="章节标题" align="center"/>
      <el-table-column prop="sortOrder" label="排序号" align="center"/>
      <el-table-column prop="name" label="操作" v-slot="scope" align="center">
        <el-button type="primary" @click="router.push({name:'main-courseResource', params:{id : scope.row.chapterId}})">
          查看资源
        </el-button>
        <el-button type="primary" @click="router.push({name:'main-chapterEdit', params:{id : scope.row.chapterId}})">
          编辑
        </el-button>
        <el-button type="danger" @click="handleDelete(scope.row.chapterId)">删除</el-button>
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
import {useRoute} from "vue-router";

const store = useStore()

const form = reactive({
  isOnline: false,
  selectByOnline: '',
  noLive: '未上线',
  courseName: '',
});
const route = useRoute()
const tableData = ref([])



const getChapterByCourseId = () => {
  get('/api/index/getChapterByCourseId/' + route.params.id, (message) => {
    tableData.value = message;
  }, () => {
    store.auth.user = null
  })

  get('/api/index/CourseById/' + route.params.id, (message) => {
    form.courseName = message.courseName;
  })
}

getChapterByCourseId();

let s = false


const handleDelete = async (id) => {
  await ElMessageBox.confirm(
      '是否要删除该章节吗?',
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
    get('/api/index/deleteChapter/' + id, () => {
      getChapterByCourseId();
    }, () => {
      getChapterByCourseId();
    })
  }
}

</script>

<style scoped>
.goods_container {
  background-color: white;
}

</style>