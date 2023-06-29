<template>

  <el-card class="box-card" style="padding: 20px">
    <template #header>
      <div class="card-header">
        <div>
          <el-button style="float: right" @click="$router.go(-1)" type="primary">返回</el-button>
        </div>
        <div>
          <h1>{{ form.chapterTitle }}</h1>
        </div>
      </div>
    </template>
    <div class="video-container">
      <video ref="videoPlayer" controls v-if="videoHref">
        <source :src="getVideoHref" type="video/mp4">
      </video>
    </div>
  </el-card>

</template>

<script setup>
import {computed, onMounted, reactive, ref} from "vue";
import {get, post} from "@/net";
import router from "@/router";
import {useStore} from "@/stores";
import {ElButton} from "element-plus";
import {useRoute} from "vue-router";

const store = useStore()

const form = reactive({
  chapterTitle: '',
  resourceUrl: '',
});
const route = useRoute()
const videoHref = ref('');

onMounted(async () => {
  await get('/api/index/getCourseResourceByResourceId/' + route.params.id, (message) => {
    videoHref.value = message.resourceUrl;
    form.chapterTitle = message.chapter.chapterTitle;
    console.log(form)
  });

  // videoHref.value = message.resourceUrl;
  // form.chapterTitle = message.chapter.chapterTitle;
});
const getVideoHref = computed(() => videoHref.value);
</script>

<style scoped>
.video-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 75vh; /* 调整高度适应具体场景 */
}

</style>