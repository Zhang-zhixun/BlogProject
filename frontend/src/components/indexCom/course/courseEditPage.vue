<template>
  <el-card class="box-card" style="width: 85vw">

    <template #header>
      <div class="card-header">
        <span style="font-weight: bold">修改课程信息</span>
        <el-button class="button" @click="updateCourse()" size="large" type="primary">确认并修改</el-button>
        <el-button style="float: right" @click="$router.go(-1)" type="primary">返回</el-button>
      </div>
    </template>

    <div style="display: flex;height: 574px">
      <div style="width: 40%;">
        <el-form
            :model="form"
        >

          <el-form-item prop="courseName" label="课程名称" style="margin-top: 0px">
            <el-input v-model="form.courseName" size="large" type="text" placeholder="课程名称" >

            </el-input>
          </el-form-item>

          <el-form-item prop="courseDescription" label="课程介绍">
            <el-input v-model="form.courseDescription" size="large" type="text" placeholder="课程介绍">

            </el-input>
          </el-form-item>

          <el-form-item prop="coursePrice" label="定价">
            <el-input v-model="form.coursePrice" size="large" type="text" placeholder="定价">

            </el-input>
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
      <div style="flex: 1;padding:0 10px">
        <el-image style="width: 100%;height: 100%"
                  src="https://ts1.cn.mm.bing.net/th/id/R-C.60361064d8eee12aa647695bf666bb38?rik=W663M7lXL03Y9A&riu=http%3a%2f%2fimg.mm4000.com%2ffile%2fe%2fe3%2f395f367325.jpg&ehk=mVwYGg59wm0g3RzSDpFZgv6EYwrdQQKlvIAtvNQsVTE%3d&risl=&pid=ImgRaw&r=0"/>
      </div>
    </div>
  </el-card>
</template>

<script setup>
import {onMounted, reactive, ref} from 'vue'
import {get, post} from "@/net";
import router from "@/router";
import {ElMessage} from "element-plus";
import {useRoute} from "vue-router";

const form = reactive({
  courseId:'',
  courseName: '',
  courseDescription: '',
  coursePrice: '',
  teacherId: [],
  isOnline: false,
  onlineStatus: '',
  courseCover: '',
})

const route = useRoute()
const teacherList = ref([]);

const tableData = ref([])

//获取订单信息
get('/api/index/CourseById/' + route.params.id, (message) => {
  console.log(message)
  form.courseId = message.courseId,
  form.courseName = message.courseName
  form.courseDescription = message.courseDescription
  form.coursePrice = message.coursePrice
  form.onlineStatus = message.onlineStatus,
  form.isOnline = message.onlineStatus === 0 ? false : true
  console.log(form)
})

const getTeachers = () => {
  get('/api/index/Teachers', (teachers) => {
    teacherList.value = teachers.map((teacher) => ({
      id: teacher.teacherId,
      name: teacher.name,
    }));
  });
};

const formRef = ref()
const updateCourse = () => {
  console.log(form)
  post('/api/index/updateCourse', {
    courseId: form.courseId,
    courseName: form.courseName,
    courseDescription: form.courseDescription,
    coursePrice: form.coursePrice,
    teacherId: JSON.stringify(form.teacherId),
    onlineStatus: form.isOnline === false ? 0 : 1,
    courseCover: form.courseCover,
  }, (message) => {
        router.push('/index/course')
        ElMessage.success(message)
  })
}

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