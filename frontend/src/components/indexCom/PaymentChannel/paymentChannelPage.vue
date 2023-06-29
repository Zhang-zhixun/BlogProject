<template>

  <el-card class="box-card" style="padding: 20px">
    <template #header>
      <div class="card-header">
        <div>
          <el-button @click="router.push('/index/paymentChannelInsert')" type="primary" size="large">添加支付渠道</el-button>
        </div>
        <div>
          <label>支付渠道编号：</label>
          <el-input v-model="form.channelId" type="text" style="width: 100px;" placeholder="输入编号"/>
          <el-button style="width: 50px;" @click="getPaymentChannelById">搜索</el-button>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <label>支付渠道名称：</label>
          <el-input v-model="form.channelName" type="text" style="width: 100px;" placeholder="输入名称"/>
          <el-button style="width: 50px;" @click="getPaymentChannelByName">搜索</el-button>
        </div>
      </div>
    </template>

    <el-table :data="tableData" max-height="550" border style="width: 100%;text-align: center">
      <el-table-column prop="channelId" label="支付渠道编号" align="center"/>
      <el-table-column prop="channelName" label="名称" align="center"/>
      <el-table-column prop="name" label="操作" v-slot="scope" align="center">
        <el-button type="primary" @click="router.push({name:'main-paymentChannelEdit', params:{id : scope.row.channelId}})">
          编辑
        </el-button>
        <el-button type="danger" @click="handleDelete(scope.row.channelId)">删除</el-button>
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
  channelName:'',
  channelId:'',
});

const tableData = ref([])

const getPaymentChannelByName = () => {
  post('/api/index/getPaymentChannelByName', {name: form.channelName}, (message) => {
    tableData.value = message;
  }, () => {
  })
}

const getPaymentChannelById = () => {
  get('/api/index/getPaymentChannelById/'+ form.channelId, (message) => {
    tableData.value = message;
  }, () => {
  })
}
getPaymentChannelByName();

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
    get('/api/index/deletePaymentChannel/' + id, () => {
      getPaymentChannelByName();
    }, () => {
      getPaymentChannelByName();
    })
  }
}

</script>

<style scoped>

</style>