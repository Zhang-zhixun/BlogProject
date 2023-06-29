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
                    path: 'course',
                    name: 'main-course',
                    component: () => import('@/components/indexCom/course/coursePage.vue'),
                    meta: {
                        title : '课程管理'
                    }
                },
                {
                    path: 'courseInsert',
                    name: 'main-courseInsert',
                    component: () => import('@/components/indexCom/course/courseInsertPage.vue'),
                    meta: {
                        title : '课程添加'
                    }
                },
                {
                    path: 'courseEdit/:id',
                    name: 'main-courseEdit',
                    component: () => import('@/components/indexCom/course/courseEditPage.vue'),
                    meta: {
                        title : '课程编辑'
                    }
                },
                {
                    path: 'chapter/:id',
                    name: 'main-chapter',
                    component: () => import('@/components/indexCom/chapter/chapterPage.vue'),
                    meta: {
                        title : '章节管理'
                    }
                },
                {
                    path: 'chapterInsert/:id',
                    name: 'main-chapterInsert',
                    component: () => import('@/components/indexCom/chapter/chapterInsertPage.vue'),
                    meta: {
                        title : '章节添加'
                    }
                },
                {
                    path: 'chapterEdit/:id',
                    name: 'main-chapterEdit',
                    component: () => import('@/components/indexCom/chapter/chapterEditPage.vue'),
                    meta: {
                        title : '章节编辑'
                    }
                },
                {
                    path: 'courseResource/:id',
                    name: 'main-courseResource',
                    component: () => import('@/components/indexCom/courseResource/courseResourcePage.vue'),
                    meta: {
                        title : '课程资源管理'
                    }
                },
                {
                    path: 'courseResourceInsert/:id',
                    name: 'main-courseResourceInsert',
                    component: () => import('@/components/indexCom/courseResource/courseResourceInsertPage.vue'),
                    meta: {
                        title : '课程资源添加'
                    }
                },
                {
                    path: 'courseResourceEdit/:id',
                    name: 'main-courseResourceEdit',
                    component: () => import('@/components/indexCom/courseResource/courseResourceEditPage.vue'),
                    meta: {
                        title : '课程资源编辑'
                    }
                },
                {
                    path: 'courseResourceView/:id',
                    name: 'main-courseResourceView',
                    component: () => import('@/components/indexCom/courseResource/courseResourceViewPage.vue'),
                    meta: {
                        title : '课程资源查看'
                    }
                },
                {
                    path: 'tag',
                    name: 'main-tag',
                    component: () => import('@/components/indexCom/tag/tagPage.vue'),
                    meta: {
                        title : '标签管理'
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
                    path: 'userMessage',
                    name: 'main-userMessage',
                    component: () => import('@/components/indexCom/userMessage/userMessagePage.vue'),
                    meta: {
                        title : '用户消息管理'
                    }
                },
                {
                    path: 'systemMessage',
                    name: 'main-systemMessage',
                    component: () => import('@/components/indexCom/systemMessage/systemMessagePage.vue'),
                    meta: {
                        title : '系统消息管理'
                    }
                },
                {
                    path: 'systemMessageInsert',
                    name: 'main-systemMessageInsert',
                    component: () => import('@/components/indexCom/systemMessage/systemMessageInsertPage.vue'),
                    meta: {
                        title : '系统消息添加'
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
                    path: 'certificateTemplate',
                    name: 'main-certificateTemplate',
                    component: () => import('@/components/indexCom/certificateTemplate/certificateTemplatePage.vue'),
                    meta: {
                        title : '模板管理'
                    }
                },
                {
                    path: 'certificateTemplateInsert',
                    name: 'main-certificateTemplateInsert',
                    component: () => import('@/components/indexCom/certificateTemplate/certificateTemplateInsertPage.vue'),
                    meta: {
                        title : '模板添加'
                    }
                },
                {
                    path: 'certificateTemplateEdit/:id',
                    name: 'main-certificateTemplateEdit',
                    component: () => import('@/components/indexCom/certificateTemplate/certificateTemplateEditPage.vue'),
                    meta: {
                        title : '模板编辑'
                    }
                },
                {
                    path: 'certificateTemplateView/:id',
                    name: 'main-certificateTemplateView',
                    component: () => import('@/components/indexCom/certificateTemplate/certificateTemplateViewPage.vue'),
                    meta: {
                        title : '模板查看'
                    }
                },
                {
                    path: 'paymentChannel',
                    name: 'main-paymentChannel',
                    component: () => import('@/components/indexCom/PaymentChannel/paymentChannelPage.vue'),
                    meta: {
                        title : '支付渠道管理'
                    }
                },
                {
                    path: 'paymentChannelInsert',
                    name: 'main-paymentChannelInsert',
                    component: () => import('@/components/indexCom/PaymentChannel/paymentChannelInsertPage.vue'),
                    meta: {
                        title : '支付渠道添加'
                    }
                },
                {
                    path: 'paymentChannelEdit/:id',
                    name: 'main-paymentChannelEdit',
                    component: () => import('@/components/indexCom/PaymentChannel/paymentChannelEditPage.vue'),
                    meta: {
                        title : '支付渠道编辑'
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
