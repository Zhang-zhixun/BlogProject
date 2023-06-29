<template>

  <el-card class="box-card" style="padding: 20px">
    <template #header>
      <div class="card-header">
        <div>
          <el-button @click="router.push('/index/systemMessageInsert')" type="primary" size="large">发送通知</el-button>
        </div>
        <div>
          <label>用户名称：</label>
          <el-input v-model="form.userAccountUsername" type="text" style="width: 100px;" placeholder="输入名称"/>
          <el-button style="width: 50px;" @click="getSystemMessageByUserAccountUsername">搜索</el-button>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <label>按内容：</label>
          <el-input v-model="form.content" type="text" style="width: 100px;" placeholder="输入内容"/>
          <el-button style="width: 50px;" @click="getSystemMessageByContent">搜索</el-button>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <label>按日期：</label>
          <el-date-picker v-model="form.day" style="width: 100px;" placeholder="选择日期"/>
          <el-button style="width: 50px;" @click="getSystemMessageByDay">搜索</el-button>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <label>是否已读：</label>
          <el-select v-model="form.selectByMessageStatus" placeholder="选择" style="width: 100px;">
            <el-option :label="form.all" @click="getSystemMessageByUserAccountUsername"></el-option>
            <el-option :label="form.readText" :key="1" :value="1" @click="getSystemMessageByMessageStatus"></el-option>
            <el-option :label="form.unreadText" :key="0" :value="0" @click="getSystemMessageByMessageStatus"></el-option>
          </el-select>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <label>时间顺序：</label>
          <el-select placeholder="选择" style="width: 100px;">
            <el-option :label="form.all" @click="getSystemMessageByUserAccountUsername"></el-option>
            <el-option :label="form.ASCText" :key="0" :value="0" @click="getAllSystemMessageInASC"></el-option>
            <el-option :label="form.DESCText" :key="0" :value="0" @click="getAllSystemMessageInDESC"></el-option>
          </el-select>
        </div>
      </div>
    </template>

    <el-table :data="tableData" max-height="550" border style="width: 100%;text-align: center">
      <el-table-column prop="messageId" label="消息编号" align="center"/>
      <el-table-column prop="content" label="内容" align="center"/>
      <el-table-column prop="messageStatus" label="是否已读" align="center"/>
      <el-table-column prop="sendTime" label="发送时间" align="center">
        <template #default="{ row }">
          {{getDate(row.sendTime)}}
        </template>
      </el-table-column>
      <el-table-column prop="userAccount.userAccountUsername" label="用户" align="center"/>
      <el-table-column prop="name" label="操作" v-slot="scope" align="center">
        <el-button type="danger" @click="handleDeleteByMessageId(scope.row.messageId)">删除</el-button>
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
  selectByMessageStatus: '',
  readText: '已读',
  unreadText: '未读',
  ASCText: '正序',
  DESCText: '倒序',
  all: '全部',
  userAccountUsername: '',
  day: '',
  content: ''
});

const tableData = ref([])

const getDate = (d) =>{
  const date = new Date(d); // 创建Date对象
  const year = date.getFullYear();
  const month = date.getMonth() + 1; // 月份从0开始，所以要加1
  const day = date.getDate();
  const hour = date.getHours();
  const minutes = date.getMinutes();
  const seconds = date.getSeconds();
  return `${year}-${month}-${day}\n${hour}:${minutes}:${seconds}`;
}

const getSystemMessageByMessageStatus = () => {
  get('/api/index/getSystemMessageByMessageStatus/' + form.selectByMessageStatus, (message) => {
    tableData.value = message;
    tableData.value.forEach((item) => {
      item.messageStatus = item.messageStatus === 1?'已读':'未读'; // 1表示已读，0表示未读
    });
  }, () => {
    store.auth.user = null
  })
}

const getAllSystemMessageInASC = () => {
  get('/api/index/getAllSystemMessageInASC', (message) => {
    console.log(message)
    tableData.value = message;
    tableData.value.forEach((item) => {
      item.messageStatus = item.messageStatus === 1?'已读':'未读';
    });
  }, () => {
    store.auth.user = null
  })
}

const getAllSystemMessageInDESC = () => {
  get('/api/index/getAllSystemMessageInDESC', (message) => {
    tableData.value = message;
    tableData.value.forEach((item) => {
      item.messageStatus = item.messageStatus === 1?'已读':'未读';
    });
  }, () => {
    store.auth.user = null
  })
}

const getSystemMessageByUserAccountUsername = () => {
  post('/api/index/getSystemMessageByUserAccountUsername', {name: form.userAccountUsername}, (message) => {
    tableData.value = message;
    tableData.value.forEach((item) => {
      item.messageStatus = item.messageStatus === 1?'已读':'未读';
    });
  }, () => {
    store.auth.user = null
  })
}
const getSystemMessageByContent = () => {
  post('/api/index/getSystemMessageByContent', {content: form.content}, (message) => {
    tableData.value = message;
    tableData.value.forEach((item) => {
      item.messageStatus = item.messageStatus === 1?'已读':'未读';
    });
  }, () => {
    store.auth.user = null
  })
}

const getSystemMessageByDay = () => {
  const selectedDate = new Date(form.day); // 创建Date对象
  const year = selectedDate.getFullYear();
  const month = selectedDate.getMonth() + 1; // 月份从0开始，所以要加1
  const day = selectedDate.getDate();
  const formattedDate = `${year}-${month}-${day}`;
  post('/api/index/getSystemMessageByDay', {day: formattedDate}, (message) => {
    tableData.value = message;
    tableData.value.forEach((item) => {
      item.messageStatus = item.messageStatus === 1?'已读':'未读';
    });
  }, () => {
    store.auth.user = null
  })
}

getAllSystemMessageInASC()

let s = false


const handleDeleteByMessageId = async (id) => {
  await ElMessageBox.confirm(
      '确定要删除该消息吗?',
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
    get('/api/index/deleteSystemMessageByMessageId/' + id, () => {
      getAllSystemMessageInASC();
    }, () => {
      getAllSystemMessageInASC();
    })
  }
}

</script>

<style scoped>
.goods_container {
  background-color: white;
}

</style>