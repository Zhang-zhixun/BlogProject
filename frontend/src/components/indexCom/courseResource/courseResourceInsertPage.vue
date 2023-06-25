<template>
  <el-card class="box-card" style="width: 85vw">
    <template #header>
      <div class="card-header">
        <span style="font-weight: bold">添加课程资源</span>
        <el-button class="button" @click="insertCourseResource()" size="large" type="primary">确认并添加</el-button>
      </div>
      <div>
        <h1>{{form.chapterTitle}}</h1>
      </div>
    </template>

    <div style="display: flex;height: 574px">
      <div style="width: 40%;">
        <el-form :model="form">
          <el-form-item prop="resourceType" label="资源类型" style="margin-top: 0px">
            <el-select v-model="form.resourceType" placeholder="选择类型">
              <el-option :key="form.image_textOption_kv" :value="form.image_textOption_kv" :label="form.image_textOption_label"></el-option>
              <el-option :key="form.videoOption_kv" :value="form.videoOption_kv" :label="form.videoOption_label"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item prop="resourceUrl" label="资源链接">
            <el-input v-model="form.resourceUrl" size="large" type="text" placeholder="资源链接"></el-input>
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
import { ElMessage , ElInput, ElButton} from 'element-plus';
import {useRoute} from "vue-router";
const route = useRoute()
const form = reactive({
  chapterId: route.params.id,
  resourceType: '',
  resourceUrl: '',
  sortOrder: '',
  chapterTitle: '',
  image_textOption_kv: 'image&text',
  image_textOption_label: '图文',
  videoOption_kv: 'video',
  videoOption_label: '视频',
});


const insertCourseResource = () => {
  post(
      '/api/index/insertCourseResource',
      {
        chapterId: form.chapterId,
        resourceType: form.resourceType,
        resourceUrl: form.resourceUrl,
        sortOrder: form.sortOrder,
      },
      (message) => {
        router.push('/index/courseResource/' + form.chapterId);
        ElMessage.success(message);
      }
  );
};
get('/api/index/getChapterByChapterId/' + route.params.id, (message) => {
  form.chapterTitle = message.chapterTitle;
})
console.log(form)
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
