<template>

  <el-card class="box-card" style="padding: 20px">
    <template #header>
      <div class="card-header">
        <div>
          <el-button @click="router.push('/index/certificateTemplateInsert')" type="primary" size="large">添加模板</el-button>
        </div>
        <div>
          <label>模板编号：</label>
          <el-input v-model="form.templateId" type="text" style="width: 100px;" placeholder="输入编号"/>
          <el-button style="width: 50px;" @click="getCertificateTemplateById">搜索</el-button>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <label>模板名称：</label>
          <el-input v-model="form.certificateTemplateName" type="text" style="width: 100px;" placeholder="输入名称"/>
          <el-button style="width: 50px;" @click="getCertificateTemplateByName">搜索</el-button>
        </div>
      </div>
    </template>

    <el-table :data="tableData" max-height="550" border style="width: 100%;text-align: center">
      <el-table-column prop="templateId" label="模板编号" align="center"/>
      <el-table-column prop="templateName" label="名称" align="center"/>
      <el-table-column prop="templateContent" label="链接" align="center"/>

      <el-table-column prop="name" label="操作" v-slot="scope" align="center">
        <el-button type="primary" @click="router.push({name:'main-certificateTemplateView', params:{id : scope.row.templateId}})">
          查看模板
        </el-button>
        <el-button type="primary" @click="router.push({name:'main-certificateTemplateEdit', params:{id : scope.row.templateId}})">
          编辑
        </el-button>
        <el-button type="danger" @click="handleDelete(scope.row.templateId)">删除</el-button>
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
  certificateTemplateName:'',
  templateId:'',
});

const tableData = ref([])

const getCertificateTemplateByName = () => {
  post('/api/index/getCertificateTemplateByName', {name: form.certificateTemplateName}, (message) => {
    tableData.value = message;
  }, () => {
  })
}

const getCertificateTemplateById = () => {
  get('/api/index/getCertificateTemplateById/'+ form.templateId, (message) => {
    tableData.value = message;
  }, () => {
  })
}
getCertificateTemplateByName();

let s = false


const handleDelete = async (id) => {
  await ElMessageBox.confirm(
      '确定要删除该模板吗?',
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
    get('/api/index/deleteCertificateTemplate/' + id, () => {
      getCertificateTemplateByName();
    }, () => {
      getCertificateTemplateByName();
    })
  }
}

</script>

<style scoped>

</style>