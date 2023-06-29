<template>

  <el-card class="box-card" style="padding: 20px">
    <template #header>
      <div class="card-header">
        <div>
          <label>课程名称：</label>
          <el-input v-model="form.courseName" type="text" style="width: 100px;" placeholder="输入名称"/>
          <el-button style="width: 50px;" @click="getCommentByCourseName">搜索</el-button>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <label>用户名称：</label>
          <el-input v-model="form.userName" type="text" style="width: 100px;" placeholder="输入名称"/>
          <el-button style="width: 50px;" @click="getCommentByUserName">搜索</el-button>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <label>按内容：</label>
          <el-input v-model="form.content" type="text" style="width: 100px;" placeholder="输入内容"/>
          <el-button style="width: 50px;" @click="getCommentByContent">搜索</el-button>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <label>按日期：</label>
          <el-date-picker v-model="form.day" style="width: 100px;" placeholder="选择日期"/>
          <el-button style="width: 50px;" @click="getCommentByDay">搜索</el-button>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <label>是否隐藏：</label>
          <el-select v-model="form.selectByHidden" placeholder="选择" style="width: 100px;">
            <el-option :label="form.all" @click="getCommentByCourseName"></el-option>
            <el-option :label="form.isHiddenText" :key="1" :value="1" @click="getCommentByisHidden"></el-option>
            <el-option :label="form.noHiddenText" :key="0" :value="0" @click="getCommentByisHidden"></el-option>
          </el-select>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <label>时间顺序：</label>
          <el-select v-model="form.selectByOrder" placeholder="选择" style="width: 100px;">
            <el-option :label="form.all" @click="getCommentByCourseName"></el-option>
            <el-option :label="form.ASCText" :key="1" :value="1" @click="getCommentsInASC"></el-option>
            <el-option :label="form.DESCText" :key="0" :value="0" @click="getCommentsInDESC"></el-option>
          </el-select>
        </div>
      </div>
    </template>

    <el-table :data="tableData" max-height="550" border style="width: 100%;text-align: center">
      <el-table-column prop="commentId" label="评论编号" align="center"/>
      <el-table-column prop="user.name" label="用户名称" align="center"/>
      <el-table-column prop="course.courseName" label="课程名称" align="center"/>
      <el-table-column prop="content" label="内容" align="center"/>
      <el-table-column prop="" label="评论时间" align="center">
        <template #default="{ row }">
          {{getDate(row.publishTime)}}
        </template>
      </el-table-column>
      <el-table-column prop="likeCount" label="点赞数" align="center"/>
      <el-table-column prop="isHidden" label="是否隐藏" align="center">
        <template #default="{ row }">
          <el-switch
              style="display: block"
              v-model="row.isHidden"
              active-color="#ff4949"
              inactive-color="#13ce66"
              active-text="隐藏"
              inactive-text="不隐藏"
              @change="updateCommentIsHidden(row)">
          </el-switch>
        </template>
      </el-table-column>

      <el-table-column prop="name" label="操作" v-slot="scope" align="center">
        <el-button type="danger" @click="handleDeleteByCommentId(scope.row.commentId)">删除当条评论</el-button>
        <el-button type="danger" @click="handleDeleteByUserId(scope.row.user.userId)">删除此用户的全部评论</el-button>
        <el-button type="danger" @click="handleDeleteByCourseId(scope.row.course.courseId)">删除此课程的全部评论</el-button>
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
  isHid: false,
  selectByHidden: '',
  selectByOrder: '',
  noHiddenText: '未隐藏',
  isHiddenText: '已隐藏',
  ASCText: '正序',
  DESCText: '倒序',
  all: '全部',
  courseName: '',
  userName: '',
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

const getCommentByisHidden = () => {
  get('/api/index/getCommentByIsHidden/' + form.selectByHidden, (message) => {
    tableData.value = message;
    tableData.value.forEach((item) => {
      item.isHidden = item.isHidden === 1; // 1表示隐藏，0表示不隐藏
    });
  }, () => {
    store.auth.user = null
  })
}

const getCommentsInASC = () => {
  get('/api/index/CommentsInASC', (message) => {
    tableData.value = message;
    tableData.value.forEach((item) => {
      item.isHidden = item.isHidden === 1; // 1表示隐藏，0表示不隐藏
    });
  }, () => {
    store.auth.user = null
  })
}

const getCommentsInDESC = () => {
  get('/api/index/CommentsInDESC', (message) => {
    tableData.value = message;
    tableData.value.forEach((item) => {
      item.isHidden = item.isHidden === 1; // 1表示隐藏，0表示不隐藏
    });
  }, () => {
    store.auth.user = null
  })
}

const getCommentByCourseName = () => {
  post('/api/index/getCommentByCourseName', {name: form.courseName}, (message) => {
    tableData.value = message;
    tableData.value.forEach((item) => {
      item.isHidden = item.isHidden === 1; // 1表示隐藏，0表示不隐藏
    });
  }, () => {
    store.auth.user = null
  })
}

const getCommentByUserName = () => {
  post('/api/index/getCommentByUserName', {name: form.userName}, (message) => {
    tableData.value = message;
    tableData.value.forEach((item) => {
      item.isHidden = item.isHidden === 1; // 1表示隐藏，0表示不隐藏
    });
  }, () => {
    store.auth.user = null
  })
}
const getCommentByContent = () => {
  post('/api/index/getCommentByContent', {content: form.content}, (message) => {
    tableData.value = message;
    tableData.value.forEach((item) => {
      item.isHidden = item.isHidden === 1; // 1表示隐藏，0表示不隐藏
    });
  }, () => {
    store.auth.user = null
  })
}

const getCommentByDay = () => {
  const selectedDate = new Date(form.day); // 创建Date对象
  const year = selectedDate.getFullYear();
  const month = selectedDate.getMonth() + 1; // 月份从0开始，所以要加1
  const day = selectedDate.getDate();
  const formattedDate = `${year}-${month}-${day}`;
  post('/api/index/getCommentByDay', {day: formattedDate}, (message) => {
    tableData.value = message;
    tableData.value.forEach((item) => {
      item.isHidden = item.isHidden === 1; // 1表示隐藏，0表示不隐藏
    });
  }, () => {
    store.auth.user = null
  })
}
getCommentByCourseName()

let s = false


const handleDeleteByCommentId = async (id) => {
  await ElMessageBox.confirm(
      '确定要删除该评论吗?',
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
    get('/api/index/deleteCommentByCommentId/' + id, () => {
      getCommentByCourseName();
    }, () => {
      getCommentByCourseName();
    })
  }
}

const handleDeleteByUserId = async (id) => {
  await ElMessageBox.confirm(
      '确定要删除该用户的所有评论吗?',
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
    get('/api/index/deleteCommentByUserId/' + id, () => {
      getCommentByCourseName();
    }, () => {
      getCommentByCourseName();
    })
  }
}

const handleDeleteByCourseId = async (id) => {
  await ElMessageBox.confirm(
      '确定要删除该课程的所有评论吗?',
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
    get('/api/index/deleteCommentByCourseId/' + id, () => {
      getCommentByCourseName();
    }, () => {
      getCommentByCourseName();
    })
  }
}
const updateCommentIsHidden = (row) => {
  const newStatus = row.isHid ? 1 : 0; // 将布尔值转换为对应的整数值

  // 发起请求，调用后端接口更新状态
  post(`/api/index/updateCommentIsHidden`, {id:row.commentId,isHidden: newStatus}, () => {

  }, () => {

  });
};

</script>

<style scoped>
.goods_container {
  background-color: white;
}

</style>