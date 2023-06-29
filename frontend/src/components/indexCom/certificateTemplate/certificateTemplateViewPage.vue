<template>

  <el-card class="box-card" style="padding: 20px">
    <template #header>
      <div class="card-header">
        <div>
          <el-button style="float: right" @click="$router.go(-1)" type="primary">返回</el-button>
        </div>
        <div>
          <h1>{{ form.templateName }}</h1>
        </div>
      </div>
    </template>
    <div class="img-container">
      <el-image ref="videoPlayer" :src="getImgSrc" controls v-if="imgSrc"/>
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
  templateName: '',
  templateContent: '',
});
const route = useRoute()
const imgSrc = ref('');

onMounted(async () => {
  await get('/api/index/getCertificateTemplateById/' + route.params.id, (message) => {
    imgSrc.value = message.templateContent;
    form.templateName = message.templateName;
    console.log(form)
  });
});
const getImgSrc = computed(() => imgSrc.value);
</script>

<style scoped>
.img-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 75vh; /* 调整高度适应具体场景 */
}

</style>