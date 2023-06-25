<template>

  <el-card class="box-card" style="padding: 20px">
    <template #header>
      <div class="card-header">
        <div>
          <el-button @click="router.push('/index/courseResourceInsert/' + route.params.id)" type="primary" size="large">
            添加资源
          </el-button>
        </div>
        <div>
          <h1>{{ form.chapterTitle }}</h1>
        </div>
        <div>
          <label>资源类型：</label>
          <el-select v-model="form.selectByResourceType" placeholder="选择" style="width: 100px;">
            <el-option :label="form.all" @click="getCourseResourceByChapterId"></el-option>
            <el-option :label="form.image_textOption_label" :key=form.image_textOption_kv :value=form.image_textOption_kv @click="getCourseResourceByResourceType"></el-option>
            <el-option :label="form.videoOption_label" :key=form.videoOption_kv :value=form.videoOption_kv @click="getCourseResourceByResourceType"></el-option>
          </el-select>
        </div>
      </div>
    </template>

    <el-table :data="tableData" max-height="550" border style="width: 100%;text-align: center">
      <el-table-column prop="resourceId" label="课程资源编号" align="center"/>
      <el-table-column prop="resourceType" label="资源类型" align="center"/>
      <el-table-column prop="resourceUrl" label="资源链接" align="center"/>
      <el-table-column prop="sortOrder" label="排序号" align="center"/>
      <el-table-column prop="name" label="操作" v-slot="scope" align="center">
        <el-button type="primary"
                   @click="router.push({name:'main-courseResource', params:{id : scope.row.resourceId}})">
          查看
        </el-button>
        <el-button type="primary"
                   @click="router.push({name:'main-courseResourceEdit', params:{id : scope.row.resourceId}})">
          编辑
        </el-button>
        <el-button type="danger" @click="handleDelete(scope.row.resourceId)">删除</el-button>
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
  chapterId: '',
  chapterTitle: '',
  all: '全部',
  image_textOption_kv: 'image&text',
  image_textOption_label: '图文',
  videoOption_kv: 'video',
  videoOption_label: '视频',
  selectByResourceType: ''
});
const route = useRoute()
const tableData = ref([])

const getCourseResourceByChapterId = () => {
  get('/api/index/getCourseResourceByChapterId/' + route.params.id, (message) => {
    tableData.value = message;
    tableData.value.forEach((item) => {
      item.resourceType = item.resourceType === 'video' ? '视频' : '图文'; // 1表示上线，0表示下线
    });
  }, () => {
    store.auth.user = null
  })

  get('/api/index/getChapterByChapterId/' + route.params.id, (message) => {
    form.chapterTitle = message.chapterTitle;
    form.chapterId = message.chapterId;
  })
}

const getCourseResourceByResourceType = () =>{
  post('/api/index/getCourseResourceByResourceType', {type:form.selectByResourceType,id:form.chapterId}, (message) => {
    tableData.value = message;
  })
}

getCourseResourceByChapterId();

let s = false


const handleDelete = async (id) => {
  await ElMessageBox.confirm(
      '是否要删除该资源吗?',
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
    get('/api/index/deleteCourseResource/' + id, () => {
      getCourseResourceByChapterId();
    }, () => {
      getCourseResourceByChapterId();
    })
  }
}

</script>

<style scoped>
.goods_container {
  background-color: white;
}

</style>