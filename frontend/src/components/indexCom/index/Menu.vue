<template>
  <el-scrollbar>
    <el-row>
        <el-menu
            default-active="1"
            class="el-menu-vertical-demo"
            :collapse="isCollapse"
        >
          <div style="display: flex">
            <div style=";padding:15px 5px">
              <el-avatar size="large" src="../src/assets/img/logo.png" style="background-color: white"/>
            </div>
            <div style="flex: 1;padding: 10px">
              <h3 style="text-align: center;margin-top: 20%;font-weight: bold;margin-left: -10px">
                个人博客
              </h3>
            </div>

          </div>
          <el-menu-item index="1"  @click="router.push('/index')">
            <el-icon>
              <icon-menu/>
            </el-icon>
            <span>首页</span>
          </el-menu-item>

            <el-menu-item index="2" @click="router.push('/index/article')">
              <el-icon>
                <document/>
              </el-icon>
              <span>文章管理</span>
            </el-menu-item>

          <el-menu-item index="3" @click="router.push('/index/course')">
            <el-icon>
              <document/>
            </el-icon>
            <span>课程管理</span>
          </el-menu-item>

          <el-menu-item index="4" @click="router.push('/index/classify')">
            <el-icon><User /></el-icon>
            <span>分类管理</span>
          </el-menu-item>

          <el-menu-item index="5" @click="router.push('/index/test')">
            <el-icon><Van /></el-icon>
            <span>标签管理</span>
          </el-menu-item>

          <el-menu-item index="6" @click="router.push('/index/comment')">
            <el-icon><Goods /></el-icon>
            <span>评论管理</span>
          </el-menu-item>

          <el-menu-item index="12" @click="router.push('/index/userMessage')">
            <el-icon><Goods /></el-icon>
            <span>用户消息管理</span>
          </el-menu-item>

          <el-menu-item index="13" @click="router.push('/index/systemMessage')">
            <el-icon><Goods /></el-icon>
            <span>系统消息管理</span>
          </el-menu-item>

          <el-menu-item index="7" @click="router.push('/index/user')">
            <el-icon><Money /></el-icon>
            <span>用户管理</span>
          </el-menu-item>

          <el-menu-item index="14" @click="router.push('/index/certificateTemplate')">
            <el-icon><Goods /></el-icon>
            <span>模板管理</span>
          </el-menu-item>

          <el-menu-item index="8" @click="router.push('/index/resources')">
            <el-icon><Money /></el-icon>
            <span>资源管理</span>
          </el-menu-item>

          <el-menu-item index="9" @click="router.push('/index/customerService')">
            <el-icon><CoffeeCup /></el-icon>
            <span>客服管理</span>
          </el-menu-item>

          <el-menu-item index="10" @click="router.push('/index/system')">
            <el-icon>
              <setting/>
            </el-icon>
            <span>系统设置</span>
          </el-menu-item>

          <el-menu-item index="11" @click="loginout()">
            <el-icon><SwitchButton /></el-icon>
            <span>退出系统</span>
          </el-menu-item>

        </el-menu>
    </el-row>
  </el-scrollbar>
</template>

<script setup>
import {ref} from 'vue'
import {Menu as IconMenu, Message, Setting} from '@element-plus/icons-vue'
import {isCollapse} from './isCollapse.ts'
import router from "@/router";
import {useStore} from "@/stores";
import {get} from "@/net";
import {ElMessage} from "element-plus";

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
.el-menu-vertical-demo{
  height: 100vh;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>