<template>
  <el-card class="box-card" style="width: 85vw">

    <template #header>
      <div class="card-header">
        <span style="font-weight: bold">修改支付渠道信息</span>
        <el-button class="button" @click="updatePaymentChannel()" size="large" type="primary">确认并修改</el-button>
        <el-button style="float: right" @click="$router.go(-1)" type="primary">返回</el-button>
      </div>
    </template>

    <div style="display: flex;height: 574px">
      <div style="width: 40%;">
        <el-form
            :model="form"
        >

          <el-form-item prop="channelId" label="支付渠道编号" style="margin-top: 0px">
            <el-input v-model="form.channelId" size="large" disabled type="text" placeholder="支付渠道编号" >

            </el-input>
          </el-form-item>

          <el-form-item prop="channelName" label="名称">
            <el-input v-model="form.channelName" size="large" type="text" placeholder="模板名称">

            </el-input>
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
  channelId:'',
  channelName: '',
})

const route = useRoute()

const tableData = ref([])

//获取订单信息
get('/api/index/getPaymentChannelById/' + route.params.id, (message) => {
  form.channelId = message.channelId,
  form.channelName = message.channelName
})


const formRef = ref()
const updatePaymentChannel = () => {
  console.log(form)
  post('/api/index/updatePaymentChannel', {
    channelId: form.channelId,
    channelName: form.channelName,
  }, (message) => {
        router.push('/index/paymentChannel')
        ElMessage.success(message)
  })
}
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