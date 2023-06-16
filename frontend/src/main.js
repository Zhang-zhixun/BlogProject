import { createApp } from 'vue'
import { createPinia } from 'pinia'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import App from './App.vue'
import router from './router'
import axios from "axios";
import $ from 'jquery';



const app = createApp(App)
//您需要从 @element-plus/icons-vue 中导入所有图标并进行全局注册。
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

//后端默认服务器地址
axios.defaults.baseURL = 'http://localhost:8080'
app.use(createPinia())
app.use(router)
app.use($)
app.mount('#app')
