<template>
  <el-card class="box-card" style="width: 85vw">
    <template #header>
      <div class="card-header">
        <span style="font-weight: bold">添加课程</span>
        <el-button class="button" @click="insertUser()" size="large" type="primary">确认并添加</el-button>
      </div>
    </template>

    <div style="display: flex;height: 574px">
      <div style="width: 40%;">
        <el-form :model="form">
          <el-form-item prop="courseName" label="课程名称" style="margin-top: 0px">
            <el-input v-model="form.courseName" size="large" type="text" placeholder="课程名称"></el-input>
          </el-form-item>

          <el-form-item prop="courseDescription" label="课程描述">
            <el-input v-model="form.courseDescription" size="large" type="textarea" placeholder="课程描述"></el-input>
          </el-form-item>

          <el-form-item prop="coursePrice" label="定价">
            <el-input v-model="form.coursePrice" size="large" type="number" placeholder="定价"></el-input>
          </el-form-item>

          <el-form-item prop="teacherId" label="授课老师">
            <el-select
                v-model="form.teacherId"
                multiple
                placeholder="选择授课老师"
            >
              <template #default>
                <el-option
                    v-for="teacher in teacherList"
                    :key="teacher.id"
                    :label="teacher.name"
                    :value="teacher.id"
                ></el-option>
              </template>
            </el-select>
          </el-form-item>

          <el-form-item prop="onlineStatus" label="上线状态">
            <el-switch
                style="display: block"
                v-model="form.isOnline"
                active-color="#13ce66"
                inactive-color="#ff4949"
                active-text="上线"
                inactive-text="下线">
            </el-switch>
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
  courseName: '',
  courseDescription: '',
  coursePrice: '',
  teacherId: [],
  isOnline: false,
  onlineStatus: '',
});

const teacherList = ref([]);

const insertUser = () => {
  post(
      '/insertCourse',
      {
        courseName: form.courseName,
        courseDescription: form.courseDescription,
        coursePrice: form.coursePrice,
        teacherId: form.teacherId,
        onlineStatus: form.isOnline === false ? 0 : 1,
      },
      (message) => {
        router.push('/course');
        ElMessage.success(message);
      }
  );
};

const getTeachers = () => {
  get('/Teachers', (teachers) => {
    teacherList.value = teachers.map((teacher) => ({
      id: teacher.teacherId,
      name: teacher.name,
    }));
  });
};

onMounted(() => {
  getTeachers(); // 在组件初始化时调用获取教师数据的方法
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
