import {createRouter, createWebHistory} from 'vue-router'
import {useStore} from "@/stores";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: "login",
            component: () => import('@/views/Login.vue'),
            children: [
                {
                    path: '/',
                    name: 'welcome-login',
                    component: () => import('@/components/LoginCom/LoginPage.vue')
                },
                {
                    path: 'register',
                    name: 'welcome-register',
                    component: () => import('@/components/LoginCom/RegisterPage.vue')
                },
                {
                    path: 'forget',
                    name: 'welcome-forget',
                    component: () => import('@/components/LoginCom/ForgetPage.vue')
                }
            ]
        },
        {
            path: '/index',
            name: "index",
            component: () => import('@/views/Index.vue'),
            children: [
                {
                    path: '',
                    name: 'main-index',
                    component: () => import('@/components/indexCom/index/indexPage.vue'),
                    meta: {
                        title : '系统首页'
                    }
                },
                {
                    path: 'article',
                    name: 'main-article',
                    component: () => import('@/components/indexCom/article/articlePage.vue'),
                    meta: {
                        title : '文章管理'
                    }
                },
                {
                    path: 'classify',
                    name: 'main-classify',
                    component: () => import('@/components/indexCom/classify/classifyPage.vue'),
                    meta: {
                        title : '分类管理'
                    }
                },
                //ag/:id
                {
                    path: 'tag',
                    name: 'main-tag',
                    component: () => import('@/components/indexCom/tag/tagPage.vue'),
                    meta: {
                        title : '标签管理'
                    }
                },
                {
                    path: 'comment',
                    name: 'main-comment',
                    component: () => import('@/components/indexCom/comment/commentPage.vue'),
                    meta: {
                        title : '评论管理'
                    }
                },
                {
                    path: 'user',
                    name: 'main-user',
                    component: () => import('@/components/indexCom/user/userPage.vue'),meta: {
                        title : '用户管理'
                    }
                },
                {
                    path: 'resources',
                    name: 'main-resources',
                    component: () => import('@/components/indexCom/resources/resourcesPage.vue'),
                    meta: {
                        title : '资源管理'
                    }
                },
                {
                    path: 'customerService',
                    name: 'main-customerService',
                    component: () => import('@/components/indexCom/customerService/customerSPage.vue'),
                    meta: {
                        title : '客服管理'
                    }
                },
                {
                    path: 'system',
                    name: 'main-system',
                    component: () => import('@/components/indexCom/system/SystemPage.vue'),
                    meta: {
                        title : '系统设置'
                    }
                },
                {
                    path: 'person',
                    name: 'main-person',
                    component: () => import('@/components/indexCom/index/personEditPage.vue'),
                    meta: {
                        title : '个人信息'
                    }
                }

            ]
        }

    ]
})

//路由守卫
router.beforeEach((to, from, next) => {
    const store = useStore()
    next()
    //如果已经登录，就不能通过连接返回登录界面
    // if (store.auth.user != null && to.name.startsWith('welcome-')) {
    //     next('/index')
    //     //如果没有登录，就不能通过连接进入index下的界面
    // } else if (store.auth.user == null && to.fullPath.startsWith('/index')) {
    //     next('/')
    //     //如果路径不存在
    // }
    // // else if (to.matched.length === 0) {
    // //     next('/index')
    // // }else if(store.auth.user != null && to.name.startsWith('main-')){
    // //     next()
    // // }
    // else{
    //      next()
    // }
})

export default router
