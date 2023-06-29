<template>
  <el-card class="box-card" style="width: 85vw">
    <template #header>
      <div class="card-header">
        <span style="font-weight: bold">发送系统消息</span>
        <el-button class="button" @click="insertSystemMessage()" size="large" type="primary">确认并添加</el-button>
        <el-button style="float: right" @click="$router.go(-1)" type="primary">返回</el-button>
      </div>
    </template>

    <div style="display: flex;height: 574px">
      <div style="width: 40%;">
        <el-form :model="form">
          <el-form-item prop="content" label="内容" style="margin-top: 0px">
            <el-input v-model="form.content" size="large" type="textarea" placeholder="内容"></el-input>
          </el-form-item>

          <el-form-item prop="userAccountUsername" label="用户">
            <el-select
                v-model="form.userAccountUsername"
                multiple
                placeholder="选择用户"
            >
              <template #default>
                <el-option
                    v-for="userAccountUsername in userAccountUsernameList"
                    :key="userAccountUsername.name"
                    :label="userAccountUsername.name"
                    :value="userAccountUsername.name"
                ></el-option>
              </template>
            </el-select>
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

const form = reactive({
  content: '',
  userAccountUsername: '',
});

const userAccountUsernameList = ref([]);

const insertSystemMessage = () => {
  const currentTime = new Date().toLocaleString();
  post(
      '/api/index/insertSystemMessage',
      {
        content: form.content,
        userAccountUsername: JSON.stringify(form.userAccountUsername),
        sendTime: currentTime,
      },
      (message) => {
        router.push('/index/systemMessage');
        ElMessage.success(message);
      }
  );
};

const getUserAccountUsernameAccounts = () => {
  get('/api/index/getByAlluserAccountUsernameAccount', (message) => {
    userAccountUsernameList.value = message.map((item) => ({
      name: item.userAccountUsername,
    }));
  });
};

onMounted(() => {
  getUserAccountUsernameAccounts(); // 在组件初始化时调用获取教师数据的方法
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
