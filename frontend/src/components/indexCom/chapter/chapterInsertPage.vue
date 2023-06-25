<template>
  <el-card class="box-card" style="width: 85vw">
    <template #header>
      <div class="card-header">
        <span style="font-weight: bold">添加章节</span>
        <el-button class="button" @click="insertUser()" size="large" type="primary">确认并添加</el-button>
      </div>
    </template>

    <div style="display: flex;height: 574px">
      <div style="width: 40%;">
        <el-form :model="form">
          <el-form-item prop="courseName" label="课程" style="margin-top: 0px">
            <el-input v-model="form.courseName" disabled size="large" type="text" placeholder="课程名称"></el-input>
          </el-form-item>

          <el-form-item prop="chapterTitle" label="章节标题">
            <el-input v-model="form.chapterTitle" size="large" type="textarea" placeholder="章节标题"></el-input>
          </el-form-item>

          <el-form-item prop="sortOrder" label="排序号">
            <el-input v-model="form.sortOrder" size="large" type="number" placeholder="排序"></el-input>
          </el-form-item>
        </el-form>
      </div>

      <div style="flex: 1; padding: 0 10px">
        <el-image style="width: 100%; height: 100%" src="https://ts1.cn.mm.bing.net/th/id/R-C.60361064d8eee12aa647695bf666bb38?rik=W663M7lXL03Y9A&riu=http%3a%2f%2fimg.mm4000.com%2ffile%2fe%2fe3%2f395f367325.jpg&ehk=mVwYGg59wm0g3RzSDpFZgv6EYwrdQQKlvIAtvNQsVTE%3d&risl=&pid=ImgRaw&r=0"></el-image>
      </div>
    </div>
  </el-card>
</template>

<script setup>
import { reactive, ref, onMounted } from 'vue';
import { post, get } from '@/net';
import router from '@/router';
import { ElMessage } from 'element-plus';
import {useRoute} from "vue-router";
const route = useRoute()
const form = reactive({
  courseId: route.params.id,
  courseName: '',
  chapterTitle: '',
  sortOrder: '',
});


const insertUser = () => {
  console.log(form)
  post(
      '/api/index/insertChapter',
      {
        courseId: form.courseId,
        courseName: form.courseName,
        chapterTitle: form.chapterTitle,
        sortOrder: form.sortOrder,
      },
      (message) => {
        router.push('/index/chapter/' + route.params.id);
        ElMessage.success(message);
      }
  );
};

const getCourse = () => {
  get('/api/index/CourseById/' + route.params.id, (message) => {
      form.courseName = message.courseName;
  });
};

onMounted(() => {
  getCourse();
});
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
