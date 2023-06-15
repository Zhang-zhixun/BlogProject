<template>
  <el-header>
    <div class="toolbar">
      <!--菜单按钮-->
      <el-icon @click="isCollapse = !isCollapse" style="font-size: 25px;padding-right: 20px">
        <Expand v-show="isCollapse"/>
        <Fold v-show="!isCollapse"/>
      </el-icon>
      <!--面包屑-->
      <el-breadcrumb>
        <el-breadcrumb-item
            v-for="item in list"
            :key="item.path"
        >
          <router-link :to="{ path: item.path }">
            {{ item.meta.title }}
            </router-link>
        </el-breadcrumb-item>
      </el-breadcrumb>
        <el-dropdown style="position: absolute;margin-left: 166%">
        <span class="el-dropdown-link">
          <el-avatar
              src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsafe-img.xhscdn.com%2Fbw1%2F8f5a1a05-d149-459e-948b-91495da6b68f%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fsafe-img.xhscdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1686901677&t=7033df9572d03382ccaf6a310f250af1"
          />
        </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item :icon="Plus" @click="router.push('/index/person')">个人信息</el-dropdown-item>
              <el-dropdown-item :icon="Plus" @click="loginout()">退出</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      <span style="font-weight: bold;position: absolute;margin-left: 175%">{{store.auth.user.username}}</span>
      </div>
  </el-header>
</template>

<script setup >
import {Expand, Fold} from "@element-plus/icons-vue";
import {ref, watch} from "vue";
import {useStore} from "@/stores";
import {isCollapse} from './isCollapse.ts'
import {useRoute} from "vue-router";
import { computed } from 'vue'
import router from "@/router";
import {get} from "@/net";
import {ElMessage} from "element-plus";


const route = useRoute()
let list = computed(() => {
  return route.matched
})

const store = useStore()
const loginout = () => {
  get('api/auth/loginout', (message) => {
    ElMessage.success(message)
    store.auth.user = null
    router.push('/')
  })
}


</script>

<style scoped>
.el-header {
  position: relative;
  color: var(--el-text-color-primary);
  background-color: white;
}
.toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}

.el-dropdown{
  /*margin-left: 1111px;*/
  clear:both;
  margin-right:0;
  position: absolute;
}
</style>