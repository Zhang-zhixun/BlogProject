<template>
  <div>
    <el-container>
      <Menu/>
      <el-container class="layout-container-demo" style="height: 100vh;flex-direction: column">
        <Header/>

        <el-main>
          <router-view v-slot="{ Component }">
              <transition name="el-fade-in-linear">
                <component :is="Component" style="height:100%"/>
              </transition>
          </router-view>
        </el-main>
      </el-container>
    </el-container>


  </div>
</template>

<script setup>
import {get} from "@/net";
import {ElMessage} from "element-plus";
import router from "@/router";
import {useStore} from "@/stores";


import {ref} from 'vue'
import {Menu as IconMenu, Message, Setting} from '@element-plus/icons-vue'
import Menu from "@/components/indexCom/index/Menu.vue";
import IndexPage from "@/components/indexCom/index/indexPage.vue";
import Header from "@/components/indexCom/index/headerPage.vue";
import Bread from "@/components/indexCom/index/Bread.vue";




</script>

<style scoped>
.container {
  background-color: aliceblue;
}

.el-main {
  padding: 10px;
}


</style>