<template>
  <el-card class="box-card" style="width: 85vw">

    <template #header>
      <div class="card-header">
        <span style="font-weight: bold">修改章节信息</span>
        <el-button type="primary" @click="router.push({name:'main-chapter', params:{id :form.chapterId}})">
          查看章节资源
        </el-button>
        <el-button class="button" @click="updateChapter()" size="large" type="primary">确认并修改</el-button>
      </div>
    </template>

    <div style="display: flex;height: 574px">
      <div style="width: 40%;">
        <el-form
            :model="form"
        >

          <el-form-item prop="chapterId" label="课程编号" style="margin-top: 0px">
            <el-input v-model="form.chapterId" size="large" type="text" disabled placeholder="课程编号">

            </el-input>
          </el-form-item>

          <el-form-item prop="course.courseName" label="课程名称">
            <el-input v-model="form.courseName" size="large" type="text" disabled placeholder="课程名称">

            </el-input>
          </el-form-item>

          <el-form-item prop="chapterTitle" label="章节标题">
            <el-input v-model="form.chapterTitle" size="large" type="text" placeholder="章节标题">

            </el-input>
          </el-form-item>

          <el-form-item prop="sortOrder" label="排序号">
            <el-input v-model="form.sortOrder" size="large" type="number" placeholder="排序号">

            </el-input>
          </el-form-item>
        </el-form>
      </div>
      <div style="flex: 1;padding:0 10px">
        <el-image style="width: 100%;height: 100%"
                  src="https://ts1.cn.mm.bing.net/th/id/R-C.60361064d8eee12aa647695bf666bb38?rik=W663M7lXL03Y9A&riu=http%3a%2f%2fimg.mm4000.com%2ffile%2fe%2fe3%2f395f367325.jpg&ehk=mVwYGg59wm0g3RzSDpFZgv6EYwrdQQKlvIAtvNQsVTE%3d&risl=&pid=ImgRaw&r=0"/>
      </div>
    </div>
  </el-card>
</template>

<script setup>
import {reactive, ref} from 'vue'
import {get, post} from "@/net";
import router from "@/router";
import {ElMessage} from "element-plus";
import {useRoute} from "vue-router";

const route = useRoute();

const form = reactive({
  chapterId: '',
  courseId: '',
  courseName: '',
  chapterTitle: '',
  sortOrder: '',
})

get('/api/index/getChapterByChapterId/' + route.params.id, (message) => {
  console.log(message)
  form.chapterId = message.chapterId,
  form.courseName = message.course.courseName
  form.chapterTitle = message.chapterTitle
  form.sortOrder = message.sortOrder
  form.courseId = message.course.courseId
})

const formRef = ref()
const updateChapter = () => {
  console.log(form)
  post('/api/index/updateChapter', {
    chapterId: form.chapterId,
    courseId: form.courseId,
    chapterTitle: form.chapterTitle,
    sortOrder: form.sortOrder,
  }, (message) => {
    router.push('/index/chapter/' + form.courseId)
    ElMessage.success(message)
  })
}
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.el-form-item {
  margin-top: 40px;
}

</style>