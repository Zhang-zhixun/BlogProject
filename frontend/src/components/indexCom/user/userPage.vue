<template>

  <el-card class="box-card" style="padding: 20px">
    <template #header>
      <div class="card-header">
        <div>
          <el-button  @click="router.push('/index/userInsert')" type="primary" size="large">添加客户信息</el-button>
        </div>
      </div>
    </template>

    <el-table :data="tableData" max-height="550" border style="width: 100%;text-align: center">
      <el-table-column prop="ctId" label="客户编号" align="center"/>
      <el-table-column prop="ctName" label="客户姓名" align="center"/>
      <el-table-column prop="ctPhone" label="客户电话" align="center"/>
      <el-table-column prop="ctAddress" label="居住地址" align="center"/>
      <el-table-column prop="customersAccount.caId" label="账号" align="center"/>
      <el-table-column prop="medicalInsurance.miCard" label="社保卡" align="center"/>

      <el-table-column prop="name" label="操作" v-slot="scope" align="center">
        <el-button type="primary" @click="router.push({name:'main-userEdit', params:{id : scope.row.ctId}})">编辑</el-button>
        <el-button type="danger"  @click="handleDelete({id : {id : scope.row.ctId}})">删除</el-button>
      </el-table-column>

    </el-table>

  </el-card>

</template>

<script setup>
import {ref} from "vue";
import {get, post} from "@/net";
import router from "@/router";
import {useStore} from "@/stores";
import {ElMessage, ElMessageBox} from "element-plus";
const store = useStore()


const tableData = ref([])
get('/api/index/customers', (message) => {
  tableData.value = message
}, () => {
  store.auth.user = null
})


let s = false


const handleDelete = async ({id}) => {
  var Customers = {
    ctid: id
  }
  await ElMessageBox.confirm(
      '是否要删除该信息吗?',
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

  var isSame = Customers.ctid["id"]
  console.log(s,isSame)
  if (s === true) {
    post('/api/index/deleteCustomers/' + isSame, {}, (message) => {
      get('/api/index/customers', (message) => {
            tableData.value = message
          }, () => {
            store.auth.user = null
          }
      )
    })
  }
}


</script>

<style scoped>
.goods_container {
  background-color: white;
}

</style>